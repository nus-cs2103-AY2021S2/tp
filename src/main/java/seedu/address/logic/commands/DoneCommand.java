package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.AbstractDate.INPUT_DATE_TIME_FORMATTER;
import static seedu.address.model.AbstractDate.TO_JSON_STRING_FORMATTER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.CompletedDate;
import seedu.address.model.order.Order;

public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks order as complete"
            + "by the index number used in the displayed order list. "
            + "Parameters : INDEX (must be a positive integer) ";

    public static final String MESSAGE_MARK_ORDER_DONE_SUCCESS = "ORDER : %1$s completed";

    private final Index targetIndex;

    public DoneCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if(targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_INDEX);
        }

        Order orderToUpdate = lastShownList.get(targetIndex.getZeroBased());
        if (orderToUpdate.isComplete()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_COMPLETE);
        }

        Order updatedOrder = createDoneOrder(orderToUpdate);
        model.setOrder(orderToUpdate, updatedOrder);
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        return new CommandResult(String.format(MESSAGE_MARK_ORDER_DONE_SUCCESS, updatedOrder));
    }

    public Order createDoneOrder(Order orderToUpdate) {
        CompletedDate completedDate = new CompletedDate(LocalDateTime.now().format(TO_JSON_STRING_FORMATTER));
        return new Order(orderToUpdate, completedDate);
    }
}
