package seedu.address.testutil;

import seedu.address.model.date.Description;
import seedu.address.model.date.Details;
import seedu.address.model.date.ImportantDate;

/**
 * A utility class to help with building ImportantDate objects.
 */
public class ImportantDateBuilder {

    public static final String DEFAULT_DESCRIPTION = "O-Levels Mathematics Paper 1";
    public static final String DEFAULT_DETAILS = "2021-11-05 0800";

    private Description description;
    private Details details;

    /**
     * Creates a {@code ImportantDateBuilder} with the default details.
     */
    public ImportantDateBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        details = new Details(DEFAULT_DETAILS);
    }

    /**
     * Initializes the ImportantDateBuilder with the data of {@code importantDateToCopy}.
     */
    public ImportantDateBuilder(ImportantDate importantDateToCopy) {
        description = importantDateToCopy.getDescription();
        details = importantDateToCopy.getDetails();
    }

    /**
     * Sets the {@code Description} of the {@code ImportantDate} that we are building.
     */
    public ImportantDateBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Details} of the {@code ImportantDate} that we are building.
     */
    public ImportantDateBuilder withDetails(String details) {
        this.details = new Details(details);
        return this;
    }

    /**
     * Builds the {@code ImportantDate}.
     */
    public ImportantDate build() {
        return new ImportantDate(description, details);
    }
}
