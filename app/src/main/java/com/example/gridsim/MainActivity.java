package com.example.gridsim;

import com.example.gridsim.Model.*;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        // Create gridview
        GridView gridview = (GridView) findViewById(R.id.gridview);

        // Create new SimGridView, and do initial attach()
        SimGridView simGridView = new SimGridView();
        simGridView.attach(infoText, gridview);

        // Check whether we're recreating a previously destroyed instance.
        if (savedInstanceState != null) {
            // Restore value of members from saved state.
            cellInfo = savedInstanceState.getString(STATE_INFO);
            infoText.setText(cellInfo);
        } else {
            infoText.setText("Click on a grid cell to get more info.");
        }

        Poller poller = new Poller();

        poller.pollerStart(simGridView, this);

    }

    @Override
    public void onClick(View v) {}

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