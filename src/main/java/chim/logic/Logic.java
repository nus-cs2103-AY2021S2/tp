package chim.logic;

import java.nio.file.Path;

import chim.commons.core.GuiSettings;
import chim.logic.commands.CommandResult;
import chim.logic.commands.exceptions.CommandException;
import chim.logic.parser.exceptions.ParseException;
import chim.model.ReadOnlyChim;
import chim.model.cheese.Cheese;
import chim.model.customer.Customer;
import chim.model.order.Order;
import javafx.collections.ObservableList;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the Chim object.
     *
     * @see chim.model.Model#getChim()
     */
    ReadOnlyChim getChim();

    /**
     * Returns an unmodifiable view of the filtered list of customers.
     */
    ObservableList<Customer> getFilteredCustomerList();

    /**
     * Returns an unmodifiable view of the filtered list of cheeses.
     */
    ObservableList<Cheese> getFilteredCheeseList();

    /**
     * Returns an unmodifiable view of the filtered list of orders.
     */
    ObservableList<Order> getFilteredOrderList();

    /**
     * Returns a complete/unfiltered list of customers.
     */
    ObservableList<Customer> getCompleteCustomerList();

    /**
     * Returns the user prefs' CHIM file path.
     */
    Path getChimFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
