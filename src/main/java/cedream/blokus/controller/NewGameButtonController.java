package cedream.blokus.controller;

import cedream.blokus.Main;
import cedream.blokus.view.ExceptionView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class NewGameButtonController implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        if (ExceptionView.showAlertEndGame())
            Main.newGame();
    }
}
