package com.example.gridsim;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.gridsim.Model.GridCell;
import com.example.gridsim.Model.GridCellFactory;
import com.example.gridsim.Model.TurnableGardenerItem;

public class GridCellTest {

    GridCellFactory gsf = new GridCellFactory();

    GridCell testCell = gsf.makeCell(0, 1, 1);

    GridCell testPlantCell = gsf.makeCell(2003, 2, 2);

    GridCell testGardenerItem = gsf.makeCell(2000000, 3, 3);

    GridCell testTGardenerItemUp = gsf.makeCell(1001000, 4, 4);

    GridCell testTGardenerItemRight = gsf.makeCell(1002002, 5, 5);

    GridCell testTGardenerItemDown = gsf.makeCell(10030004, 6, 6);

    GridCell testTGardenerItemLeft = gsf.makeCell(10040006, 7, 7);


    @Test
    public void testCellIsCorrect() {assertEquals("Empty", testCell.getCellType());}

    @Test
    public void testPlantCellIsCorrect() {assertEquals("Mushroom", testPlantCell.getCellType());}

    @Test
    public void testGardenerItemIsCorrect() {assertEquals("Shovel", testGardenerItem.getCellType());}

    @Test
    public void testTGardenerItemUpIsCorrect() {
        assertEquals("Gardener", testTGardenerItemUp.getCellType());
        TurnableGardenerItem TItem = (TurnableGardenerItem) testTGardenerItemUp;
        assertEquals(0, TItem.getOrientation());
    }

    @Test
    public void testTGardenerItemRightIsCorrect() {
        assertEquals("Gardener", testTGardenerItemRight.getCellType());
        TurnableGardenerItem TItem = (TurnableGardenerItem) testTGardenerItemRight;
        assertEquals(2, TItem.getOrientation());
    }

    @Test
    public void testTGardenerItemDownIsCorrect() {
        assertEquals("Cart", testTGardenerItemDown.getCellType());
        TurnableGardenerItem TItem = (TurnableGardenerItem) testTGardenerItemDown;
        assertEquals(4, TItem.getOrientation());
    }

    @Test
    public void testTGardenerItemLeftIsCorrect() {
        assertEquals("Cart", testTGardenerItemLeft.getCellType());
        TurnableGardenerItem TItem = (TurnableGardenerItem) testTGardenerItemLeft;
        assertEquals(6, TItem.getOrientation());
    }




}
