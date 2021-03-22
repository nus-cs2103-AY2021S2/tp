package seedu.us.among.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.us.among.commons.core.GuiSettings;
import seedu.us.among.commons.core.LogsCenter;
import seedu.us.among.logic.commands.Command;
import seedu.us.among.logic.commands.CommandResult;
import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.logic.parser.ImposterParser;
import seedu.us.among.logic.parser.exceptions.ParseException;
import seedu.us.among.logic.request.exceptions.AbortRequestException;
import seedu.us.among.logic.request.exceptions.RequestException;
import seedu.us.among.model.Model;
import seedu.us.among.model.ReadOnlyEndpointList;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ImposterParser imposterParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        imposterParser = new ImposterParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException,
            RequestException, AbortRequestException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = imposterParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveEndpointList(model.getEndpointList());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyEndpointList getEndpointList() {
        return model.getEndpointList();
    }

    @Override
    public ObservableList<Endpoint> getFilteredEndpointList() {
        return model.getFilteredEndpointList();
    }

    @Override
    public Path getEndpointListFilePath() {
        return model.getEndpointListFilePath();
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
