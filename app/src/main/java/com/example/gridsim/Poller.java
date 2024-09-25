package com.example.gridsim;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Poller {

    private String url = "http://stman1.cs.unh.edu:6191/games"; // URL to ping in requests

    public Poller() {} // Constructor

    // Poller start method, sends one POST request on start, followed by
    // GET requests every 500 milliseconds
    public void pollerStart(SimGridView simGridView, Context c) {

        // Create the ScheduledThreadPoolExecutor with 1 core
        ScheduledThreadPoolExecutor sch =
                (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);

        // Create the runnable for the first sent POST request
        Runnable pingForPOST = new Runnable() {
            @Override
            public void run() {
                RequestQueue requestQueue = Volley.newRequestQueue(c); // Create request queue

                // Create the empty JSON POST request
                JsonObjectRequest
                        jsonObjectRequest
                        = new JsonObjectRequest(
                        JsonRequest.Method.POST, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {}
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {}
                        }
                );

                // Add to the queue
                requestQueue.add(jsonObjectRequest);
            }
        };

        // Create the runnable for the repeated GET requests
        Runnable pingForGET = new Runnable() {
            @Override
            public void run() {
                RequestQueue requestQueue = Volley.newRequestQueue(c); // Create request queue

                // Create the JSON GET request to send to the server
                JsonObjectRequest
                        jsonObjectRequest
                        = new JsonObjectRequest(
                        JsonRequest.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // On response, post to eventbus, send with response, and
                                // SimGridView to use.
                                EventBus.getDefault().post(new responseEvent(response, simGridView));
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {}
                        }
                );

                // Add to the queue
                requestQueue.add(jsonObjectRequest);
            }
        };

        // Start both scheduled requests
        ScheduledFuture<?> pingOncePOST = sch.schedule(
                pingForPOST, 0, TimeUnit.MILLISECONDS);
        ScheduledFuture<?> pingWithDelay = sch.scheduleWithFixedDelay(
                pingForGET, 500, 500, TimeUnit.MILLISECONDS);

    }



}
