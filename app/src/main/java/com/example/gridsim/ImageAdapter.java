package com.example.gridsim;

import com.example.gridsim.Model.*;

import android.content.Context;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ImageAdapter extends BaseAdapter {

    private Context mContext; // Context for the application

    public SimulationGrid simGrid; // Simgrid to be used for image creation

    private TextView infoBox; // Textbox to be used for cell info

    public String cellInfo; // Cell info to be sent to bundle

    private int retPosition; // Current position, used for updating

    //Constructor
    public ImageAdapter(Context c, TextView infoBox) {
        mContext = c;
        this.infoBox = infoBox;
    }

    // Returns Cell Count
    public int getCount() {
        return 256;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public int getPosition() {return retPosition;}

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);
            imageView.setOnClickListener(new View.OnClickListener() {
                // onClick handled for each grid cell
                @Override
                public void onClick(View v) {
                    String infoString = simGrid.getCell(position).getCellType()
                            + ", " + simGrid.getCell(position).getCellInfo(); // Create cell info
                    cellInfo = infoString; // Save cell info for save state
                    infoBox.setText(infoString); // Set the textbox with the cell info
                    ((MainActivity) mContext).setCellInfo(cellInfo); // Set cell info to bundle
                    retPosition = position; // Update currently selected position
                }
            });
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(simGrid.getCell(position).getResourceID()); // Set the Cell

        // Rotate if Gardener or Cart
        if (simGrid.getCell(position).getCellType() == "Gardener" || simGrid.getCell(position).getCellType() == "Cart") {
            imageView.setRotation(simGrid.getCell(position).getRotation());
        }

        return imageView;
    }

}
