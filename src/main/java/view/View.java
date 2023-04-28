package view;

import model.*;

import java.util.List;

/**
 * Represents the view of our game
 */
public class View {
    /**
     * Display the view of the game board
     * @param grid
     */
    public static void display(GridView grid)
    {
        grid.displayGrid();
    }

    /**
     * Display the player's name and player's hand
     * @param name the name of the player
     * @param hand the player's hand
     */
    public static void display(String name, List<Tile> hand)
    {
        System.out.println("It's to "+ name + "to play");
        System.out.println(hand.toString());
    }

    /**
     * Display the commands to play the game
     */
    public static void displayHelp()
    {
        System.out.println("Q W I R K L E\n" +
                "Qwirkle command:\n" +
                "- play 1 tile : o <row> <col> <i>\n" +
                "- play line: l <row> <col> <direction> <i1> [<i2>]\n" +
                "- play plic-ploc : m <row1> <col1> <i1> [<row2> <col2> <i2>]\n" +
                "- play first : f [<direction>] <f1> [<f2> …]\n" +
                "- pass : p\n" +
                "- quit : q\n" +
                "i : index in list of tiles\n" +
                "d : direction in l (left), r (right), u (up), d(down)");
    }

    /**
     * Display the error message
     * @param message
     */
    public static void displayError(String message)
    {
        System.out.println("A problem occurred : " + message);
    }

    public static void main(String[] args)
    {
        Grid grid = new Grid();
        grid.firstAdd(Direction.UP, new Tile(Color.YELLOW, Shape.SQUARE), new Tile(Color.YELLOW, Shape.STAR),
                new Tile(Color.YELLOW, Shape.DIAMOND));
        grid.add(43, 46, Direction.RIGHT, new Tile(Color.RED, Shape.DIAMOND),
                new Tile(Color.GREEN, Shape.DIAMOND), new Tile(Color.BLUE, Shape.DIAMOND));
        GridView gridView = new GridView(grid);
        display(gridView);
    }
}
