package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.event.GeneralEvent;

/**
 * Panel containing the list of general events.
 */
public class EventListPanel extends UiPart<Region> {
    private static final String FXML = "EventListPanel.fxml";

    @FXML
    private ListView<GeneralEvent> eventListView;

    /**
     * Creates a {@code EventListPanel} with the given {@code ObservableList}.
     */
    public EventListPanel(ObservableList<GeneralEvent> eventList) {
        super(FXML);
        eventListView.setItems(eventList);
        eventListView.setCellFactory(listView -> new EventListPanel.EventListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code GeneralEvent} using a {@code EventCard}.
     */
    class EventListViewCell extends ListCell<GeneralEvent> {
        @Override
        protected void updateItem(GeneralEvent generalEvent, boolean empty) {
            super.updateItem(generalEvent, empty);

            if (empty || generalEvent == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EventCard(generalEvent, getIndex() + 1).getRoot());
            }
        }
    }

}
