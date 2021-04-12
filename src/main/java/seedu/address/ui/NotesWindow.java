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
    private final ScrollPane scroll;
    private final Label label;

    /**
     * Creates a new NotesWindow.
     * @param owner Stage to use as the root of the NotesWindow.
     */
    public NotesWindow(Stage owner) {
        super(AlertType.INFORMATION);
        getDialogPane().getStylesheets().add("view/DarkTheme.css");
        setTitle("Notes");
        initOwner(owner);
        scroll = new ScrollPane();
        label = new Label();
        getDialogPane().setMaxWidth(500);
        label.setWrapText(true);
        label.setMaxWidth(500);
        scroll.setPrefViewportWidth(500);
        scroll.setFitToWidth(true);
        scroll.setPrefViewportHeight(400);
    }

    /**
     * Sets the displayed message to the notes.
     */
    public void setMessage(Person personWithNotes) {
        setHeaderText(String.format("Notes for %s:", personWithNotes.getName()));
        label.setText(personWithNotes.getNotesString());
        scroll.setContent(label);
        getDialogPane().setContent(scroll);
    }
}
