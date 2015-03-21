package com.sharp.chat;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.sharp.chat.DragList.DragListAdapter;
import com.sharp.chat.DragList.DragListView;

import java.util.ArrayList;

/*
 * 分组管理列表
 */

public class GroupingActivity extends ActionBarActivity {


    private DragListAdapter mAdapter = null;
    private ArrayList<String> mData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grouping);
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        initData();// 在对ListView操作前要先完成数据的初始化

        DragListView dragListView = (DragListView) findViewById(R.id.grouping_lv);
        mAdapter = new DragListAdapter(this, mData);
        dragListView.setAdapter(mAdapter);
    }

    /**
     * 初始化数据
     */
    public void initData() {
        // 数据结果
        mData = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            mData.add("测试" + i + "目录");
        }
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
