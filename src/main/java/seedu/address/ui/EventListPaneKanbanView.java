package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Event;

/**
 * Panel containing the list of persons.
 */
public class EventListPaneKanbanView extends UiPart<Region> {
    private static final String FXML = "EventListPaneKanbanView.fxml";

    private final Logger logger = LogsCenter.getLogger(EventListPaneKanbanView.class);

    @FXML
    private ListView<Event> eventListView;

    /**
     * Creates a {@code EventListPaneKanbanView} with the given {@code ObservableList}.
     */
    public EventListPaneKanbanView(ObservableList<Event> eventList) {
        super(FXML);
        eventListView.setItems(eventList);
        eventListView.setCellFactory(listView -> new EventListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Event} using a {@code EventCardKanbanView}.
     */
    class EventListViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EventCardKanbanView(event, event.getIdentifier()).getRoot());
            }
        }
    }

}
