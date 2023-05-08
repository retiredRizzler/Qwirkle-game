package view;

import model.*;

import java.util.ArrayList;
import java.util.List;

import static model.Color.*;

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
        System.out.println("It's to \033[1m\033[36m" + name + "\033[0m to play : ");
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
        System.out.println("\033[1m\033[36mA problem occured --->\033[0m " + message + "Try again !");
        System.out.println();
    }

    public static void displayWelcome()
    {
        System.out.println("\n===================================================");
        System.out.println("\t\t     >>> \033[1m\033[36mTHE QWIRKLE GAME\033[0m <<<");
        System.out.println("===================================================");


    }
}
