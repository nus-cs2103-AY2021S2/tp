package seedu.address.ui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import seedu.address.commons.Observer;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.ObservableCalendarDate;

public class CalendarPanel extends UiPart<Region> implements Observer {
    private static final String FXML = "CalendarPanel.fxml";

    // Date that the user is viewing, or else today's date.
    protected LocalDate viewingDate;
    // Today's date
    private final LocalDate currentDate;
    // The start of the month that the current view is showing.
    private LocalDate startOfMonth;

    private final ArrayList<StackPane> calendarPanes = new ArrayList<>();
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
    private GridPane calendarDays;
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
        viewingDate = currentDate;
        startOfMonth = currentDate.withDayOfMonth(1);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();
                stackPane.setPrefSize(50, 50);
                calendar.add(stackPane, j, i);
                calendarPanes.add(stackPane);
            }
        }
        populateCalendarPage(startOfMonth);
    }

    /**
     * Gets updated by the observable viewing date, which changes the calendar view to the month of the viewing date.
     */
    @Override
    public void update() {
        viewingDate = observableCalendarDate.getDate();
        startOfMonth = viewingDate.withDayOfMonth(1);
        populateCalendarPage(startOfMonth);
    }

    /**
     * Fills in the numbers of the month in the date provided into the calendar.
     *
     * @param dateToView First date of the month that will be shown in the calendar.
     */
    // Solution below adapted from https://github.com/SirGoose3432/javafx-calendar/blob/master/src/FullCalendarView.java
    private void populateCalendarPage(LocalDate dateToView) {
        LocalDate dateCursor = dateToView;
        boolean isCursorOnDiffMonth = false;
        while (dateCursor.getDayOfWeek() != DayOfWeek.SUNDAY) {
            dateCursor = dateCursor.minusDays(1);
            isCursorOnDiffMonth = true;
        }
        // Populate the calendar with date numbers
        for (StackPane pane : calendarPanes) {
            pane.getChildren().clear();
            Label day = new Label("" + dateCursor.getDayOfMonth());
            isCursorOnDiffMonth = decideStyleOfDay(isCursorOnDiffMonth, dateCursor, day);

            if (dateCursor.equals(currentDate)) {
                day.setBackground(new Background(
                        new BackgroundFill(Color.WHITE, new CornerRadii(50), Insets.EMPTY)));
                day.setStyle("-fx-text-fill: black;");
            }

            if (dateCursor.equals(viewingDate)) {
                day.setBorder(new Border(new BorderStroke(
                        Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(50), new BorderWidths(2))));
            }

            pane.getChildren().add(day);
            dateCursor = dateCursor.plusDays(1);
        }
        // Change the title of the calendar
        String monthText = dateToView.getMonth().toString();
        String properCaseMonthText = StringUtil.toSentenceCase(monthText);
        monthYearLabel.setText(properCaseMonthText + " " + dateToView.getYear());
    }

    /**
     * Sets the style of dates that are not on the current month in view differently from the
     * dates in the current month. Dates on a different month are slightly darker.
     * Styles are switched at every 1st day of the month.
     *
     * @param isCursorOnDiffMonth Boolean indicating if the current date cursor is on a different month.
     * @param dateCursor          LocalDate object that indicates the current date working on.
     * @param label               Label to set the style of.
     * @return Boolean indicating if the next day is still on a different month.
     */
    private boolean decideStyleOfDay(boolean isCursorOnDiffMonth, LocalDate dateCursor, Label label) {
        if (isCursorOnDiffMonth) {
            label.getStyleClass().add("calendarDiffMonthDates");
        } else {
            label.getStyleClass().add("calendarStandardDates");
        }

        if (dateCursor.plusDays(1).getDayOfMonth() == 1) {
            return !isCursorOnDiffMonth;
        } else {
            return isCursorOnDiffMonth;
        }
    }

    /**
     * Changes the calendar to show next month, relative to the current month shown.
     */
    @FXML
    public void handleNextMonth() {
        startOfMonth = startOfMonth.plusMonths(1);
        populateCalendarPage(startOfMonth);
    }

    /**
     * Changes the calendar to show previous month, relative to the current month shown.
     */
    @FXML
    public void handlePrevMonth() {
        startOfMonth = startOfMonth.minusMonths(1);
        populateCalendarPage(startOfMonth);
    }
}
