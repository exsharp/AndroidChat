package com.sharp.chat.Custom;

/**
 * Created by sharp on 3/15/2015.
 */
public class DBContact {
    public String account;
    public String grouping;
    public String personalized;
    public String portrait;

    public DBContact(String account,String grouping,String personalized,String portrait){
        this.account = account;
        this.grouping = grouping;
        this.personalized = personalized;
        this.portrait = portrait;
    }
}
