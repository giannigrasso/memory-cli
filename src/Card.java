/**
 * Class representing a playing card.
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
     * The description of a card.
     */
    void print() {
        System.out.print(" " + symbol + " ");
    }
}
