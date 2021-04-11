package org.techtown.AndroidStudioAigoyak;

import java.io.Serializable;

public class Search {
    int _id;
    String name;
    String corp;
    String code;

    public Search(int _id, String name, String corp, String code) {
        this._id = _id;
        this.name = name;
        this.corp = corp;
        this.code = code;
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

    public String getCode() {
        return code;
    }
    public void setCode(String corp){
        this.code = code;
    }

}