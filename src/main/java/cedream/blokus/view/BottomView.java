package cedream.blokus.view;

import cedream.blokus.controller.*;
import cedream.blokus.facade.GameFacade;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

class BottomView extends HBox {

    private final Button newGame;
    private final Button next;
    private final Button stop;
    private final Button turnPiece;
    private final Button returnPiece;

    BottomView(GameFacade gameFacade) {
        setSpacing(10);
        newGame = new Button("Nouvelle partie");
        newGame.setOnAction(new NewGameButtonController());
        next = new Button("Je passe");
        next.setOnAction(new PassButtonController(gameFacade));
        stop = new Button("J'arrête");
        stop.setOnAction(new StopButtonController(gameFacade));
        turnPiece = new Button("Tourner");
        turnPiece.setOnAction(new TurnButtonController<>(gameFacade));
        returnPiece = new Button("Retourner");
        returnPiece.setOnAction(new ReturnButtonController(gameFacade));

        getChildren().addAll(newGame, next, stop, turnPiece, returnPiece);
    }

}
