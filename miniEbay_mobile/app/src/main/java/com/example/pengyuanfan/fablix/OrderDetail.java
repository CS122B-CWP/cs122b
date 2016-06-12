package com.example.pengyuanfan.fablix;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.os.Handler;
import android.widget.Toast;

import com.example.pengyuanfan.fablix.orderDetail.OrderDetailAdapter;
import com.example.pengyuanfan.fablix.orderDetail.OrderDetailParser;
import com.example.pengyuanfan.fablix.util.ConnectionState;
import com.example.pengyuanfan.fablix.util.HttpGetThread;

import java.net.URI;

public class OrderDetail extends AppCompatActivity {

    RelativeLayout mainLayout;
    ListView orderDetailList;

    private ProgressDialog progressDialog;

    private ConnectionState cs;
    private Context appContext;
    private Context actContext;

    String orderDetailUrl;

    com.example.pengyuanfan.fablix.json.OrderDetail data;

    Handler onGetOrderDetail = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDialog.dismiss();
            if(msg.what == HttpGetThread.success){
                data = OrderDetailParser.parse(msg.getData().getString("result"));
                if(data!=null) {
                    orderDetailList.setAdapter(new OrderDetailAdapter(data.getOrders_day(), OrderDetail.this));
                }
                else
                    Toast.makeText(actContext, "Data cropped", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(actContext, "DownLoad Data failed", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        mainLayout = (RelativeLayout)findViewById(R.id.orderhisoty_content);
        orderDetailList = (ListView)findViewById(R.id.orderDetail_List);

        orderDetailList.addHeaderView(getLayoutInflater().inflate(R.layout.order_detail_header, null));
        orderDetailList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String movie_Id=data.getOrders_day().get(position).getMovie_id();
                Intent searchTosingleMovie = new Intent(OrderDetail.this, ProductDetailActivity.class);
                searchTosingleMovie.putExtra(miniEbay_Search.SINGLE_MOVIE_ID, movie_Id);
                startActivity(searchTosingleMovie);
            }
        });

        appContext = this.getApplicationContext();
        actContext = this.getBaseContext();

        cs = new ConnectionState(appContext);

        orderDetailUrl = appContext.getString(R.string.miniEbay_Url) + appContext.getString(R.string.miniEbay_orderHistory);

    }

    private void downOrderHistory(){
        if(cs.isConnectingToInternet()){
            progressDialog = ProgressDialog.show(this, "", "Downloading...");
            try{
                URI queryUrl = new URI(orderDetailUrl);

                Log.d("d","clicking");
                new HttpGetThread(queryUrl.toURL(), onGetOrderDetail).start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
