package model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Grid grid;
    private Player[] player;
    private int currentPlayer;

    public Game(List<String> players)
    {
        player = new Player[players.size()];

        for (int i = 0; i < player.length; i++) {
                player[i] = new Player(players.get(i));
                player[i].refill();
            }

        grid = new Grid();
        currentPlayer = 0;
    }

    /**
     * Try to make the first move for the current player
     *
     * @param d  Direction to play the first move
     * @param is indexes of list
     */
    public void first(Direction d, int... is)
    {         grid.firstAdd(d, getTiles(is));

        player[currentPlayer].remove(getTiles(is));
        player[currentPlayer].refill();
        pass();
    }

    /**
     * Try to play a tile at a specific position for the current player
     *
     * @param row   the row of the grid
     * @param col   the column of the grid
     * @param index the index of the tile from player's hand
     */
    public void play(int row, int col, int index)
    {
        grid.add(row, col, player[currentPlayer].getHand().get(index));
        player[currentPlayer].remove(getTiles(index));
        player[currentPlayer].refill();
        pass();
    }

    /**
     * Try to play several tiles in a specific position for the current player
     *
     * @param row     the row of the grid
     * @param col     the column of the grid
     * @param d       the direction you want to add your tiles
     * @param indexes the index of the tile from player's hand
     */
    public void play(int row, int col, Direction d, int... indexes)
    {
        grid.add(row, col, d, getTiles(indexes));
        player[currentPlayer].refill();
        pass();
    }

    /**
     * Try to play several tiles which aren't lined up for the current player
     *
     * @param is indexes of the tiles from current player's hand. First index, is the row, second is the column, third
     *           is the tile and so on.
     */
    public void play(int... is)
    {
        TileAtPosition[] tap = new TileAtPosition[is.length];
        // In this for loop we have to iterate it only with multiple of 3 because every 3 indexes we have a new
        // TileAtPosition object.
        for (int i = 0; i < is.length; i += 3) {
            int r = is[i];
            int c = is[i + 1];
            Tile t = player[currentPlayer].getHand().get(is[i + 2]);
            tap[i] = new TileAtPosition(r, c, t);
        }
        grid.add(tap);
        player[currentPlayer].refill();
        pass();
    }

    /**
     * Create an array of tiles based on the indexes of the player's hand with an int varargs in argument
     *
     * @param is indexes
     * @return an array of tiles based on player's hand indexes
     */
    private Tile[] getTiles(int... is)
    {
        Tile[] tab = new Tile[is.length];

        for (int i = 0; i < tab.length; i++) {
            tab[i] = player[currentPlayer].getHand().get(is[i]);
        }
        return tab;
    }

    /**
     * Return the name of the current player
     *
     * @return String of current player's name
     */
    public String getCurrentPlayerName() {
        return player[currentPlayer].getName();
    }

    /**
     * Current player's hand
     *
     * @return List of Tile of the current player's hand
     */
    public List<Tile> getCurrentPlayerHand()
    {
        return player[currentPlayer].getHand();
    }

    /**
     * Switch round to the next player
     */
    public void pass()
    {
        if (currentPlayer != player.length - 1) {
            currentPlayer += 1;
        } else {
            currentPlayer = 0;
        }
    }

    /**
     * Getter for grid attribute
     */
    public GridView getGrid()
    {
        return new GridView(grid);
    }
}
