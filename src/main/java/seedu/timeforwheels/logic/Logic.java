package seedu.timeforwheels.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.timeforwheels.commons.core.GuiSettings;
import seedu.timeforwheels.logic.commands.CommandResult;
import seedu.timeforwheels.logic.commands.exceptions.CommandException;
import seedu.timeforwheels.logic.parser.exceptions.ParseException;
import seedu.timeforwheels.model.ReadOnlyDeliveryList;
import seedu.timeforwheels.model.customer.Customer;

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
     * Returns the DeliveryList.
     *
     * @see seedu.timeforwheels.model.Model#getDeliveryList()
     */
    ReadOnlyDeliveryList getDeliveryList();

    /** Returns an unmodifiable view of the filtered list of customers */
    ObservableList<Customer> getFilteredCustomerList();

    /**
     * Returns the user prefs' delivery list file path.
     */
    Path getDeliveryListFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
