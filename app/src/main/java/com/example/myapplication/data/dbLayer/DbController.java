package com.example.myapplication.data.dbLayer;

import android.content.Context;

public class DbController {
    VolleySingleton volley;
    AccountDbManager accountDbManager;

    public DbController(Context context){
        volley = new VolleySingleton(context);
        accountDbManager = new AccountDbManager(context);
    }

}
