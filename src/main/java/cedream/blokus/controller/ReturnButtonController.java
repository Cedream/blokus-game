package cedream.blokus.controller;

import cedream.blokus.facade.GameFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ReturnButtonController implements EventHandler<ActionEvent> {

    private GameFacade gameFacade;

    public ReturnButtonController(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    @Override
    public void handle(ActionEvent event) {
        gameFacade.returnCurrentPiece();
    }
}
