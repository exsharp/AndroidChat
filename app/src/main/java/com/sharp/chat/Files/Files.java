package com.sharp.chat.Files;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.sharp.chat.Custom.ShowInfo;
import com.sharp.chat.Service.AppUtil;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

/**
 * Created by sharp on 3/15/2015.
 */
public class Files {

    private Context context;
    private String fileName;

    public Files(Context context){
        this.context = context;
        AppUtil app = (AppUtil)context.getApplicationContext();
        fileName = app.getAccount()+"_FriendList";
    }

    public void writeFListFile(JSONArray json) throws IOException {
        try {
            FileOutputStream fout = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            OutputStreamWriter os = new OutputStreamWriter(fout);
            BufferedWriter br = new BufferedWriter(os);
            for (int i = 0; i < json.length(); i++) {
                if (json.get(i).toString().equals("")){
                    i++;
                    br.write("GroupName:" + json.get(i).toString());
                }else{
                    br.write(json.get(i).toString());
                }
                br.newLine();
            }
            br.flush();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LinkedList<String> getGroup() throws IOException {
        LinkedList<String> group = new LinkedList<>();
        FileInputStream fin = context.openFileInput(fileName);
        InputStreamReader is = new InputStreamReader(fin);
        BufferedReader br = new BufferedReader(is);
        String temp = "";
        while ((temp=br.readLine())!=null){
            if (temp.indexOf("GroupName:")>-1){
                String[] arr = temp.split(":");
                group.add(arr[1]);
            }
        }
        Log.d("FListFile", "读完");
        return group;
    }

    public LinkedList<LinkedList<ShowInfo>> getChild() throws IOException {
        LinkedList<LinkedList<ShowInfo>> child = new LinkedList<LinkedList<ShowInfo>>();
        FileInputStream fin = context.openFileInput(fileName);
        InputStreamReader is = new InputStreamReader(fin);
        BufferedReader br = new BufferedReader(is);
        String str = "";
        LinkedList<ShowInfo> temp = new LinkedList<ShowInfo>();
        br.readLine();
        while ((str=br.readLine())!=null) {
            Log.d("FListFile", str);
            if (str.indexOf("GroupName:") > -1) {
                LinkedList<ShowInfo> LL = new LinkedList<ShowInfo>(temp);
                child.add(LL);
                temp.clear();
                Log.d("FListFile", "child\t"+child.toString());
            } else {
                temp.add(new ShowInfo(str, "aaa"));
                Log.d("FListFile","temp\t"+temp.toString());
            }
        }
        child.add(temp);
        Log.d("FListFile", child.toString());
        return child;
    }

    public String[] getGroupArray() throws IOException {
        LinkedList<String> sour = getGroup();
        String[] group = new String[sour.size()];
        for (int i = 0 ; i<sour.size();i++){
            group[i] = sour.get(i).toString();
        }
        return group;
    }

    public void clean() throws IOException {
        boolean aaa;
        aaa=context.deleteFile(fileName);
        System.out.print("FListFile:aaaaaaaaaa"+aaa);
    }

}
