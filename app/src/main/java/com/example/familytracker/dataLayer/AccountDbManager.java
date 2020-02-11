package com.example.familytracker.dataLayer;



import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.familytracker.AccountActivity;
import com.example.familytracker.MainActivity;
import com.example.familytracker.Model.Account;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AccountDbManager {

    private Volley volley;
    private Context context;

    public AccountDbManager(Context context){
        this.volley = Volley.getInstance(context);
        this.context = context;
    }



    public Account getAccountByUsernameAndPassword(Account account, final MainActivity mainActivity) {
        String stringRequest = "MATCH (n:Account{ Username:'"+account.getUsername()+"', Password:'"+account.getPassword()+"'}) RETURN id(n)";


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, "https://hobby-amliolnkjhecgbkenjccfial.dbs.graphenedb.com:24780/db/data/transaction/commit", volley.getParams(stringRequest),  new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("D", "RESPONSE FOUND!");
                        try{
                           Log.d("response", response.toString());

                            if (response.getJSONArray("errors").length() >0){
                                JSONArray errors = response.getJSONArray("errors");
                                for (int i = 0; i < errors.length(); i++) {
                                    JSONObject jsonObject = errors.getJSONObject(i);
                                    Log.d("error", jsonObject.toString());
                                }
                                throw(new Exception("http has error"));
                            }
                            JSONArray results = response.getJSONArray("results");
                            if(results.getJSONObject(0).getJSONArray("data").length()>0){
                                mainActivity.onLogin(true);
                            } else{
                                mainActivity.onLogin(false);
                                // TODO:: Error handling maken.
                            }

                        } catch (Exception ex) {
                            Log.d("error", ex.getMessage());
                        }
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                String credentials = "familytrackerapp:b.lvi68jphAS5y.626ejKTx6o2cRlqX";
                String auth = "Basic "
                        + Base64.encodeToString(credentials.getBytes(),
                        Base64.NO_WRAP);
                headers.put("Authorization", auth);
                return headers;
            }
        };

        volley.sendRequest(jsObjRequest);

        return new Account("","","");
    }

    public void createAccount(final Account account, final AccountActivity accountActivity){
        String stringRequest = "CREATE (n:Account{ Username:'"+account.getUsername()+"', Password:'"+account.getPassword()+"', AdminEmail:'"+account.getAdminEmail()+"' }) RETURN id(n)";


// request bouwen
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
            (Request.Method.POST, "https://hobby-amliolnkjhecgbkenjccfial.dbs.graphenedb.com:24780/db/data/transaction/commit", volley.getParams(stringRequest),  new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("D", "RESPONSE FOUND!");
                    try{
                        response.toString();

                        Log.d("response", response.toString());
                        if (response.getJSONArray("errors").length() >0){
                            JSONArray errors = response.getJSONArray("errors");
                            for (int i = 0; i < errors.length(); i++) {
                                JSONObject jsonObject = errors.getJSONObject(i);
                                Log.d("error", jsonObject.toString());
                            }
                            throw(new Exception("http has error"));
                        }

                        accountActivity.onAccountCreated(true);

                    } catch (Exception ex) {
                        Log.d("error", ex.getMessage());

                    }
                }


            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.e("Error: ", error.getMessage());
                }
            }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                String credentials = "familytrackerapp:b.lvi68jphAS5y.626ejKTx6o2cRlqX";
                String auth = "Basic "
                        + Base64.encodeToString(credentials.getBytes(),
                        Base64.NO_WRAP);
                headers.put("Authorization", auth);
                return headers;
            }
        };

        volley.sendRequest(jsObjRequest);
    }
}