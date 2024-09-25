package com.example.gridsim;

import org.json.JSONObject;

public class responseEvent {

    private final JSONObject response; // Response from GET request
    private final SimGridView simGridView; // SimGridView to use

    public responseEvent(JSONObject response, SimGridView simGridView) {
        this.response = response;
        this.simGridView = simGridView;
    }

    public JSONObject getResponse() {
        return response;
    }

    public SimGridView getSimGridView() {
        return simGridView;
    }


}
