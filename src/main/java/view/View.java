package view;

import model.*;

import java.util.ArrayList;
import java.util.List;

import static model.Color.*;
import static model.Direction.*;
import static model.Shape.*;

/**
 * Represents the view of our game
 */
public class View {
    /**
     * Display the view of the game board
     * @param grid
     */
    public static void displayGrid(GridView grid)
    {
        grid.displayGrid(grid);
        System.out.println();
    }

    /**
     * Display a String on the screen.
     * @param message the String you want to display.
     */
    public static void display(String message)
    {
        System.out.println(message);
    }

    /**
     * Display the player's name and player's hand
     * @param name the name of the player
     * @param hand the player's hand
     */
    public static void displayPlayer(String name, List<Tile> hand)
    {
        System.out.println();
        System.out.println("It's to \033[1m\033[36m" + name + " \033[0mto play : ");
        System.out.println();
        int i = 0;
        System.out.print("[");
        for(Tile t : hand ) {
            System.out.print( " " +i+":"+t +" ");
            i++;
        }
        System.out.print("] ");
    }

    /**
     * Display the commands to play the game
     */
    public static void displayHelp()
    {
        System.out.println();
        System.out.println(
                """
                        \033[4;36mQwirkle commands\033[0m
                        - o : Play one tile
                        - l : Play severeal tiles on a line
                        - m : Play plic-ploc
                        - p : Skip the round
                        - d : Display the game board
                        - q : Quit the game
                        - To choose the tile(s) you want to play to have to use the index from your hand.
                        """);
        System.out.println();
    }

    /**
     * Display the error message
     * @param message
     */
    public static void displayError(String message)
    {
        System.out.println();
        System.out.println("\033[1m\033[36mA problem occured --->\033[0m " + message + " Try again !");
        System.out.println();
    }

    public static void displayWelcome()
    {
        System.out.println("\n===================================================");
        System.out.println("\t\t     >>> \033[1m\033[36mTHE QWIRKLE GAME\033[0m <<<");
        System.out.println("===================================================");


    }

    public static void main(String[] args) {
        Grid grid = new Grid();
        var t1 = new Tile(RED, ROUND);
        var t2 = new Tile(RED, DIAMOND);
        var t3 = new Tile(RED, PLUS);
        grid.firstAdd(UP, t1, t2, t3);

        var t4 = new Tile (RED, SQUARE);
        var t5 = new Tile (BLUE, SQUARE);
        var t6 = new Tile (PURPLE, SQUARE);
        grid.add(46, 45,RIGHT, t4,t5,t6);

        var t7 = new Tile(BLUE, ROUND);
        grid.add( 45, 46, t7);

        var t8 = new Tile(GREEN, PLUS);
        var t9 = new Tile(GREEN, DIAMOND);
        int score0 = grid.add( 43, 44, DOWN, t8,t9);

        var t10 = new TileAtPosition(42, 44, new Tile(GREEN, STAR));
        var t11 = new TileAtPosition(45, 44, new Tile(GREEN, ROUND));
        int score = grid.add(t10, t11,new TileAtPosition(46 ,44,new Tile(GREEN,SQUARE)),
                new TileAtPosition(41,44,new Tile(GREEN,CROSS)));
        score = grid.add(new TileAtPosition(45,47,new Tile(PURPLE,ROUND)),
                new TileAtPosition(47,47,new Tile(PURPLE,STAR)));
        score = grid.add(47 ,45 ,Direction.RIGHT,new Tile(RED,STAR),new Tile(BLUE,STAR));
        score = grid.add(48 ,47 , LEFT, new Tile (PURPLE, CROSS), new Tile(BLUE,CROSS),new Tile(RED,CROSS));
        View.displayGrid(new GridView(grid));
        System.out.println("Score : " + score);

    }
}
