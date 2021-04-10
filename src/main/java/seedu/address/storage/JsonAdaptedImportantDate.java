package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.date.Description;
import seedu.address.model.date.Details;
import seedu.address.model.date.ImportantDate;


/**
 * Jackson-friendly version of {@link ImportantDate}.
 */
public class JsonAdaptedImportantDate {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "ImportantDate's %s field is missing!";

    private final String description;
    private final String details;

    /**
     * Constructs a {@code JsonAdaptedImportantDate} with the given important date details.
     */
    @JsonCreator
    public JsonAdaptedImportantDate(@JsonProperty("description") String description,
                             @JsonProperty("details") String details) {
        this.description = description;
        this.details = details;

    }

    /**
     * Converts a given {@code ImportantDate} into this class for Jackson use.
     */
    public JsonAdaptedImportantDate(ImportantDate source) {
        description = source.getDescription().description;
        details = Details.parseLocalDateTimeIntoString(source.getDetails().details);
    }

    /**
     * Converts this Jackson-friendly adapted important date object into the model's {@code ImportantDate} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted importantDate.
     */
    public ImportantDate toModelType() throws IllegalValueException {

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (details == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Details.class.getSimpleName()));
        }
        if (!Details.isValidDetails(details)) {
            throw new IllegalValueException(Details.MESSAGE_CONSTRAINTS);
        }
        final Details modelDetails = new Details(details);

        return new ImportantDate(modelDescription, modelDetails);
    }


}
