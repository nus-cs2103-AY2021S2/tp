package seedu.address.ui.calendar.schedule;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.Event;
import seedu.address.model.EventList;
import seedu.address.ui.UiPart;

/**
 * UI event list for the calendar window.
 */
public class DayEventList extends UiPart<Region> {
    private static final String FXML = "schedule/DayEventListPanel.fxml";

    private EventList events;

    @FXML
    private ListView<Event> eventListView;
    @FXML
    private StackPane stackPane;

    /**
     * Creates new {@code DayEventList} to show events in {@code UpcomingSchedule}.
     */
    public DayEventList() {
        super(FXML);
        this.events = new EventList();
    }

    /**
     * Updates display of {@code Events} for a day in the {@code CalendarWindow}.
     *
     * @param events events list for the day.
     */
    public void updateList(EventList events) {
        this.events = events;
        clearDayEvents();
        loadDayEvents();
    }

    private void clearDayEvents() {
        eventListView.getItems().clear();
    }

    private void loadDayEvents() {
        for (Event event : events.getEvents()) {
            eventListView.getItems().add(event);
        }
        eventListView.setCellFactory(listView -> new EventListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class EventListViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CalendarEventCard(event).getRoot());
            }
        }
    }
}
