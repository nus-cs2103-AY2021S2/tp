package seedu.address.ui.calendar.schedule;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.Event;
import seedu.address.ui.UiPart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
Code adapted from https://github.com/AY2021S1-CS2103T-T12-3/tp
 */
public class TimeScale extends UiPart<Region> {
    private static final String FXML = "schedule/TimeScale.fxml";

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

    private List<TimeScaleCell> timeScaleCells = new ArrayList<>();
    private CurrentTimePointer currentTimePointer;
    private ObservableList<Event> events;
    private HashMap<Event, EventCell> taskMapper;

    @FXML
    private StackPane timeScale;

    @FXML
    private ScrollPane scrollPane;

    /**
     * Constructor of the TimeScale.
     * @param events the task list that TimeScale listens to.
     */
    public TimeScale(ObservableList<Event> events) {
        super(FXML);
        this.events = events;
        taskMapper = new HashMap<>();

        //ui set-up
        init();
        setMargin();

        //listener set-up
        handleListener();

    }

    private void init() {
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

        //style, temporary, move to css/fxml
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);

        addEventsToTimeScale();
    }

    /**
     * Add tasks to time scale.
     */
    public void addEventsToTimeScale() {
        //add taskCell
        for (Event event : events) {
            addEventToTimeScale(event);
        }
    }

    private void addEventToTimeScale(Event event) {
        EventCell eventCell = new EventCell(event);

        taskMapper.put(event, eventCell);

        timeScale.getChildren().add(eventCell.getRoot());
        timeScale.setMargin(eventCell.getRoot(), new Insets(eventCell.marginTop(), 0, 0, 60));
    }

    private void addEventsToTimeScale(int eventId) {
        addEventToTimeScale(events.get(eventId));
    }

    private void removeTaskFromTimeScale(Event event) {
        timeScale.getChildren().remove(taskMapper.get(event).getRoot());
    }

    /**
     * Stackpane would squeeze everything in the same place, time function is used to list the timeScaleCells.
     */
    private void setMargin() {
        for (int i = 0; i < timeScaleCells.size(); i++) {
            timeScale.getChildren().add(timeScaleCells.get(i).getRoot());
            timeScale.setMargin(timeScaleCells.get(i).getRoot(), new Insets(i * 40, 0, 0, 0));
        }
    }

    /**
     * Places a {@Code Node} in the TimeScale with marginTop being {@Code marginTop}.
     * @param node node to place
     * @param marginTop marginTop of the node
     */
    public void placeItem(Node node, double marginTop) {
        timeScale.getChildren().add(node);
        timeScale.setMargin(node, new Insets(marginTop, 0, 0, 0));
    }

    /**
     * Removes the {@Code Node} in the TimeScale.
     * @param node node to remove
     */
    public void removeItem(Node node) {
        timeScale.getChildren().remove(node);
        this.currentTimePointer = null;
        timeScaleCells.forEach(timeScaleCell -> timeScaleCell.recoverTime());
    }

    /**
     * Places the {@Code CurrentTimePointer} with the initial {@Code marginTop}.
     * @param marginTop initial marginTop
     */
    public void placeCurrentTime(CurrentTimePointer currentTimePointer, double marginTop) {
        placeItem(currentTimePointer.getRoot(), marginTop);
        this.currentTimePointer = currentTimePointer;
    }

    /**
     * Updates the position of {@CurrentTimePosition}.
     * @param newMarginTop
     */
    public void updateCurrentTimePosition(double newMarginTop) {
        timeScale.setMargin(currentTimePointer.getRoot(), new Insets(newMarginTop, 0, 0, 0));

    }

    /**
     * Handles the overlap of timeScale and the currentTimePointer
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

    private void handleListener() {
        events.addListener(eventListener);
    }
}
