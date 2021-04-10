package seedu.address.ui.calendar.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CalendarUtil.calendarTextToDate;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.EventList;
import seedu.address.storage.CalendarStorage;
import seedu.address.ui.UiPart;

/**
 * Represents a GUI to show the upcoming events for a day in the calendar.
 */
public class UpcomingSchedule extends UiPart<Region> implements EventHandler<MouseEvent> {
    //Solution adapted from
    // https://github.com/AY2021S1-CS2103T-T12-3/tp/blob/master/src/main/java/seedu/address/ui/schedule

    private static final String FXML = "schedule/UpcomingSchedule.fxml";
    private static Logger logger = LogsCenter.getLogger(UpcomingSchedule.class);

    private DayEventListPanel eventHolder;
    private LocalDate currentDay;

    private CalendarStorage calendarStorage;

    @FXML
    private VBox schedule;

    @FXML
    private Label date;

    @FXML
    private Label year;

    @FXML
    private Label day;

    /**
     * Constructs a schedule for the UpcomingSchedulePanel, which is the left panel of the {@Code CalendarWindow}.
     *
     * @param calendarStorage Storage for schedule to access events.
     */
    public UpcomingSchedule(CalendarStorage calendarStorage) {
        super(FXML);
        requireNonNull(calendarStorage);

        //Initialises attributes
        this.calendarStorage = calendarStorage;
        currentDay = LocalDate.now();
        eventHolder = new DayEventListPanel();

        //Loads schedule
        schedule.getChildren().add(eventHolder.getRoot());
        loadSchedule(currentDay);

        logger.info("Upcoming schedule successfully initialised");
    }

    /**
     * Loads the schedule for a certain date.
     *
     * @param date Date for schedule.
     */
    public void loadSchedule(LocalDate date) {
        setCurrentDay(date);
        fillTopLabelForDay();
        fillBase();
    }

    /**
     * Refreshes the {@code DayEventList} when the refresh button is clicked on the CalendarWindow.
     */
    public void refreshSchedule() {
        fillBase();
    }

    private void setCurrentDay(LocalDate date) {
        this.currentDay = date;
    }

    /**
     * Fills the {@code UpcomingSchedule} at the bottom with events for the date.
     */
    private void fillBase() {
        //Remove events previously and refresh calendar storage
        schedule.getChildren().remove(eventHolder.getRoot());
        calendarStorage.refreshStorage();

        //Get new events for the day and update the eventHolder
        EventList events = calendarStorage.getDateEvents(currentDay);
        eventHolder.updateList(events);
        schedule.getChildren().add(eventHolder.getRoot());

        logger.info("Events for the day has loaded successfully");
    }

    /**
     * Fills the {@code UpcomingSchedule} at the top with the description of the date.
     */
    private void fillTopLabelForDay() {
        year.setText(String.valueOf(currentDay.getYear()));
        date.setText(getDateString(currentDay));
        day.setText(getDayString(currentDay));
        logger.info("Date details of upcoming schedule loaded successfully");
    }

    private String getDateString(LocalDate date) {
        String month = date.getMonth().toString();
        String dayOfMonth = String.valueOf(date.getDayOfMonth());
        String dateString = month + " " + dayOfMonth + ",";
        return dateString;
    }

    private String getDayString(LocalDate date) {
        String dayString = date.getDayOfWeek().toString();
        return dayString.substring(0, 1) + dayString.substring(1).toLowerCase();
    }

    /**
     * Handles the mouse click event to load and show the
     * date events of a Calendar Box in the upcoming schedule.
     *
     * @param mouseEvent Clicking on the a {@Code CalendarBox} mouse event.
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        //Get time string from calendar box that was clicked
        String formattedTime = ((Label) ((VBox) mouseEvent.getSource()).getChildren().get(0)).getText();
        LocalDate clickedDate = calendarTextToDate(formattedTime);

        if (clickedDate == currentDay) {
            refreshSchedule();
        }

        currentDay = clickedDate;
        loadSchedule(currentDay);
    }
}
