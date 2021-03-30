package seedu.dictionote.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.dictionote.commons.exceptions.IllegalValueException;
import seedu.dictionote.model.contact.Name;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.model.tag.Tag;



/**
 * Jackson-friendly version of {@link Note}.
 */
class JsonAdaptedNote {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Note's %s field is missing!";

    private final String note;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final LocalDateTime createTime;
    private final LocalDateTime editTime;
    private final Boolean isDone;
    /**
     * Constructs a {@code JsonAdaptedNote} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedNote(@JsonProperty("note") String note,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                           @JsonProperty("createTime") LocalDateTime createTime,
                           @JsonProperty("editTime") LocalDateTime editTime,
                           @JsonProperty("isDone") Boolean isDone) {
        this.note = note;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.createTime = createTime;
        this.editTime = editTime;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code Note} into this class for Jackson use.
     */
    public JsonAdaptedNote(Note source) {
        this.note = source.getNote();
        this.tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        this.createTime = source.getCreateTime();
        this.editTime = source.getLastEditTime();
        this.isDone = source.isDone();
    }

    /**
     * Converts this Jackson-friendly adapted note object into the model's {@code Note} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted note.
     */
    public Note toModelType() throws IllegalValueException {

        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        if (note == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Note(note, modelTags, createTime, editTime, isDone);
    }

}
