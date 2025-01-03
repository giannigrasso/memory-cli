/**
 * Class representing a player.
 * 
 * @author Gianni Grasso
 * @author Andro Ibrahim
 * @version 2025.01.03
 */
class Player {
    /**
     * The player's username.
     */
    String username;

    /**
     * The number of pairs collected by the player.
     */
    int score;

    /**
     * The color of the player.
     */
    String color;    


    /**
     * Player's constructor.
     * 
     * @param username the player's username
     */
    Player(String username, String color) {
        this.username = username;
        this.color = color;
    }

    /**
     * Increases the player's score.
     */
    void addPoint() {
        score++;
    }

    /**
     * Print the player's name.
     */
    void print() {
        System.out.print(color + username + Ansi.RESET);
    }
}