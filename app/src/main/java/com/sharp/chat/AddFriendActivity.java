package com.sharp.chat;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sharp.chat.Adapter.AddFriendAdapter;
import com.sharp.chat.Custom.ShowInfo;

import java.util.LinkedList;


public class AddFriendActivity extends ActionBarActivity {

    private LinearLayout back;
    private ListView listView;
    private BaseAdapter adapter;
    private LinkedList<ShowInfo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        initView();
        back = (LinearLayout)findViewById(R.id.add_friend_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFriendActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView(){
        listView = (ListView)findViewById(R.id.add_friend_lv);
        list = new LinkedList<ShowInfo>();
        adapter = new AddFriendAdapter(this, list);
        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_friend, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
