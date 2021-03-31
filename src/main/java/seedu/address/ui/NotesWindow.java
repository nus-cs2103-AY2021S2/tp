package seedu.address.ui;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Window that shows notes.
 */
public class NotesWindow extends Alert {
    /**
     * Creates a new NotesWindow.
     * @param owner Stage to use as the root of the NotesWindow.
     */
    public NotesWindow(Stage owner) {
        super(AlertType.INFORMATION);
        initOwner(owner);
        getDialogPane().getStylesheets().add("view/DarkTheme.css");
        setTitle("Notes");
        setHeaderText("Here are your notes:");
    }

    /**
     * Sets the displayed message to the notes.
     */
    public void setMessage(String message) {
        setContentText(message);
    }
}
