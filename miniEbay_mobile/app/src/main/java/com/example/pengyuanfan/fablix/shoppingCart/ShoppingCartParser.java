package com.example.pengyuanfan.fablix.shoppingCart;

import com.example.pengyuanfan.fablix.json.ShoppingCartData;
import com.example.pengyuanfan.fablix.util.JSONCodeLocator;
import com.google.gson.Gson;

/**
 * Created by pengyuanfan on 6/9/2016.
 */
public class ShoppingCartParser {
    public static ShoppingCartData parse(String code){
        //code = JSONCodeLocator.locate(code);
        if(code!=null&&code.charAt(0)=='{') {
            Gson gs = new Gson();
            return gs.fromJson(code, ShoppingCartData.class);
        }else {
            return null;
        }
    }
}
