package seedu.address.ui;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Event;
import seedu.address.model.EventList;
import seedu.address.storage.CalendarStorage;

/**
 * The Reminder Window. Provides the user schedule within a week.
 */
public class ReminderWindow extends UiPart<Stage> {

    private static final String FXML = "ReminderWindow.fxml";
    private LocalDate todayDate;
    private CalendarStorage calendarStorage;

    @FXML
    private Text content;

    /**
     * Creates a new ReminderWindow.
     */
    public ReminderWindow(CalendarStorage calendarStorage) {
        super(FXML);
        this.todayDate = LocalDate.now();
        this.calendarStorage = calendarStorage;
        calendarStorage.refreshStorage();
        setWindowDefaultSize(new GuiSettings());
        content.setText(generateReminderContent());

    }

    private String generateReminderContent() {
        String eventContent = "";
        for (int i = 0 ; i < 3; i++) {
            EventList eventList = calendarStorage.getDateEvents(todayDate.plusDays(i));
            if (eventList.isEmpty()) {
                continue;
            }
            eventContent += eventList.toString() + "\n";
        }
        if (eventContent.length() == 0) {
            eventContent = "You have no events!";
        }
        return eventContent;
    }

    void show() {
        getRoot().show();
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        this.getRoot().setHeight(guiSettings.getWindowHeight());
        this.getRoot().setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            this.getRoot().setX(guiSettings.getWindowCoordinates().getX());
            this.getRoot().setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * This method closes the reminder window.
     */
    @FXML
    private void close() {
        getRoot().hide();
    }

}
