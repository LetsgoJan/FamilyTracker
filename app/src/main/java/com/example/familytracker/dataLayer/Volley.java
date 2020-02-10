package com.example.familytracker.dataLayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import androidx.collection.LruCache;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.familytracker.Model.MyEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Volley {

    private static Volley instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private Context context;
    private MyEventListener callback;

    public Volley(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();

        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized Volley getInstance(Context context) {
        if (instance == null) {
            instance = new Volley(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public void sendRequest(String request){

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
            statement.put("statement",request );

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
                            int errorcode = response.getInt("errorCode");
                            Log.d("error", errorcode + "");
                            String errormessage = response.getString("errorMessage");
                            Log.d("error", errormessage);
                            JSONObject data = response.getJSONObject("data");
                            JSONArray results =  data.getJSONArray("results");

                            JSONArray errors =  data.getJSONArray("errors");
                            JSONObject firstError = errors.getJSONObject(0);
                            Log.d("resonse error code", firstError.getString("code"));
                            Log.d("resonse error message", firstError.getString("message"));
                            String token = data.getString("token");
                            Toast.makeText(context,"Token: " + token, Toast.LENGTH_SHORT).show();

                        } catch (Exception ex) {

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

        this.requestQueue.add(jsObjRequest);

    }
}