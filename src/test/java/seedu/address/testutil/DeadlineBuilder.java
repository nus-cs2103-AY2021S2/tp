package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.task.deadline.Deadline;

/**
 * A utility class to help with building Deadline objects.
 */
public class DeadlineBuilder {

    public static final String DEFAULT_DESCRIPTION = "Deadline Description";
    public static final LocalDate DEFAULT_DATE = LocalDate.of(2021, 1, 1);
    public static final Boolean DEFAULT_IS_DONE = false;

    private String description;
    private LocalDate by;
    private Boolean isDone;

    /**
     * Creates a {@code DeadlineBuilder} with the default details.
     */
    public DeadlineBuilder() {
        description = DEFAULT_DESCRIPTION;
        by = DEFAULT_DATE;
        isDone = DEFAULT_IS_DONE;
    }

    /**
     * Initializes the DeadlineBuilder with the data of {@code deadlineToCopy}.
     */
    public DeadlineBuilder(Deadline deadlineToCopy) {
        description = deadlineToCopy.getDescription();
        by = deadlineToCopy.getBy();
        isDone = deadlineToCopy.getIsDone();
    }

    /**
     * Sets the {@code Description} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the {@code at} date of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withByDate(LocalDate date) {
        this.by = date;
        return this;
    }

    /**
     * Sets the {@code isDone} status of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withIsDone(Boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    /**
     * Builds the {@code Deadline} object.
     *
     * @return {@code Deadline}.
     */
    public Deadline build() {
        return new Deadline(description, by, isDone);
    }

}
