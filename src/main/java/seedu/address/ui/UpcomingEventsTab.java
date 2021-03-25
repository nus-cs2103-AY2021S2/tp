package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.PersonEvent;

public class UpcomingEventsTab extends UiPart<Region> {

    private static final String FXML = "UpcomingEventsTab.fxml";

    @FXML
    private VBox upcomingEventsPane;

    @FXML
    private Label upcomingEventsTitle;

    @FXML
    private ListView<PersonEvent> upcomingEventsListView;

    /**
     * Creates a {@code UpcomingEventsTab} with the given {@code ObservableList}.
     * @param upcomingDatesList A list of upcoming events.
     */
    public UpcomingEventsTab(ObservableList<PersonEvent> upcomingDatesList) {
        super(FXML);
        upcomingEventsTitle.setText("Upcoming Events");
        upcomingEventsListView.setItems(upcomingDatesList);
        upcomingEventsListView.setCellFactory(listView -> new UpcomingEventsListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an upcoming date.
     */
    class UpcomingEventsListViewCell extends ListCell<PersonEvent> {
        @Override
        protected void updateItem(PersonEvent personEvent, boolean empty) {
            super.updateItem(personEvent, empty);

            if (empty || personEvent == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new UpcomingDateCard(personEvent).getRoot());
            }
        }
    }
}
