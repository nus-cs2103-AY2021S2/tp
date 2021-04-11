package seedu.us.among.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.us.among.commons.core.LogsCenter;
import seedu.us.among.model.endpoint.Endpoint;

/**
 * Panel containing the list of persons.
 */
public class EndpointListPanel extends UiPart<Region> {
    private static final String FXML = "EndpointListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(EndpointListPanel.class);

    @FXML
    private ListView<Endpoint> endpointListView;

    /**
     * Creates a {@code EndpointListPanel} with the given {@code ObservableList}.
     */
    public EndpointListPanel(ObservableList<Endpoint> endpointList) {
        super(FXML);
        endpointListView.setItems(endpointList);
        endpointListView.setCellFactory(listView -> new EndpointListViewCell());
    }

    public int getSelectedEndpoint() {
        return endpointListView.getSelectionModel().getSelectedIndex() + 1;
    }

    public void setEventListener(ChangeListener<? super Endpoint> listener) {
        endpointListView.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    public void focusSelectedEndpoint(Endpoint e) {
        endpointListView.getSelectionModel().select(e);
    }

    public void unfocusEndpointList() {
        endpointListView.getSelectionModel().clearSelection();
    }

    public boolean contains(Endpoint e) {
        return endpointListView.getItems().contains(e);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Endpoint} using a {@code EndpointCard}.
     */
    class EndpointListViewCell extends ListCell<Endpoint> {
        @Override
        protected void updateItem(Endpoint endpoint, boolean empty) {
            super.updateItem(endpoint, empty);

            if (empty || endpoint == null) {
                Platform.runLater(() -> {
                    setGraphic(null);
                    setText(null);
                });
            } else {
                Platform.runLater(() -> setGraphic(new EndpointCard(endpoint, getIndex() + 1).getRoot()));
            }
        }
    }

}
