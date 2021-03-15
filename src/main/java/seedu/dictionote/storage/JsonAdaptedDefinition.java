package seedu.dictionote.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.dictionote.commons.exceptions.IllegalValueException;
import seedu.dictionote.model.dictionary.Definition;

/**
 * Jackson-friendly version of {@link Definition}.
 */
class JsonAdaptedDefinition {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Definition's %s field is missing!";

    private final String term;
    private final String defs;

    /**
     * Constructs a {@code JsonAdaptedDefinition} with the given term.
     */
    @JsonCreator
    public JsonAdaptedDefinition(@JsonProperty("term") String term,
                              @JsonProperty("defs") String defs) {
        this.term = term;
        this.defs = defs;
    }

    /**
     * Converts a given {@code Definition} into this class for Jackson use.
     */
    public JsonAdaptedDefinition(Definition source) {
        term = source.getTerm();
        defs = source.getDefs();
    }

    /**
     * Converts this Jackson-friendly adapted object into the model's {@code Definition} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted definition.
     */
    public Definition toModelType() throws IllegalValueException {
        if (term == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Definition.class.getSimpleName()));
        }
        if (defs == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Definition.class.getSimpleName()));
        }
        return new Definition(term, defs);
    }

}
