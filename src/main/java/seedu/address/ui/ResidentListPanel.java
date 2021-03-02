package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.resident.Resident;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Resident> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Resident> residentList) {
        super(FXML);
        personListView.setItems(residentList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Resident} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Resident> {
        @Override
        protected void updateItem(Resident resident, boolean empty) {
            super.updateItem(resident, empty);

            if (empty || resident == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(resident, getIndex() + 1).getRoot());
            }
        }
    }

}
