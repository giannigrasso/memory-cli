/**
 * Class representing the position of a cell in the grid.
 * 
 * @author Gianni Grasso
 * @author Andro Ibrahim
 * @version 2025.01.03
 */
class Coordinates {
    /**
     * The coordinate row.
     */
    int row;

    /**
     * The coordinate column.
     */
    int col;
    
    /**
     * Coordinates constructor.
     * 
     * @param row the coordinate row
     * @param col the coordinate column
     */
    Coordinates(int row, int col) {
        if (row >= 0 && col >= 0) {
            this.row = row;
            this.col = col;
        }
    }
}