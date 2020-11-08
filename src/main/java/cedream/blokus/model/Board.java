package cedream.blokus.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Class containing all the elements of board.
 * @author Cédric Thonus (cedream)
 */
public class Board {

    private final List<Piece> board;
    private static final int BOARD_SIZE = 20;
    private Piece[][] saveBoard;

    /**
     * initializes the list of pieces.
     */
    Board() {
        board = new LinkedList<>();
        saveBoard = generateBoard();
    }

    /**
     * Get constante BOARD_SIZE.
     * @return board size
     */
    public static int getBOARD_SIZE() { return BOARD_SIZE; }

    /**
     * Get board of pieces.
     * @return list of pieces
     */
    List<Piece> getBoard() { return board; }

    /**
     * Get a save of a generate board (array 2d).
     * @return a save of board (array 2d)
     */
    Piece[][] getSaveBoard() { return saveBoard; }

    /**
     * Method who place a piece on the board.
     * @param piece piece to place
     * @param row row where placed the piece
     * @param col column where placed the piece
     * @throws GameException if piece isn't in the board
     */
    void placePiece(Piece piece, int row, int col, boolean firstAction) throws GameException {
        checkIsInBoard(piece ,row, col);
        if (firstAction) checkIsCorner(piece, row, col);
        checkIsAdjacent(piece, row, col, firstAction);
        piece.setPoint(row, col);
        board.add(piece);
        saveBoard = generateBoard();
    }

    /**
     * Get a 2D array with pieces inside.
     * @return a 2D array
     */
    private Piece[][] generateBoard() {
        Piece[][] boardTab = new Piece[BOARD_SIZE][BOARD_SIZE];
        for(Piece piece: board) {
            for(int i = 0; i < Piece.getPIECE_SIZE();i++) {
                for(int j = 0; j < Piece.getPIECE_SIZE(); j++) {
                    if (piece.getPattern()[i][j]) {
                        boardTab[piece.getPoint().getRow() + i - 2][piece.getPoint().getCol() + j - 2] = piece;
                    }
                }
            }
        }
        return boardTab;
    }

    /**
     * Check if the piece is in a corner of the board.
     * @param piece
     * @param row
     * @param col
     */
    private void checkIsCorner(Piece piece, int row, int col) {
        piece.setPoint(row, col);
        board.add(piece);
        Piece[][] board = generateBoard();
        this.board.remove(piece);
        if (board[0][0] != piece && board[0][BOARD_SIZE-1] != piece
            && board[BOARD_SIZE-1][0] != piece && board[BOARD_SIZE-1][BOARD_SIZE-1] != piece)
            throw new GameException("La piece doit etre placee dans un coin du plateau");
    }

    /**
     * Check if the piece is correctly placed in relation to others pieces of the same colors.
     * @param piece piece to check
     * @param row row of the piece
     * @param col column of the piece
     * @param firstAction determine if the first piece of color placed on the board
     */
    private void checkIsAdjacent(Piece piece, int row, int col, boolean firstAction) {
        int rowInBoard = row-2, colInBoard = col-2;
        boolean adjacent = false, side = false;
        for (int i = 0; i < Piece.getPIECE_SIZE(); i++) {
            for (int j = 0; j < Piece.getPIECE_SIZE(); j++) {
                if (piece.getPattern()[i][j]) {
                    if (isBusy(rowInBoard, colInBoard))
                        throw new GameException("Il y a deja une piece occupant une partie " +
                                "ou la totalite de la zone grisee");
                    if (isAdjacent(rowInBoard + 1, colInBoard + 1, piece)
                            || isAdjacent(rowInBoard - 1, colInBoard - 1, piece)
                            || isAdjacent(rowInBoard - 1, colInBoard + 1, piece)
                            || isAdjacent(rowInBoard + 1, colInBoard - 1, piece)) {
                        adjacent = true;
                    }
                    if (isAdjacent(rowInBoard - 1, colInBoard, piece)
                            || isAdjacent(rowInBoard, colInBoard - 1, piece)
                            || isAdjacent(rowInBoard + 1, colInBoard, piece)
                            || isAdjacent(rowInBoard, colInBoard + 1, piece)) {
                        side = true;
                    }
                }
                colInBoard++;
            }
            colInBoard = col-2;
            rowInBoard++;
        }
        if(!firstAction) {
            if (!adjacent && !side || !adjacent || side)
                throw new GameException("La piece doit collee le coin d'une piece de meme couleur");
        }
    }

    /**
     * Check if the piece is entirely inside the board.
     * @param piece piece to check
     * @param row row of the piece
     * @param col column of the piece
     */
    private void checkIsInBoard(Piece piece, int row, int col) {
        for(int i = 0; i < Piece.getPIECE_SIZE();i++){
            for(int j = 0; j < Piece.getPIECE_SIZE(); j++) {
                if (!isInOfBoard(row ,col ,i ,j)
                        && piece.getPattern()[i][j]) {
                    throw new GameException("L'entierete de la piece doit se trouver sur le plateau");
                }
            }
        }
    }

    /**
     * Check if the position placed in argument is free.
     * @param row
     * @param col
     * @return true if the position is free, false else.
     */
    private boolean isBusy(int row, int col) {
        return correctRowCol(row, col) || saveBoard[row][col] != null;
    }

    /**
     * Check if the position is inside of the board.
     * @param row
     * @param col
     * @return true if the position is inside of the board, false else.
     */
    private boolean correctRowCol(int row, int col) {
        return row < 0 || col < 0 || row >= BOARD_SIZE || col >= BOARD_SIZE;
    }

    /**
     * Check if the piece is correctly glued to another piece of the same color
     * @param row row of the piece
     * @param col row of the piece
     * @param piece piece to check
     * @return true if the piece is correctly glued, false else.
     */
    private boolean isAdjacent(int row, int col, Piece piece) {
        return !correctRowCol(row, col)
                && (saveBoard[row][col] != null
                && saveBoard[row][col].getPieceColor() == piece.getPieceColor());
    }

    /**
     * Check if the whole of the piece is inside the board.
     * @param row row of the piece (center of the piece)
     * @param col col of the piece (center of the piece)
     * @param i position (row) in the board where placed the piece.
     * @param j position (col) in the board where placed the piece.
     * @return true if the whole of the piece is inside the board.
     */
    private boolean isInOfBoard(int row, int col, int i, int j) {
        return (((row + i - 2) >= 0)
                && ((col + j - 2) >= 0)
                && ((row + i - 2) < BOARD_SIZE)
                && ((col + j - 2) < BOARD_SIZE));
    }

}
