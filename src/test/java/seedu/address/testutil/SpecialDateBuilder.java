package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.person.SpecialDate;

public class SpecialDateBuilder {

    public static final LocalDate DEFAULT_DATE = LocalDate.of(2020, 5, 6);
    public static final String DEFAULT_DESCRIPTION = "this is a description";

    private LocalDate date;
    private String description;

    /**
     * Creates a {@code SpecialDateBuilder} with the default details.
     */
    public SpecialDateBuilder() {
        date = DEFAULT_DATE;
        description = DEFAULT_DESCRIPTION;
    }

    /**
     * Sets the {@code date} of the {@code SpecialDate} that we are building.
     */
    public SpecialDateBuilder withDate(LocalDate date) {
        this.date = LocalDate.from(date);
        return this;
    }

    /**
     * Sets the {@code description} of the {@code SpecialDate} that we are building.
     */
    public SpecialDateBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Builds an SpecialDate with the attributes from SpecialDateBuilder
     */
    public SpecialDate build() {
        return new SpecialDate(date, description);
    }
}
