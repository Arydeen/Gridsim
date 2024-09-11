package com.example.gridsim.Model;

import com.example.gridsim.R;

/**
 * Created by mdp on 1/23/2017.
 */

public class Plant extends GridCell {
    protected String cellType;
    public final int TreeType = 1000;
    public final int Bushes = 1001;
    public final int CloverType = 2002;
    public final int MushroomType = 2003;
    public final int SunflowerType = 3000;

    public Plant(int val, int r, int c) {
        super(val, r, c);
        switch (val) {
            case TreeType:
                resourceID = R.drawable.tree;
                cellType = "Tree";
                break;
            case CloverType:
                resourceID = R.drawable.clover;
                cellType = "Clover";
                break;
            case MushroomType:
                resourceID = R.drawable.mushroom;
                cellType = "Mushroom";
                break;
            case SunflowerType:
                resourceID = R.drawable.sunflower;
                cellType = "Sunflower";
                break;
            case Bushes: default:
                resourceID = R.drawable.bushes;
                cellType = "Bushes";
                break;
        }
    }

    public String getCellType() { return cellType; }
}
