package seedu.storemando.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.storemando.commons.core.GuiSettings;
import seedu.storemando.commons.core.LogsCenter;
import seedu.storemando.logic.commands.Command;
import seedu.storemando.logic.commands.CommandResult;
import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.logic.parser.StoreMandoParser;
import seedu.storemando.logic.parser.exceptions.ParseException;
import seedu.storemando.model.Model;
import seedu.storemando.model.ReadOnlyStoreMando;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.Location;
import seedu.storemando.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final StoreMandoParser storeMandoParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        storeMandoParser = new StoreMandoParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = storeMandoParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveStoreMando(model.getStoreMando());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyStoreMando getStoreMando() {
        return model.getStoreMando();
    }

    @Override
    public ObservableList<Item> getFilteredItemList() {
        return model.getFilteredItemList();
    }

    @Override
    public ObservableList<Item> getItemList() {
        return model.getItemList();
    }

    @Override
    public ObservableList<Location> getLocationList() {
        return model.getLocationList();
    }

    @Override
    public Path getStoreMandoFilePath() {
        return model.getStoreMandoFilePath();
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
