package seedu.address.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;

public class GroupListPanel extends UiPart<Region> {
    private static final String FXML = "GroupListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(GroupListPanel.class);

    @FXML
    private ListView<Name> groupListView;
    /**
     * Creates a {@code GroupListPanel} with the given {@code ObservableMap}.
     */
    public GroupListPanel(ObservableMap<Name, Group> groupMap) {
        super(FXML);
        groupListView.getItems().addAll(groupMap.keySet());
        groupMap.addListener((MapChangeListener<Name, Group>) change -> {
            if (change.wasAdded()) {
                groupListView.getItems().removeAll(change.getKey());
                groupListView.getItems().add(change.getKey());
            }
            if (change.wasRemoved()) {
                groupListView.getItems().removeAll(change.getKey());
            }
            Platform.runLater(() -> {
                groupListView.getSelectionModel().select(change.getKey());
                groupListView.scrollTo(change.getKey());
            });
        });
        groupListView.setCellFactory(listView -> new GroupListPanel.GroupListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a Group {@code Name}.
     */
    class GroupListViewCell extends ListCell<Name> {
        @Override
        protected void updateItem(Name groupName, boolean empty) {
            super.updateItem(groupName, empty);

            if (empty || groupName == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new Label(groupName.fullName));
            }
        }
    }
}
