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
public class TutorListPanel extends UiPart<Region> {
    private static final String FXML = "TutorListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TutorListPanel.class);

    @FXML
    private ListView<Person> tutorListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public TutorListPanel(ObservableList<Person> tutorList) {
        super(FXML);
        tutorListView.setItems(tutorList);
        tutorListView.setCellFactory(listView -> new TutorListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class TutorListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TutorCard(person, getIndex() + 1).getRoot());
            }
        }
    }
}
