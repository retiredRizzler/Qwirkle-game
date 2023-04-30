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
     * Display the player's name and player's hand
     * @param name the name of the player
     * @param hand the player's hand
     */
    public static void displayPlayer(String name, List<Tile> hand)
    {
        System.out.println();
        System.out.println("It's to " + name + " to play : ");
        System.out.println();
        int i = 0;
        System.out.print("[");
        for(Tile t : hand ) {
            System.out.print( " " +i+":"+t +" ");

            i++;
        }
        System.out.print("] ");
    }

    public static void displayHand(List<Tile> hand)
    {
        System.out.println();
        int i = 0;
        System.out.print("[");
        for(Tile t : hand ) {
            System.out.print(i+": "+t);

            i++;
        }
        System.out.print(" ] ");
    }

    /**
     * Display the commands to play the game
     */
    public static void displayHelp()
    {
        System.out.println();
        System.out.println(
                """
                        \033[4;36mQwirkle command\033[0m
                        - Play one tile : o
                        - Play severeal tiles on a line : l
                        - Play plic-ploc : m
                        - Play the first move (use it when you're the player who starts the game) : f
                        - Pass : p
                        - Quit : q
                        i : index in list of tiles
                        d : direction in l (left), r (right), u (up), d(down)""");
        System.out.println();
    }

    /**
     * Display the error message
     * @param message
     */
    public static void displayError(String message)
    {
        System.out.println("A problem occurred : " + message);
    }

    public static void displayWelcome()
    {
        System.out.println("\n===================================================");
        System.out.println("\t\t     >>> \033[1m\033[36mTHE QWIRKLE GAME\033[0m <<<");
        System.out.println("===================================================");


    }
    public static void main(String[] args)
    {
        displayWelcome();
        var t1 = new Tile(RED, Shape.ROUND);
        var t2 = new Tile(RED, Shape.DIAMOND);
        var t3 = new Tile(RED, Shape.PLUS);
        //cedrick tiles
        var t4 = new Tile(RED,Shape.SQUARE) ;
        var t5 = new Tile (BLUE , Shape.SQUARE) ;
        var t6 = new Tile(PURPLE,Shape.SQUARE) ;
        // Elvire
        var t7 = new Tile(BLUE,Shape.ROUND) ;
        //Vincent
        var t8 = new Tile(GREEN,Shape.PLUS) ;
        var t9 = new Tile (GREEN,Shape.DIAMOND) ;

        // sonia situation E
        var t10 = new Tile (GREEN , Shape.ROUND) ;
        var t11 = new Tile(GREEN,Shape.STAR) ;

        //situation F
        var t12 = new Tile(RED, Shape.SQUARE);
        var t13= new Tile(Color.YELLOW, Shape.SQUARE);
        //situation G
        var t14 = new Tile(YELLOW, Shape.STAR);
        var t15 = new Tile(ORANGE, Shape.STAR);
        // Situation H
        var t16 = new Tile(Color.ORANGE, Shape.CROSS);
        var  t17= new Tile(Color.ORANGE, Shape.DIAMOND);
        // situation x

        //situation I
        var t18 = new Tile(Color.YELLOW, Shape.DIAMOND);
        var t19 = new Tile(Color.YELLOW, Shape.ROUND);
        //situation J
        var t20 = new Tile(RED, Shape.STAR);
        //situation K
        var t21 = new Tile(BLUE, Shape.CROSS);
        var t22 = new Tile(RED, Shape.CROSS);
        var t23 = new Tile(Color.ORANGE, Shape.CROSS);
        //situation L
        var t24 = new Tile(Color.ORANGE, Shape.SQUARE);
        var t25 = new Tile(BLUE, Shape.SQUARE);

        Grid grid = new Grid();


        GridView gridView = new GridView(grid);

        grid.firstAdd(Direction.UP, t1, t2, t3);
        grid.add(46,45,Direction.RIGHT,t4,t5,t6) ;
        grid.add(45,46,t7);
        grid.add(43,44,Direction.DOWN,t8,t9);
        grid.add(45,44,t10);
        grid.add(42,44,t11);
        //F
        grid.add(46,48,Direction.DOWN,t13,t12);
        //G
        grid.add(42,43,Direction.LEFT,t14,t15);
        //H
        grid.add(43,42,Direction.DOWN,t16,t17);
        //x
        // grid.add(42,41,Direction.LEFT,tile1,tile2);
        //I
        grid.add(44,43,Direction.DOWN,t18,t19);
        //J
        grid.add(42,45,t20);
        //K
        grid.add(47,46 , Direction.LEFT,t21,t22,t23);
        //L
        grid.add(46,49,Direction.DOWN,t24,t25);

        displayGrid(gridView);
        displayHelp();
        List<Tile> hand = new ArrayList<>();
        hand.add(t1);
        hand.add(t2);
        hand.add(t3);
        hand.add(t4);
        hand.add(t5);
        View.displayPlayer("Rayan", hand);
    }
}
