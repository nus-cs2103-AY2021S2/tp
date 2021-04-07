package seedu.address.ui.calendar.schedule;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.Event;
import seedu.address.model.EventList;
import seedu.address.ui.UiPart;

/**
 * Represents a event list for the bottom of {@code UpcomingSchedule} GUI.
 */
public class DayEventListPanel extends UiPart<Region> {
    private static final String FXML = "schedule/DayEventListPanel.fxml";

    private EventList events;

    @FXML
    private ListView<Event> eventListView;

    /**
     * Constructs new {@code DayEventList} to show events in {@code UpcomingSchedule}.
     */
    public DayEventListPanel() {
        super(FXML);
        this.events = new EventList();
    }

    /**
     * Updates display of {@code Events} for a day in the {@code UpcomingSchedule}.
     *
     * @param events {@code EventList} for the day.
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
     * Custom {@code ListCell} that displays the graphics of a {@code Event} using a {@code CalendarEventCard}.
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
