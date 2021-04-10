package seedu.address.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import seedu.address.model.person.Person;

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
        getDialogPane().getStylesheets().add("view/DarkTheme.css");
        setTitle("Notes");
        initOwner(owner);
    }

    /**
     * Sets the displayed message to the notes.
     */
    public void setMessage(Person personWithNotes) {
        setHeaderText(String.format("Notes for %s:", personWithNotes.getName()));
        getDialogPane().setMaxWidth(1000);
        ScrollPane scroll = new ScrollPane();
        Label label = new Label(personWithNotes.getNotesString());
        label.setMaxWidth(1000);
        label.setWrapText(true);
        scroll.setContent(label);
        scroll.setPrefViewportWidth(1000);
        scroll.setFitToWidth(true);
        scroll.setPrefViewportHeight(400);
        getDialogPane().setContent(scroll);
    }
}
