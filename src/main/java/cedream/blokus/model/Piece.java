package cedream.blokus.model;

import javafx.scene.paint.Color;

import java.util.Arrays;

/**
 * Class containing all the elements of a piece
 * @author Cédric Thonus (cedream)
 */
public class Piece {

    private final PieceType pieceType;
    private final PieceColor pieceColor;
    private boolean[][] pattern;
    private final Point point;
    private boolean use;
    private static final int PIECE_SIZE = 5;
    private static final int COUNT_OF_PIECES = 22;

    /**
     * Initializes piece type and piece color.
     * @param pieceType
     * @param pieceColor
     */
    Piece(PieceType pieceType, PieceColor pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
        pattern = pieceType.getPattern();
        point = new Point();
        use = false;
    }

    /**
     * Get type of piece.
     * @return type of piece.
     */
    public PieceType getPieceType() { return pieceType; }

    /**
     * Get color of piece.
     * @return color of piece.
     */
    public PieceColor getPieceColor() { return pieceColor; }

    /**
     * Get position of piece.
     * @return Point of piece
     */
    Point getPoint() { return point; }

    public boolean[][] getPattern() {
        boolean[][] res = new boolean[pattern.length][pattern[0].length];
        for (int i = 0; i < pattern.length; i++) {
            res[i] = Arrays.copyOf(pattern[i], pattern[i].length);
        }
        return res;
    }

    /**
     * set position.
     * @param row row where piece is
     * @param col column where piece is
     */
    void setPoint(int row, int col) {
        point.setRowCol(row, col);
    }

    /**
     * Get use attribute.
     * @return attribute use
     */
    public boolean isUse() { return use; }

    /**
     * Change the use attribute in true.
     */
    void use() { use = true; }

    /**
     * Get piece size.
     * @return piece size
     */
    public static int getPIECE_SIZE() { return PIECE_SIZE; }

    /**
     * Get count of pieces.
     * @return count of pieces
     */
    public static int getCOUNT_OF_PIECES() { return COUNT_OF_PIECES; }

    /**
     * Turn the piece a quarter turn to the right.
     */
    void turnRight() {
        boolean[][] res = new boolean[PIECE_SIZE][PIECE_SIZE];
        for (int i = 0; i < PIECE_SIZE; i++) {
            for (int j = 0; j < PIECE_SIZE; j++) {
                res[j][PIECE_SIZE - i - 1] = pattern[i][j];
            }
        }
        pattern = res;
    }

    /**
     * Return the piece.
     */
    void returnPiece() {
        boolean[][] res = new boolean[PIECE_SIZE][PIECE_SIZE];
        for (int i = 0; i < PIECE_SIZE; i++) {
            for (int j = 0; j < PIECE_SIZE; j++) {
                res[PIECE_SIZE - i - 1][j] = pattern[i][j];
            }
        }
        pattern = res;
    }

    /**
     * Convert pieceColor to javafx.scene.paint.Color.
     * @param pieceColor pieceColor to convert.
     * @return a javafx.scene.paint.Color
     */
    public static Color switchColor(PieceColor pieceColor) {
        Color color = Color.LIGHTGRAY;
        if (pieceColor == PieceColor.BLUE) {
            color = Color.BLUE;
        } else if (pieceColor == PieceColor.RED) {
            color = Color.RED;
        } else if (pieceColor == PieceColor.GREEN) {
            color = Color.GREEN;
        } else if (pieceColor == PieceColor.YELLOW) {
            color = Color.YELLOW;
        }
        return color;
    }

}
