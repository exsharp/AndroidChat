package com.sharp.chat;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.sharp.chat.Fragment.ContactFragment;
import com.sharp.chat.Fragment.GroupFragment;
import com.sharp.chat.Fragment.MessageFragment;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Button messageBT;
    private Button contactBT;
    private Button groupBT;

    private MessageFragment messageFragment;
    private ContactFragment contactFragment;
    private GroupFragment groupFragment;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fragmentManager = getSupportFragmentManager();
        setTabSelection(1);
    }

    private void initView() {
        messageBT = (Button) findViewById(R.id.main_messageBT);
        contactBT = (Button) findViewById(R.id.main_contactBT);
        groupBT   = (Button) findViewById(R.id.main_groupBT);
        messageBT.setOnClickListener(this);
        contactBT.setOnClickListener(this);
        groupBT.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_messageBT:
                setTabSelection(1);
                break;
            case R.id.main_contactBT:
                setTabSelection(2);
                break;
            case R.id.main_groupBT:
                setTabSelection(3);
                break;
            default:
        }
    }

    private void setTabSelection(int index){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 1:
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                    transaction.add(R.id.main_content, messageFragment);
                } else {
                    transaction.show(messageFragment);
                }
                messageBT.setBackgroundResource(R.drawable.main_message_select);
                break;
            case 2:
                if (contactFragment == null){
                    contactFragment = new ContactFragment();
                    transaction.add(R.id.main_content,contactFragment);
                } else {
                    transaction.show(contactFragment);
                }
                contactBT.setBackgroundResource(R.drawable.main_contact_select);
                break;
            case 3:
                if (groupFragment == null){
                    groupFragment = new GroupFragment();
                    transaction.add(R.id.main_content, groupFragment);
                }else{
                    transaction.show(groupFragment);
                }
                groupBT.setBackgroundResource(R.drawable.main_group_select);
                break;
            default:
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (messageFragment != null) {
            messageBT.setBackgroundResource(R.drawable.main_message);
            transaction.hide(messageFragment);
        }
        if (contactFragment != null) {
            contactBT.setBackgroundResource(R.drawable.main_contact);
            transaction.hide(contactFragment);
        }
        if (groupFragment != null) {
            groupBT.setBackgroundResource(R.drawable.main_group);
            transaction.hide(groupFragment);
        }
    }
}
