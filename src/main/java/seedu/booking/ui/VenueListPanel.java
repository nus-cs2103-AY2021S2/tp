package seedu.booking.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.booking.commons.core.LogsCenter;
import seedu.booking.model.venue.Venue;


/**
 * Panel containing the list of persons.
 */
public class VenueListPanel extends UiPart<Region> {
    private static final String FXML = "VenueListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(VenueListPanel.class);

    @FXML
    private ListView<Venue> venueListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public VenueListPanel(ObservableList<Venue> bookingList) {
        super(FXML);
        venueListView.setItems(bookingList);
        venueListView.setCellFactory(listView -> new VenueListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class VenueListViewCell extends ListCell<Venue> {
        @Override
        protected void updateItem(Venue venue, boolean empty) {
            super.updateItem(venue, empty);

            if (empty || venue == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new VenueCard(venue, getIndex() + 1).getRoot());
            }
        }
    }

}
