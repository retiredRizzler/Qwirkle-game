package model;

/**
 * model.Grid reprents our game board
 */
public class Grid {
    private Tile[][] tiles;
    private boolean isEmpty;

    public Grid()
    {
        this.isEmpty = true;
        this.tiles = new Tile[91][91];
    }

    /**
     * Return the tile of a specific position on the board
     */
    public Tile get(int row, int col)
    {
       if (!isPositionValid(row, col)) {
           return null;
       }
       return tiles[row][col];
    }

    /**
     * This method returns isEmpty attribute
     */
    public boolean isEmpty()
    {
         return isEmpty;
    }

    /**
     * Method is used to make the first add by respecting the rules
     * @param d the direction we chose to put our tiles
     * @param line the tile(s) we want to add to the grid
     */
    public void firstAdd(Direction d, Tile... line)
    {
        if (!isEmpty()) {
            throw new QwirkleException("The grid is not empty");
        }
            for (int k = 1; k<line.length; k++) {

                if (isSameTile(line[0], line[k])) {
                    throw new QwirkleException("Tiles don't match (same shape and same color)");
                }

                if (!eitherSameShapeOrSameColor(line[0], line[k])) {
                    throw new QwirkleException("Tiles don't match (not the same shape and not the same color)");
                }
            }

            int row = tiles.length/2; // 45
            int col = tiles[0].length/2; // 45
            tiles[row][col] = line[0];
            isEmpty = false;

            for (int i = 1; i<line.length; i++) {
                tiles[row + d.getDeltaRow()] [col + d.getDeltaCol()] = line[i];
                row += d.getDeltaRow();
                col += d.getDeltaCol();
            }
    }

    /**
     * Adding a tile to a specific position on the grid
     * @param row the row of the grid
     * @param col the column of the grid
     * @param tile the tile you want to add
     */
    public void add(int row, int col, Tile tile)
    {
        if (areRulesValid(row, col, tile)) {
            tiles[row][col] = tile;
        }
    }

    /**
     * Check if the rules when you want to add a tile to a specific positions are valid
     * @param row row of the grid
     * @param col column of the grid
     * @param tile the tile you want to check if it's valid
     * @return true if all the rules are respected
     */
    private boolean areRulesValid(int row, int col, Tile tile)
    {
        if(!isPositionValid(row, col)) {
            throw  new QwirkleException("The position is not on the grid");
        }

        if (tiles[row][col] != null) {
            throw new QwirkleException("The position is already occupied by a tile");
        }

        if(arePositionsAroundFree(row, col)){
            throw new QwirkleException("The positions around the tile you want to add aren't occupied " +
                    "by at least one tile");
        }

        Direction dir[] = Direction.values();
        for (Direction d : dir) {

            int nRow = row + d.getDeltaRow(); // next row
            int nCol = col + d.getDeltaCol(); // next col
            Tile nTile = tiles[nRow][nCol]; // next position

            while (isPositionValid(nRow, nCol)) {
                // If there is no tile at next position, break the loop and pass to next direction
                if (nTile == null)
                    break;

                if (isSameTile(tile, nTile)) {
                    throw new QwirkleException("Tiles don't match : same shape and same color");
                }

                if (!eitherSameShapeOrSameColor(tile, nTile)) {
                    throw new QwirkleException("Tiles don't match : not the same shape and not the same color");
                }

                nRow += d.getDeltaRow();
                nCol += d.getDeltaCol();
                nTile = tiles[nRow][nCol];
            }
        }
            return true;
    }

    /**
     * Check if the positions around the tile we want to add are free
     * @return false if there is at least one tile around
     */
    private boolean arePositionsAroundFree(int row, int col)
    {
        Direction dir[] = Direction.values();
        for (Direction d : dir) {

            // If at least one position around  is occupied by a tile
            if (tiles[row + d.getDeltaRow()] [col + d.getDeltaCol()] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the position entered in parameters is on the grid
     * @return true if the position exists on the grid
     */
    private boolean isPositionValid (int row, int col)
    {
        return row >= 0 && row <= tiles.length
                &&
                col >= 0 && col <= tiles.length;
    }

    /**
     * Return true if two tiles have the same shapes and not the same colors
     */
    private boolean eitherSameShapeOrSameColor(Tile tile0, Tile tile1)
    {
        return tile0.shape() == tile1.shape()
                ^
                tile0.color() == tile1.color();
    }

    /**
     * Return true if two tiles have the same colors and not the same shapes

    private boolean isSameColor(Tile tile0 ,Tile tile1)
    {
        return (tile0.color() == tile1.color())
                &&
                (tile0.shape() != tile1.shape());
    }

    /**
     * Return true if both tiles have the same colors and the same shapes
     */
    private boolean isSameTile(Tile tile0, Tile tile1)
    {
        return tile0.equals(tile1);
    }

    public void add(int row, int col, Direction d, Tile... line)
    {}

}

