package cedream.blokus.controller;

import cedream.blokus.facade.GameFacade;
import cedream.blokus.view.ExceptionView;
import cedream.blokus.model.Piece;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class SelectPieceController implements EventHandler<MouseEvent> {

    private Piece piece;
    private GameFacade gameFacade;

    public SelectPieceController(Piece piece, GameFacade gameFacade) {
        this.piece = piece;
        this.gameFacade = gameFacade;
    }

    @Override
    public void handle(MouseEvent e) {
        try {
            if (e.getButton() == MouseButton.PRIMARY) gameFacade.setCurrentPiece(piece);
            if (e.getTarget() instanceof Pane && ((Pane) e.getTarget()).getParent() instanceof GridPane) {
                GridPane grid = ((GridPane) ((Pane) e.getTarget()).getParent());
                FadeTransition ft = new FadeTransition(Duration.millis(1000), grid);
                ft.setFromValue(0.0);
                ft.setToValue(1.0);
                ft.play();
            }
        } catch (Exception ex) {
            ExceptionView.showAlert(ex.getMessage());
        }
    }

}
