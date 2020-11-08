package cedream.blokus.model;

import java.util.*;
/**
 * Class containing all the elements of game logic.
 */
public class Game extends Observable {

    private final List<Player> players;
    private final Board board;
    private Player currentPlayer;
    private Piece currentPiece;
    private List<String> historical;

    /**
     * Initializes the list of players, set current player and board.
     */
    public Game() {
        players = new ArrayList<>();
        Arrays.stream(PieceColor.values()).forEach(e->players.add(new Player(e)));
        currentPlayer = players.get(0);
        board = new Board();
        historical = new ArrayList<>();
    }

    /**
     * Get list of players.
     * @return list of players
     */
    public List<Player> getPlayers() {
        List<Player> res = new ArrayList<>();
        players.forEach(e-> {
            try {
                res.add((Player)e.clone());
            } catch (CloneNotSupportedException e1) {
                e1.printStackTrace();
            }
        });
        return res;
    }

    /**
     * Get current player.
     * @return current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Get current piece.
     * @return current piece
     */
    public Piece getCurrentPiece() { return currentPiece; }

    /**
     * Get a save of board.
     * @return a save of board
     */
    public Piece[][] getSaveBoard() { return board.getSaveBoard(); }

    /**
     * Get list with historical.
     * @return list with historical
     */
    public List<String> getHistorical() { return historical; }

    /**
     * Change the current player.
     */
    private void setCurrentPlayer(Player nextPlayer) {
        if (currentPlayer.getPieces().stream().allMatch(Piece::isUse)) {
            currentPlayer.stopPlay();
        }
        if (isOver())
            throw new GameException("Fin du jeu.\n\n"
                    + getLeaderboard());
        currentPlayer = nextPlayer;
        currentPiece = null;
        notifyView();
    }

    /**
     * Change current piece by the piece set in argument.
     * @param piece the new current piece.
     */
    public void setCurrentPiece(Piece piece) {
        if (piece != null && currentPlayer.getPieceColor() != piece.getPieceColor()) {
            throw new GameException("C'est au joueur "
                    + currentPlayer.getPieceColor().getColorText().toLowerCase()
                    + " de jouer !");
        }
        currentPiece = piece;
    }

    /**
     * Check if the current piece is null and throw a Game exception.
     */
    private void checkCurrentPieceIsNull() {
        if (Objects.isNull(currentPiece)) throw new GameException("Aucune piece sélectionnee.");
    }

    /**
     * turn current piece half a turn to the right.
     */
    public void turnCurrentPiece() {
        checkCurrentPieceIsNull();
        currentPiece.turnRight();
        notifyView();
    }

    /**
     * return the piece as a mirror.
     */
    public void returnCurrentPiece() {
        checkCurrentPieceIsNull();
        currentPiece.returnPiece();
        notifyView();
    }

    /**
     * Change the current player to a bot.
     */
    public void addBot() {
        currentPlayer.convertToBot();
        setCurrentPlayer(getNextPlayer(currentPlayer));
    }

    /**
     * Method who place the piece on the board.
     * @param piece piece to place
     * @param row row where placed the piece
     * @param col column where placed the piece
     */
    public void placePiece(Piece piece, int row, int col, boolean firstAction) {
        checkCurrentPieceIsNull();
        board.placePiece(piece, row, col, firstAction);
        piece.use();
        currentPlayer.firstAction();
        currentPlayer.incrementScore();
        historical.add("Joueur " + currentPlayer.getPieceColor().getColorText() + " Pièce "
                + currentPiece.getPieceType().ordinal());
        setCurrentPiece(null);
        whoPlay();
    }

    /**
     * Remove current player from the game.
     */
    public void removeCurrentPlayer() {
        currentPlayer.stopPlay();
        historical.add("Joueur " + currentPlayer.getPieceColor().getColorText() + " a arrêté la partie");
        whoPlay();
    }

    /**
     * Current player pass his lap.
     */
    public void passCurrentPlayer() {
        historical.add("Joueur " + currentPlayer.getPieceColor().getColorText() + " à passé son tour");
        whoPlay();
    }

    /**
     * Check the player or bot playing next turn.
     */
    void whoPlay() {
        Player nextPlayer = getNextPlayer(currentPlayer);
        if (nextPlayer.isBot() && nextPlayer.isCanPlay()) {
            setCurrentPlayer(nextPlayer);
            nextPlayer.getStrategy().execute(this, nextPlayer);
        } else {
            setCurrentPlayer(nextPlayer);
        }
    }

    /**
     * Get next player.
     * @param player current player
     * @return next player
     */
    private Player getNextPlayer(Player player) {
        Player nextPlayer;
        int index = players.indexOf(player);
        if (index < players.size()-1) {
            if(!players.get(index+1).isCanPlay()) {
                nextPlayer = getNextPlayer(players.get(index+1));
            }else {
                nextPlayer = players.get(index+1);
            }
        } else {
            if (!players.get(0).isCanPlay()) nextPlayer = getNextPlayer(players.get(0));
            else nextPlayer = players.get(0);
        }
        return nextPlayer;
    }

    /**
     * Determine if the game is over.
     * @return true if the game is over, false else.
     */
    private boolean isOver() {
        return players.stream().filter(Player::isCanPlay).count() <= 1;
    }

    /**
     * Get leaderboard in string.
     * @return leader board in string format
     */
    private String getLeaderboard() {
        StringBuilder sb = new StringBuilder();
        sb.append("Scores:\n");
        players.forEach(e-> sb.append("Joueur ").append(e.getPieceColor().getColorText()).append(" : ")
                .append(e.getScore()).append("\n"));
        return sb.toString();
    }

    /**
     * Notify view that the model has changed.
     */
    private void notifyView() {
        setChanged();
        notifyObservers();
    }
}
