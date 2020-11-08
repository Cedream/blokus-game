package cedream.blokus.view;

import cedream.blokus.controller.SelectPieceController;
import cedream.blokus.facade.GameFacade;
import cedream.blokus.model.Piece;
import cedream.blokus.model.Player;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

class StockPlayerView extends VBox{

    private Player player;
    private GameFacade gameFacade;
    private final Label name;
    private final Label score;
    private final Label bot;
    private final Label count_of_pieces;
    private final FlowPane container;

    StockPlayerView(Player player, GameFacade gameFacade) {
        this.player = player;
        this.gameFacade = gameFacade;
        HBox texts = new HBox();
        name = new Label("Joueur " + player.getPieceColor().getColorText());
        score = new Label("Score: " + player.getScore());
        bot = new Label();
        count_of_pieces = new Label("Pieces: " + player.countOfPieces());
        texts.setSpacing(20);
        texts.getChildren().addAll(name, score, count_of_pieces, bot);
        container = new FlowPane();
        initializeData();
        showStocks(player);
        getChildren().addAll(texts, container);
    }

    private void showStocks(Player player) {
        for(int i = 0; i < player.getPieces().size(); i++) {
            GridPane grid = new GridPane();
            grid.setGridLinesVisible(true);
            grid.setPadding(new Insets(1,2,3,1));
            container.getChildren().add(grid);
            if (player.getPieces().get(i).isUse()) {
                grid.setOpacity(.2);
            } else {
                grid.setOnMouseClicked(new SelectPieceController(player.getPieces().get(i), gameFacade));
                grid.setCursor(Cursor.HAND);
            }
            for(int row = 0; row < Piece.getPIECE_SIZE(); row++) {
                for(int col = 0; col < Piece.getPIECE_SIZE(); col++) {
                    Pane pane = new Pane();
                    pane.setMinSize(7,7);
                    if (player.getPieces().get(i).getPattern()[row][col]){
                        FXView.setBackground(pane, Piece.switchColor(player.getPieceColor()));
                    }
                    grid.add(pane, col, row);
                }
            }
        }
    }

    private void initializeData() {
        if (player.isBot()) {
            bot.setText("Bot");
            bot.setTextFill(Color.WHITE);
            FXView.setBackground(bot, Color.FIREBRICK);
        }
        if (!player.isCanPlay()) {
            container.setOpacity(.2);
            name.setOpacity(.2);
            score.setOpacity(.2);
            bot.setOpacity(.2);
        }
        if (player.getPieceColor().getColorText().equals(gameFacade.getCurrentPlayerColor())) {
            FXView.setBackground(name, Piece.switchColor(player.getPieceColor()));
            name.setTextFill(Color.WHITE);
        } else {
            FXView.setBackground(name, Color.TRANSPARENT);
            name.setTextFill(Color.BLACK);
        }
    }

    void update() {
        container.getChildren().clear();
        gameFacade.getPlayers().forEach(e->{
            if(e.equals(player)) player = e;
        });
        score.setText("Score: "+ player.getScore());
        count_of_pieces.setText("Pieces: "+ player.countOfPieces());
        showStocks(player);
        initializeData();
    }

}
