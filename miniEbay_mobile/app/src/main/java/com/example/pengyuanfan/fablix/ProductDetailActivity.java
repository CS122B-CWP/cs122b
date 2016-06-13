package com.example.pengyuanfan.fablix;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pengyuanfan.fablix.json.BidResult;
import com.example.pengyuanfan.fablix.json.CommentResult;
import com.example.pengyuanfan.fablix.json.SingleItem;
import com.example.pengyuanfan.fablix.singleItem.BidResultParser;
import com.example.pengyuanfan.fablix.singleItem.CommentListAdapter;
import com.example.pengyuanfan.fablix.singleItem.CommentResultParser;
import com.example.pengyuanfan.fablix.singleItem.ImageSlideAdapter;
import com.example.pengyuanfan.fablix.singleItem.SingleItemParser;
import com.example.pengyuanfan.fablix.util.BitmapCache;
import com.example.pengyuanfan.fablix.util.CirclePageIndicator;
import com.example.pengyuanfan.fablix.util.ConnectionState;
import com.example.pengyuanfan.fablix.util.FixListViewInScrollView;
import com.example.pengyuanfan.fablix.util.HttpGetThread;
import com.example.pengyuanfan.fablix.util.ImageDowloader;
import com.example.pengyuanfan.fablix.util.SoftKeyBoard;
import com.example.pengyuanfan.fablix.util.URLParam;

import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ProductDetailActivity extends AppCompatActivity {

    RelativeLayout mainLayout;
    ImageView itemImage;
    TextView itemName;
    TextView itemDesc;
    TextView itemCategory;
    TextView itemTimeLeft;
    TextView itemPrice;
    TextView itemTimeStart;
    TextView itemTimeEnd;
    EditText bidPrice;
    Button bidBtn;
    Button buyBtn;
    ViewPager itemSlides;
    TextView slidePager;
    CirclePageIndicator cIndicator;
    ListView commentList;
    TextView product_comment;
    Button product_comment_btn;

    private ConnectionState cs;
    private Context appContext;
    private Context actContext;

    SingleItem si;
    BidResult br;
    CommentResult pr;
    String itemId;
    String singleItemUrl;
    String bidUrl;
    String buyUrl;
    String postUrl;
    Period p;
    Duration timeLeft;
    private ProgressDialog progressDialog;

    Handler onGetSingleItem= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDialog.dismiss();
            if(msg.what== HttpGetThread.success){
                si = SingleItemParser.parse(msg.getData().getString("result"));
                if(si!=null&&si.getItemID().equals(itemId))
                    showSingleItem(si);
                else
                    Toast.makeText(actContext, "Data cropped", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(actContext, "DownLoad Data failed", Toast.LENGTH_SHORT).show();
            }
        }
    };

    Handler onGetBidResult= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDialog.dismiss();
            if(msg.what== HttpGetThread.success){
                Log.d("bid", msg.getData().getString("result"));
                br = BidResultParser.parse(msg.getData().getString("result"));
                if(br!=null&&br.getBid_result()!=null) {
                    if(br.getBid_result().equals("success")) {
                        si=br.getItem_info();
                        Toast.makeText(actContext, "Bid success", Toast.LENGTH_SHORT).show();
                        showSingleItem(si);
                    }else{
                        Toast.makeText(actContext, br.getInfo(), Toast.LENGTH_SHORT).show();
                        downSingleItem();
                    }
                }
                else
                    Toast.makeText(actContext, "Data cropped", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(actContext, "DownLoad Data failed", Toast.LENGTH_SHORT).show();
            }
        }
    };

    Handler onGetPostResult=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDialog.dismiss();
            if(msg.what== HttpGetThread.success){
                Log.d("bid", msg.getData().getString("result"));
                pr= CommentResultParser.parse( msg.getData().getString("result"));
                if(pr!=null&&pr.getComment_result()!=null) {
                    if(pr.getComment_result().equals("success"))
                        downSingleItem();
                    else
                        Toast.makeText(actContext, pr.getInfo(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(actContext, "Data cropped", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(actContext, "DownLoad Data failed", Toast.LENGTH_SHORT).show();
            }
        }
    };

    PeriodFormatter daysHoursMinutes = new PeriodFormatterBuilder()
            .appendDays()
            .appendSuffix(" d", " d")
            .appendSeparator(" , ")
            .appendHours()
            .appendSuffix("h", "h")
            .appendSeparator(" , ")
            .appendMinutes()
            .appendSuffix(" m", " m")
            .appendSeparator(" , ")
            .appendSeconds()
            .appendSuffix(" s", " s")
            .toFormatter();

    private void getView(){
        mainLayout=(RelativeLayout)findViewById(R.id.singleItem_content);
        itemImage=(ImageView)findViewById(R.id.singleItem_img);
        itemName=(TextView)findViewById(R.id.product_name);
        itemDesc=(TextView)findViewById(R.id.product_description);
        itemCategory=(TextView)findViewById(R.id.product_category);
        itemPrice=(TextView)findViewById(R.id.product_price);
        itemTimeLeft=(TextView)findViewById(R.id.product_timeLeft);
        bidPrice=(EditText)findViewById(R.id.bidPrice);
        bidBtn=(Button)findViewById(R.id.bidBtn);
        buyBtn=(Button)findViewById(R.id.buyBtn);
        itemTimeStart=(TextView)findViewById(R.id.product_timeStart);
        itemTimeEnd=(TextView)findViewById(R.id.product_timeEnd);
        itemSlides=(ViewPager)findViewById(R.id.product_slides);
        slidePager=(TextView)findViewById(R.id.slides_pager);
        cIndicator=(CirclePageIndicator)findViewById(R.id.product_slides_indicator);
        commentList=(ListView)findViewById(R.id.commentList);
        commentList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        product_comment=(TextView)findViewById(R.id.product_comments);
        product_comment_btn=(Button)findViewById(R.id.product_comments_btn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_detail);

        getView();

        appContext = this.getApplicationContext();
        actContext = this.getBaseContext();

        itemId = getIntent().getStringExtra(miniEbay_Search.SINGLE_MOVIE_ID);

        singleItemUrl = appContext.getString(R.string.miniEbay_Url) + appContext.getString(R.string.miniEbay_singleItemUrl);
        bidUrl = appContext.getString(R.string.miniEbay_Url) + appContext.getString(R.string.miniEbay_bid);
        postUrl = appContext.getString(R.string.miniEbay_Url)+appContext.getString(R.string.miniEbay_commentMobile);


        cs = new ConnectionState(appContext);

        SoftKeyBoard.setupUI(mainLayout, this);

        downSingleItem();
    }

    public void post(View view){
        String commet=product_comment.getText().toString().trim();
        if(commet.equals("")){
            Toast.makeText(actContext, "please enter a comment", Toast.LENGTH_SHORT).show();
            return;
        }
        if(cs.isConnectingToInternet()){
            try {

                URI newPostUrl=URLParam.appendUri(postUrl, "comment="+commet,
                        "item_id="+si.getItemID(),
                        "customer_id="+LoginActivity.getLgR().getCustomer_id());
                Log.d("post",newPostUrl.toString());
                new HttpGetThread(
                        newPostUrl.toURL(), onGetPostResult).start();

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public void bid(View view){

        String bdp = bidPrice.getText().toString().trim();
        if(bdp.equals("")){
            Toast.makeText(actContext, actContext.getText(R.string.no_bidprice), Toast.LENGTH_SHORT).show();
            return;
        }
        double bidCVal=Double.valueOf(si.getCurrentPrice()),
               bidPVal=Double.valueOf(bdp);

        if(bidPVal<=bidCVal){
            Toast.makeText(actContext, "Please enter a price higher than current price", Toast.LENGTH_SHORT).show();
            return;
        }

        if(cs.isConnectingToInternet()) {
            progressDialog = ProgressDialog.show(this, "", "Downloading...");
            try {

                URI newbidUrl=URLParam.appendUri(bidUrl, "bid_price="+Double.parseDouble(bdp),
                                                "item_id="+si.getItemID(),
                                                "customer_id="+LoginActivity.getLgR().getCustomer_id());
                Log.d("bid", newbidUrl.toString());
                new HttpGetThread(
                        newbidUrl.toURL(), onGetBidResult).start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //Toast.makeText(actContext, "Currently not available...", Toast.LENGTH_SHORT).show();
    }

    public void buy(View view){

    }

    private void downSingleItem(){
        if(cs.isConnectingToInternet()){
            progressDialog = ProgressDialog.show(this, "", "Downloading...");
            try{
                URI singleMovieRequestUri = URLParam.appendUri(singleItemUrl, "item_id="+itemId);
                Log.d("sg", singleMovieRequestUri.toString());
                new HttpGetThread(singleMovieRequestUri.toURL(), onGetSingleItem).start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private Date string2Date(String code){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        try {
            return format.parse(code);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void disableBuy(){
        bidPrice.setEnabled(false);
        bidBtn.setEnabled(false);
        buyBtn.setEnabled(false);
    }

    private void diff2String(Date start, Date end){
        long duration  = end.getTime() - System.currentTimeMillis();
        Log.d("duration", Long.toString(duration));
        if(duration<=0||si.getStatus().equals("end")){
            disableBuy();
        }else{
            new CountDownTimer(duration, 1000) {

                public void onTick(long millisUntilFinished) {
                    p = new Period(millisUntilFinished, PeriodType.dayTime().withMillisRemoved());
                    //day won't show because it is not accurate(daylight change from 23~25)
                    itemTimeLeft.setText(daysHoursMinutes.print(p));
                }

                public void onFinish() {
                    disableBuy();
                }
            }.start();
        }
    }

    private void showSingleItem(SingleItem si){
        itemName.setText(si.getTitle());
        itemDesc.setText(si.getConditionDescription());
        itemCategory.setText(si.getCategoryName());
        itemPrice.setText( Double.toString(si.getCurrentPrice()));
        itemTimeStart.setText(si.getStartTime());
        itemTimeEnd.setText(si.getEndTime());
        //String exe="2016-06-08T23:24:32.000Z";
        //String exs="2016-06-01T23:24:32.000Z";

        Date start=string2Date(si.getStartTime()), end=string2Date(si.getEndTime());
        //Date start=string2Date(exs), end=string2Date(exe);
        Log.d("Date", start.toString());
        if(start!=null&&end!=null){
            diff2String(start, end);
        }
        new ImageDowloader(itemImage).execute(si.getPictureURL().get(0));

        //initial bitmap cache

        for(int i=0;i<si.getPictureURL().size();i++){
            si.getPictureCache().add(new BitmapCache());
        }

        itemSlides.setAdapter(new ImageSlideAdapter(si.getPictureURL(),si.getPictureCache(),this));
        cIndicator.setViewPager(itemSlides);
        runnable(si.getPictureURL().size());
        slidesAutoHandler.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY);

        commentList.setAdapter(new CommentListAdapter(si.getComments()));
        FixListViewInScrollView.setListViewHeightBasedOnChildren(commentList);
    }


    private Runnable animateViewPager;
    private Handler slidesAutoHandler;
    private int ANIM_VIEWPAGER_DELAY=5000;
    //
    public void runnable(final int size) {
        slidesAutoHandler = new Handler();
        animateViewPager = new Runnable() {
            public void run() {
                if (itemSlides.getCurrentItem() == size - 1) {
                    itemSlides.setCurrentItem(0);
                } else {
                    itemSlides.setCurrentItem(
                            itemSlides.getCurrentItem() + 1, true);
                }
                slidesAutoHandler.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY);
            }

        };
    }
}
