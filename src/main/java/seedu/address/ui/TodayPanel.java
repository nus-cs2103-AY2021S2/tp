package seedu.address.ui;

import java.util.logging.Logger;

import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel displaying today screen.
 */
public class TodayPanel extends UiPart<Region> {
    private static final String FXML = "TodayPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TodayPanel.class);

    /**
     * Creates a {@code TodayPanel}.
     */
    public TodayPanel() {
        super(FXML);
    }

}
