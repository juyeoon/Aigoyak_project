package org.techtown.AndroidStudioAigoyak;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Ingredient {

    int code;
    String name;
    String corp;
    String ingr;
    String add;
    String add_warn;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();


    public Ingredient(){    }

    public Ingredient(int code, String name, String corp, String ingr, String add, String add_warn){
        this.code =code;
        this.name = name;
        this.corp = corp;
        this.ingr = ingr;
        this.add = add;
        this.add_warn = add_warn;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("name", name);
        result.put("corp", corp);
        result.put("ingr", ingr);
        result.put("add", add);
        result.put("add_warn", add_warn);
        result.put("starCount", starCount);
        result.put("stars", stars);

        return result;
    }

    public int getCode(){
        return code;
    }
    public void setCode(int code){
        this.code = code;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){ this.name = name; }

    public String getCorp(){
        return corp;
    }
    public void setCorp(String corp){
        this.corp = corp;
    }

    public String getIngr(){
        return ingr;
    }
    public void setIngr(String ingr){
        this.ingr = ingr;
    }

    public String getAdd(){
        return add;
    }
    public void setAdd(String add){
        this.add = add;
    }

    public String getAddWarn(){
        return add_warn;
    }
    public void setAddWarn(String add_warn){
        this.add_warn = add_warn;
    }
}
