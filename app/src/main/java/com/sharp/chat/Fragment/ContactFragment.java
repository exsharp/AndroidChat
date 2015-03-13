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
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.sharp.chat.Adapter.ContactFragmentAdapter;
import com.sharp.chat.ChatActivity;
import com.sharp.chat.Custom.ShowInfo;
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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

                    return true;
                }else{
                    return false;
                }
            }
        });
    }

    private void initAdapter(){
        group = new LinkedList<String>();
        child = new LinkedList<>();

        group.add("aaa");
        group.add("bbb");
        group.add("ccc");

        LinkedList<ShowInfo> child1 = new LinkedList<>();
        child1.add(new ShowInfo("aaa", "bbb"));
        child1.add(new ShowInfo("bbb", "ccc"));

        child.add(child1);
        child.add(child1);
        child.add(child1);
        child.add(child1);

        adapter = new ContactFragmentAdapter(getActivity(),group,child);
        expandableListView =(ExpandableListView)getActivity().findViewById(R.id.main_contact_expandListView);
        expandableListView.setAdapter(adapter);
    }
}
