package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.date.ImportantDate;

/**
 * Panel containing the list of Important Dates.
 */
public class DateListPanel extends UiPart<Region> {

    private static final String FXML = "DateListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DateListPanel.class);

    @javafx.fxml.FXML
    private ListView<ImportantDate> importantDateListView;

    /**
     * Creates a {@code DateListPanel} with the given {@code ObservableList}.
     */
    public DateListPanel(ObservableList<ImportantDate> importantDatesList) {
        super(FXML);
        importantDateListView.setItems(importantDatesList);
        importantDateListView.setCellFactory(listView -> new DateListPanel.DateListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class DateListViewCell extends ListCell<ImportantDate> {
        @Override
        protected void updateItem(ImportantDate importantDate, boolean empty) {
            super.updateItem(importantDate, empty);

            if (empty || importantDate == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DateCard(importantDate, getIndex() + 1).getRoot());
            }
        }
    }

}
