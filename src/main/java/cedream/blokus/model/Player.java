package cedream.blokus.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Class containing all the elements of Player.
 * @author Cédric Thonus (cedream)
 */
public class Player implements Cloneable {

    private final PieceColor pieceColor;
    private final List<Piece> pieces;
    private Strategy strategy;
    private boolean bot;
    private boolean canPlay;
    private int score;
    private boolean firstAction;

    /**
     * Initializes color of player and so his pieces and score of player and his pieces.
     * @param pieceColor color of piece.
     */
    Player(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
        pieces = new ArrayList<>();
        strategy = new AI();
        bot = false;
        canPlay = true;
        score = 0;
        firstAction = true;
        Arrays.stream(PieceType.values()).forEach(pieceType->pieces.add(new Piece(pieceType, pieceColor)));
    }

    /**
     * Get color of piece.
     * @return pieceColor
     */
    public PieceColor getPieceColor() { return pieceColor; }

    /**
     * Get list of pieces.
     * @return list of pieces
     */
    public List<Piece> getPieces() { return pieces; }

    /**
     * Get strategy attribute.
     * @return strategy attribute
     */
    Strategy getStrategy() { return strategy; }

    /**
     * Get attribute bot.
     * @return attribute bot
     */
    public boolean isBot() { return bot; }

    /**
     * Get attribute canPlay.
     * @return attribute canPlay
     */
    public boolean isCanPlay() { return canPlay; }

    /**
     * Get score of player.
     * @return score
     */
    public int getScore() { return score; }

    /**
     * Get attribute firstAction.
     * @return attribute firstAction
     */
    public boolean isFirstAction() { return firstAction; }

    public int countOfPieces() {
        return (int) pieces.stream().filter(e-> !e.isUse()).count();
    }

    /**
     * Change canPlay attribute to false.
     */
    void stopPlay() { canPlay = false; }

    /**
     * Change firstAction attribute to false.
     */
    void firstAction() { firstAction = false; }

    /**
     * Change bot attribute to true.
     */
    void convertToBot() { bot = true; }

    /**
     * Increment the score attribute.
     */
    void incrementScore() { score++; }

    /**
     * Get a clone of the player
     * @return a clone of the player
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(pieceColor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Player && ((Player) obj).getPieceColor() == this.getPieceColor());
    }

}
