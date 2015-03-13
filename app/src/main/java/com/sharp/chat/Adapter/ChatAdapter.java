package com.sharp.chat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sharp.chat.Custom.ChatMessage;
import com.sharp.chat.R;

import java.util.LinkedList;

/**
 * Created by sharp on 3/11/2015.
 */
public class ChatAdapter extends BaseAdapter {

    private LinkedList<ChatMessage> message=null;
    private LayoutInflater li;
    private final int ITEM_TYPES = 3, TYPE_MY = 1, TYPE_HER = 2,TYPE_TIME=0;

    public ChatAdapter(Context context,LinkedList<ChatMessage> message){
        this.message = message;
        li = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return message.size();
    }

    @Override
    public Object getItem(int position) {
        return message.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_TYPES;
    }

    @Override
    public int getItemViewType(int position) {
        int type = message.get(position).getType();
        switch (type){
            case 1:
                //设置自己的消息
                return TYPE_MY;
            case 2:
                //设置对面的消息
                return TYPE_HER;
            default:
                return TYPE_TIME;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        InlineView temp = new InlineView();
        ChatMessage chat = message.get(position);
        int type = getItemViewType(position);
        if (null==convertView){
            switch (type){
                case TYPE_MY:
                    convertView = li.inflate(R.layout.activity_chat_my,null);
                    break;
                case TYPE_HER:
                    convertView = li.inflate(R.layout.activity_chat_her,null);
                    break;
                case TYPE_TIME:
                    convertView = li.inflate(R.layout.activity_chat_time,null);
                    break;
            }
            temp.portrait = (ImageView) convertView.findViewById(R.id.chat_portrait);
            temp.message = (TextView) convertView.findViewById(R.id.chat_message);
            convertView.setTag(temp);
        }else {
            temp = (InlineView)convertView.getTag();
        }
        if (0==type){
            TextView timeView = (TextView)convertView.findViewById(R.id.chat_time);
            timeView.setText("这里显示时间");
        }else {
            temp.portrait.setImageResource(R.drawable.ic_launcher);
            temp.message.setText(chat.getMessage());
        }
        return convertView;
    }

    private class InlineView {
        ImageView portrait;
        TextView message;
    }

    /** 添加发表私信内容，更新列表 */
    public void addItemNotifiChange(ChatMessage bean) {
        message.add(bean);
        notifyDataSetChanged();
    }

}
