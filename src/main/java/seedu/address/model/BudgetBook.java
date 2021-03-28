package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.budget.Budget;


//TODO Implement for multiple budget scenarios, Budget1 Budget2...

/**
 * BudgetBook container for holding a single budget.
 */
public class BudgetBook implements ReadOnlyBudgetBook {

    private final ObservableList<Budget> budgetList =
            FXCollections.observableArrayList();

    /**
     * Primary constructor.
     */
    public BudgetBook() {

    }

    /**
     * Initializes with budget instead of budget book.
     */
    public BudgetBook(Budget budget) {
        addBudget(budget);
    }

    /**
     * ALternative constuctor to obtain budget book.
     * @param budgetBook Budget book to initialize with.
     */
    public BudgetBook(BudgetBook budgetBook) {
        if (budgetBook.hasBudget()) {
            this.budgetList.add(budgetBook.getBudgetList().get(0));
        }
    }

    /**
     * Sets budget if already present.
     * @param budget Budget to update to.
     */
    public void setBudget(Budget budget) {
        requireNonNull(budget);
        this.budgetList.set(0, budget);
    }

    /**
     * Adds budget to list if not present, otherwise updates the budget.
     * @param budget Budget to add.
     */
    public void addBudget(Budget budget) {
        requireNonNull(budget);
        if (hasBudget()) {
            setBudget(budget);
        } else {
            budgetList.add(budget);
        }
    }

    /**
     * Removes budget from existing budget list.
     */
    public void deleteBudget() {
        this.budgetList.remove(0);
        assert !hasBudget();
    }


    /**
     * @return True is budget is present already.
     */
    public boolean hasBudget() {
        return !budgetList.isEmpty();
    }

    /**
     * @return Budget of budget book, 0 if not present.
     */
    public Budget getBudget() {
        if (hasBudget()) {
            int budgetValue = budgetList.get(0).getValue();
            return new Budget(Integer.toString(budgetValue));
        } else {
            return new Budget("0");
        }
    }

    @Override
    public ObservableList<Budget> getBudgetList() {
        return FXCollections.unmodifiableObservableList(budgetList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BudgetBook that = (BudgetBook) o;
        return Objects.equals(budgetList, that.budgetList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(budgetList);
    }
}
