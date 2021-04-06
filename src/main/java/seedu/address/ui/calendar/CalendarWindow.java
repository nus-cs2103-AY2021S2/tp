package seedu.address.ui.calendar;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.EventList;
import seedu.address.storage.CalendarStorage;
import seedu.address.ui.UiPart;
import seedu.address.ui.calendar.schedule.UpcomingSchedule;

/**
 * Represents the calendar window for the GUI.
 */
public class CalendarWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(CalendarWindow.class);
    private static final String FXML = "CalendarWindow.fxml";
    private static final int FIRST_ROW = 0;
    private static final int DAY_ONE = 1;
    private static final int CALENDER_SIDE_SIZE = 7;
    private static final int CALENDAR_SIZE = 35;
    private static final int LEAP_YEAR_FEB_DAYS = 29;
    private static final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String[] MONTHS = {"January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"};
    private static final String[] DAYS = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

    private int day;
    private int month;
    private int year;
    private int prevMonthDays;
    private int nextMonthDays;
    private int thisMonthDays;

    private YearMonth yearMonth;
    private LocalDate todayDate;
    private LocalDate firstDayOfTheMonth;

    private CalendarStorage calendarStorage;
    private UpcomingSchedule upcomingSchedule;

    @FXML
    private Label monthYearLabel;
    @FXML
    private Label dummyLabel;
    @FXML
    private GridPane calendar;
    @FXML
    private StackPane schedulePanelPlaceHolder;

    /**
     * Constructs a new CalendarWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public CalendarWindow(Stage root) {
        super(FXML, root);
    }


    /**
     * Constructs a new CalendarWindow.
     *
     * @param calendarStorage Stores the calendar events' information.
     * @param upcomingSchedule Schedule for date on the left side of the calendar window.
     */
    public CalendarWindow(CalendarStorage calendarStorage, UpcomingSchedule upcomingSchedule) {
        super(FXML);
        requireAllNonNull(calendarStorage, upcomingSchedule);
        this.todayDate = LocalDate.now();
        this.day = todayDate.getDayOfMonth();
        this.month = todayDate.getMonthValue();
        this.year = todayDate.getYear();
        this.yearMonth = YearMonth.of(this.year, this.month);
        this.firstDayOfTheMonth = yearMonth.atDay(DAY_ONE);
        this.calendarStorage = calendarStorage;
        this.upcomingSchedule = upcomingSchedule;
        schedulePanelPlaceHolder.getChildren().add(upcomingSchedule.getRoot());
        setMonthYearLabel();
        loadWindow();
        logger.info("calendar window initialised");
    }


    private void loadWindow() {
        loadSchedule();
        loadCalendar();
    }

    private void loadSchedule() {
        upcomingSchedule.loadSchedule(todayDate);
    }

    private void loadCalendar() {
        //update the number of days for the months showing in calendar
        updateMonthDays();

        //load day names (mon ... sun)
        loadDayNames();

        //fill up the calendar with the dates
        loadDayDates();

        logger.info("calendar window successfully loads");
    }

    /**
     * Refreshes Calendar storage.
     */
    private void refreshStorage() {
        calendarStorage.refreshStorage();
    }

    /**
     * Updates number of days of previous month,
     * current month and next month that are to
     * be viewed on the Calendar.
     */
    private void updateMonthDays() {
        this.thisMonthDays = findNumberOfDaysInTheMonth();
        int firstDayOfMonth = this.firstDayOfTheMonth.getDayOfWeek().getValue();
        this.prevMonthDays = firstDayOfMonth % 7 - 1;
        this.nextMonthDays = CALENDAR_SIZE - this.thisMonthDays - prevMonthDays;
    }

    /**
     * Loads the day names into the Calendar.
     */
    private void loadDayNames() {
        for (int col = 0; col < CALENDER_SIDE_SIZE; col++) {
            Label dayName = new Label(DAYS[col]);
            VBox dayNameBox = new VBox();
            dayNameBox.getChildren().add(dayName);
            dayNameBox.setPrefHeight(20);
            dayNameBox.setPrefWidth(20);
            dayNameBox.setAlignment(Pos.CENTER);
            calendar.add(dayNameBox, col, FIRST_ROW);
        }
    }

    /**
     * Loads the day dates into Calendar.
     */
    private void loadDayDates() {
        //refresh calendar storage before loading info for day dates.
        refreshStorage();
        LocalDate currentDate = firstDayOfTheMonth.minusDays(prevMonthDays);

        for (int row = 2; row < CALENDER_SIDE_SIZE; row++) {
            for (int col = 0; col < CALENDER_SIDE_SIZE; col++) {
                CalendarBox calendarBox = loadCalendarBoxInfo(currentDate);
                calendar.add(calendarBox.getRoot(), col, row);

                //change today date background color to orange
                if (todayDate.compareTo(currentDate) == 0) {
                    calendarBox.getRoot().setStyle("-fx-background-color: #FF7F50");
                }

                //check if date is not within the month and
                //change the day background color to grey
                if (currentDate.getMonthValue() != this.month) {
                    calendarBox.getRoot().setDisable(true);
                    calendarBox.getRoot().setStyle("-fx-background-color: grey");
                }

                //move on to the next day
                currentDate = currentDate.plusDays(1);
            }
        }
    }

    /**
     * Creates a {@code CalendarBox} for a date in the Calendar with relevant events for the day.
     *
     * @param date Date for the {@code CalendarBox}.
     * @return New {@code CalendarBox} of a certain date.
     */
    private CalendarBox loadCalendarBoxInfo(LocalDate date) {
        EventList events = calendarStorage.getDateEvents(date);
        CalendarBox calendarBox = new CalendarBox(date, events);
        calendarBox.addClickEventHandler(upcomingSchedule);
        return calendarBox;
    }

    /**
     * Shows the calendar window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing calendar page about the application.");
        refreshCalenderView();
        getRoot().show();
        getRoot().centerOnScreen();
    }


    /**
     * Returns true if the calendar window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the calendar window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the calendar window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    // @@author banchiang-reused
    // Reused from
    // https://github.com/AY2021S1-CS2103-W14-1/tp/blob/master/src/main/resources/view/CalendarView.fxml
    // with no modifications.
    /**
     * Finds the number of days in a month given the year and month.
     *
     * @return Number of days.
     */
    private int findNumberOfDaysInTheMonth() {
        if (this.month == 2) {
            if (this.yearMonth.isLeapYear()) {
                return LEAP_YEAR_FEB_DAYS;
            } else {
                return DAYS_IN_MONTH[month - 1];
            }
        } else {
            return DAYS_IN_MONTH[month - 1];
        }
    }

    /**
     * Updates attributes for the {@code CalendarWindow} from a date.
     *
     * @param date current date.
     */
    private void updateDayMonthYear(LocalDate date) {
        this.year = date.getYear();
        this.month = date.getMonthValue();
        this.day = date.getDayOfMonth();
        this.yearMonth = YearMonth.of(this.year, this.month);
        this.firstDayOfTheMonth = yearMonth.atDay(DAY_ONE);
    }
    // @@author


    //Solution below adapted from
    //https://github.com/AY2021S1-CS2103-W14-1/tp/blob/master/src/main/resources/view/CalendarView.fxml
    /**
     * Sets the monthYear Label's content.
     */
    private void setMonthYearLabel() {
        StringBuilder monthYear = new StringBuilder();
        monthYear.append(MONTHS[this.firstDayOfTheMonth.getMonthValue() - 1]);
        monthYear.append("  ");
        monthYear.append(this.firstDayOfTheMonth.getYear());
        String output = monthYear.toString();
        this.monthYearLabel.setText(output);
    }

    //Solution below adapted from
    //https://github.com/AY2021S1-CS2103-W14-1/tp/blob/master/src/main/resources/view/CalendarView.fxml
    /**
     * Refreshes the calendar view, the right side of the {@code CalendarWindow} GUI.
     */
    private void refreshCalenderView() {
        calendar.getChildren().clear();
        setMonthYearLabel();
        loadCalendar();
    }

    /**
     * Refreshes the schedule view, the left side of the {@code CalendarWindow} GUI.
     */
    private void refreshScheduleView() {
        upcomingSchedule.refreshSchedule();
    }

    //Solution below adapted from
    //https://github.com/AY2021S1-CS2103-W14-1/tp/blob/master/src/main/resources/view/CalendarView.fxml
    /**
     * Initialises calendar to previous month data when the prev button is clicked.
     */
    @FXML
    public void handleToPrev() {
        this.firstDayOfTheMonth = firstDayOfTheMonth.minusMonths(1);
        updateDayMonthYear(firstDayOfTheMonth);
        refreshCalenderView();
    }

    //Solution below adapted from
    //https://github.com/AY2021S1-CS2103-W14-1/tp/blob/master/src/main/resources/view/CalendarView.fxml
    /**
     * Initialises calendar to next month data when the next button is clicked.
     */
    @FXML
    public void handleToNext() {
        this.firstDayOfTheMonth = firstDayOfTheMonth.plusMonths(1);
        updateDayMonthYear(firstDayOfTheMonth);
        refreshCalenderView();
    }

    //Solution below adapted from
    //https://github.com/AY2021S1-CS2103-W14-1/tp/blob/master/src/main/resources/view/CalendarView.fxml
    /**
     * Initialises calendar to current month of today's date.
     */
    @FXML
    public void viewToday() {
        updateDayMonthYear(todayDate);
        refreshCalenderView();
    }

    //Solution below adapted from
    //https://github.com/AY2021S1-CS2103-W14-1/tp/blob/master/src/main/resources/view/CalendarView.fxml
    /**
     * Refreshes the calendar view along with the upcoming schedule view.
     */
    @FXML
    public void refresh() {
        refreshCalenderView();
        refreshScheduleView();
    }
}
