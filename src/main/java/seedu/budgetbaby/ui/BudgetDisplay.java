package seedu.budgetbaby.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.budgetbaby.model.month.Month;

/**
 * A ui for the budget information that is displayed on the application.
 */
public class BudgetDisplay extends UiPart<Region> {

    private static final String FXML = "BudgetDisplay.fxml";

    @FXML
    private Label budgetAmount;

    @FXML
    private Label budgetMonth;

    /**
     * Creates a {@code BudgetDisplay} with the given {@code Month}.
     */
    public BudgetDisplay(Month budgetMonth) {
        super(FXML);

        double remainingBudget = budgetMonth.getRemainingBudget();
        double totalBudget = budgetMonth.getBudget().getAmount();
        this.budgetAmount.setText("Budget: " + remainingBudget + "/" + totalBudget);
        this.budgetMonth.setText(budgetMonth.toString());
    }
}
