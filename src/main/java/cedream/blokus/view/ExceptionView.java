package cedream.blokus.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Objects;
import java.util.Optional;

public class ExceptionView {

    public static void showAlert(String str) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informations");
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }

    public static boolean showAlertEndGame(String str) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Fin de partie");
        alert.setHeaderText(null);
        alert.setContentText( !Objects.isNull(str) ? str : "");
        alert.setContentText(alert.getContentText() + "\nVoulez-vous recommencer une partie ?");
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public static boolean showAlertEndGame() {
        return showAlertEndGame(null);
    }


}
