package com.sharp.chat.Custom;

/**
 * Created by sharp on 3/11/2015.
 */
public class ChatMessage {

    private int id;
    private Integer portrait;
    private String name;
    private String message;
    private int type;

    public void setChatId(int id){this.id=id;}
    public int getChatId(){return id;}

    public void setPortrait(Integer portrait) {this.portrait=portrait;}
    public Integer getPortrait() {
        return portrait;
    }

    public void setChatName(String name) {
        this.name = name;
    }
    public String getChatName() {
        return name;
    }

    public void setMessage(String message){this.message=message;}
    public String getMessage(){return message;}

    public void setType(int type){this.type=type;}
    public int getType(){return type;}

    public ChatMessage(){}
    public ChatMessage(int id,Integer portrait,String name,String message,int type){
        this.id = id;
        this.portrait=portrait;
        this.name=name;
        this.message=message;
        this.type=type;
    }
    public ChatMessage(String message,int type){
        this.message=message;
        this.type=type;
    }


}
