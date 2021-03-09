package seedu.dictionote.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.dictionote.commons.exceptions.IllegalValueException;
import seedu.dictionote.model.contact.Name;
import seedu.dictionote.model.note.Note;

/**
 * Jackson-friendly version of {@link Note}.
 */
class JsonAdaptedNote {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Note's %s field is missing!";

    private final String note;

    /**
     * Constructs a {@code JsonAdaptedNote} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedNote(@JsonProperty("note") String note) {
        this.note = note;
    }

    /**
     * Converts a given {@code Note} into this class for Jackson use.
     */
    public JsonAdaptedNote(Note source) {
        note = source.getNote();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Note} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted note.
     */
    public Note toModelType() throws IllegalValueException {
        if (note == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        return new Note(note);
    }

}
