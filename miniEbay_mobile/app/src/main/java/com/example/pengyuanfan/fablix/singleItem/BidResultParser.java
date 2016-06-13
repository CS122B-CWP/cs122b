package com.example.pengyuanfan.fablix.singleItem;

import com.example.pengyuanfan.fablix.json.BidResult;
import com.google.gson.Gson;

/**
 * Created by pengyuanfan on 6/11/2016.
 */
public class BidResultParser {
    public static BidResult parse(String code){
        if(code!=null){
            Gson gs = new Gson();
            return gs.fromJson(code, BidResult.class);
        }else{
            return null;
        }
    }
}
