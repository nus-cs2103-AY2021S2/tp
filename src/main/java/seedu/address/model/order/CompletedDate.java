package seedu.address.model.order;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

import seedu.address.model.Date;

/**
 * Represents an Order's completed date in the Cheese inventory Management System (CHIM).
 * Guarantees: immutable
 */
public class CompletedDate extends Date {
    /**
     * Constructs a {@code CompletedDate}.
     *
     * @param date A valid completed date.
     */
    public CompletedDate(String date) {
        super(date);
        checkArgument(isValidCompletedDate(this.value), MESSAGE_CONSTRAINTS);
    }

    public boolean isValidCompletedDate(LocalDateTime value) {
        return true;
    }
}
