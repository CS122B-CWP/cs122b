package com.example.pengyuanfan.fablix.orderDetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pengyuanfan.fablix.R;
import com.example.pengyuanfan.fablix.json.Order;

import java.util.List;

/**
 * Created by pengyuanfan on 6/9/2016.
 */
public class OrderDetailAdapter extends BaseAdapter {

    List<Order> data;
    private LayoutInflater orderDetailInf;

    public OrderDetailAdapter(List<Order> data, Context context) {
        this.data = data;
        orderDetailInf = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null){
            convertView = orderDetailInf.inflate(R.layout.order_detail_item, null);
            vh = new ViewHolder();
            vh.setOrderDate((TextView) convertView.findViewById(R.id.orderDetail_Date));
            vh.setOrderPrice((TextView) convertView.findViewById(R.id.orderDetail_price));
            vh.setOrderQty((TextView) convertView.findViewById(R.id.orderDetail_Qty));
            vh.setOrderProductName((TextView) convertView.findViewById(R.id.orderDetail_name));
        }else {
            vh=(ViewHolder) convertView.getTag();
        }
        vh.getOrderPrice().setText(data.get(position).getUnit_price());
        //vh.getOrderQty().setText(data.get(position).getQty());
        vh.getOrderQty().setText("1");
        vh.getOrderProductName().setText(data.get(position).getTitle());
        return convertView;
    }

    private class ViewHolder{
        TextView orderPrice;
        TextView orderQty;
        TextView orderDate;
        TextView orderProductName;
        public TextView getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(TextView orderPrice) {
            this.orderPrice = orderPrice;
        }

        public TextView getOrderQty() {
            return orderQty;
        }

        public void setOrderQty(TextView orderQty) {
            this.orderQty = orderQty;
        }

        public TextView getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(TextView orderDate) {
            this.orderDate = orderDate;
        }

        public TextView getOrderProductName() {
            return orderProductName;
        }

        public void setOrderProductName(TextView orderProductName) {
            this.orderProductName = orderProductName;
        }
    }
}
