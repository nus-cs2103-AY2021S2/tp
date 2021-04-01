package seedu.address.ui.budgetpanel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.budget.Budget;
import seedu.address.ui.UiPart;
import seedu.address.ui.schedulepanel.ScheduleListPanel;

import java.util.logging.Logger;

public class BudgetCardPanel extends UiPart<Region> {

    private static final String FXML = "budgetpanel/BudgetCardPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ScheduleListPanel.class);

    private ListView<Budget> budgetListView;

    public BudgetCardPanel(ObservableList<Budget> budgetList) {
        super(FXML);
        if (budgetList.isEmpty()) {
            ObservableList<Budget> tempList = FXCollections.observableArrayList();
            tempList.add(new Budget("0"));
            budgetListView.setItems(tempList);
        } else {
            budgetListView.setItems(budgetList);
        }
    }

}
