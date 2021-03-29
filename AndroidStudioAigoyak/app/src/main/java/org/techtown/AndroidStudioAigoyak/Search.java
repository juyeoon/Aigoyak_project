package org.techtown.AndroidStudioAigoyak;

public class Search {
    int _id;
    String name;
    String corp;


    public Search(int _id, String name, String corp) {
        this._id = _id;
        this.name = name;
        this.corp = corp;

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

    public String getCorp() {
        return corp;
    }
    public void setCorp(String corp){
        this.corp = corp;
    }

}