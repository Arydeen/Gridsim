package com.example.gridsim.Model;

import java.sql.Timestamp;
import java.util.LinkedList;

import com.example.gridsim.R;

/**
 * Created by mdp on 1/23/2017.
 * 1/5/2024 -- Modified by mdp to use as template to new Milestone 2
 */

public class GardenerItem extends GridCell {
    protected String cellType;
    int gardenerId;

    public final int ShovelType = 2000000;

    public GardenerItem(int val, int r, int c) {
        super(val, r, c);

        int typeVal = ShovelType;
        int scaleFactor = 1000;
        
        resourceID = R.drawable.shovel_icon;
        cellType = "Shovel";

        gardenerId = (val - ShovelType) / scaleFactor;
    }

    public String getCellType() { return cellType; }

    public String getCellInfo() {
        String info = super.getCellInfo() + "\nGardener ID: " + gardenerId;
        return info;
    }
}
