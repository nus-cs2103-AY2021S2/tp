package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.PersonEvent;

import java.util.logging.Logger;

public class DetailsBarPanel extends UiPart<Region> {
    private static final String FXML = "DetailsBarPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DetailsBarPanel.class);

    @FXML
    private Label title;

    @FXML
    private ListView<PersonEvent> detailsListView;

    public DetailsBarPanel(ObservableList<PersonEvent> detailsList) {
        super(FXML);
        title.setText("Upcoming Events");
        detailsListView.setItems(detailsList);
        detailsListView.setCellFactory(listView -> new DetailsListViewCell());
    }

    class DetailsListViewCell extends ListCell<PersonEvent> {
        @Override
        protected void updateItem(PersonEvent personEvent, boolean empty) {
            super.updateItem(personEvent, empty);

            if (empty || personEvent == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new UpcomingDateCard(personEvent).getRoot());
            }
        }
    }
}
