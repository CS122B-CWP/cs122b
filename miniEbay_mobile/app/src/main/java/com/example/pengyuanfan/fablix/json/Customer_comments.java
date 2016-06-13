package com.example.pengyuanfan.fablix.json;

/**
 * Created by pengyuanfan on 6/11/2016.
 */
public class Customer_comments {
    String post_date;
    String comment;
    String customer_id;

    String comment_show=null;

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        if(comment_show==null){
            comment_show = post_date+" - "+customer_id+"\n"+comment;
        }
        return comment_show;
    }
}
