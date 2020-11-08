package cedream.blokus.view;

import java.util.Observable;
import java.util.Observer;

import cedream.blokus.facade.GameFacade;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FXView extends VBox implements Observer{

    private final PopupBotView popup;
    private final MenuView menuView;
    private final PiecesView piecesView;
    private final BoardView gridView;
    private final BottomView bottomView;

    public FXView(GameFacade gameFacade) {
        popup = new PopupBotView(gameFacade);
        menuView = new MenuView(gameFacade);
        piecesView = new PiecesView(gameFacade);
        gridView = new BoardView(gameFacade);
        bottomView = new BottomView(gameFacade);
        bottomView.setAlignment(Pos.CENTER);
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(12,12,12,12));
        hbox.setSpacing(12);
        hbox.getChildren().addAll(piecesView, gridView);
        getChildren().addAll(menuView, hbox, bottomView);
    }

    static void setBackground(Region region, Color color) {
        region.setBackground(
                new Background(
                        new BackgroundFill(
                                color,
                                CornerRadii.EMPTY,
                                Insets.EMPTY)));
    }

    @Override
    public void update(Observable o, Object arg) {
        piecesView.update();
        gridView.update();
    }
}
