package cedream.blokus.view;

import cedream.blokus.facade.GameFacade;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Observable;
import java.util.Observer;

public class HistoricalView extends Pane implements Observer {

    private final GameFacade gameFacade;
    private final ScrollPane scrollPane;
    private final VBox container;

    public HistoricalView(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
        scrollPane = new ScrollPane();
        container = new VBox();
        scrollPane.setContent(container);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setMinWidth(300);
        scrollPane.setMinHeight(300);
        scrollPane.setMaxHeight(300);
        getChildren().add(scrollPane);
        refreshHistorical();
    }

    private void refreshHistorical() {
        gameFacade.getHistorical().forEach(e->container.getChildren().add(new Label(e)));
    }

    @Override
    public void update(Observable o, Object arg) {
        refreshHistorical();
    }
}
