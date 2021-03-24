package seedu.budgetbaby.ui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Region;
import seedu.budgetbaby.commons.core.LogsCenter;
import seedu.budgetbaby.model.month.Month;

import java.util.logging.Logger;

/**
 * A ui for the budget information that is displayed on the application.
 */
public class BudgetDisplay extends UiPart<Region> {

    private static final String FXML = "BudgetDisplay.fxml";
    private static final String PROGRESSBAR_ACCENT_GREEN = "-fx-accent: green;";
    private static final String PROGRESSBAR_ACCENT_RED = "-fx-accent: red;";

    private final Logger logger = LogsCenter.getLogger(FinancialRecordListPanel.class);
    private final SimpleIntegerProperty currentMonthIndex;
    private final ObservableList<Month> budgetMonthList;

    @FXML
    private ComboBox budgetMonths;

    @FXML
    private Label budgetAmount;

    @FXML
    private ProgressBar budgetProgressBar;

    /**
     * Creates a {@code BudgetDisplay} with the given {@code Month}.
     */
    public BudgetDisplay(ObservableList<Month> budgetMonthList) {
        super(FXML);

        this.budgetMonthList = budgetMonthList;
        this.currentMonthIndex = new SimpleIntegerProperty(0);

        this.initBudgetMonthComboBox();
        this.initEventListeners();
    }

    public SimpleIntegerProperty getCurrentMonthIndex() {
        return this.currentMonthIndex;
    }

    public void initEventListeners() {
        this.currentMonthIndex.bind(this.budgetMonths.getSelectionModel().selectedIndexProperty());
        this.currentMonthIndex.addListener((args, oldIndex, newIndex) -> {
            updateBudgetMonthUI(newIndex.intValue());
        });

        // Event listener to update progress bar accent colour
        this.budgetProgressBar.progressProperty().addListener((args, oldValue, newValue) -> {
            if (newValue.intValue() >= 1) {
                this.budgetProgressBar.setStyle(PROGRESSBAR_ACCENT_RED);
                return;
            }
            this.budgetProgressBar.setStyle(PROGRESSBAR_ACCENT_GREEN);
        });
    }

    /**
     * TODO comments
     */
    public void initBudgetMonthComboBox() {
        this.budgetMonths.setItems(this.budgetMonthList);
        this.budgetMonths.getSelectionModel().select(this.currentMonthIndex.intValue());
    }

    /**
     * Updates a {@code BudgetDisplay} with a specified list index.
     */
    public void updateBudgetMonthUI(int listIndex) {
        Month budgetMonth = this.budgetMonthList.get(listIndex);

        double totalBudget = budgetMonth.getBudget().getAmount();
        double remainingBudget = budgetMonth.getRemainingBudget();
        double usedBudget = totalBudget - remainingBudget;
        double usedBudgetPercentage = usedBudget / totalBudget;

        this.budgetAmount.setText("Budget: " + Math.abs(remainingBudget) + "/" + totalBudget);
        this.budgetProgressBar.setProgress(usedBudgetPercentage);
    }

    /**
     * Updates a {@code BudgetDisplay} with the most recent {@code Month}.
     */
    public void updateObservableList(ObservableList<Month> budgetMonthList) {
        updateBudgetMonthUI(0);
    }
}
