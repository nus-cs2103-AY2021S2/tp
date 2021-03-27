package seedu.cakecollate.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_DELIVERY_STATUS;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ORDER_DESCRIPTION;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_REQUEST;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.cakecollate.commons.core.Messages;
import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.order.ContainsKeywordsPredicate;

/**
 * Finds and lists all orders in cakecollate whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all orders which contain any of "
            + "the specified keywords/sub-keywords (case-insensitive) with or without prefixes and displays them "
            + "as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "KEYWORD_NAME]... "
            + "[" + PREFIX_PHONE + "KEYWORD_PHONE]... "
            + "[" + PREFIX_EMAIL + "KEYWORD_EMAIL]... "
            + "[" + PREFIX_ADDRESS + "KEYWORD_ADDRESS]... "
            + "[" + PREFIX_ORDER_DESCRIPTION + "KEYWORD_ORDER_DESCRIPTION]... "
            + "[" + PREFIX_TAG + "KEYWORD_TAG]... "
            + "[" + PREFIX_DATE + "KEYWORD_DELIVERY_DATE]... "
            + "[" + PREFIX_DELIVERY_STATUS + "KEYWORD_DELIVERY_STATUS]... "
            + "[" + PREFIX_REQUEST + "KEYWORD_REQUEST]... \n"
            + "Example 1: " + COMMAND_WORD + " alice .com chocolate\n"
            + "-> find all orders that contains \"alice\" or \".com\" or \"chocolate\"\n"
            + "Example 2: " + COMMAND_WORD + " n/ali e/.co o/choco\n"
            + "-> find all orders that contains \"ali\" in NAME and \".co\" in EMAIL "
            + "and \"choco\" in ORDER_DESCRIPTION";

    private final ContainsKeywordsPredicate predicate;

    public FindCommand(ContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }

    public String toString() {
        return predicate.toString();
    }
}
