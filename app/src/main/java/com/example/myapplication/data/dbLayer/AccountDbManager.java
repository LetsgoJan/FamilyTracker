package com.example.myapplication.data.dbLayer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.data.model.Account;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.ui.login.LoginActivity;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccountDbManager {

    private VolleySingleton volley;
    private Context context;
    RequestQueue requestQueue;

    public AccountDbManager(Context context){
        this.volley = VolleySingleton.getInstance(context);
        this.context = context;
    }



    public Account getAccount(String username) {

        VolleyLog.DEBUG = true;
        RequestQueue queue = volley.getRequestQueue();
        String uri_page_one = String.format("/api/users?page=%1$s");

        HashMap headers = new HashMap();
        headers.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");


// body fixen
        JSONObject params = new JSONObject();
        
        try{


            List<String> statements = new ArrayList<String>();

            String statement = new String("CREATE (n) RETURN id(n)");
            statements.add(statement);

            params.put("statements", statements);

            JSONArray jArray = params.getJSONArray("statements");
            for(int ii=0; ii < jArray.length(); ii++)
                System.out.println(jArray.getJSONObject(ii).getString("value"));

        } catch (Exception e) {

        }





// request bouwen
        JsonObjectRequest jsObjRequest = new JsonObjectRequest

                (Request.Method.POST, "https://hobby-amliolnkjhecgbkenjccfial.dbs.graphenedb.com:24780/db/data/transaction/commit", params,  new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            int errorcode = response.getInt("errorCode");
                            String errormessage = response.getString("errorMessage");
                            JSONObject data = response.getJSONObject("data");
                            String token = data.getString("token");
                            Toast.makeText(context,"Token: " + token, Toast.LENGTH_SHORT).show();
                        } catch (Exception ex) {

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        VolleyLog.e("Error: ", error.getMessage());
                    }
                });



        queue.add(jsObjRequest);


        return new Account("","");
    }

    public void createAccount(Account account){

    }
}
