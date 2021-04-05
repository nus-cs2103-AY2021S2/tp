package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.PersonStreak;

public class StreaksTab extends UiPart<Region> {

    private static final String FXML = "StreaksTab.fxml";

    @FXML
    private VBox streaksPane;

    @FXML
    private Label streaksTitle;

    @FXML
    private ListView<PersonStreak> streaksListView;

    /**
     * Creates a {@code StreaksTab} with the given {@code ObservableList}.
     */
    public StreaksTab(ObservableList<PersonStreak> personStreaks) {
        super(FXML);
        streaksTitle.setText("Streaks");

        //Prevent mouse selection
        streaksListView.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                event.consume();
            }
        });

        streaksListView.setItems(personStreaks);
        streaksListView.setCellFactory(listView -> new StreaksListViewCell());
    }

    class StreaksListViewCell extends ListCell<PersonStreak> {
        @Override
        protected void updateItem(PersonStreak personStreak, boolean empty) {
            super.updateItem(personStreak, empty);

            if (empty || personStreak == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StreakCard(personStreak).getRoot());
            }
        }
    }
}
