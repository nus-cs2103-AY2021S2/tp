package seedu.address.ui.timetablepanel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * A region to display the date on the schedule.
 * Solution below adapted from
 * https://github.com/AY2021S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/homerce/ui/schedulepanel/
 * DisplayDateSlot.java
 */
public class DisplayDateSlot extends SlotContainer {

    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("d/M");
    private static final String FXML = "timetablepanel/DisplayDateSlot.fxml";

    @FXML
    private Label day;

    @FXML
    private Label date;

    // @@author RuiFengg-reused
    // Reused from
    // https://github.com/AY2021S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/homerce/ui/schedulepanel/
    // DisplayDateSlot.java
    // with no modifications.
    /**
     * Constructor for a slot to be added to the timetable that displays the date.
     */
    public DisplayDateSlot(LocalDate dateDisplay) {
        super(FXML);

        String dayText = dateDisplay.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US);
        String dateText = dateDisplay.format(DISPLAY_FORMAT);

        day.setText(dayText);
        date.setText(dateText);
    }
}
