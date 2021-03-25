package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AliasCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.DisplayFilterPredicate;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUniqueAliasMap;
import seedu.address.model.person.Person;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {

    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText, model.getAliases());
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
            storage.saveAliases(model.getAliases());
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
    public ReadOnlyUniqueAliasMap getAliases() {
        return model.getAliases();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Person> getSortedFilteredPersonList() {
        return model.getSortedFilteredPersonList();
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

    @Override
    public DisplayFilterPredicate getDisplayFilter() {
        return model.getDisplayFilter();
    }

    @Override
    public ObservableList<String> getAutocompleteCommands(String value) {
        List<String> commandList = new ArrayList<>();
        commandList.add(AddCommand.COMMAND_WORD);
        commandList.add(EditCommand.COMMAND_WORD);
        commandList.add(DeleteCommand.COMMAND_WORD);
        commandList.add(ClearCommand.COMMAND_WORD);
        commandList.add(FindCommand.COMMAND_WORD);
        commandList.add(ListCommand.COMMAND_WORD);
        commandList.add(FilterCommand.COMMAND_WORD);
        commandList.add(ExitCommand.COMMAND_WORD);
        commandList.add(HelpCommand.COMMAND_WORD);
        commandList.add(AliasCommand.COMMAND_WORD);
        commandList.add(SelectCommand.COMMAND_WORD);
        Collections.sort(commandList);


        if (value == null || value.isEmpty()) {
            return FXCollections.observableList(commandList);
        } else {
            assert (!value.isEmpty());
            List<String> filteredCommandList = commandList
                    .stream()
                    .filter((command) -> command.startsWith(value))
                    .collect(Collectors.toList());

            return FXCollections.observableList(filteredCommandList);
        }
    }

    @Override
    public Predicate<Person> getSelectedPersonPredicate() {
        return model.getSelectedPersonPredicate();
    }
}
