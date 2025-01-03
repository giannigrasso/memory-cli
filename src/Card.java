/**
 * Class representing a playing card.
 * 
 * @author Gianni Grasso
 * @author Andro Ibrahim
 * @version 2025.01.03
 */
class Card {
    /**
     * The card symbol.
     */
    char symbol;

    /**
     * The state of the card (true = flipped)
     */
    boolean status;

    
    /**
     * Card's constructor.
     * 
     * @param symbol the card symbol
     */
    Card(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Print the card.
     */
    void print() {
        if (status) {
            System.out.print(" " + symbol + " ");
        } else {
            System.out.print(" " + Constants.BACK + " ");
        }
    }
}
