package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.address.model.Event;
import seedu.address.model.module.Description;
import seedu.address.model.tag.Tag;

public class GeneralEvent extends Event {
    public static final String MESSAGE_CONSTRAINTS = "Event date must be formatted "
            + "to a valid DD/MM/YYYY TIME";

    public final Description description;
    public final LocalDateTime date;

    /**
     * Constructs an {@code GeneralEvent}.
     *
     * @param description A valid event description.
     * @param date A valid date and time.
     */
    public GeneralEvent(Description description, LocalDateTime date) {
        super(description, date, new Tag());
        this.description = description;
        this.date = date;
    }

    public GeneralEvent setDescription(Description newDescription) {
        return new GeneralEvent(newDescription, date);
    }

    public GeneralEvent setDate(LocalDateTime newDate) {
        return new GeneralEvent(description, newDate);
    }

    public boolean isSameEvent(GeneralEvent other) {
        return this.equals(other);
    }

    @Override
    public String toString() {
        return description + " due: " + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GeneralEvent // instanceof handles nulls
                && description.equals(((GeneralEvent) other).description)
                && date.equals(((GeneralEvent) other).date));
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, date);
    }
}
