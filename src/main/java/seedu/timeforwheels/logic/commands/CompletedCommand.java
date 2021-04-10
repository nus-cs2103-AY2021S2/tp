package seedu.timeforwheels.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

import seedu.timeforwheels.commons.core.Messages;
import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.customer.AttributeContainsKeywordsPredicate;

/**
 * Finds and lists all completed deliveries in delivery list
 */
public class CompletedCommand extends Command {

    public static final String COMMAND_WORD = "completed";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filter all completed deliveries"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute (Model model) {
        requireNonNull(model);
        String trimmedArgs = "[✓]";
        String[] nameKeywords = trimmedArgs.split("\\s+");
        AttributeContainsKeywordsPredicate complete =
            new AttributeContainsKeywordsPredicate(Arrays.asList(nameKeywords));
        model.updateFilteredCustomerList(complete);
        return new CommandResult(
                String.format(Messages.MESSAGE_CUSTOMERS_COMPLETED, model.getFilteredCustomerList().size()));
    }
}
