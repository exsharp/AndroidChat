package com.sharp.chat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sharp.chat.Service.SendMessage;


public class RegisterActivity extends ActionBarActivity implements View.OnClickListener {

    Button registerBT;
    Button backBT;
    Intent intent;
    EditText accountET;
    EditText passwordET;
    EditText checkET;
    EditText emailET;

    String account;
    String password;
    String check;
    String email;
    BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        receiver = new RegisterReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("REGISTER");
        registerReceiver(receiver,filter);
    }

    private void initView(){
        registerBT = (Button)findViewById(R.id.register_BT);
        backBT = (Button) findViewById(R.id.register_back_to_login);
        registerBT.setOnClickListener(this);
        backBT.setOnClickListener(this);

        accountET = (EditText)findViewById(R.id.register_user);
        passwordET = (EditText)findViewById(R.id.register_password1);
        checkET = (EditText)findViewById(R.id.register_password2);
        emailET = (EditText) findViewById(R.id.register_email);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_BT:
                register();
                break;
            case R.id.register_back_to_login:
                intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                RegisterActivity.this.finish();
                break;
            default:
        }
    }

    private void register(){

        account = accountET.getText().toString();
        password = passwordET.getText().toString();
        check = checkET.getText().toString();
        email = emailET.getText().toString();

        if (account.isEmpty()||password.isEmpty()||check.isEmpty()||email.isEmpty()){
            Toast.makeText(this,"请输入完整信息",Toast.LENGTH_SHORT).show();
        }else if(!password.equals(check)){
            Toast.makeText(this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
        }else if(false){

        }else {
            SendMessage sendMessage = new SendMessage("REGISTER", this);
            sendMessage.setJSON(new String[]{account,password,email});
        }

    }

    private class RegisterReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("RESULT");
            switch (result){
                case "exist":
                    Toast.makeText(RegisterActivity.this,"该账号已存在",Toast.LENGTH_SHORT).show();
                    accountET.setText("");
                    break;
                case "success":
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(RegisterActivity.this,MainActivity.class);
                    intent1.putExtra("account", account);
                    startActivity(intent1);
                    RegisterActivity.this.finish();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
