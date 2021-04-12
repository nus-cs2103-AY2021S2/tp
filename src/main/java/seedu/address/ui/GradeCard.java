package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.grade.Grade;

/**
 * An UI component that displays information of a {@code Grade}.
 */
public class GradeCard extends UiPart<Region> {

    private static final String FXML = "GradeListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Grade grade;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label subjectName;
    @FXML
    private Label gradedItem;
    @FXML
    private Label gradeLetter;

    /**
     * Creates a {@code GradeCode} with the given {@code Grade} and index to display.
     */
    public GradeCard(Grade grade, int displayedIndex) {

        super(FXML);
        this.grade = grade;
        id.setText(displayedIndex + ". ");
        subjectName.setWrapText(true);
        gradedItem.setWrapText(true);
        gradeLetter.setWrapText(true);

        subjectName.setText(grade.getSubject().name);
        gradedItem.setText(grade.getGradedItem().gradedItem);
        gradeLetter.setText(grade.getGrade().gradeLetter);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GradeCard)) {
            return false;
        }

        // state check
        GradeCard card = (GradeCard) other;
        return id.getText().equals(card.id.getText())
                && grade.equals(card.grade);
    }
}
