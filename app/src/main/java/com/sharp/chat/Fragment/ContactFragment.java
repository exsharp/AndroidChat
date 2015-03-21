package com.sharp.chat.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.sharp.chat.Adapter.ContactFragmentAdapter;
import com.sharp.chat.AddFriendActivity;
import com.sharp.chat.ChatActivity;
import com.sharp.chat.Custom.DBContact;
import com.sharp.chat.Custom.ShowInfo;
import com.sharp.chat.Database.DBContactManager;
import com.sharp.chat.GroupingActivity;
import com.sharp.chat.R;

import java.util.LinkedList;

/**
 * Created by sharp on 3/7/2015.
 */
public class ContactFragment extends Fragment {

    private ExpandableListAdapter adapter;
    private ExpandableListView expandableListView;
    private LinkedList<String> group;
    private LinkedList<LinkedList<ShowInfo>> child;
    private DBContactManager manager;

    private Button addFriendButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("ContactFragment", "到了ContactFragment");
        View contactView = inflater.inflate(R.layout.activity_main_contact, container, false);
        return contactView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        addFriendButton = (Button)getActivity().findViewById(R.id.main_contact_addBT);
        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddFriendActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initListData(){
        group = new LinkedList<String>();
        child = new LinkedList<>();
        String temp = "";
        String grouping ="";
        manager = new DBContactManager(getActivity());
        LinkedList<DBContact> list = new LinkedList<>(manager.Query());
        for (int i = 0 ;i<list.size();i++){

            grouping=list.get(i).grouping;
            String name = list.get(i).friend;
            String personalized = list.get(i).personalized;

            if (grouping.equals(temp)) {
                child.getLast().add(new ShowInfo(name, personalized));
            }else{
                temp = grouping;
                group.add(temp);
                LinkedList<ShowInfo> tempList = new LinkedList<>();
                tempList.add(new ShowInfo(name,personalized));
                child.add(tempList);
            }
        }
    }
    private void initAdapter(){
        adapter = new ContactFragmentAdapter(getActivity(),group,child);
        expandableListView =(ExpandableListView)getActivity().findViewById(R.id.main_contact_expandListView);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
                return false;
            }
        });
        expandableListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int groupPosition,childPosition;
                int itemType = ExpandableListView.getPackedPositionType(id);
                if (itemType == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {

                    return true;
                }else if(itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP){
                    Intent intent = new Intent(getActivity(), GroupingActivity.class);
                    startActivity(intent);
                    return true;
                }else{
                    return false;
                }
            }
        });
    }
}
