package seedu.budgetbaby.logic.statistics;

import java.time.format.DateTimeFormatter;

import seedu.budgetbaby.model.month.Month;

public class MonthStatistics {
    private final Month month;

    MonthStatistics(Month month) {
        this.month = month;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");
        return String.format("%s: %.2f/%.2f\n", month.getMonth().format(formatter), month.getTotalExpenses(),
                    month.getBudget().getAmount());
    }
}
