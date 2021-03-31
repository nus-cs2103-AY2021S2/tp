package dog.pawbook.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import dog.pawbook.commons.core.GuiSettings;
import dog.pawbook.commons.core.LogsCenter;
import dog.pawbook.logic.commands.Command;
import dog.pawbook.logic.commands.CommandResult;
import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.logic.parser.PawbookParser;
import dog.pawbook.logic.parser.exceptions.ParseException;
import dog.pawbook.model.Model;
import dog.pawbook.model.ReadOnlyDatabase;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.storage.Storage;
import javafx.collections.ObservableList;
import javafx.util.Pair;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final PawbookParser pawbookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        pawbookParser = new PawbookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = pawbookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveDatabase(model.getDatabase());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyDatabase getDatabase() {
        return model.getDatabase();
    }

    @Override
    public ObservableList<Pair<Integer, Entity>> getFilteredEntityList() {
        return model.getFilteredEntityList();
    }

    @Override
    public Path getDatabaseFilePath() {
        return model.getDatabaseFilePath();
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
