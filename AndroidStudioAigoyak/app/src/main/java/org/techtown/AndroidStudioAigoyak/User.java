package org.techtown.AndroidStudioAigoyak;

public class User {
    int _id;
    String feature;

    public User(int _id, String feature) {
        this._id = _id;
        this.feature = feature;
    }

    public int get_id(){
        return _id;
    }
    public void set_id(int _id){
        this._id = _id;
    }

    public String getFeature(){ return feature; }
    public void setFeature(String feature){ this.feature = feature; }
}