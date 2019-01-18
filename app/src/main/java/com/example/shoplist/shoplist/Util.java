package com.example.shoplist.shoplist;

public class Util {

    public static Util instance = null;
    public static Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }

    public static String TitleCustom(String itemText){
        int Space = itemText.indexOf(" ");
        String Title = itemText.substring(0,Space);
        return Title;
    }
}
