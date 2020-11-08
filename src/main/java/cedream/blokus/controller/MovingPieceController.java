package cedream.blokus.controller;

import cedream.blokus.facade.GameFacade;
import cedream.blokus.model.Piece;
import cedream.blokus.model.PieceColor;
import cedream.blokus.view.BoardView;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MovingPieceController implements EventHandler<MouseEvent> {

    private int row;
    private int col;
    private GridPane grid;
    private GameFacade gameFacade;

    public MovingPieceController(int row, int col, GridPane grid, GameFacade gameFacade) {
        this.row = row;
        this.col = col;
        this.grid = grid;
        this.gameFacade = gameFacade;
    }

    static void coloredPlaceOfPiece(int row, int col, GameFacade gameFacade,GridPane grid, Color color) {
        if (gameFacade.getCurrentPiece() == null) return;
        int rowInBoard = row-2;
        int colInBoard = col-2;
        for (int i = 0; i < Piece.getPIECE_SIZE(); i++) {
            for (int j = 0; j < Piece.getPIECE_SIZE(); j++) {
                if (gameFacade.getCurrentPiece().getPattern()[i][j]) {
                    try {
                        PieceColor oldColor = null;
                        if(color == Color.TRANSPARENT && gameFacade.getSaveBoard()[rowInBoard][colInBoard] != null) {
                            oldColor = gameFacade.getSaveBoard()[rowInBoard][colInBoard].getPieceColor();
                        }
                        ((Pane) BoardView.getNodeByRowColumnIndex(rowInBoard, colInBoard, grid)).setBackground(
                                new Background(new BackgroundFill(
                                        oldColor == null ? color : Piece.switchColor(oldColor),
                                        CornerRadii.EMPTY,
                                        Insets.EMPTY
                                )));
                    } catch(NullPointerException | IndexOutOfBoundsException ex) {}
                }
                colInBoard++;
            }
            colInBoard = col-2;
            rowInBoard++;
        }
    }

    @Override
    public void handle(MouseEvent e) {
        if (e.getEventType() == MouseEvent.MOUSE_ENTERED) {
            coloredPlaceOfPiece(row, col, gameFacade, grid, Color.GREY);
        } else if (e.getEventType() == MouseEvent.MOUSE_EXITED) {
            coloredPlaceOfPiece(row, col, gameFacade, grid, Color.TRANSPARENT);
        }
    }
}
