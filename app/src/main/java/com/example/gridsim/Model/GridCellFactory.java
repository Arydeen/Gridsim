package com.example.gridsim.Model;

/**
 * Created by mdp on 1/23/2017.
 * Gutted by mdp on 1/5/2024 as starting point for new Milestone 2
 */

public class GridCellFactory {

    public GridCell makeCell(int val, int row, int col) {

        if (val == 0) {
            return new GridCell(val, row, col);
        } else if (val >= 1000 && val <= 3000) {
            return new Plant(val, row, col);
        } else if (val >= 2000000 && val <= 2999999) {
            return new GardenerItem(val, row, col);
        } else if (val >= 1000000 && val <= 1999999 || val >= 10000000 && val <= 20000000) {
            return new TurnableGardenerItem(val, row, col);
        } else {
            return new GridCell(val, row, col);
        }
    }

}
