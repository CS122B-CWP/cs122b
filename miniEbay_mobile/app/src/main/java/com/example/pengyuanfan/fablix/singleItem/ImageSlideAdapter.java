package com.example.pengyuanfan.fablix.singleItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pengyuanfan.fablix.R;
import com.example.pengyuanfan.fablix.util.BitmapCache;
import com.example.pengyuanfan.fablix.util.ImageDowloader;


/**
 * Created by pengyuanfan on 6/10/2016.
 */
public class ImageSlideAdapter extends PagerAdapter {

    private List<String> PictureURL;
    private List<BitmapCache> PictureCache;
    private List<View> imgVs=new ArrayList<>();
    Activity activity;
    LayoutInflater inflater;

    public ImageSlideAdapter(List<String> pictureURL, List<BitmapCache> PictureCache, Activity activity) {
        PictureURL = pictureURL;
        this.activity = activity;
        this.PictureCache=PictureCache;
        inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        for(int i=0;i<pictureURL.size();i++){
            imgVs.add(null);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View page=imgVs.get(position);
        if(page==null) {
            page = inflater.inflate(R.layout.product_slide_page, container, false);
            ImageView img = (ImageView) page.findViewById(R.id.product_slides_img);
            page.setTag(img);
            imgVs.set(position, page);
        }
        PictureCache.get(position).showImg((ImageView) page.getTag(), PictureURL.get(position));
        //new ImageDowloader(img).execute(PictureURL.get(position));
        container.addView(page);
        return page;
    }

    @Override
    public int getCount() {
        return PictureURL.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
