package seedu.iscam.ui;

import java.util.logging.Logger;

import javax.swing.plaf.synth.Region;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.iscam.commons.core.LogsCenter;
import seedu.iscam.model.client.InsurancePlan;

/**
 * Panel containing the list of plans.
 */
public class PlanListPanel extends UiPart<Region> {
    private static final String FXML = "PlanListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MeetingListPanel.class);

    @FXML
    private ListView<InsurancePlan> planListView;

    /**
     * Creates a {@code planListPanel} with the given {@code ObservableList}.
     */
    public PlanListPanel(ObservableList<InsurancePlan> planList) {
        super(FXML);
        planListView.setItems(planList);
        planListView.setCellFactory(listView -> new PlanListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code plan} using a {@code insuranceCard}.
     */
    static class PlanListViewCell extends ListCell<InsurancePlan> {
        @Override
        protected void updateItem(InsurancePlan plan, boolean empty) {
            super.updateItem(plan, empty);

            if (empty || plan == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PlanCard(plan).getRoot());
            }
        }
    }
}
