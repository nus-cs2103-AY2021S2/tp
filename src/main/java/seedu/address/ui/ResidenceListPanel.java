package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.residence.Residence;

public class ResidenceListPanel extends UiPart<Region> {
    private static final String FXML = "ResidenceListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ResidenceListPanel.class);

    @javafx.fxml.FXML
    private ListView<Residence> residenceListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ResidenceListPanel(ObservableList<Residence> residenceList) {
        super(FXML);
        residenceListView.setItems(residenceList);
        residenceListView.setCellFactory(listView -> new ResidenceListPanel.ResidenceListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ResidenceListViewCell extends ListCell<Residence> {
        @Override
        protected void updateItem(Residence residence, boolean empty) {
            super.updateItem(residence, empty);

            if (empty || residence == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ResidenceCard(residence, getIndex() + 1).getRoot());
            }
        }
    }

}
