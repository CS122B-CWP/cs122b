package com.example.pengyuanfan.fablix.json;

import android.widget.ImageView;

import com.example.pengyuanfan.fablix.util.BitmapCache;
import com.example.pengyuanfan.fablix.util.PositionHolder;

/**
 * Created by pengyuanfan on 6/9/2016.
 */
public class Brief_item {
    String category_name;
    String category_id;
    String item_id;
    String current_price;
    String gallery_url;
    String title;

    BitmapCache bitmapCache = new BitmapCache();

    public void showImg(ImageView v, PositionHolder ph, int position){
        //if(!gallery_url.equals(""))

            bitmapCache.showImgInLV(v, gallery_url, ph, position);
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(String current_price) {
        this.current_price = current_price;
    }

    public String getGallery_url() {
        return gallery_url;
    }

    public void setGallery_url(String gallery_url) {
        this.gallery_url = gallery_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
