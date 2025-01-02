/**
 * Class representing the logic of the game.
 */
class Game {
    ConsoleInteractionUtils console = new ConsoleInteractionUtils();

    int playersNumber;

    Player[] players;

    Grid grid;


    Game() {
        playersNumber = setPlayersNumber();

        players = new Player[playersNumber];

        createPlayers(players);

        setGrid();
    }



    int setPlayersNumber() {
        System.out.println("Enter the number of players");
        return console.readIntegerInRange(Constants.MIN_PLAYERS_NUMBER, Constants.MAX_PLAYERS_NUMBER);
    }


    void createPlayers(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            System.out.print("Enter the name of player " + (i + 1) + ": ");
            
            players[i] = new Player(console.readStringAndEnsureIsNotEmptyOrWhiteSpaces());
        }
    }


    void setGrid() {
        int[] gridSizes = console.getGridDimensions();
        grid = new Grid(gridSizes[0], gridSizes[1]);
        grid.print();
    }



    
}
