package com.example.pengyuanfan.fablix.singleItem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pengyuanfan.fablix.R;
import com.example.pengyuanfan.fablix.json.Customer_comments;

import java.util.List;

/**
 * Created by pengyuanfan on 6/11/2016.
 */
public class CommentListAdapter extends BaseAdapter {
    List<Customer_comments> Comments;

    public CommentListAdapter(List<Customer_comments> comments) {
        Comments = comments;
    }

    @Override
    public int getCount() {
        return Comments.size();
    }

    @Override
    public Object getItem(int position) {
        return Comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;
        if(convertView==null){
            LayoutInflater inflater= LayoutInflater.from(parent.getContext());
            convertView=inflater.inflate(R.layout.comment, null);
            vh=new ViewHolder();
            vh.setComments((TextView) convertView.findViewById(R.id.comment_item));
            convertView.setTag(vh);
        }else {
            vh=(ViewHolder) convertView.getTag();
        }
        vh.getComments().setText(Comments.get(position).toString());
        return convertView;
    }
    class ViewHolder{
        TextView comments;

        public TextView getComments() {
            return comments;
        }

        public void setComments(TextView comments) {
            this.comments = comments;
        }
    }
}
