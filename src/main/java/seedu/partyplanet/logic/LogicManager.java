package seedu.partyplanet.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.partyplanet.commons.core.GuiSettings;
import seedu.partyplanet.commons.core.LogsCenter;
import seedu.partyplanet.commons.util.EditAutocompleteUtil;
import seedu.partyplanet.logic.commands.Command;
import seedu.partyplanet.logic.commands.CommandResult;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.logic.parser.AddressBookParser;
import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.ReadOnlyAddressBook;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;
    private final EditAutocompleteUtil editAutocompleteUtil;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
        editAutocompleteUtil = new EditAutocompleteUtil();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {

        //Logging
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        //Parse user input from String to a Command
        Command command = addressBookParser.parseCommand(commandText);
        //Executes the Command and stores the result
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public String autoComplete(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER REQUEST AUTOCOMPLETE][" + commandText + "]");
        return editAutocompleteUtil.parseEditCommand(commandText, model);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
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
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
