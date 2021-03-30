package seedu.address.ui.reminderpanel;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.reminder.Reminder;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of reminders.
 */
public class ReminderListPanel extends UiPart<Region> {
    private static final String FXML = "reminderpanel/ReminderListPanel.fxml";

    @FXML
    private ListView<Reminder> reminderListView;

    /**
     * Creates a {@code ReminderListPanel} with the given {@code ObservableList}.
     */
    public ReminderListPanel(ObservableList<Reminder> reminderList) {
        super(FXML);
        reminderListView.setItems(reminderList);
        reminderListView.setCellFactory(listView -> new ReminderListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Reminder} using a {@code ReminderCard}.
     */
    class ReminderListViewCell extends ListCell<Reminder> {
        @Override
        protected void updateItem(Reminder reminder, boolean empty) {
            super.updateItem(reminder, empty);

            if (empty || reminder == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ReminderCard(reminder, getIndex() + 1).getRoot());
            }
        }
    }
}
