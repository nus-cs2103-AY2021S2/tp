package seedu.address.logic.commands;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.CheeseId;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Phone;
import seedu.address.model.order.Order;
import seedu.address.model.order.Quantity;

/**
 *
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addCustomer(Customer customer) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasCustomer(Customer customer) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteCustomer(Customer target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setCustomer(Customer target, Customer editedCustomer) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasOrder(Order order) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteOrder(Order order) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addOrder(Order order) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setOrder(Order target, Order editedOrder) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasCheese(Cheese cheese) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteCheese(Cheese cheese) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addCheese(Cheese cheese) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setCheese(Cheese target, Cheese editedCheese) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Customer> getFilteredCustomerList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Order> getFilteredOrderList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Cheese> getFilteredCheeseList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredCustomerList(Predicate<Customer> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredOrderList(Predicate<Order> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredCheeseList(Predicate<Cheese> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPanelToCustomerList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPanelToCheeseList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPanelToOrderList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasCustomerWithPhone(Phone phone) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Customer getCustomerWithPhone(Phone phone) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Set<CheeseId> getUnassignedCheeses(CheeseType cheesetype, Quantity quantity) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateCheesesStatus(Set<CheeseId> cheesesAssigned) {
        throw new AssertionError("This method should not be called.");
    }
}
