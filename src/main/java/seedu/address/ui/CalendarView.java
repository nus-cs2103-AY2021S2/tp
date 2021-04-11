package seedu.address.ui;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * UI calendar component that is displayed.
 * Solution below adapted from
 * https://github.com/AY1920S2-CS2103T-T10-3/main/blob/master/src/main/java/seedu/saveit/ui/CalendarView.java
 */
public class CalendarView extends UiPart<Region> {

    private static final String FXML = "CalendarView.fxml";
    private static final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String[] MONTHS = {"January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"};

    private int[] simulateGridPane = new int[42];
    private int day;
    private int month;
    private int year;

    private YearMonth yearMonth;
    private LocalDate todayDate;
    private LocalDate nonPivotDate;
    private LocalDate pivotDate;
    private LocalDate firstDayOfTheMonth;

    private int prevMonthBalance;
    private int nextMonthBalance;
    private int thisMonthBalance;
    private final int currentDay;
    private final CommandBox.CommandExecutor commandExecutor;
    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private Text monthYearText;

    @FXML
    private GridPane dateGridPane;

    @FXML
    private GridPane monthYearGridPane;

    @FXML
    private GridPane weekDayGridPane;

    @FXML
    private ImageView leftButton;

    @FXML
    private ImageView rightButton;

    /**
     * Constructor for the calendar view in TutorTracker
     */
    public CalendarView(CommandBox.CommandExecutor commandExecutor) {
        super(FXML);
        this.todayDate = LocalDate.now();
        this.pivotDate = todayDate;
        this.nonPivotDate = todayDate;
        this.day = todayDate.getDayOfMonth();
        this.month = todayDate.getMonthValue();
        this.year = todayDate.getYear();
        this.currentDay = this.day;
        this.yearMonth = YearMonth.of(this.year, this.month);
        this.firstDayOfTheMonth = yearMonth.atDay(1);
        this.commandExecutor = commandExecutor;
        setMonthYearLabel();
        generateCalender();
    }

    // @@author zwasd-reused
    // Reused from
    // https://github.com/AY1920S2-CS2103T-T10-3/main/blob/master/src/main/java/seedu/saveit/ui/CalendarView.java
    // with minor modifications (renaming variables).
    /**
     * It will update the attributes in the class according to the appointmentDate parse in
     * @param date the new appointmentDate
     */
    private void updateDayMonthYear(LocalDate date) {
        this.year = date.getYear();
        this.month = date.getMonthValue();
        this.day = date.getDayOfMonth();
        this.yearMonth = YearMonth.of(this.year, this.month);
        this.firstDayOfTheMonth = yearMonth.atDay(1);
    }

    private boolean isSameMonth(LocalDate d1, LocalDate d2) {
        return d1.withDayOfMonth(1).equals(d2.withDayOfMonth(1));
    }

    // @@author zwasd-reused
    // Reused from
    // https://github.com/AY1920S2-CS2103T-T10-3/main/blob/master/src/main/java/seedu/saveit/ui/CalendarView.java
    // with no modifications.
    /**
     * Find the number of days in a month given the year and month.
     *
     * @return return the number of days.
     */
    public int findNumberOfDaysInTheMonth() {
        if (this.month == 2) {
            if (this.yearMonth.isLeapYear()) {
                return 29;
            } else {
                return DAYS_IN_MONTH[month - 1];
            }
        } else {
            return DAYS_IN_MONTH[month - 1];
        }
    }

    // @@author zwasd-reused
    // Reused from
    // https://github.com/AY1920S2-CS2103T-T10-3/main/blob/master/src/main/java/seedu/saveit/ui/CalendarView.java
    // with no modifications.
    /**
     * Find the number of days in a month given the year and month.
     *
     * @return return the number of days.
     */
    public int findNumberOfDaysOfAMonth(int month, int year) {
        if (month == 2) {
            if (Year.isLeap(year)) {
                return 29;
            } else {
                return DAYS_IN_MONTH[month - 1];
            }
        } else {
            return DAYS_IN_MONTH[month - 1];
        }
    }

    // @@author zwasd-reused
    // Reused from
    // https://github.com/AY1920S2-CS2103T-T10-3/main/blob/master/src/main/java/seedu/saveit/ui/CalendarView.java
    // with no modifications.
    /**
     * Find the number of days in the previous month given the year and month.
     *
     * @return return the number of days.
     */
    public int findNumberOfDaysInPreviousMonth() {
        if (this.month >= 2) {
            return findNumberOfDaysOfAMonth(this.month - 1, this.year);
        } else {
            return DAYS_IN_MONTH[11];
        }
    }

    // @@author zwasd-reused
    // Reused from
    // https://github.com/AY1920S2-CS2103T-T10-3/main/blob/master/src/main/java/seedu/saveit/ui/CalendarView.java
    // with no modifications.
    /**
     * Set the monthYear Label's content.
     */
    private void setMonthYearLabel() {
        StringBuilder monthYear = new StringBuilder();
        monthYear.append(MONTHS[this.nonPivotDate.getMonthValue() - 1]);
        monthYear.append("  ");
        monthYear.append(this.nonPivotDate.getYear());
        String output = monthYear.toString();
        this.monthYearText.setText(output);
    }


    // @@author zwasd-reused
    // Reused from
    // https://github.com/AY1920S2-CS2103T-T10-3/main/blob/master/src/main/java/seedu/saveit/ui/CalendarView.java
    // with no modifications.
    /**
     * Fill up the simulateGridPane[] with the appointmentDate in order.
     */
    private void fill() {
        this.thisMonthBalance = findNumberOfDaysInTheMonth();
        int firstDayOfMonth = this.firstDayOfTheMonth.getDayOfWeek().getValue();
        this.prevMonthBalance = firstDayOfMonth % 7;
        int firstValue = findNumberOfDaysInPreviousMonth() - this.prevMonthBalance + 1;
        for (int i = 0; i < this.prevMonthBalance; i++) {
            this.simulateGridPane[i] = firstValue;
            firstValue++;
        }

        for (int i = 0; i < this.thisMonthBalance; i++) {
            this.simulateGridPane[this.prevMonthBalance + i] = i + 1;
        }

        this.nextMonthBalance = 42 - this.thisMonthBalance - prevMonthBalance;
        int newStartingPoint = this.thisMonthBalance + prevMonthBalance;
        for (int i = 0; i < this.nextMonthBalance; i++) {
            this.simulateGridPane[newStartingPoint + i] = i + 1;
        }
    }

    // @@author zwasd-reused
    // Reused from
    // https://github.com/AY1920S2-CS2103T-T10-3/main/blob/master/src/main/java/seedu/saveit/ui/CalendarView.java
    // with no modifications.
    /**
     * Generate Label for dateGridPane.
     *
     * @param dayNumber text for the Label
     * @return a label with specific text and font
     */
    private Label createLabel(int dayNumber) {
        Label label = new Label();
        label.setText("" + dayNumber);
        label.setStyle("-fx-text-fill: white");
        return label;
    }

    // @@author zwasd-reused
    // Reused from
    // https://github.com/AY1920S2-CS2103T-T10-3/main/blob/master/src/main/java/seedu/saveit/ui/CalendarView.java
    // with no modifications.
    /**
     * Generate a VBox with specific calendar.
     *
     * @return a VBox for dateGridPane.
     */
    private VBox placeHolderForLabel() {
        VBox holder = new VBox();
        holder.setFillWidth(false);
        holder.setPrefHeight(20);
        holder.setPrefWidth(20);
        holder.setMinSize(20, 20);
        holder.setMaxSize(30, 30);
        holder.setAlignment(Pos.CENTER);
        return holder;
    }


    // @@author zwasd-reused
    // Reused from
    // https://github.com/AY1920S2-CS2103T-T10-3/main/blob/master/src/main/java/seedu/saveit/ui/CalendarView.java
    // with minor modifications (change of colors and code structure).
    /**
     * Assign a Vbox to each GridPane.
     * Each Vbox contains a Label.
     * Each label's text is correspond to the calendar.
     */
    public void generateCalender() {
        fill();
        int i = 0;
        this.weekDayGridPane.setBackground(new Background(
                new BackgroundFill(Color.valueOf("#383838"), CornerRadii.EMPTY, Insets.EMPTY)));
        this.dateGridPane.setBackground(new Background(
                new BackgroundFill(Color.valueOf("#383838"), CornerRadii.EMPTY, Insets.EMPTY)));
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                VBox holder = placeHolderForLabel();

                if (i < this.prevMonthBalance || i > 42 - 1 - this.nextMonthBalance) {
                    holder.setBlendMode(BlendMode.SOFT_LIGHT);
                }

                if (i == this.prevMonthBalance + this.day - 1 && isSameMonth(this.pivotDate, this.nonPivotDate)) {
                    holder.setBackground(new Background(
                            new BackgroundFill(Color.valueOf("#5e5e5e"), CornerRadii.EMPTY, Insets.EMPTY)));
                }

                if (isSameMonth(this.todayDate, this.nonPivotDate)
                        && i == this.prevMonthBalance + this.currentDay - 1) {
                    holder.setBorder(new Border(new BorderStroke(Color.valueOf("white"),
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                }

                Label num = createLabel(this.simulateGridPane[i]);

                holder.getChildren().add(num);

                holder.setOnMouseClicked(event -> {
                    Label a = (Label) holder.getChildren().get(0);
                    int clickedDate = Integer.parseInt(a.getText());
                    if (holder.getBlendMode() != BlendMode.SOFT_LIGHT) {
                        pivotDate = nonPivotDate.withDayOfMonth(clickedDate);
                    } else {
                        pivotDate = getNewDate(clickedDate);
                    }

                    try {
                        commandExecutor.execute("view_event " + pivotDate);
                    } catch (CommandException | ParseException e) {
                        logger.info("Invalid command");
                    }
                    nonPivotDate = pivotDate;
                    updateDayMonthYear(pivotDate);
                    refreshCalenderView();
                });

                this.dateGridPane.add(holder, col, row);
                GridPane.setHalignment(holder, HPos.CENTER);
                GridPane.setValignment(holder, VPos.CENTER);
                i++;
            }
        }
    }


    // @@author zwasd-reused
    // Reused from
    // https://github.com/AY1920S2-CS2103T-T10-3/main/blob/master/src/main/java/seedu/saveit/ui/CalendarView.java
    // with no modifications.
    /**
     * Refresh the whole dateGridPane to show latest UI.
     */
    private void refreshCalenderView() {
        dateGridPane.getChildren().clear();
        updateDayMonthYear(nonPivotDate);
        setMonthYearLabel();
        generateCalender();
    }


    // @@author zwasd-reused
    // Reused from
    // https://github.com/AY1920S2-CS2103T-T10-3/main/blob/master/src/main/java/seedu/saveit/ui/CalendarView.java
    // with no modifications.
    /**
     * generate a new local appointmentDate according to input new appointmentDate.
     *
     * @param value appointmentDate indicator.
     * @return a new localDate object with that appointmentDate.
     */
    private LocalDate getNewDate(int value) {
        if (value <= 31 && value >= 21) {
            LocalDate prevM = this.nonPivotDate.minusMonths(1);
            prevM = prevM.withDayOfMonth(value);
            return prevM;
        } else {
            LocalDate nextM = this.nonPivotDate.plusMonths(1);
            nextM = nextM.withDayOfMonth(value);
            return nextM;
        }
    }

    // @@author zwasd-reused
    // Reused from
    // https://github.com/AY1920S2-CS2103T-T10-3/main/blob/master/src/main/java/seedu/saveit/ui/CalendarView.java
    // with no modifications.
    /**
     * Initialise calendar to previous month data when the next button is clicked.
     */
    @FXML
    public void handleToPrev() {
        this.nonPivotDate = nonPivotDate.minusMonths(1);
        updateDayMonthYear(nonPivotDate);
        refreshCalenderView();
    }


    // @@author {zwasd}-reused
    // Reused from
    // https://github.com/AY1920S2-CS2103T-T10-3/main/blob/master/src/main/java/seedu/saveit/ui/CalendarView.java
    // with no modifications.
    /**
     * Initialise calendar to next month data when the next button is clicked.
     */
    @FXML
    public void handleToNext() {
        this.nonPivotDate = nonPivotDate.plusMonths(1);
        updateDayMonthYear(nonPivotDate);
        refreshCalenderView();
    }
}
