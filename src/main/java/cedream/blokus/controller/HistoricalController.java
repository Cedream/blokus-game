package cedream.blokus.controller;

import cedream.blokus.facade.GameFacade;
import cedream.blokus.view.HistoricalView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HistoricalController implements EventHandler<ActionEvent> {

    private GameFacade gameFacade;

    public HistoricalController(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    @Override
    public void handle(ActionEvent event) {
        HistoricalView historicalView = new HistoricalView(gameFacade);
        GameController.addObserver(historicalView);
        Scene scene = new Scene(historicalView, 300, 300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Historique des actions");
        stage.show();
    }
}
