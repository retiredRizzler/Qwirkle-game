package model;

public class GridView {
    private Grid grid;
    GridView(Grid grid)
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
}
