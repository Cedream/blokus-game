package cedream.blokus.view;

import cedream.blokus.controller.MovingPieceController;
import cedream.blokus.controller.PlacePieceController;
import cedream.blokus.controller.TurnButtonController;
import cedream.blokus.facade.GameFacade;
import cedream.blokus.model.Board;
import cedream.blokus.model.Piece;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class BoardView extends GridPane {

    private GameFacade gameFacade;

    BoardView(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
        for(int row = 0; row < Board.getBOARD_SIZE(); row++) {
            for(int col = 0; col < Board.getBOARD_SIZE(); col++) {
                Pane pane = new Pane();
                pane.setMinSize(33,33);
                pane.setOnMouseEntered(new MovingPieceController(row, col,this, gameFacade));
                pane.setOnMouseExited(new MovingPieceController(row, col,this, gameFacade));
                pane.setOnMouseClicked(new PlacePieceController(row, col, gameFacade));
                add(pane, col, row);
            }
        }
        setGridLinesVisible(true);
        setOnMouseClicked(new TurnButtonController<>(this, gameFacade));
    }

    void update() {
        for (int i = 0; i < Board.getBOARD_SIZE(); i++) {
            for ( int j = 0; j < Board.getBOARD_SIZE(); j++) {
                Piece piece = gameFacade.getSaveBoard()[i][j];
                FXView.setBackground(
                        ((Pane) BoardView.getNodeByRowColumnIndex(i, j, this)),
                        (piece != null) ? Piece.switchColor(piece.getPieceColor()) : Color.TRANSPARENT);
            }
        }
    }

    public static Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();
        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }

}
