package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.person.passenger.Passenger;

/**
 * Panel containing the list of passengers.
 */
public class FilteredPassengerListPanel extends UiPart<Region> {
    private static final String FXML = "PassengerListPanel.fxml";
    @FXML
    private ListView<Passenger> passengerListView;

    /**
     * Creates a {@code PassengerListPanel} with the given {@code ObservableList}.
     */
    public FilteredPassengerListPanel(ObservableList<Passenger> passengerList) {
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
