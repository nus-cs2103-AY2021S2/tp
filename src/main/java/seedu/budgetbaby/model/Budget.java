package seedu.budgetbaby.model;

import static java.util.Objects.requireNonNull;
import static seedu.budgetbaby.commons.util.AppUtil.checkArgument;

/**
 * Represents the abstraction of a budget set by the user.
 */
public class Budget {
    public static final Double DEFAULT_BUDGET = 1000.0;
    public static final Double DEFAULT_BUDGET_UPPER_LIMIT = 1000000.0;
    public static final String MESSAGE_CONSTRAINTS =
        "Budget amount should be a positive number, and it should not be blank";

    protected Double amount;

    /**
     * Default constructor.
     */
    public Budget() {
        this.amount = DEFAULT_BUDGET;
    }

    /**
     * Initializes a Budget with a specified amount.
     *
     * @param amount The amount specified by the user.
     */
    public Budget(Double amount) {
        this.amount = amount;
    }

    /**
     * Constructs a {@code Budget}.
     *
     * @param amount A valid budget.
     */
    public Budget(String amount) {
        requireNonNull(amount);
        checkArgument(isValidBudget(amount), MESSAGE_CONSTRAINTS);
        this.amount = Double.parseDouble(amount);
    }

    /**
     * Returns true if a given string is a valid budget.
     */
    public static boolean isValidBudget(String test) {
        double amount;
        try {
            amount = Double.parseDouble(test);
        } catch (NumberFormatException e) {
            return false;
        }
        return Double.compare(amount, 0.0) >= 0;
    }

    /**
     * @return The budget amount set by the user.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the budget to a specified amount.
     *
     * @param amount The amount specified by the user.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        if (Double.compare(amount, 0.0) == 0) {
            return "No budget set";
        }
        return String.format("$%.2f", amount);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof Budget
            && Double.compare(amount, ((Budget) other).amount) == 0);
    }
}
