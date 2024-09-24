package com.example.gridsim;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Poller {

    private String url = "http://stman1.cs.unh.edu:6191/games";

    public Poller() {}

    public void pollerStart(SimGridView simGridView, Context c) {

        ScheduledThreadPoolExecutor sch =
                (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);

        Runnable pingForPOST = new Runnable() {
            @Override
            public void run() {
                RequestQueue requestQueue = Volley.newRequestQueue(c);

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

                requestQueue.add(jsonObjectRequest);
            }
        };

        Runnable pingForGET = new Runnable() {
            @Override
            public void run() {
                // Retrieve the JSON data from the server
                RequestQueue requestQueue = Volley.newRequestQueue(c);

                JsonObjectRequest
                        jsonObjectRequest
                        = new JsonObjectRequest(
                        JsonRequest.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    updateGrid(response, simGridView);
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                );

                requestQueue.add(jsonObjectRequest);
            }
        };

        ScheduledFuture<?> pingOncePOST = sch.schedule(
                pingForPOST, 0, TimeUnit.MILLISECONDS);
        ScheduledFuture<?> pingWithDelay = sch.scheduleWithFixedDelay(
                pingForGET, 500, 500, TimeUnit.MILLISECONDS);

    }

    public void updateGrid(JSONObject response, SimGridView simGridView) throws JSONException {

        JSONArray gridArray = response.getJSONArray("grid");

        simGridView.setUsingJSON(gridArray);

    }



}
