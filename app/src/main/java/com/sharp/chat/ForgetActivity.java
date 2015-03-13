package com.sharp.chat;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class ForgetActivity extends ActionBarActivity implements View.OnClickListener {

    Button forgetBT;
    Button backBT;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
    }

    private void initView(){
        forgetBT = (Button)findViewById(R.id.forget_BT);
        backBT = (Button)findViewById(R.id.forget_back);
        forgetBT.setOnClickListener(this);
        backBT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forget_BT:
                break;
            case R.id.forget_back:
                intent = new Intent(ForgetActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forget, menu);
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