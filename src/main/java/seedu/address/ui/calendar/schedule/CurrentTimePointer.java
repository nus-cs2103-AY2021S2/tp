package seedu.address.ui.calendar.schedule;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.ui.UiPart;

import java.util.logging.Logger;

/**
 * Represents a current time pointer in the {@code TimeScale} schedule UI.
 */
public class CurrentTimePointer extends UiPart<Region> {
    //Code adapted from https://github.com/AY2021S1-CS2103T-T12-3/tp
    private static final String FXML = "schedule/CurrentTimePointer.fxml";
    private static Logger logger = LogsCenter.getLogger(CurrentTimePointer.class);

    @FXML
    private Label currentTime;
    @FXML
    private HBox currentTimePointer;

    /**
     * Constructor of CurrentTimePointer
     *
     * @param time must be the format of hh:mm AM/PM
     */
    public CurrentTimePointer(String time) {
        super(FXML);
        currentTime.setText(time);
        logger.info("successfully initialised current time pointer");
    }

    public void updateTime(String time) {
        currentTime.setText(time);
    }
}
