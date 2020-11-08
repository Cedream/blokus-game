package cedream.blokus.controller;

import cedream.blokus.model.Game;

import java.util.Observer;

public class GameController {

     private static Game game;

    public GameController(Game game) {
        GameController.game = game;
    }

    public static void addObserver(Observer o) {
        game.addObserver(o);
    }

}
