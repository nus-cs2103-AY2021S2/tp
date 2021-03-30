package seedu.address.ui.reminderpanel;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.reminder.ReminderList;
import seedu.address.ui.AppointmentListPanel;
import seedu.address.ui.UiPart;

/**
 * Controller for a help page
 */
public class ReminderWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(ReminderWindow.class);
    private static final String FXML = "reminderpanel/ReminderWindow.fxml";
    private Logic logic;
    private AppointmentListPanel appointmentListPanel;
    private ReminderListPanel tmrReminderListPanel;
    private ReminderListPanel todayReminderListPanel;

    @FXML
    private StackPane tomorrowReminderPanelPlaceholder;

    @FXML
    private StackPane todayReminderPanelPlaceholder;

    @FXML
    private StackPane appointmentPanelPlaceholder;

    @FXML
    private StackPane schedulePanelPlaceholder;

    /**
     * Creates a new ReminderWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public ReminderWindow(Stage root, Logic logic) {
        super(FXML, root);
        this.logic = logic;
        appointmentListPanel = new AppointmentListPanel(logic.getFilteredAppointmentList());
        appointmentPanelPlaceholder.getChildren().add(appointmentListPanel.getRoot());

        tmrReminderListPanel = new ReminderListPanel(logic.getFilteredReminderList());
        tomorrowReminderPanelPlaceholder.getChildren().add(tmrReminderListPanel.getRoot());

        todayReminderListPanel = new ReminderListPanel(logic.getFilteredReminderList());
        todayReminderPanelPlaceholder.getChildren().add(todayReminderListPanel.getRoot());
    }

    /**
     * Creates a new HelpWindow.
     */
    public ReminderWindow(Logic logic) {
        this(new Stage(), logic);
    }

    /**
     * Shows the help window.
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
