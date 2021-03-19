package seedu.cakecollate.ui;

import javafx.scene.layout.Region;

abstract class Panel extends UiPart<Region> {
    private static final String FXML = "ListPanel.fxml";
    /**
     * Creates a {@code OrderListPanel} with the given {@code ObservableList}.
     */

    public Panel() {
        super(FXML);
    }
}
