package seedu.address.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.schedule.Schedulable;

/**
 * Renders a timetable onto the UI. note that a timetable consists of columns which represent a day in the
 * schedule.
 */

public class TimetableView extends UiPart<Region> {

    public static final int NUMBER_OF_COLUMNS = 7;

    private static final String FXML = "TimetableWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(TimetableView.class);
    private final ListChangeListener<Schedulable> listener = change -> {
        while (change.next()) {
            if (change.wasAdded() || change.wasRemoved()) {
                this.populateWithData(change.getList());
            }
        }
    };


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

    private ObservableList<? extends Schedulable> timetableSlots;

    private LocalDate firstDayOfTimetable;


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

    public TimetableView(ObservableList<? extends Schedulable> timetableSlots, LocalDate firstDayOfTimetable) {
        super(FXML);
        this.timetableSlots = timetableSlots;
        this.firstDayOfTimetable = firstDayOfTimetable;
        this.timetablePlacementPolicy = new TimetablePlacementPolicy(firstDayOfTimetable);
        populateWithData(timetableSlots);
        //add Listener
        timetableSlots.addListener(this.listener);
    }

    public void setTimetablePlacementPolicy(TimetablePlacementPolicy policy) {
        this.timetablePlacementPolicy = policy;
    }

    /**
     * Clears old data and populates the view with new da
     * @param obsList
     */

    public void populateWithData(ObservableList<? extends Schedulable> obsList) {
        reset();
        List<? extends Schedulable> processedList = obsList.stream()
                .filter(timetablePlacementPolicy :: test)
                .flatMap(timetablePlacementPolicy :: breakIntoUnits)
                .collect(Collectors.toList());
        for (Schedulable schedulable : processedList) {
            double slotLength = timetablePlacementPolicy.getLengthOfSlot(schedulable);
            String name = schedulable.getNameString();
            Column col = timetablePlacementPolicy.getColumnPlacement(schedulable);
            double position = timetablePlacementPolicy.getVerticalPosition(schedulable);
            TimetableSlot slotToAdd = new TimetableSlot(slotLength, name);
            putIntoSlot(slotToAdd, col, position);
        }
    }

    /**
     * Enum representing an assigned column in the timetable.
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

        scheduleToPut.getChildren().addAll(timetableSlot.getRoot());
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
