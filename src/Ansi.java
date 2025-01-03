/**
 * Class containing functions to interact with the terminal using ANSI.
 * 
 * ANSI are a set of escape codes used for formatting text in terminals.
 *
 * @author Gianni Grasso
 * @author Andro Ibrahim
 * @version 2025.01.02
 */
class Ansi {
    public static final String CLEAR = "\u001B[2J";
    public static final String CURSOR_UP = "\u001B[1A";
    public static final String RESET = "\u001B[0m";

    public static final String[] COLORS = {
        "\033[36m", //Cyan
        "\033[33m", //Yellow
        "\033[34m", //Blue
        "\033[32m", //Green
        "\033[31m", //Red
        "\033[35m", //Magenta
    };

    /**
     * Constants for formatting message text in terminal (see last lines of ConsoleInteractionUtils class).
     */
    public static final String BACKGROUND_ERROR = "\033[41m";
    public static final String BACKGROUND_REQUEST = "\u001B[44m";
    public static final String BACKGROUND_SUCCESS = "\u001B[42m";

    void cursorTo(int row, int col) {
        System.out.printf("\u001B[%d;%dH", row, col);
    }

    /**
     * Function to clear the screen (not yet used).
     */
    void clearScreen() {
        System.out.println(CLEAR);
        cursorTo(0, 0);
    }
}
