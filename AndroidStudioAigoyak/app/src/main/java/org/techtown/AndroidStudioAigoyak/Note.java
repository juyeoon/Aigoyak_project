package org.techtown.AndroidStudioAigoyak;

public class Note {
    int _id;
    String name;
    String clock;


    public Note(int _id, String name, String clock) {
        this._id = _id;
        this.name = name;
        this.clock = clock;

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

}