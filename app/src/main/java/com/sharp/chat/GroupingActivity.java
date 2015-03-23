package com.sharp.chat;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sharp.chat.Database.DBContactManager;
import com.sharp.chat.DragList.DragListAdapter;
import com.sharp.chat.DragList.DragListView;
import com.sharp.chat.Service.SendMessage;

import org.w3c.dom.Text;

import java.util.ArrayList;

/*
 * 分组管理列表
 */

public class GroupingActivity extends ActionBarActivity implements View.OnClickListener {

    private DragListAdapter mAdapter = null;
    private ArrayList<String> mData = new ArrayList<String>();

    private Button complete;
    private LinearLayout add;
    private BroadcastReceiver receiver = new Receiver();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grouping);

        IntentFilter filter = new IntentFilter();
        filter.addAction("ADD_GROUP");
        registerReceiver(receiver,filter);

        initView();
        initAdapter();
    }

    private void initView(){
        complete = (Button)findViewById(R.id.grouping_complete);
        add = (LinearLayout)findViewById(R.id.grouping_add);
        complete.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.grouping_add:
                addGroup();
                break;
            case R.id.grouping_complete:
                complete();
                break;

        }
    }

    private void addGroup(){
        final EditText editText = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
        .setTitle("请输入分组名")
        .setIcon(android.R.drawable.ic_dialog_info)
        .setView(editText)
        .setNegativeButton("取消", null)
        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String group = editText.getText().toString();
                mData.add(group);
                SendMessage msg = new SendMessage("ADD_GROUP",GroupingActivity.this);
                msg.setJSON(new String[]{group});
            }
        }).show();
    }


    class Receiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("RESULT");
            if (!result.equals("添加成功")) {
                mData.remove(mData.size() - 1);
                Toast.makeText(getApplication(), result, Toast.LENGTH_SHORT);
            }
        }
    }

    private void complete(){

    }

    /**
     * 初始化视图
     */
    private void initAdapter() {

        // 数据结果
        DBContactManager manager = new DBContactManager(this);
        mData = new ArrayList<String>(manager.getGrouping());

        DragListView dragListView = (DragListView) findViewById(R.id.grouping_lv);
        mAdapter = new DragListAdapter(this, mData);
        dragListView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grouping, menu);
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
