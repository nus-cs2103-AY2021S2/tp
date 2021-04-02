package seedu.address.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.parser.DateTimeUtil;
import seedu.address.model.schedule.Schedulable;

/**
 * Renders a timetable onto the UI. note that a timetable consists of columns which represent a day in the
 * schedule.
 */

public class TimetableView extends UiPart<Region> {

    public static final int NUMBER_OF_COLUMNS = 7;

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

    @FXML
    private Label firstDayLabel;

    @FXML
    private Label secondDayLabel;

    @FXML
    private Label thirdDayLabel;

    @FXML
    private Label fourthDayLabel;

    @FXML
    private Label fifthDayLabel;

    @FXML
    private Label sixthDayLabel;

    @FXML
    private Label seventhDayLabel;


    /**
     * Using strategy pattern. Logic for implementing timetable.
     * Used to determine node placement of a Schedulable object in the timetable UI.
     * it should also provide method to check if it can be scheduled or not.
     */
    private TimetablePlacementPolicy timetablePlacementPolicy;

    private ObservableList<? extends Schedulable> timetableSlots;

    private ObservableValue<LocalDate> firstDayOfTimetable;

    private final ListChangeListener<Schedulable> meetingsListener = change -> {
        while (change.next()) {
            if (change.wasAdded() || change.wasRemoved()) {
                this.populateWithData(change.getList());
            }
        }
    };

    private final ChangeListener<LocalDate> dateListener = (observable, oldValue, newValue) -> {
        resetColumns();
        timetablePlacementPolicy = new TimetablePlacementPolicy(newValue);
        populateWithData(timetableSlots);
        refreshDayLabels(newValue);
    };


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

    public TimetableView(ObservableList<? extends Schedulable> timetableSlots,
                         ObservableValue<LocalDate> firstDayOfTimetable) {
        super(FXML);
        this.timetableSlots = timetableSlots;
        this.firstDayOfTimetable = firstDayOfTimetable;
        this.timetablePlacementPolicy = new TimetablePlacementPolicy(firstDayOfTimetable.getValue());
        populateWithData(timetableSlots);
        refreshDayLabels(firstDayOfTimetable.getValue());
        //add Listener
        timetableSlots.addListener(this.meetingsListener);
        firstDayOfTimetable.addListener(this.dateListener);
    }

    public void setTimetablePlacementPolicy(TimetablePlacementPolicy policy) {
        this.timetablePlacementPolicy = policy;
    }

    /**
     * Clears old data and populates the view with new da
     * @param obsList
     */

    public void populateWithData(ObservableList<? extends Schedulable> obsList) {
        resetColumns();
        List<? extends Schedulable> processedList = obsList.stream()
                .filter(timetablePlacementPolicy :: test)
                .flatMap(timetablePlacementPolicy :: breakIntoDayUnits)
                .collect(Collectors.toList());
        for (Schedulable schedulable : processedList) {
            double slotLength = timetablePlacementPolicy.getLengthOfSlot(schedulable);
            String header = getHeader(schedulable);
            Column col = timetablePlacementPolicy.getColumnPlacement(schedulable);
            double position = timetablePlacementPolicy.getVerticalPosition(schedulable);
            TimetableSlot slotToAdd = new TimetableSlot(slotLength, header);
            putIntoSlot(slotToAdd, col, position);
        }
    }

    /**
     * Given the schedulable object, returns a nice header consisting of the name, followed by the timestamp below
     * (h:mm a - h:mm a)
     * @param schedulable
     * @return
     */

    public String getHeader(Schedulable schedulable) {
        LocalTime startTime = schedulable.getStartLocalDateTime().toLocalTime();
        LocalTime endTime = schedulable.getTerminateLocalDateTime().toLocalTime();
        return schedulable.getNameString()
                + "\n"
                + DateTimeUtil.prettyPrintFormatLocalTime(startTime)
                + " - "
                + DateTimeUtil.prettyPrintFormatLocalTime(endTime);
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
    public void resetColumns() {
        dayScheduleOne.getChildren().clear();
        dayScheduleTwo.getChildren().clear();
        dayScheduleThree.getChildren().clear();
        dayScheduleFour.getChildren().clear();
        dayScheduleFive.getChildren().clear();
        dayScheduleSix.getChildren().clear();
        dayScheduleSeven.getChildren().clear();
    }

    /**
     * refreshes all day header labels display day of the week starting
     * on the currentDate.
     */
    public void refreshDayLabels(LocalDate currentDate) {
        firstDayLabel.setText(generateDayLabel(currentDate));
        secondDayLabel.setText(generateDayLabel(currentDate.plusDays(1)));
        thirdDayLabel.setText(generateDayLabel(currentDate.plusDays(2)));
        fourthDayLabel.setText(generateDayLabel(currentDate.plusDays(3)));
        fifthDayLabel.setText(generateDayLabel(currentDate.plusDays(4)));
        sixthDayLabel.setText(generateDayLabel(currentDate.plusDays(5)));
        seventhDayLabel.setText(generateDayLabel(currentDate.plusDays(6)));
    }

    /**
     * generate a nicely formatted String to display on the column headers
     * @param date the date corresponding to the column to generate the header for
     * @return
     */

    public String generateDayLabel(LocalDate date) {
        return DateTimeUtil.prettyPrintFormatLocalDate(date)
                + "\n"
                + date.getDayOfWeek().name();
    }





}
