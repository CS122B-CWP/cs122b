package com.example.pengyuanfan.fablix;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.pengyuanfan.fablix.json.OrderHistoryList;
import com.example.pengyuanfan.fablix.orderHistory.OrderHistoryAdapter;
import com.example.pengyuanfan.fablix.orderHistory.OrderHistoryParser;
import com.example.pengyuanfan.fablix.util.ConnectionState;
import com.example.pengyuanfan.fablix.util.HttpGetThread;
import com.example.pengyuanfan.fablix.util.SoftKeyBoard;

import java.net.URI;

public class OrderHistory extends AppCompatActivity {

    RelativeLayout mainLayout;

    private ProgressDialog progressDialog;
    private ListView orderList;

    private ConnectionState cs;
    private Context appContext;
    private Context actContext;

    String orderHistoryUrl;
    OrderHistoryList data;


    Handler onGetOrderHistory = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDialog.dismiss();
            if(msg.what == HttpGetThread.success){
                Log.d("message", msg.getData().getString("result"));
                data = OrderHistoryParser.parse(msg.getData().getString("result"));
                if(data!=null) {
                    orderList.setAdapter(new OrderHistoryAdapter(data.getOrders(), OrderHistory.this));
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
        setContentView(R.layout.activity_order_history);

        mainLayout = (RelativeLayout)findViewById(R.id.orderhisoty_content);
        orderList = (ListView)findViewById(R.id.orderList);

        appContext = this.getApplicationContext();
        actContext = this.getBaseContext();

        cs = new ConnectionState(appContext);

        orderHistoryUrl = appContext.getString(R.string.miniEbay_Url) + appContext.getString(R.string.miniEbay_orderHistory);

        orderList.addHeaderView(getLayoutInflater().inflate(R.layout.order_item_head, null));
        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String orderId=data.getOrders().get(position).getId();
                Intent searchTosingleMovie = new Intent(OrderHistory.this, OrderDetail.class);
                searchTosingleMovie.putExtra(SINGLE_ORDER_ID, orderId);
                startActivity(searchTosingleMovie);
            }
        });

        SoftKeyBoard.setupUI(mainLayout, this);

        downOrderHistory();
    }

    private void downOrderHistory(){
        if(cs.isConnectingToInternet()){
            progressDialog = ProgressDialog.show(this, "", "Downloading...");
            try{
                URI queryUrl = new URI(orderHistoryUrl);

                Log.d("d","clicking");
                new HttpGetThread(queryUrl.toURL(), onGetOrderHistory).start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static final String SINGLE_ORDER_ID="MINI_EBAY.SINGLE_ORDER_ID";

}
