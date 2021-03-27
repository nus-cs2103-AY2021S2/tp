package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

public class StreaksTab extends UiPart<Region> {

    private static final String FXML = "StreaksTab.fxml";

    @FXML
    private VBox streaksPane;

    @FXML
    private Label streaksTitle;

    @FXML
    private ListView<Person> streaksListView;

    public StreaksTab(ObservableList<Person> personList) {
        super(FXML);
        streaksTitle.setText("Streaks");
        streaksListView.setItems(personList);
        streaksListView.setCellFactory(listView -> new StreaksListViewCell());
    }

    class StreaksListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                prefWidthProperty().bind(streaksListView.widthProperty());
                setGraphic(new StreakCard(person).getRoot());
            }
        }
    }
}
