package seedu.address.ui.calendar.schedule;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.ui.UiPart;

import java.util.logging.Logger;

/**
 * Represents cells to construct the backbone of {@Code TimeScale}.
 */
public class TimeScaleCell extends UiPart<Region> {
    //Code adapted from https://github.com/AY2021S1-CS2103T-T12-3/tp
    private static final String FXML = "schedule/TimeScaleCell.fxml";
    private static Logger logger = LogsCenter.getLogger(TimeScaleCell.class);

    @FXML
    private Label time;

    private String timeStr;

    /**
     * Constructs TimeScaleCell for the timeline.
     *
     * @param timeString String format of time.
     */
    public TimeScaleCell(String timeString) {
        super(FXML);
        time.setText(timeString);
        //memoize the time, for hiding purpose.
        this.timeStr = timeString;
        logger.info("successfully initialised time scale cell");
    }

    public void hideTime() {
        time.setText("  ");
    }

    public void recoverTime() {
        time.setText(timeStr);
    }
}
