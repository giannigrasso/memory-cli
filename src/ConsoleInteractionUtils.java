import java.util.Scanner;

class ConsoleInteractionUtils {
    Scanner scanner = new Scanner(System.in);
    Ansi cmd = new Ansi();

    int readIntegerInRange(int min, int max) {
        int input = 0;
        boolean correctInput = false;
        while (!correctInput) {
            System.out.println("Please enter a number between " + min + " and " + max + ":");
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input < min || input > max) {
                    //cmd.clearScreen();
                    printError("Error: number not in range.");
                }
                else
                    correctInput = true;
            } else {
                //cmd.clearScreen();
                printError("Error: input is not a number.");
                emptyTheScanner();
            }
        }
        emptyTheScanner();
        return input;
    }

    String readStringAndEnsureIsNotEmptyOrWhiteSpaces() {
        String input = "";
        boolean correctInput = false;
        while (!correctInput) {
            System.out.println("Please enter a string:");
            input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                //cmd.clearLines(1);
                printError("Error: string is empty or contains only white spaces.");
            } else {
                correctInput = true;
            }
        }
        return input;
    }

    boolean isValidGridDimensions(int height, int width) {
        return height > 0 && width > 0 && height * width <= Constants.MAX_NR_CELLS && height * width % 2 == 0;
    }

    int[] getGridDimensions() {
        int height = 0;
        int width = 0;
        boolean isValidInputs = false;

        String message = """
                Insert height and width of the grid such that:
                - height > 0,
                - width > 0,
                - height * width < %d
                - height * width %% 2 == 0
                """.formatted(Constants.MAX_NR_CELLS);

        System.out.println(message);
        do {
            printRequest("Insert height: ");
            height = readIntegerInRange(1, Constants.MAX_NR_CELLS);

            printRequest("Insert width: ");
            width = readIntegerInRange(1, Constants.MAX_NR_CELLS);

            isValidInputs = isValidGridDimensions(height, width);

            if (!isValidInputs) {
                printError("One or more constraints are not met.");
            }

        } while (!isValidInputs);
        return new int[] { height, width };
    }

    Coordinates getCoordinate(int gridHeight, int gridWidth) {
        String message = String.format("""
                Insert row and column of the coordinate such that:
                - row >= 0,
                - row < %d,
                - col >= 0,
                - col < %d
                """, gridHeight, gridWidth);

        System.out.println(message);
        printRequest("Insert row: ");
        int row = readIntegerInRange(0, gridHeight - 1);

        printRequest("Insert column: ");
        int col = readIntegerInRange(0, gridWidth - 1);

        return new Coordinates(row, col);
    }

    void closeScanner() {
        scanner.close();
    }

    void emptyTheScanner() {
        scanner.nextLine();
    }

    void printError(String msg) {
        System.out.println(Ansi.BACKGROUND_ERROR + msg + Ansi.RESET);
    }

    void printRequest(String msg) {
        System.out.println(Ansi.BACKGROUND_REQUEST + msg + Ansi.RESET);
    }

    void printSuccess(String msg) {
        System.out.println(Ansi.BACKGROUND_SUCCESS + msg + Ansi.RESET);
    }
}