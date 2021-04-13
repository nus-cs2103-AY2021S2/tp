package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ClientBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.shortcut.ShortcutLibrary;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private ClientBookParser clientBookParser;

    private boolean isListModified;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        clientBookParser = new ClientBookParser(model.getShortcutLibrary());
        this.isListModified = false;
    }

    private boolean isListModifiedByCommand(Command command) {
        boolean isListCommandWithAttributes = command instanceof ListCommand
                && ((ListCommand) command).isAttributeSpecified();
        boolean isFindCommandWithAttributes = command instanceof FindCommand
                && ((FindCommand) command).isAttributeSpecified();
        return isFindCommandWithAttributes || isListCommandWithAttributes;
    }

    private void updateClientBookParser(ShortcutLibrary shortcutLibrary) {
        this.clientBookParser = new ClientBookParser(shortcutLibrary);
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        if (isListModified) {
            model.undoListModification();
        }

        CommandResult commandResult;
        Command command = clientBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            if (isListModifiedByCommand(command)) {
                this.isListModified = true;
            } else {
                storage.saveAddressBook(model.getAddressBook());
            }
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        this.updateClientBookParser(model.getShortcutLibrary());

        try {
            this.storage.saveShortcutLibrary(model.getShortcutLibrary());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ShortcutLibrary getShortcutLibrary() {
        return model.getShortcutLibrary();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public Path getShortcutLibraryFilePath() {
        return model.getShortcutLibraryFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
