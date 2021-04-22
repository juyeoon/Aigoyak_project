package org.techtown.AndroidStudioAigoyak;

public class Ingredient {

    String code;
    String name;
    String corp;
    String ingr;
    String add;
    String add_warn;


    public Ingredient(){    }

    public String getCode(){
        return code;
    }
    public void setCode(String code){
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
