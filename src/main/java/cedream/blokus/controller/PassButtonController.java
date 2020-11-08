package cedream.blokus.controller;

import cedream.blokus.facade.GameFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PassButtonController implements EventHandler<ActionEvent> {

    private GameFacade gameFacade;

    public PassButtonController(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    @Override
    public void handle(ActionEvent event) {
        gameFacade.passCurrentPlayer();
    }
}
