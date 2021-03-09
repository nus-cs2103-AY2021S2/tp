package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class DoctorListPanel extends UiPart<Region> {
    private static final String FXML = "DoctorListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DoctorListPanel.class);

    @FXML
    private ListView<Person> doctorListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public DoctorListPanel(ObservableList<Person> doctorList) {
        super(FXML);
        doctorListView.setItems(doctorList);
        doctorListView.setCellFactory(listView -> new DoctorListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class DoctorListViewCell extends ListCell<Person> {
        // TODO create doctor class and convert person class to doctor class
        @Override
        protected void updateItem(Person doctor, boolean empty) {
            super.updateItem(doctor, empty);

            if (empty || doctor == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DoctorCard(doctor, getIndex() + 1).getRoot());
            }
        }
    }

}
