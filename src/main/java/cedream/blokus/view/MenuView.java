package cedream.blokus.view;

import cedream.blokus.controller.HistoricalController;
import cedream.blokus.facade.GameFacade;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

class MenuView extends MenuBar {

    MenuView(GameFacade gameFacade) {
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu help = new Menu("Help");

        MenuItem historical = new MenuItem("Historique");
        historical.setOnAction(new HistoricalController(gameFacade));
        edit.getItems().add(historical);
        getMenus().addAll(file, edit, help);
    }

}
