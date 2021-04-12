package seedu.address.ui;

import java.util.Comparator;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.subject.TutorSubject;
import seedu.address.model.tutor.Tutor;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class TutorCard extends UiPart<Region> {

    private static final String FXML = "TutorListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Tutor tutor;

    private TutorNotesField tutorNotesField;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label gender;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane subjects;
    @FXML
    private FlowPane tags;
    @FXML
    private StackPane notesPane;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TutorCard(Tutor tutor, int displayedIndex) {
        super(FXML);
        this.tutor = tutor;
        id.setText(displayedIndex + ". ");

        boolean isFavourite = tutor.isFavourite();
        if (isFavourite) {
            name.setText(tutor.getName().fullName + "  " + new String(Character.toChars(127775)));
        } else {
            name.setText(tutor.getName().fullName);
        }

        gender.setText(tutor.getGender().toString().toUpperCase());
        phone.setText(tutor.getPhone().value);
        address.setText(tutor.getAddress().value);
        email.setText(tutor.getEmail().value);
        tutor.getSubjectList().asUnmodifiableObservableList().stream()
                .filter(subject -> Objects.nonNull(subject))
                .forEach(subject -> subjects.getChildren()
                        .add(getSubjectLabel(subject)));
        tutor.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        if (tutor.hasNotes()) {
            tutorNotesField = new TutorNotesField(tutor.getNotes());
            notesPane.getChildren().add(tutorNotesField.getRoot());
        }
    }

    private Label getSubjectLabel(TutorSubject subject) {
        Label subjectDetail = new Label(displaySubjectDetails(subject));
        subjectDetail.setWrapText(true);
        return subjectDetail;
    }

    private String displaySubjectDetails(TutorSubject subject) {
        String text = subject.getName().name + "\n"
                + "    Level: " + subject.getLevel().level + "\n"
                + "    Rate: SGD" + subject.getRate().rate + "/hr" + "\n"
                + "    Experience: " + subject.getExperience().experience + " years" + "\n"
                + "    Qualification: " + subject.getQualification().qualification;
        return text;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TutorCard)) {
            return false;
        }

        // state check
        TutorCard card = (TutorCard) other;
        return id.getText().equals(card.id.getText())
                && tutor.equals(card.tutor);
    }
}
