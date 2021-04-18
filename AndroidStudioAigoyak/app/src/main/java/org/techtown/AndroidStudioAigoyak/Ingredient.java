package org.techtown.AndroidStudioAigoyak;

public class Ingredient {

    String code;
    String ingr;
    String add;
    String add_warn;
    String url;


    public Ingredient(){

    }

    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code = code;
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


    public String getUrl(){ return url; }
    public void setUrl(String url){ this.url = url; }

}
