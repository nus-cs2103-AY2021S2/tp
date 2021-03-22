package seedu.address.model.fee;

import java.text.DateFormatSymbols;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the month of the fee
 */
public class Month {
    public static final String MESSAGE_CONSTRAINTS = "Format of month input is incorrect." +
        "(Must be between 1 to 12)";

    private int month;

    /**
     * Constructs a {@code Month}.
     *
     * @param month The year to get the fees from
     */
    public Month(int month) {
        checkArgument(isValidMonth(month), MESSAGE_CONSTRAINTS);
        this.month = month;
    }

    @Override
    public String toString() {
        return String.valueOf(month);
    }

    public int getMonth() {
        return month;
    }

    /**
     * Get the month's name. (E.g. January / February)
     * @return The month name in String
     */
    public String getMonthName() {
        return new DateFormatSymbols().getMonths()[month-1];
    }

    /**
     * Returns true if month is valid.
     */
    public static boolean isValidMonth(int month) {
        return month > 0 && month <= 12;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Month)) {
            return false;
        }

        Month otherMonth = (Month) other;
        return otherMonth.getMonth() == getMonth();
    }
}
