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
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pengyuanfan.fablix.json.ShoppingCartData;
import com.example.pengyuanfan.fablix.json.ShoppingCartItem;
import com.example.pengyuanfan.fablix.json.SingleItem;
import com.example.pengyuanfan.fablix.shoppingCart.ShoppingCartAdapter;
import com.example.pengyuanfan.fablix.shoppingCart.ShoppingCartParser;
import com.example.pengyuanfan.fablix.util.ConnectionState;
import com.example.pengyuanfan.fablix.util.HttpPostThread;
import com.example.pengyuanfan.fablix.util.SoftKeyBoard;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends AppCompatActivity {

    RelativeLayout mainLayout;
    TextView totalPrice;
    Button checkout;

    private ProgressDialog progressDialog;
    private ListView shoppingCartList;

    private ConnectionState cs;
    private Context appContext;
    private Context actContext;

    private ShoppingCartData data;

    private String userId;
    private String shoppingcartUrl;

    Handler onGetShoppingCart =  new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDialog.dismiss();
            if(msg.what == HttpPostThread.success){
                Log.d("shoppingcart_returnM", msg.getData().getString("result"));
                data = ShoppingCartParser.parse(msg.getData().getString("result"));
                if(data!=null) {
                    List<ShoppingCartItemButtonListener> update=new ArrayList<ShoppingCartItemButtonListener>();
                    List<ShoppingCartItemButtonListener> delete=new ArrayList<ShoppingCartItemButtonListener>();
                    putListener(update, delete);
                    shoppingCartList.setAdapter(new ShoppingCartAdapter(data.getItems(),update,delete,ShoppingCart.this));
                    double total=0;
                    for(ShoppingCartItem i:data.getItems()){
                        total+=Double.valueOf(i.getPrice());
                    }
                    totalPrice.setText(Double.toString(total));
                    if(data.getItems()!=null&&!data.getItems().isEmpty()){
                        checkout.setEnabled(true);
                    }
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
        setContentView(R.layout.activity_shopping_cart);

        mainLayout = (RelativeLayout)findViewById(R.id.shoppingcart_content);
        totalPrice = (TextView)findViewById(R.id.shoppingcart_total);
        shoppingCartList = (ListView)findViewById(R.id.shoppingList);
        checkout = (Button)findViewById(R.id.shoppingcart_checkout);

        appContext = this.getApplicationContext();
        actContext = this.getBaseContext();

        cs = new ConnectionState(appContext);

        shoppingCartList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String movieId=data.getItems().get(position).getItem_id();
                Intent searchTosingleMovie = new Intent(ShoppingCart.this, ProductDetailActivity.class);
                searchTosingleMovie.putExtra(miniEbay_Search.SINGLE_MOVIE_ID, movieId);
                startActivity(searchTosingleMovie);
            }
        });

        SoftKeyBoard.setupUI(mainLayout, this);

        shoppingcartUrl = appContext.getString(R.string.miniEbay_Url) + appContext.getString(R.string.miniEbay_buy);

        downShoppingCart("customer_id="+LoginActivity.getLgR().getCustomer_id());
    }


    private void downShoppingCart(String option){
        if(cs.isConnectingToInternet()){
            progressDialog = ProgressDialog.show(this, "", "Downloading...");
            try{
                new HttpPostThread(new URL(shoppingcartUrl), onGetShoppingCart, option).start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void putListener(List<ShoppingCartItemButtonListener> update,
                             List<ShoppingCartItemButtonListener> delete){
        for (ShoppingCartItem i:data.getItems()
             ) {

            //update.add(new ShoppingCartItemButtonListener("type=update&movie_id="+i.getItem_id()+
            //                                                "&"+"qty="+i.getQty()));
            update.add(new ShoppingCartItemButtonListener("type=update&item_id="+i.getItem_id()+
                                                                    "&"+"qty="+"1"+
                                                                    "&customer_id"+LoginActivity.getLgR().getCustomer_id()));
            delete.add(new ShoppingCartItemButtonListener("type=removeSingle&item_id="+i.getItem_id()+
                                                          "&customer_id="+LoginActivity.getLgR().getCustomer_id()));
        }
    }

    public class ShoppingCartItemButtonListener implements View.OnClickListener {

        private String params;

        public ShoppingCartItemButtonListener(String params) {
            this.params = params;
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(actContext, "Currently not available...", Toast.LENGTH_SHORT).show();
            downShoppingCart(params);
        }
    }
}
