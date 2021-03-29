package seedu.address.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;

// Solution below adapted from https://github.com/SirGoose3432/javafx-calendar/blob/master/src/FullCalendarView.java
public class CalendarPanel extends UiPart<Region> {
    private static final String FXML = "CalendarPanel.fxml";
    // Today's date
    private final LocalDate currentDate;
    // The start of the month that the current view is showing.
    private LocalDate startOfMonth;

    private ArrayList<AnchorPane> calendarPanes = new ArrayList<>();

    @FXML
    private VBox calendarPanel;
    @FXML
    private Button prevButton;
    @FXML
    private Label monthYearLabel;
    @FXML
    private Button nextButton;
    @FXML
    private GridPane calendar;

    private final Logger logger = LogsCenter.getLogger(CalendarPanel.class);

    /**
     * Instantiates the grid and dates of the calendar panel according to the current date.
     */
    public CalendarPanel() {
        super(FXML);
        currentDate = LocalDate.now();
        startOfMonth = currentDate.withDayOfMonth(1);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.setPrefSize(200, 200);
                calendar.add(anchorPane, j, i);
                calendarPanes.add(anchorPane);
            }
        }
        populateCalendarDates(startOfMonth);
    }

    /**
     * Fills in the numbers of the month in the date provided into the calendar.
     *
     * @param dateToView Date with the month that will be shown in the calendar.
     */
    void populateCalendarDates(LocalDate dateToView) {
        LocalDate firstSundayOfCalendar = dateToView;
        while (!firstSundayOfCalendar.getDayOfWeek().toString().equals("SUNDAY")) {
            logger.info(firstSundayOfCalendar.getDayOfWeek().toString());
            firstSundayOfCalendar = firstSundayOfCalendar.minusDays(1);
        }

        // Populate the calendar with day numbers
        for (AnchorPane pane : calendarPanes) {
            if (pane.getChildren().size() != 0) {
                pane.getChildren().remove(0);
            }
            Label day = new Label("" + firstSundayOfCalendar.getDayOfMonth());
            pane.setTopAnchor(day, 5.0);
            pane.setLeftAnchor(day, 5.0);
            pane.getChildren().add(day);
            firstSundayOfCalendar = firstSundayOfCalendar.plusDays(1);
        }
        // Change the title of the calendar
        monthYearLabel.setText(dateToView.getMonth().toString() + " " + dateToView.getYear());
    }

    @FXML
    void handleNextMonth() {
        startOfMonth = startOfMonth.plusMonths(1);
        populateCalendarDates(startOfMonth);
    }

    @FXML
    void handlePrevMonth() {
        startOfMonth = startOfMonth.minusMonths(1);
        populateCalendarDates(startOfMonth);
    }
}
