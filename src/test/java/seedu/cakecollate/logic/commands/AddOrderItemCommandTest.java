package seedu.cakecollate.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.cakecollate.commons.core.GuiSettings;
import seedu.cakecollate.logic.commands.exceptions.CommandException;
import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.OrderItems;
import seedu.cakecollate.model.ReadOnlyCakeCollate;
import seedu.cakecollate.model.ReadOnlyOrderItems;
import seedu.cakecollate.model.ReadOnlyUserPrefs;
import seedu.cakecollate.model.order.Order;
import seedu.cakecollate.model.orderitem.OrderItem;
import seedu.cakecollate.testutil.OrderItemBuilder;

public class AddOrderItemCommandTest {

    @Test
    public void constructor_nullOrderItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOrderItemCommand(null));
    }

    @Test
    public void execute_orderItemAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingOrderItemAdded modelStub = new ModelStubAcceptingOrderItemAdded();
        OrderItem validOrderItem = new OrderItemBuilder().build();

        CommandResult commandResult = new AddOrderItemCommand(validOrderItem).execute(modelStub);

        assertEquals(String.format(AddOrderItemCommand.MESSAGE_SUCCESS, validOrderItem),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateOrderItem_throwsCommandException() {
        OrderItem validOrderItem = new OrderItemBuilder().build();
        AddOrderItemCommand addOrderItemCommand = new AddOrderItemCommand(validOrderItem);
        ModelStub modelStub = new ModelStubWithOrderItem(validOrderItem);

        assertThrows(CommandException.class, AddOrderItemCommand.MESSAGE_DUPLICATE_ORDER_ITEM, ()
            -> addOrderItemCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        OrderItem chocolate = new OrderItemBuilder().withType("Chocolate").build();
        OrderItem vanilla = new OrderItemBuilder().withType("Vanilla").build();
        AddOrderItemCommand addChocolateCommand = new AddOrderItemCommand(chocolate);
        AddOrderItemCommand addVanillaCommand = new AddOrderItemCommand(vanilla);

        // same object -> returns true
        assertTrue(addChocolateCommand.equals(addChocolateCommand));

        // same values -> returns true
        AddOrderItemCommand addChocolateCommandCopy = new AddOrderItemCommand(chocolate);
        assertTrue(addChocolateCommand.equals(addChocolateCommandCopy));

        // different types -> returns false
        assertFalse(addChocolateCommand.equals(1));

        // null -> returns false
        assertFalse(addChocolateCommand.equals(null));

        // different order item -> returns false
        assertFalse(addChocolateCommand.equals(addVanillaCommand));
    }

    // ======== MODEL STUBS ========


    /**
     * A default model stub that has all of the methods failing.
     */
    private class ModelStub implements Model {
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
        public Path getCakeCollateFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCakeCollateFilePath(Path cakeCollateFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCakeCollate(ReadOnlyCakeCollate cakeCollate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCakeCollate getCakeCollate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteOrder(Order target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setOrder(Order target, Order editedOrder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Order> getFilteredOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFilteredOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredOrderList(Predicate<Order> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteOrderItem(OrderItem target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<OrderItem> getFilteredOrderItemsList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addOrderItem(OrderItem item) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOrderItem(OrderItem orderItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyOrderItems getOrderItems() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setOrderItems(ReadOnlyOrderItems orderItems) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single order item.
     */
    private class ModelStubWithOrderItem extends AddOrderItemCommandTest.ModelStub {
        private final OrderItem orderItem;

        ModelStubWithOrderItem(OrderItem orderItem) {
            requireNonNull(orderItem);
            this.orderItem = orderItem;
        }

        @Override
        public boolean hasOrderItem(OrderItem orderItem) {
            requireNonNull(orderItem);
            return this.orderItem.equals(orderItem);
        }

    }

    /**
     * A Model stub that always accepts the order item being added.
     */
    private class ModelStubAcceptingOrderItemAdded extends AddOrderItemCommandTest.ModelStub {
        final ArrayList<OrderItem> orderItems = new ArrayList<>();

        @Override
        public ReadOnlyOrderItems getOrderItems() {
            return new OrderItems();
        }

        @Override
        public boolean hasOrderItem(OrderItem item) {
            requireNonNull(item);
            return orderItems.stream().anyMatch(item::equals);
        }

        @Override
        public void addOrderItem(OrderItem item) {
            requireNonNull(item);
            orderItems.add(item);
        }
    }

}
