package com.example.gridsim;

import com.example.gridsim.Model.*;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.gridsim.Model.GridCell;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final String STATE_INFO = " ";

    public String cellInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Unused Button
        Button button1 = (Button) this.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });

        // Unused Button
        Button button2 = (Button) this.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });


        // Textview for text to be displayed for cell info;
        TextView infoText = (TextView) this.findViewById(R.id.textView);

        // Check whether we're recreating a previously destroyed instance.
        if (savedInstanceState != null) {
            // Restore value of members from saved state.
            cellInfo = savedInstanceState.getString(STATE_INFO);
            infoText.setText(cellInfo);
        } else {
            infoText.setText("Click on a grid cell to get more info.");
        }

        // Create gridview and apply Image Adapter
        GridView gridview = (GridView) findViewById(R.id.gridview);
        ImageAdapter imgAdapt = new ImageAdapter(this, infoText);

        // Retrieve the JSON data from the server
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        String url = "http://stman1.cs.unh.edu:6191/games";

        JsonObjectRequest
                jsonObjectRequest
                = new JsonObjectRequest(
                JsonRequest.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            updateGrid(response, gridview, imgAdapt);
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

    @Override
    public void onClick(View v) {}

    public void updateGrid(JSONObject response, GridView gridView, ImageAdapter imageAdapter) throws JSONException {

        JSONArray gridArray = response.getJSONArray("grid");

        SimulationGrid simGrid = new SimulationGrid(16, 16);

        simGrid.setUsingJSON(gridArray);

        imageAdapter.simGrid = simGrid;

        gridView.setAdapter(null);
        gridView.setAdapter(imageAdapter);

    }

    public void setCellInfo(String toSet) {
        cellInfo = toSet;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state.
        savedInstanceState.putString(STATE_INFO, cellInfo);

        // Always call the superclass so it can save the view hierarchy state.
        super.onSaveInstanceState(savedInstanceState);
    }
}