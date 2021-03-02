package seedu.address.model.cheese;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

import seedu.address.model.Date;

public class ExpiryDate extends Date {
    /**
     * Constructs a {@code Phone}.
     *
     * @param date A valid phone number.
     */
    public ExpiryDate(String date) {
        super(date);
        checkArgument(isValidManufactureDate(this.value), MESSAGE_CONSTRAINTS);
    }

    public boolean isValidManufactureDate(LocalDateTime value) {
        return true;
    }
}
