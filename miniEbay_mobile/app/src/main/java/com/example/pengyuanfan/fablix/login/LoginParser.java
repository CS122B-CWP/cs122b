package com.example.pengyuanfan.fablix.login;

import com.example.pengyuanfan.fablix.json.LoginResult;
import com.google.gson.Gson;

/**
 * Created by pengyuanfan on 5/18/2016.
 */
public class LoginParser {
    public static LoginResult parse(String code){
       if(code!=null) {
           Gson gs = new Gson();
           return gs.fromJson(code, LoginResult.class);
       }else {
           return null;
       }
   }
}
