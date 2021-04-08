package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.CustomerId;
import seedu.address.model.customer.Phone;
import seedu.address.model.order.CompletedDate;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderDate;
import seedu.address.model.order.OrderId;
import seedu.address.model.order.Quantity;

/**
 * Edits the details of an existing order in CHIM.
 */
public class EditOrderCommand extends EditCommand {

    public static final String COMMAND_WORD = "editorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the order identified "
            + "by the index number used in the displayed order list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_CHEESE_TYPE + "CHEESE TYPE] "
            + "[" + PREFIX_QUANTITY + "QUANTITY] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_ORDER_DATE + "ORDER DATE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CHEESE_TYPE + "Parmesan "
            + PREFIX_QUANTITY + "4 "
            + PREFIX_PHONE + "65558888 "
            + PREFIX_ORDER_DATE + "2020-12-30";

    public static final String MESSAGE_EDIT_ORDER_SUCCESS = "Edited Order: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_NO_CUSTOMERS_FOUND = "No customer in CHIM owns that phone number.";
    public static final String MESSAGE_SAME_CUSTOMER_PHONE = "Customer phone number provided is the same.";
    public static final String MESSAGE_COMPLETED_ORDER_ERROR = "Not allowed to change the fields of a completed order.";

    protected final Index index;
    protected final EditOrderDescriptor editOrderDescriptor;

    /**
     * Creates an AddOrderCommand to add the specified order at index {@code index}
     */
    public EditOrderCommand(Index index, EditOrderDescriptor editOrderDescriptor) {
        requireNonNull(index);
        requireNonNull(editOrderDescriptor);

        this.index = index;
        this.editOrderDescriptor = new EditOrderDescriptor(editOrderDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToEdit = lastShownList.get(index.getZeroBased());

        if (orderToEdit.isComplete()) {
            throw new CommandException(MESSAGE_COMPLETED_ORDER_ERROR);
        }

        if (editOrderDescriptor.getPhone().isPresent()
                && !model.hasCustomerWithPhone(editOrderDescriptor.getPhone().get())) {
            throw new CommandException(MESSAGE_NO_CUSTOMERS_FOUND);
        }

        Order editedOrder = createEditedOrder(orderToEdit, editOrderDescriptor, model);

        model.setOrder(orderToEdit, editedOrder);
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        model.setPanelToOrderList();

        return new CommandResult(String.format(MESSAGE_EDIT_ORDER_SUCCESS, editedOrder));
    }

    /**
     * Creates and returns an {@code Order} with the details of {@code orderToEdit}
     * edited with {@code editOrderDescriptor}.
     */
    private static Order createEditedOrder(Order orderToEdit, EditOrderDescriptor editOrderDescriptor, Model model)
            throws CommandException {
        assert orderToEdit != null;

        CheeseType updatedCheeseType = editOrderDescriptor.getCheeseType().orElse(orderToEdit.getCheeseType());
        Quantity updatedQuantity = editOrderDescriptor.getQuantity().orElse(orderToEdit.getQuantity());
        Optional<CustomerId> customerId = editOrderDescriptor.getPhone()
                .map(model::getCustomerWithPhone).map(Customer::getId);
        if (customerId.isPresent() && customerId.get().equals(orderToEdit.getCustomerId())) {
            throw new CommandException(MESSAGE_SAME_CUSTOMER_PHONE);
        }
        CustomerId updatedCustomerId = customerId.orElse(orderToEdit.getCustomerId());
        OrderDate updatedOrderDate = editOrderDescriptor.getOrderDate().orElse(orderToEdit.getOrderDate());

        CompletedDate completedDate = orderToEdit.getCompletedDate().orElse(null);
        OrderId orderId = orderToEdit.getOrderId();

        Order retOrder;

        try {
            retOrder = new Order(updatedCheeseType, updatedQuantity, updatedOrderDate,
                completedDate, orderId, updatedCustomerId);
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }

        return retOrder;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditOrderCommand // instanceof handles nulls
                && index.equals(((EditOrderCommand) other).index)
                && editOrderDescriptor.equals(((EditOrderCommand) other).editOrderDescriptor));
    }

    /**
     * Stores the details to edit the order with. Each non-empty field value will replace the
     * corresponding field value of the order.
     */
    public static class EditOrderDescriptor {
        private CheeseType cheeseType;
        private Quantity quantity;
        private Phone phone;
        private OrderDate orderDate;

        public EditOrderDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditOrderDescriptor(EditOrderDescriptor toCopy) {
            setCheeseType(toCopy.cheeseType);
            setQuantity(toCopy.quantity);
            setPhone(toCopy.phone);
            setOrderDate(toCopy.orderDate);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(cheeseType, quantity, phone, orderDate);
        }

        public void setCheeseType(CheeseType cheeseType) {
            this.cheeseType = cheeseType;
        }

        public Optional<CheeseType> getCheeseType() {
            return Optional.ofNullable(cheeseType);
        }

        public void setQuantity(Quantity quantity) {
            this.quantity = quantity;
        }

        public Optional<Quantity> getQuantity() {
            return Optional.ofNullable(quantity);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setOrderDate(OrderDate orderDate) {
            this.orderDate = orderDate;
        }

        public Optional<OrderDate> getOrderDate() {
            return Optional.ofNullable(orderDate);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditOrderDescriptor)) {
                return false;
            }

            // state check
            EditOrderDescriptor e = (EditOrderDescriptor) other;

            return getOrderDate().equals(e.getOrderDate())
                    && getCheeseType().equals(e.getCheeseType())
                    && getQuantity().equals(e.getQuantity())
                    && getPhone().equals(e.getPhone());
        }
    }

}
