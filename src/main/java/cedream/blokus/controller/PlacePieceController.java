package cedream.blokus.controller;

import cedream.blokus.facade.GameFacade;
import cedream.blokus.view.ExceptionView;
import cedream.blokus.model.GameException;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class PlacePieceController implements EventHandler<MouseEvent> {

    private final int row;
    private final int col;
    private final GameFacade gameFacade;

    public PlacePieceController(int row, int col, GameFacade gameFacade) {
        this.row = row;
        this.col = col;
        this.gameFacade = gameFacade;
    }

    @Override
    public void handle(MouseEvent e) {
        try {
            if (e.getButton() == MouseButton.PRIMARY)
            gameFacade.setPieceOnBoard(gameFacade.getCurrentPiece(), row, col, gameFacade.getCurrentFirstAction());
        } catch (GameException ex) {
            ExceptionView.showAlert(ex.getMessage());
        }
    }

}
