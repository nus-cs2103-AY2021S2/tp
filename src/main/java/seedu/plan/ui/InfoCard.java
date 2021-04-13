package seedu.plan.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.plan.storage.JsonModule;

/**
 * An UI component that displays information of a {@code Plan}.
 */
public class InfoCard extends UiPart<Region> {

    private static final String FXML = "InfoListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final JsonModule plan;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Plan} and index to display.
     */
    public InfoCard(JsonModule module, int displayedIndex) {
        super(FXML);
        this.plan = module;
        id.setText(displayedIndex + ". ");
        description.setText(module.toString());
    }
}
