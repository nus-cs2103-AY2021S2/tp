package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

public class PersonDetailsTab extends UiPart<Region> {

    private static final String FXML = "PersonDetailsTab.fxml";

    @FXML
    private VBox personDetailsPane;

    @FXML
    private Label personDetailsTitle;

    @FXML
    private ListView<Person> personDetailsListView;

    /**
     * Creates a {@code PersonDetailsTab} with the given {@code ObservableList}.
     * @param detailedPerson A list containing a single {@code Person}.
     */
    public PersonDetailsTab(ObservableList<Person> detailedPerson) {
        super(FXML);
        personDetailsTitle.setText("Contact Details");
        personDetailsListView.setItems(detailedPerson);
        personDetailsListView.setCellFactory(listView -> new PersonDetailsListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the full details of a {@code Person}.
     */
    class PersonDetailsListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonDetailsCard(person).getRoot());
            }
        }
    }
}
