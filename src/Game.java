/**
 * Class representing the logic of the game.
 */
class Game {
    ConsoleInteractionUtils console = new ConsoleInteractionUtils();

    Ansi cmd = new Ansi();

    int playersNumber;

    Player[] players;

    Grid grid;


    Game() {
        cmd.clearScreen();
        playersNumber = setPlayersNumber();
        cmd.clearScreen();
        players = new Player[playersNumber];

        createPlayers(players);
        
        setGrid();

        turn(players[0]);
    }



    int setPlayersNumber() {
        System.out.println("Enter the number of players");
        return console.readIntegerInRange(Constants.MIN_PLAYERS_NUMBER, Constants.MAX_PLAYERS_NUMBER);
    }


    void createPlayers(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            System.out.print("Enter the name of player " + (i + 1) + ": ");
            
            players[i] = new Player(console.readStringAndEnsureIsNotEmptyOrWhiteSpaces(), Ansi.COLORS[i]);
            cmd.clearScreen();
        }
    }


    void setGrid() {
        int[] gridSizes = console.getGridDimensions();
        grid = new Grid(gridSizes[0], gridSizes[1]);
        cmd.clearScreen();
        grid.print();
    }


    boolean turn(Player player) {
        System.out.print("It's ");
        player.print();
        System.out.println("'s turn");

        Coordinates firstCell = chooseFirstCell(grid);
        Coordinates secondCell = chooseSecondCell(grid, firstCell);

        /**Coordinates firstCell = console.getCoordinate(grid.cards.length, grid.cards[0].length);
        checkIfEmpty(firstCell, grid);
        grid.cards[firstCell.row][firstCell.col].status = true;
        cmd.clearScreen();
        grid.print();

        Coordinates secondCell = console.getCoordinate(grid.cards.length, grid.cards[0].length);
        
        grid.cards[secondCell.row][secondCell.col].status = true;
        */
        cmd.clearScreen();
        grid.print();

        if(checkPair(firstCell, secondCell)) {
            System.out.println("Congratulations, you find a pair!");
            grid.cards[firstCell.row][firstCell.col] = null;
            grid.cards[secondCell.row][secondCell.col] = null;
            player.addPoint();
            return true;
        } else {
            System.out.println("Sorry, the cards are not identical.");
            grid.cards[firstCell.row][firstCell.col].status = false;
            grid.cards[secondCell.row][secondCell.col].status = false;
        }
        grid.print();
        return false;
    }

    boolean checkPair(Coordinates firstCell, Coordinates secondCell) {
        return grid.cards[firstCell.row][firstCell.col].symbol == grid.cards[secondCell.row][secondCell.col].symbol;
    }

    boolean checkIfEmpty(Coordinates cellCoordinates, Grid grid) {
        if (grid.cards[cellCoordinates.row][cellCoordinates.col] == null) {
            return true;
        }
        return false;
    }

    boolean checkIfCoordinatesAreEquals(Coordinates firstCellCoordinates, Coordinates secondCellCoordinates) {
        if(firstCellCoordinates.row == secondCellCoordinates.row && firstCellCoordinates.col == secondCellCoordinates.row) {
            return true;
        }
        return false;
    }

    Coordinates chooseFirstCell(Grid grid) {
        boolean correctInput = false;
        Coordinates cellCoordinates = new Coordinates(0, 0);
        while (!correctInput) {
            cellCoordinates = console.getCoordinate(grid.cards.length, grid.cards[0].length);
            if (checkIfEmpty(cellCoordinates, grid)) {
                System.out.println("There is no card on the provided coordinates. Please enter new coordinates.");
            } else {
                correctInput = true;
                grid.cards[cellCoordinates.row][cellCoordinates.col].status = true;
            }
        }
        return cellCoordinates;
    }

    Coordinates chooseSecondCell(Grid grid, Coordinates firstCellCoordinates) {
        boolean correctInput = false;
        Coordinates secondCellCoordinates = new Coordinates(0, 0);
        while (!correctInput) {
            secondCellCoordinates = console.getCoordinate(grid.cards.length, grid.cards[0].length);
            if (checkIfEmpty(secondCellCoordinates, grid)) {
                System.out.println("There is no card on the provided coordinates. Please enter new coordinates.");
            } else if (checkIfCoordinatesAreEquals(firstCellCoordinates, secondCellCoordinates)) {
                System.out.println("The card in the specified position is already turned over.");
            }else {
                correctInput = true;
                grid.cards[secondCellCoordinates.row][secondCellCoordinates.col].status = true;
            }
        }
        return secondCellCoordinates;
    }
    
}
