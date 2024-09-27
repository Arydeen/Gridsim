package com.example.gridsim;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.widget.GridView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Mock;

public class SimGridViewTests {

    @Mock
    EventBus mockEventBus = mock(EventBus.class);
    @Mock
    GridView mockGridView = mock(GridView.class);
    @Mock
    TextView mockTextView = mock(TextView.class);

    JSONArray grid = new JSONArray();

    @Test
    public void testSimGridAttachesEventBus() {
        // Do action
        SimGridView simGridView = new SimGridView();

        simGridView.attach(mockTextView, mockGridView);

        // Assert verify
        verify(mockEventBus).getDefault().isRegistered(simGridView);

    }

    // Every other mockito test I try to implement leads to endless trails of null pointers

}
