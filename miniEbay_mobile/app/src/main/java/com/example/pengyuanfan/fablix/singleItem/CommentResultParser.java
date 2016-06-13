package com.example.pengyuanfan.fablix.singleItem;

import com.example.pengyuanfan.fablix.json.CommentResult;
import com.google.gson.Gson;

/**
 * Created by pengyuanfan on 6/11/2016.
 */
public class CommentResultParser {
    public static CommentResult parse(String code){
        if(code!=null){
            Gson gs = new Gson();
            return gs.fromJson(code, CommentResult.class);
        }else{
            return null;
        }
    }
}
