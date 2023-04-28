package model;

/**
 * Color represents color of a tile
 */
public enum Color {
    BLUE("\033[34m"), RED("\033[31m"), GREEN("\033[32m"), ORANGE("\033[38;5;214m"),
    YELLOW("\033[38;5;227m"), PURPLE("\033[35m");

    private String colorCode;

    /**
     * Constructor for Color enum
     * @param colorCode the ansi code of the color
     */
    private Color(String colorCode)
    {
        this.colorCode = colorCode;
    }

    /**
     * Getter for colorCode of a color
     * @return String color code
     */
    public String getColorCode()
    {
        return colorCode;
    }
}
