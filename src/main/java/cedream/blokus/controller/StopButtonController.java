package cedream.blokus.controller;

import cedream.blokus.Main;
import cedream.blokus.facade.GameFacade;
import cedream.blokus.model.GameException;
import cedream.blokus.view.ExceptionView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StopButtonController implements EventHandler<ActionEvent> {

    private GameFacade gameFacade;

    public StopButtonController(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    @Override
    public void handle(ActionEvent e) {
        try {
            gameFacade.removeCurrentPlayer();
        } catch (GameException ex) {
            if (ExceptionView.showAlertEndGame(ex.getMessage()))
                Main.newGame();
            else
                Main.close();
        }
    }

}
