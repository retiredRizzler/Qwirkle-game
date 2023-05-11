import model.*;
import view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        View.displayWelcome();

        List<String> players = enterPlayers();
        Game game = new Game(players);
        GridView grid = game.getGrid();

        View.displayPlayer(game.getCurrentPlayerName(), game.getCurrentPlayerHand(), game.getCurrentPlayerScore());
        boolean isValid = false;
        while (!isValid) {
            try {
                playFirst(game);
                isValid = true;
            } catch (QwirkleException e) {
                View.displayError(e.getMessage());
                View.displayPlayer(game.getCurrentPlayerName(), game.getCurrentPlayerHand(),
                        game.getCurrentPlayerScore());
            }
        }
        View.displayGrid(grid);
        View.displayHelp();

        while (true)
        {
            View.displayPlayer(game.getCurrentPlayerName(), game.getCurrentPlayerHand(), game.getCurrentPlayerScore());
            askCommandToPlayer(game, grid);
            View.displayGrid(grid);
        }



    }

    /**
     * Ask the player to input a command to make a move and check if the move is valid else ask again to the current
     * player.
     * @param game Game
     */
    private static void askCommandToPlayer(Game game, GridView grid)
    {
        boolean isValid = false;
        while (!isValid) {
            String cmd = readString("Enter a command to make a move ('o', 'l', 'm', 'p', " +
                    "or any keys from your keyboard if you want to see all the commands) : ");
            // The string array will automatically ignore the space
            // String[] cmds = readString("Enter a command to make a move ('o', 'l', 'm', 'p', " +
            // "or any keys from your keyboard if you want to see all the commands) : ").split("\\s+");

            try {
                switch (cmd.toLowerCase().charAt(0)) {
                    case 'o':
                        playOneTile(game);
                        break;

                    case 'l':
                        playSomeTilesOnLine(game);
                        break;

                    case 'm':
                        playPlicPloc(game);
                        break;

                    case 'p':
                        game.pass();
                        break;

                    case 'q':
                        System.exit(0);
                        View.display("You left the game :(  See you soon ! ");
                        break;

                    case 'd':
                        View.displayGrid(grid);
                    default:
                        View.displayHelp();
                }
                isValid = true;
            } catch (QwirkleException e) {
                View.displayError(e.getMessage());
                View.displayPlayer(game.getCurrentPlayerName(), game.getCurrentPlayerHand(),
                        game.getCurrentPlayerScore());
            }
        }
    }

    /**
     * Ask player indexes of tile from his hand to play some tiles on the grid
     * @param game Game
     */
    private static void playPlicPloc(Game game)
    {
        int[] indexes = enterTiles();
        game.play(indexes);
    }

    /**
     * Ask player the row and column of the grid, and indexes of his hand and try to play the move.
     * @param game Game
     */
    private static void playOneTile(Game game)
    {
        int row = readInt("Please first enter the row where you want to play your first tile : ");
        int col = readInt("Now enter the column where you want to play your first tile : ");
        int index = readInt("Enter the index of the tile " + " from your hand that you want to play : ");

        while (index < 0 || index >= 6) {
            index= readInt("Enter a correct index of the tile " + " from your hand that you want to play : ");
        }

        game.play(row, col, index);
    }

    /**
     * Ask player the row, the column of the grid of the first tile and the indexes of the tiles he want to play
     * then try to make a move on the grid
     *
     * @param game Game
     */
    private static void playSomeTilesOnLine(Game game)
    {
        int row = readInt("Please first enter the row where you want to play your first tile : ");
        int col = readInt("Now enter the column where you want to play your first tile : ");
        int[] indexes = enterTiles();
        Direction dir = enterDirection();
        game.play(row, col, dir, indexes);
    }

    /**
     * Make the first move on the grid after askin the indexes of his tiles and the direction.
     *
     * @param game Game
     */
    private static void playFirst(Game game)
    {
        int[] indexes = enterTiles();
        Direction dir = enterDirection();
        game.first(dir, indexes);
    }

    /**
     * Ask the tiles the player want to play and try to make the move
     * @return array of int: the indexes of tiles.
     */
    private static int[] enterTiles()
    {
        int tiles = readInt("How many tiles do you want to play ?");
        int[] index = new int[tiles];
        int j = 0;
        for (int i = 0; i < tiles; i++) {
            while (index[j] < 0 || index[j] >= 6) {
                index[j] = readInt("Enter a correct index of the tile " + (i + 1) +
                        " from your hand that you want to play : ");
            }
            index[j] = readInt("Enter the index of the tile " + (i + 1) +
                    " from your hand that you want to play : ");
            j++;
        }
        return index;
    }

    /**
     * Enter the direction command you want to put your tiles
     * @return Direction
     */
    private static Direction enterDirection()
    {
        String direction = readString("Enter a direction you want to play the first tile (UP: u, DOWN: d, " +
                "LEFT: l and " + "RIGHT: r) : ").toLowerCase();

        Direction dir = whichDir(direction);

        while (dir == null) {
            direction = readString("Enter a valid direction please (UP: u, DOWN: d, LEFT: l and " +
                    "RIGHT: r) : ").toLowerCase();
            dir = whichDir(direction);
        }
        return dir;
    }


    /**
     * Check in the string received in parameter which direction the user chose to put his first tile
     * @param dir the user's command
     * @return null unknown direction
     */
    private static Direction whichDir(String dir)
    {
        return switch (dir.toLowerCase().charAt(0)) {
            case 'u' -> Direction.UP;
            case 'd' -> Direction.DOWN;
            case 'l' -> Direction.LEFT;
            case 'r' -> Direction.RIGHT;
            default -> null;
        };
    }
         /*

    /**
     * Ask the user a number of players to play the game
     * @return int the number of players
     */
    private static List<String> enterPlayers()
    {
        List<String> players = new ArrayList<>();
        String ask = "Please enter a number of players between 2 and 4 : ";
        int nbPLayers = readIntMinMax(ask, 2, 4);
        for (int p = 0; p < nbPLayers; p++) {
            if (p == 0) {
                String name = readString("Please enter a name for the first player : ");
                players.add(name);
            }
            else if (p == 1) {
                String name = readString("Please enter a name for the second player : ");
                players.add(name);
            }
            else if (p == 2) {
                String name = readString("Please enter a name for the third player : ");
                players.add(name);
            }
            else if (p == 3) {
                String name = readString("Please enter a name for the fourth player : ");
                players.add(name);
            }

        }
        return players;
    }

        private static String readString(String message) {
            Scanner clavier = new Scanner(System.in);
            System.out.println(message);
            while (!clavier.hasNextLine()) {
                clavier.next();
                System.out.println("Something is wrong");
                System.out.println(message);
            }
            return clavier.nextLine();
        }

    private static int readInt(String message) {
        Scanner clavier = new Scanner(System.in);
        System.out.println(message);
        while (!clavier.hasNextInt()) {
            clavier.next();
            System.out.println("Not a number");
            System.out.println(message);
        }
        return clavier.nextInt();
    }

    private static int readIntMinMax(String message, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Minimum is higher than maximum " + min + " " + max);
        }

        int entier = readInt(message);
        while (entier < min || entier > max) {
            System.out.println("Number of players must be included between " + min + " and " + max);
            entier = readInt(message);
        }
        return entier;
    }
}
