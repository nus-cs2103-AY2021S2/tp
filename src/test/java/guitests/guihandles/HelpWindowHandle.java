package guitests.guihandles;

import guitests.GuiRobot;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * @@author {se-edu}-reused
 * Reused with modification from AB4 https://github.com/se-edu/addressbook-level4/
 *
 * A handle to the {@code HelpWindow} of the application.
 */
public class HelpWindowHandle extends StageHandle {

    public static final String HELP_WINDOW_TITLE = "Help";

    private static final String HELP_MESSAGE_FIELD_ID = "#helpMessage";
    private final Label helpMessage;

    /**
     * Constructs a {@code HelpWindowHandle} handler object.
     * @param helpWindowStage Stage to use as root of the {@code HelpWindow}.
     */
    public HelpWindowHandle(Stage helpWindowStage) {
        super(helpWindowStage);

        helpMessage = getChildNode(HELP_MESSAGE_FIELD_ID);
    }

    /**
     * Returns true if a help window is currently present in the application.
     */
    public static boolean isWindowPresent() {
        return new GuiRobot().isWindowShown(HELP_WINDOW_TITLE);
    }

    public String getHelpMessage() {
        return helpMessage.getText();
    }
}
