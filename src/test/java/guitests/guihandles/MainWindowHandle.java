package guitests.guihandles;

import javafx.stage.Stage;

/**
 * @@author {se-edu}-reused
 * Reused from AB4 https://github.com/se-edu/addressbook-level4/
 *
 * Provides a handle for {@code MainWindow}.
 */
public class MainWindowHandle extends StageHandle {

    private final ContactListPanelHandle contactListPanel;
    private final ResultDisplayHandle resultDisplay;
    private final CommandBoxHandle commandBox;
    private final StatusBarFooterHandle statusBarFooter;
    private final MainMenuHandle mainMenu;

    /**
     * Constructs a {@code MainWindowHandle} handler object.
     * @param stage Stage to use as root of the {@code MainWindow}.
     */
    public MainWindowHandle(Stage stage) {
        super(stage);

        contactListPanel = new ContactListPanelHandle(getChildNode(ContactListPanelHandle.CONTACT_LIST_VIEW_ID));
        resultDisplay = new ResultDisplayHandle(getChildNode(ResultDisplayHandle.RESULT_DISPLAY_ID));
        commandBox = new CommandBoxHandle(getChildNode(CommandBoxHandle.COMMAND_INPUT_FIELD_ID));
        statusBarFooter = new StatusBarFooterHandle(getChildNode(StatusBarFooterHandle.STATUS_BAR_PLACEHOLDER));
        mainMenu = new MainMenuHandle(getChildNode(MainMenuHandle.MENU_BAR_ID));
    }

    public ContactListPanelHandle getContactListPanel() {
        return contactListPanel;
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

}
