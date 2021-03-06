package org.techtown.AndroidStudioAigoyak;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Image {

    public int code;
    public String name;
    public String corp;
    public String shape;
    public String color;
    public String line;
    public String form;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public Image() {

    }

    public Image(int code, String name, String corp, String shape, String color,  String line, String form) {
        this.code = code;
        this.name = name;
        this.corp = corp;
        this.shape = shape;
        this.color = color;
        this.line = line;
        this.form = form;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("name", name);
        result.put("corp", corp);
        result.put("shape", shape);
        result.put("color", color);
        result.put("line", line);
        result.put("form",form);
        result.put("starCount", starCount);
        result.put("stars", stars);

        return result;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code){
        this.code = code;
    }

    public String getName() {
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

    public String getShape() {
        return shape;
    }
    public void setShape(String shape){
        this.shape = shape;
    }


    public String getColor() {
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }


    public String getLine() {
        return line;
    }
    public void setLine(String line){
        this.line = line;
    }


    public String getForm() {
        return form;
    }
    public void setForm(String form){
        this.form = form;
    }

}
