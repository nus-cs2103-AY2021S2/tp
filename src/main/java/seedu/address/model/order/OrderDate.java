package seedu.address.model.order;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

import seedu.address.model.Date;

public class OrderDate extends Date {
    /**
     * Constructs a {@code Phone}.
     *
     * @param date A valid phone number.
     */
    public OrderDate(String date) {
        super(date);
        checkArgument(isValidOrderDate(this.value), MESSAGE_CONSTRAINTS);
    }

    public boolean isValidOrderDate(LocalDateTime value) {
        return true;
    }
}
