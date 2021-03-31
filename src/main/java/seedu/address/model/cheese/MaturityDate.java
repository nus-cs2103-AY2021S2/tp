package seedu.address.model.cheese;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

import seedu.address.model.AbstractDate;

/**
 * Represents a Cheese's maturity date in the Cheese Inventory Management System (CHIM).
 * Guarantees: immutable
 */
public class MaturityDate extends AbstractDate {

    /**
     * Constructs a {@code MaturityDate}.
     *
     * @param date A valid maturity date.
     */
    public MaturityDate(String date) {
        super(date);
        checkArgument(isValidMaturityDate(this.value), MESSAGE_CONSTRAINTS);
    }

    public boolean isValidMaturityDate(LocalDateTime value) {
        return true;
    }
}
