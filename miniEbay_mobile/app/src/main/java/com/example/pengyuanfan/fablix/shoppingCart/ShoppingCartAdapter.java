package com.example.pengyuanfan.fablix.shoppingCart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pengyuanfan.fablix.R;
import com.example.pengyuanfan.fablix.ShoppingCart;
import com.example.pengyuanfan.fablix.json.ShoppingCartItem;
import com.example.pengyuanfan.fablix.util.PositionHolder;

import java.util.List;

/**
 * Created by pengyuanfan on 6/9/2016.
 */
public class ShoppingCartAdapter extends BaseAdapter{

    List<ShoppingCartItem> data;
    List<ShoppingCart.ShoppingCartItemButtonListener> update;
    List<ShoppingCart.ShoppingCartItemButtonListener> delete;
    private LayoutInflater scItemInf;


    public ShoppingCartAdapter(List<ShoppingCartItem> data, List<ShoppingCart.ShoppingCartItemButtonListener> update, List<ShoppingCart.ShoppingCartItemButtonListener> delete, Context context) {
        this.data = data;
        this.update = update;
        this.delete = delete;
        this.scItemInf = LayoutInflater.from(context);
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
            convertView = scItemInf.inflate(R.layout.shopingcart_item, null);
            vh=new ViewHolder();
            vh.setItemImage((ImageView) convertView.findViewById(R.id.shoppingcart_item_img));
            vh.setTitle((TextView)convertView.findViewById(R.id.shoppingcart_item_title));
            vh.setPrice((TextView)convertView.findViewById(R.id.shoppingcart_item_price));
            vh.setQty((EditText)convertView.findViewById(R.id.shoppingcart_item_qty));
            vh.setUpdate((Button)convertView.findViewById(R.id.shoppingcart_item_update));
            vh.setDelete((Button)convertView.findViewById(R.id.shoppingcart_item_delete));
            convertView.setTag(vh);
        }else {
            vh=(ViewHolder) convertView.getTag();
        }
        vh.getTitle().setText(data.get(position).getTitle());
        vh.getPrice().setText(data.get(position).getPrice());
        //vh.getQty().setText(data.get(position).getQty());
        vh.getQty().setText("1");
        vh.getUpdate().setOnClickListener(update.get(position));
        vh.getDelete().setOnClickListener(delete.get(position));
        return convertView;
    }
    private class ViewHolder{
        ImageView itemImage;
        TextView title;
        TextView price;
        EditText qty;
        Button update;
        Button delete;

        PositionHolder position;

        public ImageView getItemImage() {
            return itemImage;
        }

        public void setItemImage(ImageView itemImage) {
            this.itemImage = itemImage;
        }

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public TextView getPrice() {
            return price;
        }

        public void setPrice(TextView price) {
            this.price = price;
        }

        public EditText getQty() {
            return qty;
        }

        public void setQty(EditText qty) {
            this.qty = qty;
        }

        public Button getUpdate() {
            return update;
        }

        public void setUpdate(Button update) {
            this.update = update;
        }

        public Button getDelete() {
            return delete;
        }

        public void setDelete(Button delete) {
            this.delete = delete;
        }
    }
}
