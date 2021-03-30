package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A ui for a FilterCard that contains a filter string.
 */
public class FilterCard extends UiPart<Region> {

    private static final String FXML = "FilterCard.fxml";

    @FXML
    private Label filterString;

    /**
     * Creates a {@code FilterCard} with the given {@code filterString}.
     */
    public FilterCard(String filterString) {
        super(FXML);
        this.filterString.setText(filterString);
    }
}
