package seedu.cakecollate.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.cakecollate.commons.core.LogsCenter;

/**
 * Panel containing the list of orders.
 */
public class HelpListPanel extends Panel {
    private final Logger logger = LogsCenter.getLogger(HelpListPanel.class);

    @FXML
    private ListView<String> listView;


    /**
     * Creates a {@code HelpCommandPanel} with the given {@code ObservableList}.
     */
    public HelpListPanel(ObservableList<String> commandDescriptionList) {
        super();
        listView.setItems(commandDescriptionList);
        listView.setCellFactory(listView -> new ListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code String command} using a {@code HelpCard}.
     */
    class ListViewCell extends ListCell<String> {
        @Override
        protected void updateItem(String command, boolean empty) {
            super.updateItem(command, empty);

            if (empty || command == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new HelpCard(command).getRoot());
            }
        }
    }

}
