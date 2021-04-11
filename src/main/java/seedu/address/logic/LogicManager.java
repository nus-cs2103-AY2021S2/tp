package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddUserCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ResetCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.DietLahParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyDietLah;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";

    // Enforce user initialization
    private static final String INITIALIZATION_ERROR_MESSAGE = "Please input your user information first,"
            + " using the following command:\nbmi g/gender a/age h/height(cm) w/weight(kg) i/ideal_weight";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final DietLahParser dietLahParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        dietLahParser = new DietLahParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        if (model.hasUser()) {
            Command command = dietLahParser.parseCommand(commandText);
            commandResult = command.execute(model);
        } else {
            // Check if command is creating new user
            Command command = dietLahParser.parseCommand(commandText);
            if (command instanceof AddUserCommand || command instanceof ResetCommand) {
                commandResult = command.execute(model);
            } else {
                // Prompt user to initialize user
                commandResult = new CommandResult(INITIALIZATION_ERROR_MESSAGE, false, false);
            }
        }

        try {
            //storage.saveDietLah(model.getDietLah());
            storage.saveFoodList(model.getUniqueFoodList());
            storage.saveFoodIntakeList(model.getFoodIntakeList());
            if (model.getUser() == null) {
                storage.deleteUser();
            } else {
                storage.saveUser(model.getUser());
            }
            //storage.saveDietPlanList(model.getDietPlanList());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyDietLah getDietLah() {
        return model.getDietLah();
    }

    @Override
    public Path getDietLahFilePath() {
        return model.getDietLahFilePath();
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
