package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.room.Room;

public class RoomListPanel extends UiPart<Region> {
    private static final String FXML = "RoomListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RoomListPanel.class);

    @javafx.fxml.FXML
    private ListView<Room> roomListView;

    /**
     * Creates a {@code RoomListPanel} with the given {@code ObservableList}.
     */
    public RoomListPanel(ObservableList<Room> roomList) {
        super(FXML);
        roomListView.setItems(roomList);
        roomListView.setCellFactory(listView -> new RoomListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Room} using a {@code RoomCard}.
     */
    class RoomListViewCell extends ListCell<Room> {
        @Override
        protected void updateItem(Room room, boolean empty) {
            super.updateItem(room, empty);

            if (empty || room == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new RoomCard(room, getIndex() + 1).getRoot());
            }
        }
    }
}
