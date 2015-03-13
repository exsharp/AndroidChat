package com.sharp.chat.Custom;

/**
 * Created by sharp on 3/9/2015.
 */
public class ShowInfo {

    private int id;
    private String name;
    private Integer portrait;
    private String personalized;
    private int state;

    public int getId(){return id;}
    public void setId(int id){this.id=id;};

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public Integer getPortrait() {
        return portrait;
    }
    public void setPortrait(Integer portrait) {
        this.portrait = portrait;
    }

    public String getPersonalized(){return personalized;}
    public void setPersonalized(String personalized){this.personalized = personalized;}

    public ShowInfo(){}

    public ShowInfo(String name,Integer portrait,String personalized,int state){
        this.name = name;
        this.portrait = portrait;
        this.personalized = personalized;
        this.state = state;
    }

    public ShowInfo(String name,Integer portrait,String personalized,int state,int id){
        this.name = name;
        this.portrait = portrait;
        this.personalized = personalized;
        this.state = state;
        this.id = id;
    }

    public ShowInfo(String name,String personalized){
        this.name = name;
        this.personalized = personalized;
    }

    @Override
    public String toString() {
        return "PeopleStudentBean [name=" + name + ", portrait="
                + portrait + ", personalized=" + personalized + ", id=" + id + ",state=" + state + "]";
    }
}
