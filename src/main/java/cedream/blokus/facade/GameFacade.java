package cedream.blokus.facade;

import cedream.blokus.model.*;
import java.util.List;

public class GameFacade {

    private Game game;

    public GameFacade(Game game) {
        this.game = game;
    }

    /**
     * Get list of pieces of current player.
     * @return list of pieces of current player.
     */
    public List<Piece> getCurrentPlayerPieces() {
        return game.getCurrentPlayer().getPieces();
    }

    /**
     * Get color in format text of the current player.
     * @return color in format text of the current player
     */
    public String getCurrentPlayerColor() { return game.getCurrentPlayer().getPieceColor().getColorText(); }

    /**
     * Get list of players.
     * @return list of players
     */
    public List<Player> getPlayers() { return game.getPlayers(); }

    /**
     * Get current piece.
     * @return current piece
     */
    public Piece getCurrentPiece() { return game.getCurrentPiece(); }

    /**
     * Get first attribute of current player.
     * @return firstAction attribute of current player
     */
    public boolean getCurrentFirstAction() { return game.getCurrentPlayer().isFirstAction(); }

    /**
     * Get a save of board.
     * @return get a save of board
     */
    public Piece[][] getSaveBoard() { return game.getSaveBoard(); }

    public List<String> getHistorical() { return game.getHistorical(); }

    /**
     * Turn the current piece.
     */
    public void turnCurrentPiece() { game.turnCurrentPiece(); }

    /**
     * Return the current piece.
     */
    public void returnCurrentPiece() { game.returnCurrentPiece(); }

    /**
     * Convert the current player to a bot.
     */
    public void addBot() { game.addBot(); }

    /**
     * Method who place the piece on the board.
     * @param piece piece to place
     * @param row row where placed the piece
     * @param col column where placed the piece
     */
    public void setPieceOnBoard(Piece piece, int row, int col, boolean firstAction) {
        game.placePiece(piece, row, col, firstAction);
    }

    /**
     * Change current piece by the piece set in argument.
     * @param piece the new current piece.
     */
    public void setCurrentPiece(Piece piece) { game.setCurrentPiece(piece); }

    /**
     * Remove current player from the game.
     */
    public void removeCurrentPlayer() { game.removeCurrentPlayer(); }

    /**
     * Current player pass his lap.
     */
    public void passCurrentPlayer() { game.passCurrentPlayer(); }

}
