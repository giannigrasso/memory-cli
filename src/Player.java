/**
 * Class representing a player.
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
     * Player's constructor.
     * 
     * @param username the player's username
     */
    public Player(String username) {
        this.username = username;
    }
}