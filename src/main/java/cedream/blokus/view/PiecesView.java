package cedream.blokus.view;

import cedream.blokus.facade.GameFacade;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

class PiecesView extends VBox {

    private final StockPlayerView player1;
    private final StockPlayerView player2;
    private final StockPlayerView player3;
    private final StockPlayerView player4;

    PiecesView(GameFacade gameFacade) {
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        player1 = new StockPlayerView(gameFacade.getPlayers().get(0), gameFacade);
        player2 = new StockPlayerView(gameFacade.getPlayers().get(1), gameFacade);
        player3 = new StockPlayerView(gameFacade.getPlayers().get(2), gameFacade);
        player4 = new StockPlayerView(gameFacade.getPlayers().get(3), gameFacade);
        getChildren().addAll(player1, player2, player3, player4);
    }

    void update() {
        player1.update();
        player2.update();
        player3.update();
        player4.update();
    }

}
