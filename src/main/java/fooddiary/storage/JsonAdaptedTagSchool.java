package fooddiary.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import fooddiary.commons.exceptions.IllegalValueException;
import fooddiary.model.tag.TagSchool;

public class JsonAdaptedTagSchool {

    private final String tag;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTagSchool(String tagName) {
        this.tag = tagName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTagSchool(TagSchool source) {
        this.tag = source.getTag();
    }

    @JsonValue
    public String getTagName() {
        return tag;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public TagSchool toModelType() throws IllegalValueException {
        if (!TagSchool.isValidTagName(tag)) {
            throw new IllegalValueException(TagSchool.MESSAGE_CONSTRAINTS);
        }
        return new TagSchool(tag);
    }
}
