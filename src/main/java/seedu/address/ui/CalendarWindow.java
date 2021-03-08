package seedu.address.ui;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

public class CalendarWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(CalendarWindow.class);
    private static final String FXML = "CalendarWindow.fxml";

    @FXML
    private Label currentMonth;
    @FXML
    private GridPane calendar;

    /**
     * Creates a new CalendarWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public CalendarWindow(Stage root) {
        super(FXML, root);
    }


    /**
     * Creates a new CalendarWindow.
     */
    public CalendarWindow() {
        super(FXML);
        loadCalendar();
    }


    private CalendarBox loadInfo(LocalDate startDateTime) {
        return new CalendarBox(startDateTime);
    }

    private void loadCalendar() {
        LocalDate today = LocalDate.now();
        LocalDate currentDate = LocalDate.of(today.getYear(), today.getMonth(), 1);
        for (int i = 2; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                CalendarBox calendarBox = loadInfo(currentDate);
                calendar.add(calendarBox.getRoot(), j, i);

                if (currentDate.getMonthValue() != LocalDate.now().getMonthValue()) {
                    calendarBox.getRoot().setDisable(true);
                    calendarBox.getRoot().setStyle("-fx-background-color: grey");
                }
                currentDate = currentDate.plusDays(1);
            }
        }
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


}
