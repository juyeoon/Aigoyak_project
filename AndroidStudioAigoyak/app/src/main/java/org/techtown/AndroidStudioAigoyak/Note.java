package org.techtown.AndroidStudioAigoyak;

public class Note {
    int _id;
    String name;
    String clock;
    String locationX;
    String locationY;
    String warning;
    public Note(int _id,String name, String clock, String locationX, String locationY) {
        this._id = _id;
        this.name = name;
        this.clock = clock;
        this.locationX = locationX;
        this.locationY = locationY;
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

    public String getLocationX() {
        return locationX;
    }
    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }
    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

}