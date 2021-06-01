package guitests.guihandles;

import javafx.stage.Stage;

/**
 * @@author {se-edu}-reused
 * Reused from AB4 https://github.com/se-edu/addressbook-level4/
 *
 * Provides a handle for {@code MainWindow}.
 */
public class MainWindowHandle extends StageHandle {

    private final ResultDisplayHandle resultDisplay;
    private final CommandBoxHandle commandBox;
    private final StatusBarFooterHandle statusBarFooter;
    private final MainMenuHandle mainMenu;
    private final InfoDisplayHandle infoDisplayPanel;

    /**
     * Constructs a {@code MainWindowHandle} handler object.
     * @param stage Stage to use as root of the {@code MainWindow}.
     */
    public MainWindowHandle(Stage stage) {
        super(stage);

        infoDisplayPanel = new InfoDisplayHandle(getChildNode(InfoDisplayHandle.INFO_DISPLAY_ID));
        resultDisplay = new ResultDisplayHandle(getChildNode(ResultDisplayHandle.RESULT_DISPLAY_ID));
        commandBox = new CommandBoxHandle(getChildNode(CommandBoxHandle.COMMAND_INPUT_FIELD_ID));
        statusBarFooter = new StatusBarFooterHandle(getChildNode(StatusBarFooterHandle.STATUS_BAR_PLACEHOLDER));
        mainMenu = new MainMenuHandle(getChildNode(MainMenuHandle.MENU_BAR_ID));
    }

    public ResultDisplayHandle getResultDisplay() {
        return resultDisplay;
    }

    public CommandBoxHandle getCommandBox() {
        return commandBox;
    }

    public StatusBarFooterHandle getStatusBarFooter() {
        return statusBarFooter;
    }

    public MainMenuHandle getMainMenu() {
        return mainMenu;
    }

    public boolean contains(String id) {
        return infoDisplayPanel.contains(id);
    }
}
