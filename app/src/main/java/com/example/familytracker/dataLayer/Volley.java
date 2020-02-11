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

    public void sendRequest(JsonObjectRequest jsObjRequest){
        this.requestQueue.add(jsObjRequest);
    }

    public JSONObject getParams(String stringRequest){

        JSONObject params = new JSONObject();

        try{
            JSONObject statement = new JSONObject();
            statement.put("statement",stringRequest );

            JSONArray jsonArrayStatments = new JSONArray();
            jsonArrayStatments.put(0,statement);

            params.put("statements", jsonArrayStatments);

        } catch (Exception e) {
            Log.d("request builder error", e.getMessage());
        }

        return params;
    }
}