package chim.logic.commands;

import java.nio.file.Path;
import java.util.Set;

import chim.commons.core.GuiSettings;
import chim.model.Model;
import chim.model.ReadOnlyChim;
import chim.model.ReadOnlyUserPrefs;
import chim.model.cheese.Cheese;
import chim.model.cheese.CheeseId;
import chim.model.cheese.CheeseType;
import chim.model.customer.Customer;
import chim.model.customer.CustomerId;
import chim.model.customer.Phone;
import chim.model.order.Order;
import chim.model.order.OrderId;
import chim.model.order.Quantity;
import chim.model.util.predicate.FieldPredicate;
import javafx.collections.ObservableList;

/**
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
    public Path getChimFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setChimFilePath(Path chimFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addCustomer(Customer customer) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setChim(ReadOnlyChim newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyChim getChim() {
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
    public ObservableList<Customer> getCompleteCustomerList() {
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
    public Order getOrderWithId(OrderId orderId) {
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
    public Cheese getCheeseWithId(CheeseId cheeseId) {
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
    public void updateFilteredCustomerList(FieldPredicate<Customer> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredOrderList(FieldPredicate<Order> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredCheeseList(FieldPredicate<Cheese> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getFilteredOrderListIncompleteCount() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getFilteredCheeseListUnassignedCount() {
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
    public Customer getCustomerWithId(CustomerId id) {
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
