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

        if (!tilesMatchEachOther(line)) {
            throw new QwirkleException("Tiles don't match : either not the same shape or not the same color " +
                            "(or both)");
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
     * Adding tile(s) to a specific position in a specific direction on the grid
     * @param row the row of the grid
     * @param col the column of the grid
     * @param d the direction
     * @param line the tile(s) you want to add
     */
    public void add(int row, int col, Direction d, Tile... line)
    {
        // Check first if the tiles we want to add match each other
        if (!tilesMatchEachOther(line)) {
                throw new QwirkleException("Tiles don't match : either not the same shape or not the same color " +
                        "(or both)");
        }
        for (int i = 0; i<line.length; i++) {
            if(areRulesValid(row, col, line[i])) {
                tiles[row][col] = line[i];
                row += d.getDeltaRow();
                col += d.getDeltaCol();
            }
        }
    }

    public void add(TileAtPosition... line)
    {
        // There is a specific rule about that if we had a tile, and we want to add a second tile or more,
        // this/these tile(s) must be on the same line (same row or same column).
        if (!areTilesOnSameLine(line)) {
            throw new QwirkleException("Each tile you want to add must be on the same line");
        }
        for (TileAtPosition t : line) {
            add(t.row(), t.col(), t.tile());
        }

    }

    /**
     * Check if all the tiles of a varargs have the same row or the same column
     * @param line our tile(s)
     * @return true if all the tiles have the same row or the same column
     */
    private boolean areTilesOnSameLine(TileAtPosition... line)
    {
        for ( int k = 1; k<line.length; k++) {
            if (!(line[0].row() == line[k].row()) && !(line[0].col() == line[k].col())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if tiles match each other in a Tile varargs
     * @param line the tile(s) we want to check
     * @return false if one tile doesn't match
     */
    private boolean tilesMatchEachOther(Tile... line)
    {
        for (int k = 1; k<line.length; k++) {
            if (!eitherSameShapeOrSameColor(line[0], line[k])) {
                return false;
            }
        }
        return true;
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
            throw new QwirkleException("The positions around the tile aren't occupied by at least one tile");
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

                if (!eitherSameShapeOrSameColor(tile, nTile)) {
                    throw new QwirkleException("Tiles don't match : either not the same shape or not the same color " +
                            "(or both)");
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

            // If at least one position around is occupied by a tile
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
     * Return true either if tiles have the same shape or the same color but not both
     */
    private boolean eitherSameShapeOrSameColor(Tile tile0, Tile tile1)
    {
        return tile0.shape().equals(tile1.shape())
                ^
                tile0.color().equals(tile1.color());
    }

}

