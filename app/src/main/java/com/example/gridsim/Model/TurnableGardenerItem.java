package com.example.gridsim.Model;

import java.sql.Timestamp;
import java.util.LinkedList;

import com.example.gridsim.R;

/**
 * Created by mdp on 1/23/2017.
 * 1/5/2024 -- Modified by mdp to use as template to new Milestone 2
 */

public class TurnableGardenerItem extends GardenerItem {
    public final int GardenerType = 1000000;
    public final int CartType = 10000000;
    private int orientation;

    public static int UP = 0;
    public static int RIGHT = 2;
    public static int DOWN = 4;
    public static int LEFT = 6;

    public TurnableGardenerItem(int val, int r, int c) {
        super(val, r, c);
        int typeVal = (val / GardenerType) * GardenerType;
        int scaleFactor = 1000;
        switch (typeVal) {
            case GardenerType:
                resourceID = R.drawable.gardender_icon;
                orientation = val % 10;
                cellType = "Gardener";
                break;
            case CartType:
                resourceID = R.drawable.golfcart_icon;
                orientation = val % 10;
                cellType = "Cart";
                scaleFactor *= 10;
                break;
            default:
                orientation = UP;
                break; //rest is already handled by parent class
        }
        gardenerId = (val - typeVal) / scaleFactor;
    }

    public int getOrientation() { return orientation; }

    @Override
    public int getRotation() { return 45 * (orientation - 2); }

}
