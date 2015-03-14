package com.sharp.chat;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sharp.chat.Service.SendMessage;
import com.sharp.chat.Service.ServiceNet;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    EditText accountET;
    EditText passwordET;
    Button loginBT;
    Button registerBT;
    Button forgetBT;

    LoginReceiver loginReceiver;

    ProgressDialog progressDialog;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(loginReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = new Intent(LoginActivity.this, ServiceNet.class);
        startService(intent);

        initView();
    }

    private void initView(){

        loginReceiver = new LoginReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("LOGIN");
        registerReceiver(loginReceiver,filter);

        loginBT = (Button)findViewById(R.id.login_loginBT);
        registerBT = (Button)findViewById(R.id.login_registerBT);
        forgetBT = (Button)findViewById(R.id.login_problemBT);
        accountET = (EditText)findViewById(R.id.login_user);
        passwordET = (EditText)findViewById(R.id.login_password);

        loginBT.setOnClickListener(this);
        registerBT.setOnClickListener(this);
        forgetBT.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.login_loginBT:
                login();
                break;
            case R.id.login_problemBT:
                intent = new Intent(LoginActivity.this, ForgetActivity.class);
                startActivity(intent);
                break;
            case R.id.login_registerBT:
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }

    private void login(){
        EditText usernameET = (EditText)findViewById(R.id.login_user);
        EditText passwordET = (EditText) findViewById(R.id.login_password);
        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
//        String username = "aaa";
//        String password = "111";
        if (username.isEmpty()){
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_SHORT).show();
        }
        if (password.isEmpty()){
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
        }
        if (!username.isEmpty() && !password.isEmpty()) {
            SendMessage sendMessage = new SendMessage("LOGIN",this);
            sendMessage.setJSON(new String[]{username,password});
        }
    }

    private class LoginReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("RESULT");
            if (result.equals("SUCCESS")) {
                Intent sIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(sIntent);
                LoginActivity.this.finish();
            }else{
                String why = intent.getStringExtra("WHY");
                Toast.makeText(LoginActivity.this, why, Toast.LENGTH_SHORT);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
