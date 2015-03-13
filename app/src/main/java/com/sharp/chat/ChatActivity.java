package com.sharp.chat;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.sharp.chat.Adapter.ChatAdapter;
import com.sharp.chat.Custom.ChatMessage;
import com.sharp.chat.Service.AppUtil;

import java.util.LinkedList;


public class ChatActivity extends ActionBarActivity {


    private Context context = this;

    private LinkedList<ChatMessage> beans = null;
    /** 聊天message 格式 */
    private ListView listView;
    /** 信息编辑框 */
    private EditText edt;
    /** 信息发送按钮 */

    private Button btnEnter;
    private ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        beans = new LinkedList<ChatMessage>();
//        beans.add(new ChatMessage("Hello",1));
//        beans.add(new ChatMessage("fhfhgkldf",1));
//        beans.add(new ChatMessage("asdfg",0));

        setContentView(R.layout.activity_chat);


        initViewsMethod();
        onHandleMethod();
    }

    //处理listView的item方法
    private void initViewsMethod(){
        listView = (ListView)findViewById(R.id.chat_ListView);
        edt = (EditText)findViewById(R.id.edt);
        btnEnter = (Button)findViewById(R.id.enter);

        listView.setOnCreateContextMenuListener(
                new View.OnCreateContextMenuListener() {
                    @Override
                    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                        menu.setHeaderTitle("提示：");
                        menu.setHeaderIcon(android.R.drawable.stat_notify_error);
                        menu.add(0,0,1,"删除");
                        menu.add(1,1,0,"取消");
                    }
                }
        );

    }

    //处理发送消息的方法
    public void onHandleMethod(){
        adapter = new ChatAdapter(context,beans);
        listView.setAdapter(adapter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = edt.getText().toString().trim();
                if (txt.equals("")){
                    Toast.makeText(getApplicationContext(), "发送内容不能为空 !", Toast.LENGTH_SHORT).show();
                }else{
                    adapter.addItemNotifiChange(new ChatMessage("abcdefg",1));
                    edt.setText("");
                    listView.setSelection(beans.size() - 1);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
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
