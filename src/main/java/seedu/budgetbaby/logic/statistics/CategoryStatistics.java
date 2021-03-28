package seedu.budgetbaby.logic.statistics;

import seedu.budgetbaby.model.record.Category;

public class CategoryStatistics implements Comparable<CategoryStatistics> {
    private final Category category;
    private final double amount;

    CategoryStatistics(Category category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }


    @Override
    public int compareTo(CategoryStatistics cs) {
        return (int) (cs.getAmount() - this.getAmount());
    }
}
