package com.sharp.chat.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sharp.chat.R;

/**
 * Created by sharp on 3/7/2015.
 */
public class GroupFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("GroupFragment", "到了GroupFragment");
        View groupView = inflater.inflate(R.layout.activity_main_group, container, false);
        return groupView;
    }
}
