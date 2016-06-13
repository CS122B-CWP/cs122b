package com.example.pengyuanfan.fablix.movieListAdapter;

import com.example.pengyuanfan.fablix.json.SearchResult;
import com.example.pengyuanfan.fablix.util.JSONCodeLocator;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by pengyuanfan on 5/13/2016.
 */
public class MovieListParser {

    public static SearchResult parse(String code){

        if(code!=null){
            Gson gs = new Gson();
            return gs.fromJson(code, SearchResult.class);
        }else {
            return null;
        }


    }
}
