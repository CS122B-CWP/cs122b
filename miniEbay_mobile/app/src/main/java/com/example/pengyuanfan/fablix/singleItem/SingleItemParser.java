package com.example.pengyuanfan.fablix.singleItem;

import com.example.pengyuanfan.fablix.json.SingleItem;
import com.google.gson.Gson;

/**
 * Created by pengyuanfan on 6/8/2016.
 */
public class SingleItemParser {
    public static SingleItem parse(String code){
        if(code!=null){
            Gson gs = new Gson();
            return gs.fromJson(code, SingleItem.class);
        }else{
            return null;
        }
    }
}
