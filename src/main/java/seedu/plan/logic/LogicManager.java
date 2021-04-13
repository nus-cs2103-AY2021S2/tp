package seedu.plan.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.plan.commons.core.GuiSettings;
import seedu.plan.commons.core.LogsCenter;
import seedu.plan.logic.commands.Command;
import seedu.plan.logic.commands.CommandResult;
import seedu.plan.logic.commands.exceptions.CommandException;
import seedu.plan.logic.parser.ModulePlannerParser;
import seedu.plan.logic.parser.exceptions.ParseException;
import seedu.plan.model.Model;
import seedu.plan.model.ReadOnlyModulePlanner;
import seedu.plan.model.plan.Plan;
import seedu.plan.storage.JsonModule;
import seedu.plan.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ModulePlannerParser modulePlannerParser;
    private final StringProperty displayPanelListCommand;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        modulePlannerParser = new ModulePlannerParser();
        displayPanelListCommand = model.getCurrentCommand();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[EXECUTE][START]");

        CommandResult commandResult;
        Command command = modulePlannerParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.savePlans(model.getPlans());
        } catch (IOException ioe) {
            logger.info("----------------[EXECUTE][EXCEPTION]");
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
        logger.info("----------------[EXECUTE][END][" + commandText + "]");
        return commandResult;
    }

    @Override
    public ReadOnlyModulePlanner getModulePlanner() {
        return model.getPlans();
    }

    @Override
    public ObservableList<Plan> getFilteredPersonList() {
        return model.getFilteredPlanList();
    }

    @Override
    public Path getModulePlannerFilePath() {
        return model.getPlansFilePath();
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
    public ObservableList<JsonModule> getModuleInfoList() {
        JsonModule[] informationOfModules = model.getPlans().getModuleInfo();
        ObservableList<JsonModule> listOfModules = FXCollections.observableArrayList();
        listOfModules.setAll(informationOfModules);
        return listOfModules;
    }

    @Override
    public StringProperty getDisplayPanelListCommand() {
        return displayPanelListCommand;
    }
    @Override
    public ObservableList<JsonModule> getSingleModuleInfoList() {
        return model.getPlans().getFoundModule();
    }
}
