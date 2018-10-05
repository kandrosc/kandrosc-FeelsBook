package com.example.feelsbook;

public class Emotion {
    private String type;
    private String time;
    private String comment;
public Emotion(String t,String ts,String c) {
    type=t;
    time=ts;
    comment=c;

}
String getType() {
    return type;
}
String getTime(){return time;}
String getComment(){return comment;}
void setType(String s){type=s;}
void setTime(String s){time=s;}
void setComment(String s){comment=s;}



}
