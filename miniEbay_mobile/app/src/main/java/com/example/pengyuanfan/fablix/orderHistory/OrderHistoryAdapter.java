package com.example.pengyuanfan.fablix.orderHistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pengyuanfan.fablix.R;
import com.example.pengyuanfan.fablix.json.SingleOrder;

import java.util.List;

/**
 * Created by pengyuanfan on 6/9/2016.
 */
public class OrderHistoryAdapter extends BaseAdapter {

    List<SingleOrder> data;
    private LayoutInflater orderItemInf;

    public OrderHistoryAdapter(List<SingleOrder> data, Context context) {
        this.data = data;
        orderItemInf = LayoutInflater.from(context);
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
            convertView=orderItemInf.inflate(R.layout.order_item, null);
            vh=new ViewHolder();
            vh.setOrderDate((TextView)convertView.findViewById(R.id.orderList_Date));
            vh.setOrderId((TextView)convertView.findViewById(R.id.orderList_id));
            vh.setOrderPrice((TextView)convertView.findViewById(R.id.orderList_price));
        }else{
            vh=(ViewHolder)convertView.getTag();
        }
        vh.getOrderDate().setText(data.get(position).getSale_date());
        vh.getOrderId().setText(data.get(position).getId());
        vh.getOrderPrice().setText(data.get(position).getTotal());
        return convertView;
    }

    private class ViewHolder{
        TextView orderId;
        TextView orderPrice;
        TextView orderDate;

        public TextView getOrderId() {
            return orderId;
        }

        public void setOrderId(TextView orderId) {
            this.orderId = orderId;
        }

        public TextView getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(TextView orderPrice) {
            this.orderPrice = orderPrice;
        }

        public TextView getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(TextView orderDate) {
            this.orderDate = orderDate;
        }
    }
}
