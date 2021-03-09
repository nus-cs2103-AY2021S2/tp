package seedu.dictionote.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.dictionote.model.note.Note;

/**
 * An UI component that displays information of a {@code Note}.
 */
public class NoteCard extends UiPart<Region> {

    private static final String FXML = "NoteListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Note note;
    @FXML
    private Label id;
    @FXML
    private HBox cardPane;
    @FXML
    private Label notecontent;

    /**
     * Creates a {@code NoteCard} with the given {@code Note} and index to display.
     */
    public NoteCard(Note note, int displayedIndex) {
        super(FXML);
        this.note = note;
        id.setText(displayedIndex + ". ");
        notecontent.setText(note.getNote());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        NoteCard note = (NoteCard) other;
        return this.note.getNote().equals(note.note.getNote());
    }
}
