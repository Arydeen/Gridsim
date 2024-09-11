package com.example.gridsim.Model;

import com.example.gridsim.R.drawable;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by mdp on 1/23/2017.
 */

public class SimulationGrid {
    private int numRows, numCols;
    private GridCell[] cells;
    private GridCell badCell = new GridCell(0, -1, -1);

    public SimulationGrid(int rows, int cols) {
        numRows = rows;
        numCols = cols;
        cells = new GridCell[numRows * numCols];
    }

    public GridCell getCell(int index) {
        if (index < 0 || index >= numRows * numCols || cells[index] == null)
            return badCell;
        return cells[index];
    }

    public GridCell getCell(int row, int col) {
        return getCell(row * numCols + col);
    }

    public int getNumRows() { return numRows; }
    public int getNumCols() { return numCols; }

    public void setCell(int index, GridCell cell) {
        if (index >= 0 && index < numRows * numCols)
            cells[index] = cell;
    }

    public void setCell(int row, int col, GridCell cell) {
        setCell(row * numCols + col, cell);
    }

    public int size() {return numRows * numCols;}

    public void setUsingJSON(JSONArray arr) {
        //TODO: fill in your implementation
    }
}
