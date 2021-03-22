package seedu.cakecollate.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.cakecollate.commons.core.GuiSettings;
import seedu.cakecollate.commons.core.LogsCenter;
import seedu.cakecollate.logic.commands.Command;
import seedu.cakecollate.logic.commands.CommandResult;
import seedu.cakecollate.logic.commands.exceptions.CommandException;
import seedu.cakecollate.logic.parser.CakeCollateParser;
import seedu.cakecollate.logic.parser.exceptions.ParseException;
import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.ReadOnlyCakeCollate;
import seedu.cakecollate.model.order.DeliveryStatus;
import seedu.cakecollate.model.order.Order;
import seedu.cakecollate.model.order.Status;
import seedu.cakecollate.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final CakeCollateParser cakeCollateParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        cakeCollateParser = new CakeCollateParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = cakeCollateParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveCakeCollate(model.getCakeCollate());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    public String updateDeliveryStatus() {
        ObservableList<Order> orderList = getFilteredOrderList();
        String result = "delivered";
        boolean entered = false;
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getDeliveryDate().value.isBefore(LocalDate.now())
                    && orderList.get(i).getDeliveryStatus().equals(new DeliveryStatus(Status.DELIVERED))) {
                result += " " + String.valueOf(i + 1);
                entered = true;
            }
        }
        if (entered) {
            return result;
        } else {
            return "";
        }
    }

    @Override
    public ReadOnlyCakeCollate getCakeCollate() {
        return model.getCakeCollate();
    }

    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return model.getFilteredOrderList();
    }

    @Override
    public Path getCakeCollateFilePath() {
        return model.getCakeCollateFilePath();
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
