package seedu.address.ui;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class NotesWindow extends Alert {
    public NotesWindow(Stage owner) {
        super(AlertType.INFORMATION);
        initOwner(owner);
        getDialogPane().getStylesheets().add("view/DarkTheme.css");
        setTitle("Notes");
        setHeaderText("Here are your notes:");
    }

    public void setMessage(String message) {
        setContentText(message);
    }
}
