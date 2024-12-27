/**
 * Class representing the game grid.
 */
class Grid {
    /**
     * The game grid.
     */
    Card[][] grid;

    Grid(int height, int width) {
        /*
         * Printable unicode characters are 95.
         * We use:
         * - the space (decimal 32) to indicate empty cell,
         * - '!' (decimal 33) the back of a card.
         * This leaves 93 characters available.
         * As each character is used for two cards, we have 186 possible cards.
         * Sqrt(186) = 13.64, so maximum square grid is 13 x 13.
         */
        if (height <= 0 || width <= 0 || height * width > Constants.MAX_NR_CELLS || height * width % 2 != 0) {
            height = 13;
            width = 13;
        }

        Card[] pairs = generatePairs(height, width);
        Card[] shuffledPairs = shuffle(pairs);
        this.grid = generateGrid(shuffledPairs, height, width);
    }

    /**
     * Creates character pairs used by the grid.
     * 
     * @param height the height of the grid
     * @param width the width of the grid
     * @return the array containing the pairs of the grid
     */
    Card[] generatePairs(int height, int width) {
        int totalPairs = height * width;
        Card pairs[] = new Card[totalPairs];

        for (int i = 0; i < totalPairs / 2; i++) {
            int randomInt = (int) ((Constants.MAX_CHAR + 1 - Constants.MIN_CHAR) * Math.random() + Constants.MIN_CHAR);
            char randomChar = (char) randomInt;

            pairs[2 * i] = new Card(randomChar);
            pairs[2 * i + 1] = new Card(randomChar);
        }

        return pairs;
    }

    /**
     * Mixes the array using the Fisher-Yates method. 
     * 
     * @param array the array to be shuffled
     * @return the shuffled one-dimensional array
     */
    Card[] shuffle(Card[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int randomIndex = (int) ((i + 1) * Math.random());

            Card tmp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = tmp;
        }

        return array;
    }

    Card[][] generateGrid(Card[] array, int height, int width) {
        Card[][] grid = new Card[height][width];

        int index = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = array[index++];
            }
        }

        return grid;
    }


    void print() {
        // Print indices for columns
        System.out.print("    "); // initial space for row indices
        for (int i = 0; i < grid[0].length; i++) {
            System.out.printf(" %2d ", i);
        }
        System.out.println();

        // Print grid content
        for (int i = 0; i < grid.length; i++) {
            // Print top border for row
            System.out.print("    "); // initial space for row indices
            for (int j = 0; j < grid[i].length * 4 + 1; j++) {
                System.out.print("-");
            }
            System.out.println();

            // Print row index
            System.out.printf(" %2d |", i);

            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != null) {
                    grid[i][j].print();
                } else {
                    System.out.print("   ");
                }
                System.out.print("|");
            }
            System.out.println();
        }

        // Print bottom border for last row
        System.out.print("    "); // initial space for row indices
        for (int i = 0; i < grid[0].length * 4 + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
