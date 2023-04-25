package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents a player of the game
 */
public class Player {

    private String name;
    private List<Tile> tiles;

    public Player (String name)
    {
        this.name = name;
        tiles = new ArrayList();
    }

    /**
     * Getter for name attribute
     */
    public String getName()
    {
        return name;
    }

    /**
     * Return an unmodifiable list of the player's hand
     */
    public List<Tile> getHand()
    {
        return Collections.unmodifiableList(tiles);
    }

    /**
     * Refill player's hand until he has 6 tiles
     */
    public void refill()
    {
        // In order to know how many tiles we should pick in the bag
        int missingTilesNb = 6 - tiles.size();

        if (missingTilesNb == 0) {
            throw new QwirkleException("You already has 6 tiles in your hand");
        }
        // Refill the hand with missing tiles number
        Tile[] randomTiles = Bag.getInstance().getRandomTiles(missingTilesNb);
        tiles.addAll(Arrays.stream(randomTiles).toList());
    }

    /**
     * Remove tiles from player's hand
     * @param ts varargs of Tile
     */
    public void remove(Tile...ts)
    {
        for (int i = 0; i < ts.length; i++) {
            tiles.remove(ts[i]);
        }
    }
}
