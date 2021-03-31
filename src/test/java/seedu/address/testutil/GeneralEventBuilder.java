package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Description;

public class GeneralEventBuilder {
    public static final String DEFAULT_DESCRIPTION = "This is a general Event";
    public static final LocalDateTime DEFAULT_DATE = LocalDateTime.of(2021, 04, 17, 12, 0);

    private Description description;
    private LocalDateTime date;

    /**
     * Creates a {@code GeneralEventBuilder} with default details;
     */
    public GeneralEventBuilder() {
        this.description = new Description(DEFAULT_DESCRIPTION);
        this.date = DEFAULT_DATE;
    }

    /**
     * Creates a GeneralEventBuilder with the data of {@code eventToCopy}.
     */
    public GeneralEventBuilder(GeneralEvent eventToCopy) {
        this.description = eventToCopy.getDescription();
        this.date = eventToCopy.getDateTime();
    }

    /**
     * Sets the {@code Description} of the {@code GeneralEvent} that we are building.
     */
    public GeneralEventBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the date of the {@code GeneralEvent} that we are building.
     */
    public GeneralEventBuilder withDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public GeneralEvent build() {
        return new GeneralEvent(description, date);
    }
}
