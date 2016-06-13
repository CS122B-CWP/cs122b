package com.example.pengyuanfan.fablix.json;

/**
 * Created by pengyuanfan on 6/11/2016.
 */
public class BidResult {
    String bid_result;
    String info;
    SingleItem item_info;

    public String getBid_result() {
        return bid_result;
    }

    public void setBid_result(String bid_result) {
        this.bid_result = bid_result;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public SingleItem getItem_info() {
        return item_info;
    }

    public void setItem_info(SingleItem item_info) {
        this.item_info = item_info;
    }
}
