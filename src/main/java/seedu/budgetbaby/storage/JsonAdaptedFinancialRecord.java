package seedu.budgetbaby.storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.budgetbaby.commons.exceptions.IllegalValueException;
import seedu.budgetbaby.model.record.Amount;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.Description;
import seedu.budgetbaby.model.record.FinancialRecord;


/**
 * Jackson-friendly version of {@link FinancialRecord}.
 */
class JsonAdaptedFinancialRecord {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Financial Record's %s field is missing!";

    private final String description;
    private final String amount;
    private final Date timestamp;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedFinancialRecord} with the given financial record details.
     */
    @JsonCreator
    public JsonAdaptedFinancialRecord(@JsonProperty("description") String description,
                                      @JsonProperty("amount") String amount,
                                      @JsonProperty("timestamp") Date timestamp,
                                      @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.description = description;
        this.amount = amount;
        this.timestamp = timestamp;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code FinancialRecord} into this class for Jackson use.
     */
    public JsonAdaptedFinancialRecord(FinancialRecord source) {
        description = source.getDescription().description;
        amount = source.getAmount().value.toString();
        timestamp = source.getTimestamp();
        tagged.addAll(source.getCategories().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted financial record object into the model's {@code FinancialRecord} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public FinancialRecord toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (amount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName()));
        }
        if (!Amount.isValidAmount(amount)) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }
        final Amount modelAmount = new Amount(amount);

        final List<Category> modelCategories = new ArrayList<>();
        for (JsonAdaptedTag category : tagged) {
            modelCategories.add(category.toModelType());
        }
        final Set<Category> modelUniqueCategories = new HashSet<>(modelCategories);

        return new FinancialRecord(modelDescription, modelAmount, timestamp, modelUniqueCategories);
    }

}
