package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Note;

/**
 * Jackson-friendly version of {@link Note}.
 */
class JsonAdaptedNote {

    private final String noteContent;

    /**
     * Constructs a {@code JsonAdaptedNote} with the given {@code noteContent}.
     */
    @JsonCreator
    public JsonAdaptedNote(String noteContent) {
        this.noteContent = noteContent;
    }

    /**
     * Converts a given {@code note} into this class for Jackson use.
     */
    public JsonAdaptedNote(Note source) {
        noteContent = source.toString();
    }

    @JsonValue
    public String getNoteContent() {
        return noteContent;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Note toModelType() throws IllegalValueException {
        if (!Note.isValidNote(noteContent)) {
            throw new IllegalValueException(Note.MESSAGE_CONSTRAINTS);
        }
        return new Note(noteContent);
    }

}
