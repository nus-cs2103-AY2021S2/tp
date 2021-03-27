package seedu.timeforwheels.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.timeforwheels.commons.core.GuiSettings;
import seedu.timeforwheels.commons.core.LogsCenter;
import seedu.timeforwheels.logic.commands.Command;
import seedu.timeforwheels.logic.commands.CommandResult;
import seedu.timeforwheels.logic.commands.exceptions.CommandException;
import seedu.timeforwheels.logic.parser.DeliveryListParser;
import seedu.timeforwheels.logic.parser.exceptions.ParseException;
import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.ReadOnlyDeliveryList;
import seedu.timeforwheels.model.customer.Customer;
import seedu.timeforwheels.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final DeliveryListParser deliveryListParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        deliveryListParser = new DeliveryListParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = deliveryListParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveDeliveryList(model.getDeliveryList());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyDeliveryList getDeliveryList() {
        return model.getDeliveryList();
    }

    @Override
    public ObservableList<Customer> getFilteredCustomerList() {
        return model.getFilteredCustomerList();
    }

    @Override
    public Path getDeliveryListFilePath() {
        return model.getDeliveryListFilePath();
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
