package com.example.pengyuanfan.fablix.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.pengyuanfan.fablix.R;

/**
 * Created by pengyuanfan on 5/13/2016.
 */
public class BitmapCache {
    Bitmap img;
    boolean first=true;
    boolean complete=false;
    public void showImg(ImageView v, String url){
        if(first) {
            v.setImageResource(R.mipmap.ic_launcher);
            new ImageDowloader(v,this).execute(url);
            first = false;
        }else if(complete){
            v.setImageBitmap(img);
        }
    }
    public  void showImgInLV(ImageView v, String url, PositionHolder ph, int position){
        if(first){
            img= BitmapFactory.decodeResource(v.getContext().getResources(),R.mipmap.ic_launcher);
            v.setImageResource(R.mipmap.ic_launcher);
            new ListViewImageDLder(v, this, ph, position).execute(url);
            first = false;
        }else if(complete){
            v.setImageBitmap(img);
        }
    }
    public void setImg(Bitmap img) {
        if(img!=null)
            this.img = img;
        complete=true;
    }

    public Bitmap getImg() {
        return img;
    }
}
