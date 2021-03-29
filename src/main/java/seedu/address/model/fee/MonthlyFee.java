package seedu.address.model.fee;

import seedu.address.model.session.Fee;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents the monthly fee of a particular month and year.
 */
public class MonthlyFee {

    // Data fields
    private double monthlyFee;
    private Month month;
    private Year year;

    public MonthlyFee(double monthlyFee, Month month, Year year) {
        requireAllNonNull(monthlyFee, month, year);
        this.monthlyFee = monthlyFee;
        this.month = month;
        this.year = year;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public Month getMonth() {
        return month;
    }

    public Year getYear() {
        return year;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MonthlyFee)) {
            return false;
        }

        MonthlyFee otherMonthFee = (MonthlyFee) other;
        return otherMonthFee.getMonthlyFee() == getMonthlyFee()
            && otherMonthFee.getMonth() == getMonth()
            && otherMonthFee.getYear() == getYear();
    }
}
