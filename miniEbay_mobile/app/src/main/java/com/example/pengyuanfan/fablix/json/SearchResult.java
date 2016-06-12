package com.example.pengyuanfan.fablix.json;

import java.util.List;

/**
 * Created by pengyuanfan on 6/9/2016.
 */
public class SearchResult {
    String curPageS;
    String maxPageS;

    int curPage;
    int maxPage;
    String search_title;
    String lowPrice;
    String highPrice;
    String search_category_id;
    String rowPerPage;
    List<Brief_item> brief_items;

    public String getCurPageS() {
        return curPageS;
    }

    public void setCurPageS(String curPageS) {
        this.curPageS = curPageS;
    }

    public String getMaxPageS() {
        return maxPageS;
    }

    public void setMaxPageS(String maxPageS) {
        this.maxPageS = maxPageS;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
        this.curPageS=Integer.toString(curPage);
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
        this.maxPageS=Integer.toString(maxPage);
    }

    public String getSearch_title() {
        return search_title;
    }

    public void setSearch_title(String search_title) {
        this.search_title = search_title;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public String getSearch_category_id() {
        return search_category_id;
    }

    public void setSearch_category_id(String search_category_id) {
        this.search_category_id = search_category_id;
    }

    public String getRowPerPage() {
        return rowPerPage;
    }

    public void setRowPerPage(String rowPerPage) {
        this.rowPerPage = rowPerPage;
    }

    public List<Brief_item> getBrief_items() {
        return brief_items;
    }

    public void setBrief_items(List<Brief_item> brief_items) {
        this.brief_items = brief_items;
    }
}
