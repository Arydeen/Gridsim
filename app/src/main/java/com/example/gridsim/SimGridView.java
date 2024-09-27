package com.example.gridsim;

import com.example.gridsim.Model.*;

import android.media.Image;
import android.widget.GridView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SimGridView {

    private TextView TextView; // TextView to use, set by attach
    private GridView GridView; //  GridView to use, set by attach
    private SimulationGrid simGrid; // SimulationGrid to use, created by attach
    private ImageAdapter imageAdapter; // Image Adapter to use, created by attach

    public SimGridView() {} // Constructor

    // Method to set Local variables for use in other methods, called in onCreate
    public void attach(TextView tView, GridView gView) {

        // Set Local Variables
        TextView = tView;
        GridView = gView;

        // Make new SimulationGrid
        simGrid = new SimulationGrid(16, 16);

        // register this class with the EventBus
        EventBus.getDefault().register(this);
    }

    // Method to separate the SimGridView from some other objects, called in onStop
    public void detach() {
        EventBus.getDefault().unregister(this); // Un-register from EventBus
    }

    public void setImageAdapter() {
        // Make new ImageAdapter
        imageAdapter = new ImageAdapter(TextView.getContext(), TextView);

        // Set ImageAdapter's SimGrid to the new simgrid
        setImageAdapter(imageAdapter, simGrid);
    }

    // Method to set the grid and info text using a JSONArray
    public void setUsingJSON(JSONArray gridArray) throws JSONException {

        String infoString = simGrid.getCell(imageAdapter.getPosition()).getCellType() + ", " +
                simGrid.getCell(imageAdapter.getPosition()).getCellInfo();

        TextView.setText(infoString);

        simGrid.setUsingJSON(gridArray);

        GridView.setAdapter(null);
        GridView.setAdapter(imageAdapter);
    }

    // Method Called to Update the Grid
    public void updateGrid(JSONObject response, SimGridView simGridView) throws JSONException {

        JSONArray gridArray = response.getJSONArray("grid");

        simGridView.setUsingJSON(gridArray);

    }

    public void setImageAdapter(ImageAdapter imageAdapter, SimulationGrid simGrid) {
        imageAdapter.simGrid = simGrid;
    }

    // This method will be called when a responseEvent is posted
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResponseEvent(responseEvent event) throws JSONException {
        updateGrid(event.getResponse(), event.getSimGridView());
    }
}
