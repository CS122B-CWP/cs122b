package com.example.pengyuanfan.fablix.orderHistory;

import com.example.pengyuanfan.fablix.json.OrderHistoryList;
import com.google.gson.Gson;

/**
 * Created by pengyuanfan on 6/9/2016.
 */
public class OrderHistoryParser {
    public static OrderHistoryList parse(String code){
        if(code!=null&&code.charAt(0)=='{') {
            Gson gs = new Gson();
            return gs.fromJson(code, OrderHistoryList.class);
        }else {
            return null;
        }
    }
}
