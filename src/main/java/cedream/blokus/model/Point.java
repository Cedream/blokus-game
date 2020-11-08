package cedream.blokus.model;

/**
 * Class containing all the elements of point.
 */
class Point {

    private int row;
    private int col;

    /**
     * Initializes row and column.
     * @param row row of piece
     * @param col column of piece
     */
    Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Initialize constructor.
     */
    Point() { this(0,0); }

    /**
     * Get row.
     * @return row.
     */
    int getRow() { return row; }

    /**
     * Get col.
     * @return col;
     */
    int getCol() { return col; }

    /**
     * Set row and col.
     * @param row
     * @param col
     */
    void setRowCol(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
