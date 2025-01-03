/**
 * Class representing the logic of the game.
 * 
 * @author Gianni Grasso
 * @author Andro Ibrahim
 * @version 2025.01.03
 */
class Game {

    ConsoleInteractionUtils console = new ConsoleInteractionUtils();

    Ansi cmd = new Ansi();

    int playersNumber;

    Player[] players;

    Grid grid;

    int takenCards;


    /**
     * Game constructor.
     */
    Game() {
        cmd.clearScreen();
        playersNumber = setPlayersNumber();
        players = new Player[playersNumber];

        createPlayers(players);
        
        setGrid();
    }


    /**
     * Function that starts the game.
     */
    void start() {
        boolean isFinished = false;
        int index = 0;
        while (!isFinished) {
            if (!turn(players[index])) {
                if (index == playersNumber - 1) {
                    index = 0;
                }else {
                    index++;
                }
            }

            if(grid.cardsNumber == takenCards) {
                isFinished = true;
                Player[] winners = declareWinners(players);

                console.printSuccess("The winner is:");
                for (int i = 0; i < winners.length; i++) {
                    System.out.print("Player: ");
                    winners[i].print();
                    System.out.println();
                    
                    System.out.print("Nr of collected card pairs: " + winners[i].score);
                }
            }
        }
    }


    /**
     * Determines the number of players.
     * 
     * @return the number of players
     */
    int setPlayersNumber() {
        console.printRequest("Enter the number of players");
        return console.readIntegerInRange(Constants.MIN_PLAYERS_NUMBER, Constants.MAX_PLAYERS_NUMBER);
    }

    /**
     * Creates the players.
     * 
     * @param players the array that will contain the players
     */
    void createPlayers(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            console.printRequest("Enter the name of player " + (i + 1) + ": ");
            
            players[i] = new Player(console.readStringAndEnsureIsNotEmptyOrWhiteSpaces(), Ansi.COLORS[i]);
        }
    }

    /**
     * Set grid parameters.
     */
    void setGrid() {
        int[] gridSizes = console.getGridDimensions();
        grid = new Grid(gridSizes[0], gridSizes[1]);

        grid.print();
    }

    /**
     * Single- turn logic.
     * 
     * @param player the player who takes the turn
     * @return the result of the shift (true = pair found)
     */
    boolean turn(Player player) {
        System.out.print("It's ");
        player.print();
        System.out.println("'s turn");

        Coordinates firstCell = chooseFirstCell(grid);
        Coordinates secondCell = chooseSecondCell(grid, firstCell);

        if(checkPair(firstCell, secondCell)) {
            console.printSuccess("Congratulations, you find a pair!");
            grid.cards[firstCell.row][firstCell.col] = null;
            grid.cards[secondCell.row][secondCell.col] = null;
            player.addPoint();
            takenCards += 2;
            grid.print();
            return true;
        } else {
            System.out.println("Sorry, the cards are not identical.");
            grid.cards[firstCell.row][firstCell.col].status = false;
            grid.cards[secondCell.row][secondCell.col].status = false;
            grid.print();
        }
        return false;
    }

    /**
     * Check if two cards are the same.
     * 
     * @param firstCell the coordinates of the first card
     * @param secondCell the coordinates of the second card
     * @return
     */
    boolean checkPair(Coordinates firstCell, Coordinates secondCell) {
        return grid.cards[firstCell.row][firstCell.col].symbol == grid.cards[secondCell.row][secondCell.col].symbol;
    }

    /**
     * Check if a cell is empty.
     * 
     * @param cellCoordinates the coordinates of the cell
     * @param grid the grid containing the cell
     * @return
     */
    boolean checkIfEmpty(Coordinates cellCoordinates, Grid grid) {
        if (grid.cards[cellCoordinates.row][cellCoordinates.col] == null) {
            return true;
        }
        return false;
    }

    /**
     * Check if two coordinates are the same.
     * 
     * @param firstCellCoordinates the first coordinates
     * @param secondCellCoordinates the second coordinates
     * @return true if coordinates are equals
     */
    boolean checkIfCoordinatesAreEquals(Coordinates firstCellCoordinates, Coordinates secondCellCoordinates) {
        if(firstCellCoordinates.row == secondCellCoordinates.row && firstCellCoordinates.col == secondCellCoordinates.col) {
            return true;
        }
        return false;
    }

    /**
     * Make the player choose the first card.
     * 
     * @param grid the grid containing the cells
     * @return the coordinates of the chosen card
     */
    Coordinates chooseFirstCell(Grid grid) {
        System.out.println("Enter the coordinates of the card you want to turn:");
        boolean correctInput = false;
        Coordinates cellCoordinates = new Coordinates(0, 0);
        while (!correctInput) {
            cellCoordinates = console.getCoordinate(grid.cards.length, grid.cards[0].length);
            if (checkIfEmpty(cellCoordinates, grid)) {
                console.printError("There is no card on the provided coordinates. Please enter new coordinates.");
            } else {
                correctInput = true;
                grid.cards[cellCoordinates.row][cellCoordinates.col].status = true;
                grid.print();
            }
        }
        return cellCoordinates;
    }

    /**
     * Make the player choose the second card.
     * 
     * @param grid the grid containing the cells
     * @param firstCellCoordinates the coordinates of the first card
     * @return the coordinates of the chosen card
     */
    Coordinates chooseSecondCell(Grid grid, Coordinates firstCellCoordinates) {
        System.out.println("Enter the coordinates of the second card you want to turn:");
        boolean correctInput = false;
        Coordinates secondCellCoordinates = new Coordinates(0, 0);
        while (!correctInput) {
            secondCellCoordinates = console.getCoordinate(grid.cards.length, grid.cards[0].length);
            if (checkIfEmpty(secondCellCoordinates, grid)) {
                console.printError("There is no card on the provided coordinates. Please enter new coordinates.");
            } else if (checkIfCoordinatesAreEquals(firstCellCoordinates, secondCellCoordinates)) {
                console.printError("The card in the specified position is already turned over.");
            }else {
                correctInput = true;
                grid.cards[secondCellCoordinates.row][secondCellCoordinates.col].status = true;
                grid.print();
            }
        }
        return secondCellCoordinates;
    }

    /**
     * Determines the winning player.
     * 
     * @param players the array of players
     * @return the array containing the winners of the game
     */
    Player[] declareWinners(Player[] players) {
        Player winner = players[0];
        int maxScore = -1;
        for (int i = 0; i < players.length; i++) {
            if (players[i].score > maxScore) {
                winner = players[i];
                maxScore = winner.score;
            }
        }

        
        int winnersCount = 0;
        for (int i = 0; i < players.length; i++) {
            if (players[i].score == maxScore) {
                winnersCount++;
            }
        }

        Player[] winners = new Player[winnersCount];
        int index = 0;
        for (int i = 0; i < players.length; i++) {
            if (players[i].score == maxScore) {
                winners[index] = players[i];
                index++;
            }
        }

        return winners;
    }
}
