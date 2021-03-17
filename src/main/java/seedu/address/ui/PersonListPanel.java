package seedu.address.ui;

import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.DisplayFilterPredicate;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {

    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> personListView;

    private DisplayFilterPredicate displayFilterPredicate;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList,
            DisplayFilterPredicate displayFilterPredicate) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        this.displayFilterPredicate = displayFilterPredicate;
    }

    /**
     * Updates the filter used for PersonCard fields. Forces the listview to re-draw it's content.
     * @param displayFilterPredicate display filter
     */
    public void updateDisplayFilter(DisplayFilterPredicate displayFilterPredicate) {
        this.displayFilterPredicate = displayFilterPredicate;
        this.personListView.refresh();
    }

    /**
     * Selects the previous item in the ListView and returns the result.
     *
     * @param callback accept value for demo purposes.
     */
    public void selectPrev(Consumer<String> callback) {
        personListView.getSelectionModel().selectPrevious();
        int selectedIndex = personListView.getSelectionModel().getSelectedIndex();
        personListView.scrollTo(selectedIndex);
        callback.accept(String.valueOf(selectedIndex + 1));
    }

    /**
     * Selects the previous item in the ListView and returns the result.
     *
     * @param callback accept value for demo purposes.
     */
    public void selectNext(Consumer<String> callback) {
        personListView.getSelectionModel().selectNext();
        int selectedIndex = personListView.getSelectionModel().getSelectedIndex();
        personListView.scrollTo(selectedIndex);
        callback.accept(String.valueOf(selectedIndex + 1));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code
     * PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {

        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(
                        new PersonCard(person, getIndex() + 1, displayFilterPredicate).getRoot());
            }
        }
    }

}
