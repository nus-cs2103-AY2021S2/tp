package seedu.address.ui;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
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
    private AnchorPane mainWindow;
    @FXML
    private TextArea contentArea;

    /**
     * Creates a new ReminderWindow.
     */
    public ReminderWindow(CalendarStorage calendarStorage) {
        super(FXML);
        this.todayDate = LocalDate.now();
        this.calendarStorage = calendarStorage;
        calendarStorage.refreshStorage();
        contentArea.setText(generateReminderContent());
        BackgroundImage backgroundImage = new BackgroundImage(new Image("images/reminderwindowbackground.png"),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
        mainWindow.setBackground(new Background(backgroundImage));
    }

    private String generateReminderContent() {
        String eventContent = "";
        for (int i = 0; i < 3; i++) {
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
     * This method closes the reminder window.
     */
    @FXML
    private void close() {
        getRoot().hide();
    }

}
