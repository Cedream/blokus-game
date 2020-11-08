package cedream.blokus.view;

import cedream.blokus.facade.GameFacade;
import javafx.scene.control.ChoiceDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class PopupBotView extends ChoiceDialog<String> {

    private List<String> choices;

    PopupBotView(GameFacade gameFacade) {
        choices = new ArrayList<>();
        for(int i = 0; i < 4; i++)
            choices.add(i+"");
        setSelectedItem("0");
        getItems().addAll(choices);
        setTitle("Choisisez le nombre de bot");
        setHeaderText(null);
        setContentText("Choisisez le nombre de bot a ajouter dans la partie");
        Optional<String> result = showAndWait();
        if (result.isPresent()) {
            for (int i = 0; i < Integer.parseInt(result.get()); i++) {
                gameFacade.addBot();
            }
        }
    }

}
