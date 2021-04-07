package fooddiary.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import fooddiary.commons.exceptions.IllegalValueException;
import fooddiary.model.tag.TagCategory;

/**
 * Jackson-friendly version of {@link TagCategory}.
 */
class JsonAdaptedTagCategory {

    private final String tag;

    /**
     * Constructs a {@code JsonAdaptedTagCategory} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTagCategory(String tagName) {
        this.tag = tagName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTagCategory(TagCategory source) {
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
    public TagCategory toModelType() throws IllegalValueException {
        if (!TagCategory.isValidTagName(tag)) {
            throw new IllegalValueException(TagCategory.MESSAGE_CONSTRAINTS);
        }
        return new TagCategory(tag);
    }

}
