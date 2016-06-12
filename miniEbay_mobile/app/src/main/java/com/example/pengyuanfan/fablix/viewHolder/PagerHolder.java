package com.example.pengyuanfan.fablix.viewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by pengyuanfan on 6/9/2016.
 */
public class PagerHolder {
    View mvLVPager;
    Button prev;
    Button next;
    TextView max;
    TextView cur;

    public View getMvLVPager() {
        return mvLVPager;
    }

    public void setMvLVPager(View mvLVPager) {
        this.mvLVPager = mvLVPager;
    }

    public Button getPrev() {
        return prev;
    }

    public void setPrev(Button prev) {
        this.prev = prev;
    }

    public Button getNext() {
        return next;
    }

    public void setNext(Button next) {
        this.next = next;
    }

    public TextView getMax() {
        return max;
    }

    public void setMax(TextView max) {
        this.max = max;
    }

    public TextView getCur() {
        return cur;
    }

    public void setCur(TextView cur) {
        this.cur = cur;
    }

    public void setPage(int curpage, int maxpage){
        getCur().setText(Integer.toString(curpage));
        getMax().setText(Integer.toString(maxpage));
        getPrev().setEnabled(true);
        getNext().setEnabled(true);
        if(curpage==1){
            getPrev().setEnabled(false);
        }
        if(curpage==maxpage){
            getNext().setEnabled(false);
        }
    }
}
