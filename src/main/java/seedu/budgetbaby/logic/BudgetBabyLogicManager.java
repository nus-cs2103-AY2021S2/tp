package seedu.budgetbaby.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.budgetbaby.commons.core.GuiSettings;
import seedu.budgetbaby.commons.core.LogsCenter;
import seedu.budgetbaby.logic.commands.BudgetBabyCommand;
import seedu.budgetbaby.logic.commands.CommandResult;
import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.logic.parser.BudgetBabyParser;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.ReadOnlyBudgetTracker;
import seedu.budgetbaby.model.month.Month;
import seedu.budgetbaby.model.record.FinancialRecord;
import seedu.budgetbaby.storage.BudgetBabyStorage;

/**
 * The main LogicManager of the app.
 */
public class BudgetBabyLogicManager implements BudgetBabyLogic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(BudgetBabyLogicManager.class);

    private final BudgetBabyModel model;
    private final BudgetBabyStorage storage;
    private final BudgetBabyParser budgetBabyParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public BudgetBabyLogicManager(BudgetBabyModel model, BudgetBabyStorage storage) {
        this.model = model;
        this.storage = storage;
        budgetBabyParser = new BudgetBabyParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        BudgetBabyCommand command = budgetBabyParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveBudgetTracker(model.getBudgetTracker());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyBudgetTracker getBudgetTracker() {
        return model.getBudgetTracker();
    }

    @Override
    public ObservableList<Month> getFilteredMonthList() {
        return model.getFilteredMonthList();
    }

    @Override
    public ObservableList<FinancialRecord> getFilteredFinancialRecordList() {
        return model.getFilteredFinancialRecordList();
    }

    @Override
    public Path getBudgetBabyFilePath() {
        return model.getBudgetBabyFilePath();
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
