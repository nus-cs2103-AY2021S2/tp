package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.medical.Section;
import seedu.address.model.tag.Tag;

public class JsonAdaptedSection {
    private final String title;
    private final String body;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedSection(@JsonProperty("title") String title, @JsonProperty("body") String body) {
        this.title = title;
        this.body = body;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedSection(Section source) {
        title = source.getTitle();
        body = source.getBody();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Section toModelType() throws IllegalValueException {
        if (!Section.isValidTitleBody(title, body)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Section(title, body);
    }

}
