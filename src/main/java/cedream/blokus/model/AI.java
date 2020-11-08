package cedream.blokus.model;

import cedream.blokus.Main;
import cedream.blokus.view.ExceptionView;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AI implements Strategy {

    /**
     * Get a list with pieces to play.
     * @param player
     * @return a list with pieces to play.
     */
    private List<Piece> getPiecesToPlay(Player player) {
        List<Piece> piecesToPlay = player.getPieces().stream().filter(e-> !e.isUse()).collect(Collectors.toList());
        Collections.shuffle(piecesToPlay);
        return piecesToPlay;
    }

    /**
     * Place a piece in the board.
     * @param game game instane
     * @param player player instance
     */
    private void play(Game game, Player player) {
        boolean piecePlaced = false;
        for (int col = 0; col < Board.getBOARD_SIZE(); col++) {
            for (int row = 0; row < Board.getBOARD_SIZE(); row++) {
                for (Piece piece : getPiecesToPlay(player)) {
                    try {
                        game.setCurrentPiece(piece);
                        game.placePiece(piece, row, col, player.isFirstAction());
                        piecePlaced = true;
                        break;
                    } catch (GameException e) {
                        continue;
                    }
                }
                if (col == Board.getBOARD_SIZE() - 1 && row == Board.getBOARD_SIZE() - 1 && !piecePlaced) {
                    game.removeCurrentPlayer();
                }
            }
        }
    }

    /**
     * Execute algo of the bot.
     * @param game game instance
     * @param player player instance
     */
    @Override
    public void execute(Game game, Player player) {
        try {
            play(game,player);
        } catch(GameException ex) {
            if (ExceptionView.showAlertEndGame(ex.getMessage()))
                Main.newGame();
            else
                Main.close();
        }
    }

}
