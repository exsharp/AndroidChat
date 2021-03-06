package com.sharp.chat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sharp.chat.Custom.ShowInfo;
import com.sharp.chat.R;

import java.util.LinkedList;

/**
 * Created by sharp on 3/18/2015.
 */
public class AddFriendAdapter extends BaseAdapter {

    LinkedList<ShowInfo> message;
    LayoutInflater li;

    public AddFriendAdapter(Context context,LinkedList<ShowInfo> message) {
        this.message=message;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = li.inflate(R.layout.listview_main_message,null);

        PersonView temp = new PersonView();
        temp.portrait = (ImageView)convertView.findViewById(R.id.ListView_add_friend_portrait);
        temp.name = (TextView)convertView.findViewById(R.id.ListView_add_friend_user);
        temp.personalized = (TextView)convertView.findViewById(R.id.ListView_add_friend_personalized);

        ShowInfo showInfo=message.get(position);
        temp.portrait.setImageResource(R.drawable.ic_launcher);
        temp.name.setText(showInfo.getName());
        temp.personalized.setText(showInfo.getPersonalized());

        return convertView;
    }

    class PersonView{
        ImageView portrait;
        TextView name;
        TextView personalized;
    }
}
