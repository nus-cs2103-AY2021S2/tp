package chim.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import chim.commons.core.GuiSettings;
import chim.commons.core.LogsCenter;
import chim.logic.commands.Command;
import chim.logic.commands.CommandResult;
import chim.logic.commands.exceptions.CommandException;
import chim.logic.parser.ChimParser;
import chim.logic.parser.exceptions.ParseException;
import chim.model.Model;
import chim.model.ReadOnlyChim;
import chim.model.cheese.Cheese;
import chim.model.customer.Customer;
import chim.model.order.Order;
import chim.storage.Storage;
import javafx.collections.ObservableList;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ChimParser chimParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        chimParser = new ChimParser(model);
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = chimParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveChim(model.getChim());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyChim getChim() {
        return model.getChim();
    }

    @Override
    public ObservableList<Customer> getFilteredCustomerList() {
        return model.getFilteredCustomerList();
    }

    @Override
    public ObservableList<Cheese> getFilteredCheeseList() {
        return model.getFilteredCheeseList();
    }

    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return model.getFilteredOrderList();
    }

    @Override
    public ObservableList<Customer> getCompleteCustomerList() {
        return model.getCompleteCustomerList();
    }

    @Override
    public Path getChimFilePath() {
        return model.getChimFilePath();
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
