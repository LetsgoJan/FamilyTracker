package com.example.familytracker.dataLayer;



import android.content.Context;

import com.android.volley.RequestQueue;
import com.example.familytracker.Model.Account;

public class AccountDbManager {

    private Volley volley;
    private Context context;
    RequestQueue requestQueue;

    public AccountDbManager(Context context){
        this.volley = Volley.getInstance(context);
        this.context = context;
    }



    public Account getAccount(String username) {

        return new Account("","","");
    }

    public void createAccount(Account account){
        volley.sendRequest("CREATE (n:Account{ Username:'"+account.getUsername()+"', Password:'"+account.getPassword()+"', AdminEmail:'"+account.getAdminEmail()+"' }) RETURN id(n)");
    }
}