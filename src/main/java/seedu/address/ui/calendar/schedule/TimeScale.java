package seedu.address.ui.calendar.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Event;
import seedu.address.model.EventList;
import seedu.address.ui.UiPart;


/**
 * Represents the backbone of the time scale of the {@Code UpcomingSchedule}.
 */
public class TimeScale extends UiPart<Region> {
    //Code adapted from https://github.com/AY2021S1-CS2103T-T12-3/tp
    private static final String FXML = "schedule/TimeScale.fxml";
    private static Logger logger = LogsCenter.getLogger(TimeScale.class);

    // Solution adapted from Stack Overflow
    // https://stackoverflow.com/questions/25498747/javafx-gridpane-observablelist-and-listchangelistener
    private final ListChangeListener<Event> eventListener = new ListChangeListener<Event>() {
        @Override
        public void onChanged(Change<? extends Event> c) {
            while (c.next()) {
                if (c.wasRemoved()) {
                    for (Event event : c.getRemoved()) {
                        //removeTaskFromTimeScale(event);
                    }
                }

                if (c.wasAdded()) {
                    for (Event event : c.getAddedSubList()) {
                        //addTaskToTimeScale(task);
                    }
                }


                return;
            }
        }
    };

    private List<TimeScaleCell> timeScaleCells;
    private CurrentTimePointer currentTimePointer;
    private EventList events;

    @FXML
    private StackPane timeScale;
    @FXML
    private ScrollPane scrollPane;

    /**
     * Constructs a new TimeScale.
     */
    public TimeScale() {
        super(FXML);
        this.events = new EventList();
        this.timeScaleCells = new ArrayList<>();

        //ui set-up
        init();
        logger.info("time scale successfully initialised");
    }

    private void init() {
        setUpTimeScale();
        logger.info("successfully initialised all time scale cells");
        //style, temporary, move to css/fxml
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);

        loadTimeScale();
    }

    private void loadTimeScale() {
        setMargin();
        logger.info("successfully loaded all time scale cells");
        addEventsToTimeScale();
        logger.info("successfully loaded all event cells");
    }

    private void clearTimeScale() {
        timeScale.getChildren().clear();
        logger.info("successfully cleared time scale children nodes");
    }

    /**
     * Updates time scale for schedule.
     *
     * @param events events to be loaded as event cells in schedule.
     */
    public void updateTimeScale(EventList events) {
        this.events = events;
        logger.info("sucessfully updated events list");
        clearTimeScale();
        loadTimeScale();
    }

    private void addEventsToTimeScale() {
        //add taskCell
        for (Event event : events.getEvents()) {
            addEventToTimeScale(event);
        }
    }

    private void addEventToTimeScale(Event event) {
        EventCell eventCell = new EventCell(event);

        //taskMapper.put(event, eventCell);

        timeScale.getChildren().add(eventCell.getRoot());
        timeScale.setMargin(eventCell.getRoot(), new Insets(eventCell.marginTop(), 0, 0, 60));
    }

    private void setUpTimeScale() {
        //set morning
        timeScaleCells.add(new TimeScaleCell("12 AM"));
        for (int i = 1; i < 12; i++) {
            timeScaleCells.add(new TimeScaleCell(i + " AM"));
        }

        //set noon
        timeScaleCells.add(new TimeScaleCell("Noon"));

        //set afternoon
        for (int i = 1; i < 12; i++) {
            timeScaleCells.add(new TimeScaleCell(i + " PM"));
        }

        //repeat 12 AM
        timeScaleCells.add(new TimeScaleCell("12 AM"));
    }

    /*
    private void addEventToTimeScale(int eventId) {
        //addEventToTimeScale(events.get(eventId));
    }

    private void removeTaskFromTimeScale(Event event) {
        //timeScale.getChildren().remove(taskMapper.get(event).getRoot());
    }
    */

    /**
     * Inserts the {@Code TimeScaleCells} into the time scale.
     */
    private void setMargin() {
        for (int i = 0; i < timeScaleCells.size(); i++) {
            timeScale.getChildren().add(timeScaleCells.get(i).getRoot());
            timeScale.setMargin(timeScaleCells.get(i).getRoot(), new Insets(i * 40, 0, 0, 0));
        }
    }

    /**
     * Places a {@Code Node} in the TimeScale with marginTop being {@Code marginTop}.
     *
     * @param node node to place
     * @param marginTop marginTop of the node
     */
    public void placeItem(Node node, double marginTop) {
        timeScale.getChildren().add(node);
        timeScale.setMargin(node, new Insets(marginTop, 0, 0, 0));
    }

    /**
     * Removes the {@Code Node} in the TimeScale.
     *
     * @param node node to remove
     */
    public void removeItem(Node node) {
        timeScale.getChildren().remove(node);
        this.currentTimePointer = null;
        timeScaleCells.forEach(timeScaleCell -> timeScaleCell.recoverTime());
    }

    /**
     * Places the {@Code CurrentTimePointer} with the initial {@Code marginTop}.
     *
     * @param marginTop initial marginTop
     */
    public void placeCurrentTime(CurrentTimePointer currentTimePointer, double marginTop) {
        placeItem(currentTimePointer.getRoot(), marginTop);
        this.currentTimePointer = currentTimePointer;
    }

    /**
     * Updates the position of {@CurrentTimePosition}.
     *
     * @param newMarginTop
     */
    public void updateCurrentTimePosition(double newMarginTop) {
        timeScale.setMargin(currentTimePointer.getRoot(), new Insets(newMarginTop, 0, 0, 0));

    }

    /**
     * Handles the overlap of timeScale and the currentTimePointer.
     *
     * @param time time has to be in the format of HH:mm.
     */
    public void handleOverlap(String time) {
        assert time.matches("^([0-1][0-9]|2[0-3]):[0-5][0-9]$");
        String[] splitTime = time.split(":");
        int hour = Integer.valueOf(splitTime[0]);
        int minute = Integer.valueOf(splitTime[1]);

        // ugly implementation, should try to improve.
        // a bit violate LoD.
        if (minute <= 15) {
            //hour is one-based, and the timeScaleCell starts from 12AM
            TimeScaleCell overlappedCell = timeScaleCells.get(hour);
            overlappedCell.hideTime();
        } else if (minute > 15 && minute < 45) {
            timeScaleCells.get(hour).recoverTime();
            timeScaleCells.get(hour + 1).recoverTime();
        } else if (minute >= 45) {
            TimeScaleCell overlappedCell = timeScaleCells.get(hour + 1);
            overlappedCell.hideTime();
        }

    }

    /*private void handleListener() {
        events.addListener(eventListener);
    }*/
}
