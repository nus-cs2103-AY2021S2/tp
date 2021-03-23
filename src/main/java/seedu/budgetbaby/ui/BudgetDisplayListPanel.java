package seedu.budgetbaby.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.budgetbaby.commons.core.LogsCenter;
import seedu.budgetbaby.model.month.Month;

import java.util.logging.Logger;

/**
 * Panel containing the list of budget months.
 * Only one month will be displaying at one time.
 * A list panel is created to facilitate event listener.
 */
public class BudgetDisplayListPanel extends UiPart<Region> {

    private static final String FXML = "BudgetDisplayListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(BudgetDisplayListPanel.class);

    @FXML
    private ListView<Month> budgetMonthListView;

    /**
     * Creates a {@code FinancialRecordListPanel} with the given {@code ObservableList}.
     */
    public BudgetDisplayListPanel(ObservableList<Month> budgetMonthList) {
        super(FXML);
        budgetMonthListView.setItems(budgetMonthList);
        budgetMonthListView.setCellFactory(listView -> new BudgetDisplayListPanel.BudgetDisplayListViewCell());
        budgetMonthListView.setPrefHeight(150); // TODO: temp fix for budget display panel height
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code FinancialRecord}
     * using a {@code FinancialRecordCard}.
     */
    class BudgetDisplayListViewCell extends ListCell<Month> {
        @Override
        protected void updateItem(Month month, boolean empty) {
            super.updateItem(month, empty);

            if (empty || month == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new BudgetDisplay(month).getRoot());
            }
        }
    }
}
