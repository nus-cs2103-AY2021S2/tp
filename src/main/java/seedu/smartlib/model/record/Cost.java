package seedu.smartlib.model.record;

/**
 * Represents the cost of overdue books.
 */
public class Cost {

    public static final double RATES_PER_HOUR = 0.1;
    private final int overdueHour;

    /**
     * Creates a cost of overdue book.
     *
     * @param overdueHour The duration, in hours, that the book was overdue.
     */
    public Cost(int overdueHour) {
        assert overdueHour >= 0 : "Overdue hours cannot be negative.";
        this.overdueHour = overdueHour;
    }

    /**
     * Calculate the total cost given the overdue hours.
     *
     * @return The total cost of the overdue book.
     */
    public double getCost() {
        return RATES_PER_HOUR * overdueHour;
    }

    /**
     * Returns this Cost in String format.
     *
     * @return this Cost in String format.
     */
    @Override
    public String toString() {
        return String.format("The total cost is $%.2f.", getCost());
    }

    /**
     * Checks if this Cost is equal to another Cost.
     *
     * @param other the other Cost to be compared.
     * @return true if this Cost is equal to the other Cost, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Cost // instanceof handles nulls
                && overdueHour == ((Cost) other).overdueHour); // state check
    }

}
