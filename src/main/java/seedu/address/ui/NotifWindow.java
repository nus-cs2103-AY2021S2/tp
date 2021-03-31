package seedu.address.ui;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class NotifWindow extends Alert {
    public NotifWindow(Stage owner) {
        super(AlertType.INFORMATION);
        initOwner(owner);
        getDialogPane().getStylesheets().add("view/DarkTheme.css");
        setTitle("Notification");
        setHeaderText("Welcome to Link.me!");
    }

    public void setMessage(String message) {
        setContentText(message);
    }
}
