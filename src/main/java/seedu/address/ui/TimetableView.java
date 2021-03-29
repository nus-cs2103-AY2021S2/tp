package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.scheduler.Schedulable;

/**
 * Renders a timetable onto the UI. note that a timetable consists of columns which represent a day in the
 * schedule.
 */

public class TimetableView extends UiPart<Region> {
    private static final String FXML = "TimetableWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(TimetableView.class);

    @FXML
    private GridPane timetableGrid;

    @FXML
    private AnchorPane dayScheduleOne;

    @FXML
    private AnchorPane dayScheduleTwo;

    @FXML
    private AnchorPane dayScheduleThree;

    @FXML
    private AnchorPane dayScheduleFour;

    @FXML
    private AnchorPane dayScheduleFive;

    @FXML
    private AnchorPane dayScheduleSix;

    @FXML
    private AnchorPane dayScheduleSeven;

    /**
     * Using strategy pattern. Logic for implementing timetable.
     * Used to determine node placement of a Schedulable object in the timetable UI.
     * it should also provide method to check if it can be scheduled or not.
     */
    private TimetablePlacementPolicy timetablePlacementPolicy;

    public static int NUMBER_OF_COLUMNS  = 7;

    /**
     * Renders an empty timetable.
     */
    public TimetableView() {
        super(FXML);
    }

    /**
     * Given an observable list, does the following
     * -Filters out relevant meetings to be displayed
     * -Finds the meeting position and column to slot in given the timeTablePlacementPolicy.
     * -Puts it into the timetable.
     * @param timetableSlots
     */

    public TimetableView(ObservableList<Schedulable> timetableSlots) {
        super(FXML);
    }

    /**
     * Enum representing a constant assigned to each column in the timetable
     */

    public static enum Column {
        ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN
    }

    /**
     * Puts a timetableSlot into the timetable to render, given the column to place it in
     * and the position from the top we are supposed to place it. Note that the timetableSlots must be
     * non clashing.
     * @param timetableSlot
     * @param col
     * @param position
     */

    public void putIntoSlot(TimetableSlot timetableSlot, Column col, double position) {

        AnchorPane scheduleToPut = null;
        switch(col) {
        case ONE:
            scheduleToPut = dayScheduleOne;
            break;
        case TWO:
            scheduleToPut = dayScheduleTwo;
            break;
        case THREE:
            scheduleToPut = dayScheduleThree;
            break;
        case FOUR:
            scheduleToPut = dayScheduleFour;
            break;
        case FIVE:
            scheduleToPut = dayScheduleFive;
            break;
        case SIX:
            scheduleToPut = dayScheduleSix;
            break;
        case SEVEN:
            scheduleToPut = dayScheduleSeven;
            break;
        }
        scheduleToPut.setTopAnchor(timetableSlot.getRoot(), position);
        scheduleToPut.setLeftAnchor(timetableSlot.getRoot(), 0.0);
        scheduleToPut.setRightAnchor(timetableSlot.getRoot(), 0.0);
    }

    /**
     * resets to an empty timetable.
     */
    public void reset() {
        dayScheduleOne.getChildren().clear();
        dayScheduleTwo.getChildren().clear();
        dayScheduleThree.getChildren().clear();
        dayScheduleFour.getChildren().clear();
        dayScheduleFive.getChildren().clear();
        dayScheduleSix.getChildren().clear();
        dayScheduleSeven.getChildren().clear();
    }

}
