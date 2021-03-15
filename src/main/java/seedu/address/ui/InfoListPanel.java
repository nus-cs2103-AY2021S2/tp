package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.plan.Plan;
import seedu.address.storage.JsonModule;

/**
 * Panel containing the list of persons.
 */
public class InfoListPanel extends UiPart<Region> {
    private static final String FXML = "PlanListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PlanListPanel.class);

    @FXML
    private ListView<JsonModule> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public InfoListPanel(ObservableList<JsonModule> planList) {
        super(FXML);
        personListView.setItems(planList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Plan} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<JsonModule> {
        @Override
        protected void updateItem(JsonModule plan, boolean empty) {
            super.updateItem(plan, empty);

            if (empty || plan == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new InfoCard(plan, getIndex() + 1).getRoot());
            }
        }
    }

}
