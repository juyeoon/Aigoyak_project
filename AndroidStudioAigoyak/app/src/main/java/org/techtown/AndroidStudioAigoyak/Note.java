package org.techtown.AndroidStudioAigoyak;

public class Note {
    int _id;
    String code;
    String name;
    String corp;
    String clock;
    int date;
    int alarm;
    int date2;

    public Note(int _id, String code, String name, String corp, String clock, int date, int alarm, int date2) {
        this._id = _id;
        this.code = code;
        this.name = name;
        this.corp = corp;
        this.clock = clock;
        this.date = date;
        this.alarm = alarm;
        this.date2 = date2;

    }

    public int get_id(){
        return _id;
    }
    public void set_id(int _id){
        this._id = _id;
    }

    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code = code;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getCorp(){
        return corp;
    }
    public void setCorp(String corp){
        this.corp = corp;
    }

    public String getClock() {
        return clock;
    }
    public void setClock(String clock){
        this.clock = clock;
    }

    public int getDate() {
        return date;
    }
    public void setDate(int date){
        this.date = date;
    }

    public int getAlarm() { return alarm; }
    public void setAlarm(int alarm){
        this.alarm = alarm;
    }

    public int getDate2() {
        return date2;
    }
    public void setDate2(int date2){
        this.date2 = date2;
    }


}