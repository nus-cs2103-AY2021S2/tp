package seedu.address.ui.budgetpanel;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.budget.Budget;
import seedu.address.ui.UiPart;

public class BudgetListPanel extends UiPart<Region> {

    private static final String FXML = "budgetpanel/BudgetListPanel.fxml";

    @FXML
    private ListView<Budget> budgetListView;

    /**
     * Primary constructor.
     * Instantiate a placeholder if there is no existing budget in the {@code budgetList}.
     *
     * @param budgetList Budget list to add to ListView
     */
    public BudgetListPanel(ObservableList<Budget> budgetList) {
        super(FXML);
        budgetListView.setItems(budgetList);
        budgetListView.setCellFactory(listview -> new BudgetListViewCell());

        /* If empty, shows a placeholder with budget $0 */
        budgetListView.setMinHeight(60);
        budgetListView.setMaxHeight(100);
        BudgetCard budgetCard = new BudgetCard(new Budget("0"));
        budgetCard.getRoot().setStyle("-fx-background-color: #515658;");
        budgetListView.setPlaceholder(budgetCard.getRoot());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Budget} using a
     * {@code BudgetCard}.
     */
    class BudgetListViewCell extends ListCell<Budget> {
        @Override
        protected void updateItem(Budget budget, boolean empty) {
            super.updateItem(budget, empty);

            if (empty || budget == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new BudgetCard(budget).getRoot());
            }
        }
    }
}
