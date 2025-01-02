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

        for (int i = 0; i < players.length; i++) {
            players[i].print();
        }
    }



    int setPlayersNumber() {
        System.out.println("Enter the number of players");
        return console.readIntegerInRange(Constants.MIN_PLAYERS_NUMBER, Constants.MAX_PLAYERS_NUMBER);
    }


    void createPlayers(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            System.out.print("Enter the name of player " + (i + 1) + ": ");
            
            players[i] = new Player(console.readStringAndEnsureIsNotEmptyOrWhiteSpaces(), cmd.COLORS[i]);
            cmd.clearScreen();
        }
    }


    void setGrid() {
        int[] gridSizes = console.getGridDimensions();
        grid = new Grid(gridSizes[0], gridSizes[1]);
        cmd.clearScreen();
        grid.print();
    }
    
}
