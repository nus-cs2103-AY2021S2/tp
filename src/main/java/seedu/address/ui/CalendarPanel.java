package seedu.address.ui;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seedu.address.commons.Observer;
import seedu.address.model.ObservableCalendarDate;

public class CalendarPanel extends UiPart<Region> implements Observer {
    private static final String FXML = "CalendarPanel.fxml";
    // Today's date
    private final LocalDate currentDate;
    // The start of the month that the current view is showing.
    private LocalDate startOfMonth;
    private ArrayList<StackPane> calendarPanes = new ArrayList<>();
    private final ObservableCalendarDate observableCalendarDate;

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


    /**
     * Instantiates the grid and dates of the calendar panel according to the current date.
     */
    public CalendarPanel(ObservableCalendarDate observableCalendarDate) {
        super(FXML);
        this.observableCalendarDate = observableCalendarDate;
        observableCalendarDate.addObserver(this);

        currentDate = LocalDate.now();
        startOfMonth = currentDate.withDayOfMonth(1);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();
                stackPane.setPrefSize(100, 100);
                calendar.add(stackPane, j, i);
                calendarPanes.add(stackPane);
            }
        }
        populateCalendarDates(startOfMonth);
    }

    /**
     * Gets updated by the observable viewing date, which changes the calendar view to the month of the viewing date.
     */
    @Override
    public void update() {
        LocalDate viewingDate = observableCalendarDate.getDate();
        startOfMonth = viewingDate.withDayOfMonth(1);
        populateCalendarDates(startOfMonth);
    }

    /**
     * Fills in the numbers of the month in the date provided into the calendar.
     *
     * @param dateToView Date with the month that will be shown in the calendar.
     */
    // Solution below adapted from https://github.com/SirGoose3432/javafx-calendar/blob/master/src/FullCalendarView.java
    private void populateCalendarDates(LocalDate dateToView) {
        LocalDate firstSundayOfCalendar = dateToView;
        while (!firstSundayOfCalendar.getDayOfWeek().toString().equals("SUNDAY")) {
            firstSundayOfCalendar = firstSundayOfCalendar.minusDays(1);
        }

        // Populate the calendar with day numbers
        for (StackPane pane : calendarPanes) {
            if (pane.getChildren().size() != 0) {
                pane.getChildren().remove(0);
            }
            Label day = new Label("" + firstSundayOfCalendar.getDayOfMonth());
            day.setAlignment(Pos.CENTER);
            pane.getChildren().add(day);
            pane.setAlignment(Pos.CENTER);
            firstSundayOfCalendar = firstSundayOfCalendar.plusDays(1);
        }
        // Change the title of the calendar
        String monthText = dateToView.getMonth().toString();
        String properCaseMonthText = monthText.charAt(0) + monthText.substring(1).toLowerCase();
        monthYearLabel.setText(properCaseMonthText + " " + dateToView.getYear());
    }

    @FXML
    private void handleNextMonth() {
        startOfMonth = startOfMonth.plusMonths(1);
        populateCalendarDates(startOfMonth);
    }

    @FXML
    private void handlePrevMonth() {
        startOfMonth = startOfMonth.minusMonths(1);
        populateCalendarDates(startOfMonth);
    }
}
