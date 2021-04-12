package seedu.address.logic.commands.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.dish.Dish;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;

public class OrderEditCommand extends Command {

    public static final String COMPONENT_WORD = "order";
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMPONENT_WORD + " " + COMMAND_WORD
            + ": Edits the details of the order "
            + "identified by the index number used in the displayed order list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_DATETIME + "DELIVERY_DATETIME (DD-MM-YYYY HH:MM)] "
            + "[" + PREFIX_DISH + "DISH_NUMBER] "
            + "[" + PREFIX_QUANTITY + "INGREDIENT_QUANTITY]...\n "
            + "Example: " + COMPONENT_WORD + " " + COMMAND_WORD + " 1 "
            + PREFIX_DATETIME + "14-02-2021 18:30";

    public static final String MESSAGE_EDIT_ORDER_SUCCESS = "Edited Order: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ORDER = "This order already exists in the order.";

    private final Index index;
    private final OrderEditCommand.EditOrderDescriptor editOrderDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editOrderDescriptor details to edit the person with
     */
    public OrderEditCommand(Index index, OrderEditCommand.EditOrderDescriptor editOrderDescriptor) {
        requireNonNull(index);
        requireNonNull(editOrderDescriptor);

        this.index = index;
        this.editOrderDescriptor = new OrderEditCommand.EditOrderDescriptor(editOrderDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // You can only edit orders that are uncompleted.
        model.updateFilteredOrderList(order -> order.getState() == Order.State.UNCOMPLETED);
        List<Order> lastShownList = model.getFilteredOrderList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, Messages.ITEM_ORDER));
        }

        Order orderToEdit = lastShownList.get(index.getZeroBased());

        Order editedOrder = createEditedOrder(orderToEdit, editOrderDescriptor, model);

        if (!orderToEdit.isSame(editedOrder) && model.hasOrder(editedOrder)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER);
        }

        model.deleteOrder(orderToEdit);
        model.increaseIngredientByOrder(orderToEdit);

        try {
            // isValidOrderAddition throws a command exception and acts as a guard clause
            OrderCommandUtil.isValidOrderAddition(editedOrder, model);
            model.addOrder(editedOrder);
            model.decreaseIngredientByOrder(editedOrder);
        } catch (CommandException exception) {
            model.addOrder(orderToEdit);
            model.decreaseIngredientByOrder(orderToEdit);
            throw exception;
        } finally {
            Comparator<Order> comparator = new OrderChronologicalComparator();
            model.updateFilteredOrderList(comparator);
        }


        return new CommandResult(String.format(MESSAGE_EDIT_ORDER_SUCCESS, editedOrder),
                CommandResult.CRtype.PERSON);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Order createEditedOrder(Order orderToEdit,
                                         OrderEditCommand.EditOrderDescriptor editOrderDescriptor,
                                         Model model) throws CommandException {
        assert orderToEdit != null;

        Optional<Integer> updatedCustomerIdOptional = editOrderDescriptor.getCustomerId();

        Person updatedCustomer;
        if (updatedCustomerIdOptional.isPresent()) {
            updatedCustomer = OrderCommandUtil.getValidCustomerByOneIndex(updatedCustomerIdOptional.get(), model);
        } else {
            updatedCustomer = orderToEdit.getCustomer();
        }

        assert updatedCustomer != null;

        LocalDateTime updatedDateTime = editOrderDescriptor.getDateTime().orElse(orderToEdit.getDatetime());

        Optional<List<Pair<Index, Integer>>> updatedDishIdsQuantityListOptional =
                editOrderDescriptor.getDishIdsQuantityList();

        List<Pair<Dish, Integer>> updatedDishQuantityList;
        if (updatedDishIdsQuantityListOptional.isPresent()) {
            updatedDishQuantityList =
                    OrderCommandUtil.lookupDishIds(updatedDishIdsQuantityListOptional.get(), model);
        } else {
            updatedDishQuantityList = orderToEdit.getDishQuantityList();
        }

        assert updatedDishQuantityList != null;

        Order editedOrder = new Order(updatedDateTime, updatedCustomer, updatedDishQuantityList,
            orderToEdit.getState());

        return editedOrder;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrderEditCommand)) {
            return false;
        }

        // state check
        OrderEditCommand e = (OrderEditCommand) other;
        return index.equals(e.index)
                && editOrderDescriptor.equals(e.editOrderDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditOrderDescriptor {
        private Integer customerId;
        private LocalDateTime dateTime;
        private List<Pair<Index, Integer>> dishIdsQuantityList;

        public EditOrderDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditOrderDescriptor(OrderEditCommand.EditOrderDescriptor toCopy) {
            setCustomerId(toCopy.customerId);
            setDateTime(toCopy.dateTime);
            setDishIdsQuantityList(toCopy.dishIdsQuantityList);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(customerId, dateTime, dishIdsQuantityList);
        }

        public void setCustomerId(Integer customerId) {
            this.customerId = customerId;
        }

        public Optional<Integer> getCustomerId() {
            return Optional.ofNullable(customerId);
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public Optional<LocalDateTime> getDateTime() {
            return Optional.ofNullable(dateTime);
        }

        public void setDishIdsQuantityList(List<Pair<Index, Integer>> ingredientIdsQuantityList) {
            this.dishIdsQuantityList = ingredientIdsQuantityList;
        }

        public Optional<List<Pair<Index, Integer>>> getDishIdsQuantityList() {
            return Optional.ofNullable(dishIdsQuantityList);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof OrderEditCommand.EditOrderDescriptor)) {
                return false;
            }

            // state check
            OrderEditCommand.EditOrderDescriptor e = (OrderEditCommand.EditOrderDescriptor) other;

            return getCustomerId().equals(e.getCustomerId())
                    && getDateTime().equals(e.getDateTime())
                    && getDishIdsQuantityList().equals(e.getDishIdsQuantityList());
        }
    }
}
