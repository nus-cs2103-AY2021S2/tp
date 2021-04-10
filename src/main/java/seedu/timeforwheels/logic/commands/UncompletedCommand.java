package seedu.timeforwheels.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

import seedu.timeforwheels.commons.core.Messages;
import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.customer.AttributeContainsKeywordsPredicate;

/**
 * Finds and lists all uncompleted deliveries in delivery list
 */
public class UncompletedCommand extends Command {

    public static final String COMMAND_WORD = "uncompleted";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filter all uncompleted deliveries"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute (Model model) {
        requireNonNull(model);
        String trimmedArgs = "[X]";
        String[] nameKeywords = trimmedArgs.split("\\s+");
        AttributeContainsKeywordsPredicate uncomplete =
            new AttributeContainsKeywordsPredicate(Arrays.asList(nameKeywords));
        model.updateFilteredCustomerList(uncomplete);
        return new CommandResult(
                String.format(Messages.MESSAGE_CUSTOMERS_UNCOMPLETED, model.getFilteredCustomerList().size()));
    }
}
