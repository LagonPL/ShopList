package com.example.shoplist.shoplist;

import android.content.Context;
import android.widget.Toast;

public class Util {

    public static Util instance = null;

    public static Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }

    public static String TitleCustom(String itemText) {
        int Space = itemText.indexOf("\u2063");
        String Title = itemText.substring(0, Space);
        return Title;
    }
    public static void ToastMaker(Context context, String toast ){
        if (context == null)
            return;


        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();

    }
}
