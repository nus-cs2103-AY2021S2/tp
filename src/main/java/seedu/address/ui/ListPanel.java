package seedu.address.ui;
import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;


/**
 * Panel containing the list of bugs.
 */
public class ListPanel extends UiPart<Region> {
    private static final String FXML = "ListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(EventListPaneKanbanView.class);
    private final Logic logic;
    private int statusSize;

    @FXML
    private VBox mainColumn;

    /**
     * Creates a {@code BugListPanel} with the given {@code ObservableList}.
     */
    public ListPanel(Logic logic) {
        super(FXML);
        this.logic = logic;
        fillKanban();
    }

    private void fillKanban() {
        fillColumn(mainColumn, "all");
    }

    private void fillColumn(VBox column, String title) {
        EventListPaneListView eventListPanel = new EventListPaneListView(logic.getFilteredEventList());
        VBox.setVgrow(eventListPanel.getRoot(), Priority.ALWAYS);

        Label label = new Label();
        label.textProperty().bind(
                Bindings.size(logic.getFilteredEventList())
                        .asString("Number of events: %1$s"));
        label.getStyleClass().add("status");

        column.getChildren().addAll(label, eventListPanel.getRoot());
    }

}

