package org.techtown.AndroidStudioAigoyak;

public class Note {
    int _id;
    String name;
    String clock;
    int date;
    int date2;

    public Note(int _id, String name, String clock, int date, int date2) {
        this._id = _id;
        this.name = name;
        this.clock = clock;
        this.date = date;
        this.date2 = date2;

    }

    public int get_id(){
        return _id;
    }
    public void set_id(int _id){
        this._id = _id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
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

    public int getDate2() {
        return date2;
    }
    public void setDate2(int date2){
        this.date2 = date2;
    }


}