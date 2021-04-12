package seedu.address.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 * Window that shows notifications.
 */
public class NotifWindow extends Alert {
    private final ScrollPane scroll;
    private final Label label;

    /**
     * Creates a new NotifWindow.
     * @param owner Stage to use as the root of the NotifWindow.
     */
    public NotifWindow(Stage owner) {
        super(AlertType.INFORMATION);
        getDialogPane().getStylesheets().add("view/DarkTheme.css");
        setTitle("Notification");
        setHeaderText("Welcome to Link.me!");
        initOwner(owner);
        scroll = new ScrollPane();
        label = new Label();
        getDialogPane().setMaxWidth(400);
        label.setWrapText(true);
        label.setMaxWidth(400);
        scroll.setPrefViewportWidth(400);
        scroll.setFitToWidth(true);
        scroll.setPrefViewportHeight(300);
    }

    /**
     * Sets the displayed message to the notifications.
     */
    public void setMessage(String message) {
        label.setText(message);
        scroll.setContent(label);
        getDialogPane().setContent(scroll);
    }
}
