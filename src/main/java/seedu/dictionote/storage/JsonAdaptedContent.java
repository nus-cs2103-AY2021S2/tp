package seedu.dictionote.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.dictionote.commons.exceptions.IllegalValueException;
import seedu.dictionote.model.dictionary.Content;

/**
 * Jackson-friendly version of {@link Content}.
 */
class JsonAdaptedContent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Note's %s field is missing!";

    private final String content;

    /**
     * Constructs a {@code JsonAdaptedContent} with the given person d.
     */
    @JsonCreator
    public JsonAdaptedContent(@JsonProperty("content") String content) {
        this.content = content;
    }

    /**
     * Converts a given {@code Content} into this class for Jackson use.
     */
    public JsonAdaptedContent(Content source) {
        content = source.getContent();
    }

    /**
     * Converts this Jackson-friendly adapted object into the model's {@code Content} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted content.
     */
    public Content toModelType() throws IllegalValueException {
        if (content == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Content.class.getSimpleName()));
        }
        return new Content(content);
    }

}
