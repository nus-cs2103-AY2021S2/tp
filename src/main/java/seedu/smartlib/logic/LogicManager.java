package seedu.smartlib.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.smartlib.commons.core.GuiSettings;
import seedu.smartlib.commons.core.LogsCenter;
import seedu.smartlib.logic.commands.Command;
import seedu.smartlib.logic.commands.CommandResult;
import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.logic.parser.SmartLibParser;
import seedu.smartlib.logic.parser.exceptions.ParseException;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.ReadOnlySmartLib;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final SmartLibParser smartLibParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        smartLibParser = new SmartLibParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = smartLibParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveSmartLib(model.getSmartLib());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlySmartLib getSmartLib() {
        return model.getSmartLib();
    }

    @Override
    public ObservableList<Reader> getFilteredReaderList() {
        return model.getFilteredReaderList();
    }

    @Override
    public ObservableList<Book> getFilteredBookList() {
        return model.getFilteredBookList();
    }

    @Override
    public Path getSmartLibFilePath() {
        return model.getSmartLibFilePath();
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
