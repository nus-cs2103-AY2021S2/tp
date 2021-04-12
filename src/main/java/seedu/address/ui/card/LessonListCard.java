package seedu.address.ui.card;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.lesson.Lesson;
import seedu.address.ui.UiPart;


/**
 * An UI component that displays information of an {@code Important Date}.
 */
public class LessonListCard extends UiPart<Region> {

    private static final String FXML = "LessonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Lesson lesson;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label lessonDetails;
    @FXML
    private Label personNames;

    /**
     * Creates a {@code LessonListCard} with the given {@code Lesson} and index to display.
     */
    public LessonListCard(Lesson lesson) {
        super(FXML);
        this.lesson = lesson;
        lessonDetails.setText(lesson.getTimeInString());
        personNames.setText(lesson.getPersonInString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LessonListCard)) {
            return false;
        }

        // state check
        LessonListCard card = (LessonListCard) other;
        return lessonDetails.getText().equals(card.lessonDetails.getText())
                && personNames.getText().equals(card.personNames.getText());
    }

}
