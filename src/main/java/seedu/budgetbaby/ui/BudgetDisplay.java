package seedu.budgetbaby.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Region;
import seedu.budgetbaby.commons.core.LogsCenter;
import seedu.budgetbaby.logic.statistics.CategoryStatistics;
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

    private final Logger logger = LogsCenter.getLogger(BudgetDisplay.class);

    @FXML
    private Label budgetMonth;

    @FXML
    private Label budgetAmount;

    @FXML
    private ProgressBar budgetProgressBar;

    @FXML
    private Label budgetPercentage;

    @FXML
    private ListView topCategories;

    /**
     * Creates a {@code BudgetDisplay} with the given {@code Month}.
     */
    public BudgetDisplay(ObservableList<Month> budgetMonthList, List<CategoryStatistics> topCategoriesList) {
        super(FXML);

        addProgressBarListener();
        updateBudgetUi(budgetMonthList);
        updateTopCategoriesUi(topCategoriesList);
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
     * Updates a {@code BudgetDisplay} with {@code Month}.
     */
    public void updateBudgetUi(ObservableList<Month> budgetMonthList) {
        assert budgetMonthList.size() == 1;
        Month month = budgetMonthList.get(0);

        double totalBudget = month.getBudget().getAmount();
        double totalExpenses = month.getTotalExpenses();
        double expensesRatio = totalExpenses / totalBudget;
        double expensesPercentage = totalExpenses / totalBudget * 100;

        String monthStr = month.toString();
        String totalBudgetStr = String.format("%.2f", totalBudget);
        String totalExpensesStr = String.format("%.2f", totalExpenses);
        String expensesPercentageStr = String.format("%.1f%%", expensesPercentage);

        this.budgetMonth.setText("Statistics: " + monthStr);
        this.budgetAmount.setText("Budget($): " + totalExpensesStr + "/" + totalBudgetStr);
        this.budgetProgressBar.setProgress(expensesRatio);
        this.budgetPercentage.setText(expensesPercentageStr);
    }

    /**
     * Updates a {@code BudgetDisplay} with the top categories list.
     */
    public void updateTopCategoriesUi(List<CategoryStatistics> topCategoriesList) {
        this.topCategories.getItems().clear();
        for (CategoryStatistics item : topCategoriesList) {
            String itemName = item.getCategory().toString();
            itemName = itemName.substring(1, itemName.length() - 1); // category name with [ and ] stripped
            String itemValue = String.format("$%.2f", item.getAmount());
            String topCategoryItem = itemName + ": " + itemValue;
            this.topCategories.getItems().add(topCategoryItem);
        }
    }
}
