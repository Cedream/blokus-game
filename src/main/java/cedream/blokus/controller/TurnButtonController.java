package cedream.blokus.controller;

import cedream.blokus.facade.GameFacade;
import cedream.blokus.model.GameException;
import cedream.blokus.view.ExceptionView;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Objects;


public class TurnButtonController<T extends Event> implements EventHandler<T> {

    private GridPane grid;
    private GameFacade gameFacade;

    public TurnButtonController(GridPane grid, GameFacade gameFacade) {
        this(gameFacade);
        this.grid = grid;
    }

    public TurnButtonController(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    @Override
    public void handle(T e) {
        try {
            if (e.toString().contains("SECONDARY") || e.getEventType() == ActionEvent.ACTION) {
                gameFacade.turnCurrentPiece();
                if (e.getTarget() instanceof Pane) {
                    Integer row = GridPane.getRowIndex((Pane) e.getTarget());
                    Integer col = GridPane.getColumnIndex((Pane) e.getTarget());
                    if (!Objects.isNull(row) && !Objects.isNull(col))
                        MovingPieceController.coloredPlaceOfPiece(row, col, gameFacade, grid, Color.GREY);
                }
            }
        }catch (GameException ex) {
            ExceptionView.showAlert(ex.getMessage());
        }
    }
}
