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
     * @param budgetList Budget list to add to ListView
     */
    public BudgetListPanel(ObservableList<Budget> budgetList) {
        super(FXML);
        assert budgetList.size() > 0;
        budgetListView = new ListView<>(budgetList);
        budgetListView.setCellFactory(listview -> new BudgetListViewCell());
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
                setGraphic(new BudgetCard(budget, getIndex() + 1).getRoot());
            }
        }
    }

}
