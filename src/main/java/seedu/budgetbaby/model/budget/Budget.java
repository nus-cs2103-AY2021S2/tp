//package seedu.budgetbaby.model.budget;

// Represents the abstraction of a budget set by the user.
public class Budget {
    private double amount;

    /**
     * Default constructor.
     */
    public Budget() {}

    /**
     * Initializes a Budget with a specified amount.
     * @param amount The amount specified by the user.
     */
    public Budget(double amount) {
        this.amount = amount;
    }

    /**
     * @return The budget amount set by the user.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the budget to a specified amount.
     * @param amount The amount specified by the user.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        if (Double.compare(amount, 0.0) == 0) {
            return "No budget set";
        }
        return String.format("$%.2f", amount);
    }
}
