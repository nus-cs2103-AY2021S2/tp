package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTutorBook;
import seedu.address.model.TutorBook;
import seedu.address.model.tutor.Tutor;


/**
 * An Immutable TutorBook that is serializable to JSON format.
 */
@JsonRootName(value = "tutorbook")
class JsonSerializableTutorBook {

    public static final String MESSAGE_DUPLICATE_TUTOR = "Tutors list contains duplicate tutor(s).";

    private final List<JsonAdaptedTutor> tutors = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTutorBook} with the given tutors.
     */
    @JsonCreator
    public JsonSerializableTutorBook(@JsonProperty("tutors") List<JsonAdaptedTutor> tutors) {
        this.tutors.addAll(tutors);
    }

    /**
     * Converts a given {@code ReadOnlyTutorBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTutorBook}.
     */
    public JsonSerializableTutorBook(ReadOnlyTutorBook source) {
        tutors.addAll(source.getTutorList().stream().map(JsonAdaptedTutor::new).collect(Collectors.toList()));
    }

    /**
     * Converts this tutor book into the model's {@code TutorBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TutorBook toModelType() throws IllegalValueException {
        TutorBook tutorBook = new TutorBook();
        for (JsonAdaptedTutor jsonAdaptedTutor : tutors) {
            Tutor tutor = jsonAdaptedTutor.toModelType();
            if (tutorBook.hasTutor(tutor)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TUTOR);
            }
            tutorBook.addTutor(tutor);
        }
        return tutorBook;
    }

}
