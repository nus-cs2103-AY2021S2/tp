package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * A ui for the FiltersPanel containing filter lists.
 */
public class FiltersPanel extends UiPart<Region> {

    private static final String FXML = "FiltersPanel.fxml";

    private FilterList tutorFilterList;
    private FilterList appointmentFilterList;

    @FXML
    private StackPane tutorFilterListPlaceholder;

    @FXML
    private StackPane appointmentFilterListPlaceholder;

    /**
     * Creates a {@code FiltersPanel}.
     */
    public FiltersPanel() {
        super(FXML);
    }

    void fillInnerParts(ObservableList<String> tutorFilterStringList,
            ObservableList<String> appointmentFilterStringList) {
        tutorFilterList = new FilterList(tutorFilterStringList);
        tutorFilterListPlaceholder.getChildren().add(tutorFilterList.getRoot());

        appointmentFilterList = new FilterList(appointmentFilterStringList);
        appointmentFilterListPlaceholder.getChildren().add(appointmentFilterList.getRoot());
    }
}
