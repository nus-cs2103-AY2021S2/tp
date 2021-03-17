package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class AutocompleteListCell extends UiPart<Region> {

    private static final String FXML = "AutocompleteListCell.fxml";

    @FXML
    private Label commandName;

    /**
     * Creates an {@code AutocompleteListCell} with the given {@code Name} to display.
     *
     */
    public AutocompleteListCell(String name) {
        super(FXML);
        this.commandName.setText(name);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AutocompleteListCell)) {
            return false;
        }

        // state check
        AutocompleteListCell card = (AutocompleteListCell) other;
        return this.commandName == card.commandName;
    }
}
