package com.example.pengyuanfan.fablix;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pengyuanfan.fablix.json.LoginResult;
import com.example.pengyuanfan.fablix.json.SearchResult;
import com.example.pengyuanfan.fablix.movieListAdapter.MovieListAdapter;
import com.example.pengyuanfan.fablix.movieListAdapter.MovieListParser;
import com.example.pengyuanfan.fablix.movieListAdapter.MvItemBean;
import com.example.pengyuanfan.fablix.util.ConnectionState;
import com.example.pengyuanfan.fablix.util.HttpGetThread;
import com.example.pengyuanfan.fablix.util.SoftKeyBoard;
import com.example.pengyuanfan.fablix.util.URLParam;
import com.example.pengyuanfan.fablix.viewHolder.PagerHolder;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class miniEbay_Search extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private EditText searchText;
    private ListView mvLV;
    private LinearLayout mainLayout;
    private PagerHolder mvLVPagerHolder;

    private SearchResult data;

    private ConnectionState cs;
    private Context appContext;
    private Context actContext;

    private String searchURL;
    private String logoutURL;

    Handler onGetSearchResult=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            progressDialog.dismiss();
            Log.d("d","returned");
            if(msg.what== HttpGetThread.success){
                Log.d("d","showing");
                //showData(msg.getData().getString("result"));
                data=MovieListParser.parse(msg.getData().getString("result"));
                if(data!=null){
                    mvLV.setAdapter(new MovieListAdapter(miniEbay_Search.this, data.getBrief_items()));
                    mvLVPagerHolder.getCur().setText(Integer.toString(data.getCurPage()));
                    mvLVPagerHolder.getMax().setText(Integer.toString(data.getMaxPage()));
                    mvLVPagerHolder.getPrev().setEnabled(true);
                    mvLVPagerHolder.getNext().setEnabled(true);
                    if(data.getCurPage()==1){
                        mvLVPagerHolder.getPrev().setEnabled(false);
                    }
                    if(data.getCurPage()==data.getMaxPage()){
                        mvLVPagerHolder.getNext().setEnabled(false);
                    }
                    mvLVPagerHolder.getMvLVPager().setVisibility(View.VISIBLE);
                }else{
                    mvLVPagerHolder.getMvLVPager().setVisibility(View.GONE);
                   Toast.makeText(actContext,actContext.getString(R.string.no_movie), Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(actContext, "DownLoad Data failed", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchText = (EditText) findViewById(R.id.query_message);
        mvLV = (ListView)findViewById(R.id.mv_list);
        mainLayout = (LinearLayout)findViewById(R.id.main_content);
        mvLVPagerHolder = new PagerHolder();
        mvLVPagerHolder.setMvLVPager(getLayoutInflater().inflate(R.layout.movie_listpager, null));
        mvLVPagerHolder.setCur((TextView) mvLVPagerHolder.getMvLVPager().findViewById(R.id.curPage));
        mvLVPagerHolder.setMax((TextView) mvLVPagerHolder.getMvLVPager().findViewById(R.id.maxPage));
        mvLVPagerHolder.setPrev((Button) mvLVPagerHolder.getMvLVPager().findViewById(R.id.btn_prev));
        mvLVPagerHolder.setNext((Button) mvLVPagerHolder.getMvLVPager().findViewById(R.id.btn_next));
        mvLVPagerHolder.getMvLVPager().setVisibility(View.GONE);
        mvLV.addFooterView(mvLVPagerHolder.getMvLVPager());
        appContext = this.getApplicationContext();
        actContext = this.getBaseContext();

        cs = new ConnectionState(appContext);

        SoftKeyBoard.setupUI(mainLayout, this);

        mvLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                View title=view.findViewById(R.id.mv_title);
                if(title.isSelected()){
                    title.setSelected(false);
                }else{
                    title.setSelected(true);
                }

                return true;
            }
        });
        mvLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String movieId=data.getBrief_items().get(position).getItem_id();
                Intent searchTosingleMovie = new Intent(miniEbay_Search.this, ProductDetailActivity.class);
                searchTosingleMovie.putExtra(SINGLE_MOVIE_ID, movieId);
                startActivity(searchTosingleMovie);

            }
        });

        searchURL = appContext.getString(R.string.miniEbay_Url) + appContext.getString(R.string.miniEbay_searchUrl);
        logoutURL = appContext.getString(R.string.miniEbay_Url) + appContext.getString(R.string.fablix_logout);
        if(savedInstanceState!=null)
            onRestartSearch(savedInstanceState);
    }

    private void onRestartSearch(Bundle savedInstanceState){
        String query=savedInstanceState.getString(QUERY),
               page=savedInstanceState.getString(PAGE);
        if(query!=null){
            searchText.setText(query);
            if(page!=null){
                searchOnline("title="+query+"&"+"page="+page);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LoginResult lgR;
        if((lgR=LoginActivity.getLgR())!=null&&lgR.isLogin_result()){
            Log.d("login", lgR.getLogin_name());
            getSupportActionBar().setSubtitle(lgR.getLogin_name());
        }else{
            Intent backToLogin = new Intent(miniEbay_Search.this, LoginActivity.class);
            startActivity(backToLogin);
        }
    }

    private void searchOnline(String query){
        //List<MvItemBean> data = generateData();
        if(cs.isConnectingToInternet()){
            progressDialog = ProgressDialog.show(this, "", "Downloading...");

            try{
                URI queryUrl = URLParam.appendUri(searchURL, query,
                        "normal=true");

                Log.d("d","clicking");
                new HttpGetThread(queryUrl.toURL(), onGetSearchResult).start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void sendQuery(View view) {
        String queryTitle = searchText.getText().toString();
        if(queryTitle.trim().equals("")){
            Toast.makeText(actContext, actContext.getText(R.string.no_title), Toast.LENGTH_SHORT).show();
        }else {
            searchOnline("title="+queryTitle);
        }

    }

    public void nextPage(View view){
        String query=data.getSearch_title();
        if(!query.trim().equals("")){
            searchOnline("title="+query+"&"+"page="+Integer.toString(data.getCurPage()+1));
        }
    }

    public void backPage(View view){
        String query=data.getSearch_title();
        if(!query.trim().equals("")){
            searchOnline("title="+query+"&"+"page="+Integer.toString(data.getCurPage()-1));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(data!=null){
            Log.d("Destory",data.getSearch_title()+Integer.toString(data.getCurPage()));
            if(data.getSearch_title()!=null&&!data.getSearch_title().trim().equals("")){
                outState.putString(QUERY, data.getSearch_title());
            }
            if(data!=null){
                outState.putString(PAGE, data.getCurPageS());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater actionbar_addon = getMenuInflater();
        actionbar_addon.inflate(R.menu.actionbar_addon, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                Intent backToLogin = new Intent(miniEbay_Search.this, LoginActivity.class);
                startActivity(backToLogin);
                try {
                    new HttpGetThread(new URL(logoutURL),new Handler()).start();
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
                Log.d("login","logout");return true;
            case R.id.cart:
                Intent toCart = new Intent(miniEbay_Search.this, ShoppingCart.class);
                startActivity(toCart);return true;
            case R.id.order:
                //Intent toOrder = new Intent(miniEbay_Search.this, OrderHistory.class);
                //startActivity(toOrder);
                Toast.makeText(actContext, "Currently not available...", Toast.LENGTH_SHORT).show();
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }

    private List<MvItemBean> generateData(){
        List<MvItemBean> data=new ArrayList<MvItemBean>();
        for(int i=0;i<20;i++){
            data.add(new MvItemBean("null", "movie"+i, "content"+i, Integer.toString(i)));
        }
        return data;
    }
    void showData(String str){
        TextView tmp=new TextView(this);
        Log.d("d",str);
        tmp.setText(str);
        mainLayout.addView(tmp);
    }

    private String QUERY="QUERY",
                   PAGE ="PAGE";

    static public String SINGLE_MOVIE_ID="FABLIX.SINGLE_MOVIE_ID";
    static public String SINGLE_STAR_ID = "FABLIX.SINGLE_STAR_ID";
}
