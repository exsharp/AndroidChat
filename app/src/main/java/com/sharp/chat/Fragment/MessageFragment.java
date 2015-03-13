package com.sharp.chat.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sharp.chat.Adapter.ChatAdapter;
import com.sharp.chat.Adapter.MessageFragmentAdapter;
import com.sharp.chat.ChatActivity;
import com.sharp.chat.Custom.ShowInfo;
import com.sharp.chat.R;

import java.util.LinkedList;

/**
 * Created by sharp on 3/7/2015.
 */
public class MessageFragment extends Fragment {

    private MessageFragmentAdapter adapter;
    private ListView listView;
    private LinkedList<ShowInfo> message = new LinkedList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MessageFragment", "到了MessageFragment");
        ((ActionBarActivity)getActivity()).getSupportActionBar().hide();
        message.add(new ShowInfo("aaa","bbb"));
        message.add(new ShowInfo("ccc","ddd"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View messageView = inflater.inflate(R.layout.activity_main_message, container, false);
        return messageView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    void initAdapter(){
        adapter = new MessageFragmentAdapter(getActivity(),message);
        listView = (ListView)getActivity().findViewById(R.id.main_message_LV);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String withWho;
                ShowInfo info;
                info = (ShowInfo)adapter.getItem(position);
                withWho=info.getName();
                Intent intent = new Intent(getActivity(),ChatActivity.class);
                intent.putExtra("WHO",withWho);
                startActivity(intent);
            }
        });
    }
}
