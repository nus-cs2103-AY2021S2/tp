package seedu.address.model.order;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

import seedu.address.model.Date;

public class CompletedDate extends Date {
    /**
     * Constructs a {@code Phone}.
     *
     * @param date A valid phone number.
     */
    public CompletedDate(String date) {
        super(date);
        checkArgument(isValidCompletedDate(this.value), MESSAGE_CONSTRAINTS);
    }

    public boolean isValidCompletedDate(LocalDateTime value) {
        return true;
    }
}
