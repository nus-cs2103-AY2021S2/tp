package seedu.address.model.budget;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

import seedu.address.model.tag.Filterable;



public class Budget implements Filterable {

    public static final String MESSAGE_CONSTRAINTS =
            "Budget should only be an integer, and it should be at least 1 digit long";
    public static final String VALIDATION_REGEX = "\\d+";

    private final Integer value;
    private final Integer totalCost;

    /**
     * Primary constructor.
     * @param value Value of budget provided by CLI.
     */
    public Budget(String value) {
        requireNonNull(value);
        checkArgument(isValidBudget(value), MESSAGE_CONSTRAINTS);
        GetTotalCost getTotalCost = new GetTotalCost();
        this.value = Integer.parseInt(value);
        this.totalCost = getTotalCost.getTotalCost();
    }

    /**
     * Ensures value provided to Budget constructor is an integer.
     */
    public static boolean isValidBudget(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * @return Current Total cost of all outstanding appointments
     */
    public int getTotalCost() {
        return totalCost;
    }

    /**
     * @return Budget size as set by user.
     */
    public int getValue() {
        return value;
    }

    /**
     * @return True if budget is exceeded, else returns false.
     */
    public boolean isBudgetExceeded() {
        return this.value < this.totalCost;
    }

    @Override
    public String toString() {
        return String.format("Budget: %d\nCurrent Total Cost: %d", this.value,
                this.totalCost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Budget budget = (Budget) o;
        return value == budget.value && totalCost == budget.totalCost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, totalCost);
    }

    public boolean filter(String s) {
        return value.equals(Integer.parseInt(s));
    }

}
