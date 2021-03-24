package seedu.budgetbaby.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Region;
import seedu.budgetbaby.commons.core.LogsCenter;
import seedu.budgetbaby.model.month.Month;

/**
 * A ui for the budget information that is displayed on the application.
 */
public class BudgetDisplay extends UiPart<Region> {

    private static final String FXML = "BudgetDisplay.fxml";
    private static final String PROGRESSBAR_ACCENT_NORMAL = "-fx-accent: #557571;";
    private static final String PROGRESSBAR_ACCENT_WARNING = "-fx-accent: #f0cf85;";
    private static final String PROGRESSBAR_ACCENT_DANGER = "-fx-accent: #bd574e;";
    private static final double PROGRESSBAR_LEVEL_WARNING = 0.6;
    private static final double PROGRESSBAR_LEVEL_DANGER = 0.9;

    private final Logger logger = LogsCenter.getLogger(FinancialRecordListPanel.class);

    @FXML
    private Label budgetMonth;

    @FXML
    private Label budgetAmount;

    @FXML
    private ProgressBar budgetProgressBar;

    /**
     * Creates a {@code BudgetDisplay} with the given {@code Month}.
     */
    public BudgetDisplay(ObservableList<Month> budgetMonthList) {
        super(FXML);

        addProgressBarListener();
        updateObservableList(budgetMonthList);
    }

    /**
     * Adds a progressProperty listener to update budget
     * progress bar in real time.
     */
    public void addProgressBarListener() {
        this.budgetProgressBar.progressProperty().addListener((args, oldValue, newValue) -> {
            if (newValue.doubleValue() >= PROGRESSBAR_LEVEL_DANGER) {
                this.budgetProgressBar.setStyle(PROGRESSBAR_ACCENT_DANGER);
            } else if (newValue.doubleValue() >= PROGRESSBAR_LEVEL_WARNING) {
                this.budgetProgressBar.setStyle(PROGRESSBAR_ACCENT_WARNING);
            } else {
                this.budgetProgressBar.setStyle(PROGRESSBAR_ACCENT_NORMAL);
            }
        });
    }

    /**
     * Updates a {@code BudgetDisplay} with the most recent {@code Month}.
     */
    public void updateObservableList(ObservableList<Month> budgetMonthList) {
        Month month = budgetMonthList.get(0);

        double totalBudget = month.getBudget().getAmount();
        double totalExpenses = month.getTotalExpenses();
        double expensesPercentage = totalExpenses / totalBudget;

        this.budgetMonth.setText("Statistics: " + month.toString());
        this.budgetAmount.setText("Budget: " + totalExpenses + "/" + totalBudget);
        this.budgetProgressBar.setProgress(expensesPercentage);
    }
}
