package com.example.familytracker.dataLayer;



import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.familytracker.AccountActivity;
import com.example.familytracker.Model.Account;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

    public void createAccount(final Account account, final AccountActivity accountActivity){
        String stringRequest = "CREATE (n:Account{ Username:'"+account.getUsername()+"', Password:'"+account.getPassword()+"', AdminEmail:'"+account.getAdminEmail()+"' }) RETURN id(n)";

        VolleyLog.DEBUG = true;
        // String uri_page_one = String.format("/api/users?page=%1$s");

        HashMap headers = new HashMap();
        headers.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        String credentials = "app:b.EUGOpksfkIxf.Gz6WQBbpOSvwjjtA";
        String auth = "Basic "
                + Base64.encodeToString(credentials.getBytes(),
                Base64.NO_WRAP);
        headers.put("Authorization", auth);

// body fixen
        JSONObject params = new JSONObject();

        try{

            JSONObject statement = new JSONObject();
            statement.put("statement",stringRequest );

            JSONArray jsonArrayStatments = new JSONArray();
            jsonArrayStatments.put(0,statement);

            params.put("statements", jsonArrayStatments);

//            JSONArray jArray = params.getJSONArray("statements");
//            for(int ii=0; ii < jArray.length(); ii++){
//                Log.d("Body builder",jArray.getJSONObject(ii).getString("value"));
//            }

        } catch (Exception e) {
            Log.d("request builder error", e.getMessage());
        }

// request bouwen
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, "https://hobby-amliolnkjhecgbkenjccfial.dbs.graphenedb.com:24780/db/data/transaction/commit", params,  new Response.Listener<JSONObject>() {

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