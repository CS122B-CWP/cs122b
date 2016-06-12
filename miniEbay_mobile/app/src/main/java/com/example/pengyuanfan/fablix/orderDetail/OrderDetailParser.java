package com.example.pengyuanfan.fablix.orderDetail;

import com.example.pengyuanfan.fablix.json.OrderDetail;
import com.google.gson.Gson;

/**
 * Created by pengyuanfan on 6/9/2016.
 */
public class OrderDetailParser {
    public static OrderDetail parse(String code){
        if(code!=null&&code.charAt(0)=='{') {
            Gson gs = new Gson();
            return gs.fromJson(code, OrderDetail.class);
        }else {
            return null;
        }
    }
}
