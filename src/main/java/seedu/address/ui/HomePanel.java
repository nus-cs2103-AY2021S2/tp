package seedu.address.ui;

import java.util.logging.Logger;

import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel displaying home screen.
 */
public class HomePanel extends UiPart<Region> {
    private static final String FXML = "HomePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(HomePanel.class);

    /**
     * Creates a {@code HomePanel}.
     */
    public HomePanel() {
        super(FXML);
    }

}
