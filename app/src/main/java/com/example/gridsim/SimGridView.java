package com.example.gridsim;

import com.example.gridsim.Model.*;

import android.widget.GridView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;


public class SimGridView {

    private TextView TextView;
    private GridView GridView;
    private SimulationGrid simGrid;
    private ImageAdapter imageAdapter;

    public SimGridView() {}

    public void attach(TextView tView, GridView gView) {
        TextView = tView;
        GridView = gView;

        simGrid = new SimulationGrid(16, 16);

        imageAdapter = new ImageAdapter(tView.getContext(), TextView);

        imageAdapter.simGrid = simGrid;

    }

    public void detach() {
        //TODO
    }

    public void setUsingJSON(JSONArray gridArray) throws JSONException {

        simGrid.setUsingJSON(gridArray);

        GridView.setAdapter(null);
        GridView.setAdapter(imageAdapter);
    }
}
