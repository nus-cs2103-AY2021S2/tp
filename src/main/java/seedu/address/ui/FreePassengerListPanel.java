package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.passenger.Passenger;

/**
 * Panel containing the list of passengers.
 */
public class FreePassengerListPanel extends UiPart<Region> {
    private static final String FXML = "PassengerListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FreePassengerListPanel.class);

    @FXML
    private ListView<Passenger> passengerListView;

    /**
     * Creates a {@code PassengerListPanel} with the given {@code ObservableList}.
     */
    public FreePassengerListPanel(ObservableList<Passenger> passengerList) {
        super(FXML);
        passengerListView.setItems(passengerList);
        passengerListView.setCellFactory(listView -> new FreePassengerListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Passenger} using a {@code PassengerCard}.
     */
    class FreePassengerListViewCell extends ListCell<Passenger> {
        @Override
        protected void updateItem(Passenger passenger, boolean empty) {
            super.updateItem(passenger, empty);

            if (empty || passenger == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PassengerCard(passenger, getIndex() + 1).getRoot());
            }
        }
    }

}
