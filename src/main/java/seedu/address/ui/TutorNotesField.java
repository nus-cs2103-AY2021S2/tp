package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.model.person.Notes;

public class TutorNotesField extends UiPart<Region> {

    private static final String FXML = "TutorNotesField.fxml";

    public final Notes notes;

    @FXML
    private TextArea notesArea;

    public TutorNotesField(Notes notes) {
        super(FXML);
        this.notes = notes;

        notesArea.setText(notes.toString());
    }

}

