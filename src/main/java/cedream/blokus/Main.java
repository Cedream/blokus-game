package cedream.blokus;

import cedream.blokus.controller.GameController;
import cedream.blokus.facade.GameFacade;
import cedream.blokus.model.Game;
import cedream.blokus.view.FXView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class.
 * @author Cédric Thonus (cedream)
 */

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;
        newGame();
    }

    public static void newGame() {
        Game game = new Game();
        GameFacade gameFacade = new GameFacade(game);
        FXView fxView = new FXView(gameFacade);
        GameController gameController = new GameController(game);
        GameController.addObserver(fxView);

        primaryStage.setTitle("blokus");
        primaryStage.setScene(new Scene(fxView, 1000, 750));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void close() {
        Main.primaryStage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

