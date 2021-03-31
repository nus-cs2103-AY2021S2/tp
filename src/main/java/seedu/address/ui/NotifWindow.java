package seedu.address.ui;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Window that shows notifications.
 */
public class NotifWindow extends Alert {
    /**
     * Creates a new NotifWindow.
     * @param owner Stage to use as the root of the NotifWindow.
     */
    public NotifWindow(Stage owner) {
        super(AlertType.INFORMATION);
        initOwner(owner);
        getDialogPane().getStylesheets().add("view/DarkTheme.css");
        setTitle("Notification");
        setHeaderText("Welcome to Link.me!");
    }

    /**
     * Sets the displayed message to the notifications.
     */
    public void setMessage(String message) {
        setContentText(message);
    }
}
