package com.example.gridsim;

import static java.util.Arrays.fill;

import com.example.gridsim.Model.*;

import android.content.Context;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONObject;
import org.w3c.dom.Text;


public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public SimulationGrid simGrid;

    private TextView infoBox;

    public String cellInfo;

    //Constructor
    public ImageAdapter(Context c, TextView infoBox) {
        mContext = c;
        this.infoBox = infoBox;
    }

    public int getCount() {
        return 256;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Index", "Index: " + position);
                    String infoString = simGrid.getCell(position).getCellType() + ", " + simGrid.getCell(position).getCellInfo();
                    cellInfo = infoString;
                    infoBox.setText(infoString);
                    ((MainActivity) mContext).setCellInfo(cellInfo);
                }
            });


        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(simGrid.getCell(position).getResourceID());

        // Rotate if Gardener or Cart
        if (simGrid.getCell(position).getCellType() == "Gardener" || simGrid.getCell(position).getCellType() == "Cart") {
            imageView.setRotation(simGrid.getCell(position).getRotation());
        }

        return imageView;
    }

}
