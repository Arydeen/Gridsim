package com.example.gridsim;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
                            updateGrid(response);
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



        Button button1 = (Button) this.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        R.string.button_one_toast, Toast.LENGTH_SHORT).show();
            }
        });
        Button button2 = (Button) this.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        R.string.button_two_toast, Toast.LENGTH_SHORT).show();
            }
        });

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, R.drawable.small_goblin));

    }

    @Override
    public void onClick(View v) {

    }

    public void updateGrid(JSONObject response) throws JSONException {

        JSONArray gridArray = response.getJSONArray("grid");

        int totalElements = gridArray.length() * gridArray.getJSONArray(0).length();

        int[] gridValues = new int[totalElements];

        int index = 0;
        for (int i = 0; i < gridArray.length(); i++) {
            JSONArray rowArray = gridArray.getJSONArray(i);
            for (int x = 0; x < rowArray.length(); x++) {
                gridValues[index++] = rowArray.getInt(x);
            }
        }

    }
}