/**
 * Class containing functions to interact with the terminal using ANSI.
 *
 * @author Gianni Grasso
 * @author Andro Ibrahim
 * @version 2025.01.02
 */
class Ansi {
    public static final String CLEAR = "\u001B[2J";
    public static final String CLEAR_LINE = "\u001B[2K";
    public static final String CURSOR_UP = "\u001B[1A";

    public static final String RESET = "\u001B[0m";
    public static final String[] COLORS = {
        "\033[34m", //Blue
        "\033[32m", //Green
        "\033[33m", //Yellow
        "\033[36m", //Cyan
        "\033[31m", //Red
        "\033[35m", //Magenta
    };
    

    void cursorTo(int row, int col) {
        System.out.printf("\u001B[%d;%dH", row, col);
    }

    void clearScreen() {
        System.out.println(CLEAR);
        cursorTo(0, 0);
    }

    void clearLine() {
        System.out.print(CLEAR_LINE);
        System.out.print('\r');
    }

    void clearLines(int n) {
        for (int i = 0; i < n; i++) {
            clearLine();
            System.out.print(CURSOR_UP);
        }
    }
}
