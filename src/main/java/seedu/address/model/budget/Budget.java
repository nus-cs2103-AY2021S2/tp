package seedu.address.model.budget;

import java.util.Objects;

public class Budget {

    private final int limit;
    private final int currentAmount;

    public Budget(int limit, int currentAmount) {
        this.limit = limit;
        this.currentAmount = currentAmount;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public int getLimit() {
        return limit;
    }

    public boolean isExceeded() {
        return this.limit < this.currentAmount;
    }

    @Override
    public String toString() {
        return "Budget ===>" +
                "limit:" + limit +
                "\n Current Amount:" + currentAmount;
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
        return limit == budget.limit && currentAmount == budget.currentAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(limit, currentAmount);
    }

}
