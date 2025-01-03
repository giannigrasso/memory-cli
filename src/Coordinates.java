/**
 * Class representing the position of a cell in the grid.
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