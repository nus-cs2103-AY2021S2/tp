package seedu.cakecollate.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.cakecollate.commons.core.GuiSettings;
import seedu.cakecollate.logic.commands.CommandResult;
import seedu.cakecollate.logic.commands.exceptions.CommandException;
import seedu.cakecollate.logic.parser.exceptions.ParseException;
import seedu.cakecollate.model.ReadOnlyCakeCollate;
import seedu.cakecollate.model.order.Order;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the CakeCollate.
     *
     * @see seedu.cakecollate.model.Model#getCakeCollate()
     */
    ReadOnlyCakeCollate getCakeCollate();

    /** Returns an unmodifiable view of the filtered list of orders */
    ObservableList<Order> getFilteredOrderList();

    /** Updates the deliveryStatus of an order to Status.DELIVERED if the delivery date is before the current date */
    String updateDeliveryStatus();

    /**
     * Returns the user prefs' cakecollate file path.
     */
    Path getCakeCollateFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
