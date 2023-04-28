package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the view of our board
 */
public class GridView {
    private Grid grid;
    public GridView(Grid grid)
    {
        this.grid = grid;

    }
    Tile get(int row, int col)
    {
        return grid.get(row, col);
    }
    boolean isEmpty()
    {
        return grid.isEmpty();
    }

    public void displayGrid()
    {
        List<Integer> lR = listRow();
        List<Integer> lC = listCol();
        int beginRow = lR.get(0);
        int beginCol = lC.get(0);
        int lastRow = lR.get(lR.size() - 1);
        int lastCol = lC.get(lC.size() - 1);

        for (int r = beginRow; r <= lastRow; r++) {
            System.out.println();
            System.out.print( r +" |");
            for (int c = beginCol; c <= lastCol; c++) {
                if (get(r, c) != null) {
                    System.out.print(get(r, c).toString());
                }

            }
        }
        System.out.println();
        for (int c = beginCol; c <= lastCol; c++) {
            System.out.print(c);
        }
    }

    private List<Integer> listRow()
    {
        List<Integer> lR = new ArrayList();
        for (int r = 0; r < 91; r++) {
            for (int c = 0; c < 91; c++) {
                if (get(r, c) != null) {
                    if (!lR.contains(r)) {
                        lR.add(r);
                    }
                }
            }
        }
        return lR;
    }

    private List<Integer> listCol()
    {
        List<Integer> lC = new ArrayList();
        for (int r = 0; r < 91; r++) {
            for (int c = 0; c < 91; c++) {
                if (get(r, c) != null) {
                    if(!lC.contains(c)) {
                        lC.add(c);
                    }
                }
            }
        }
        return lC;
    }
}
