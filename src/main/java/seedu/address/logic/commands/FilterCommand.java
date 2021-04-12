package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.predicates.AttributeContainsKeywordsPredicate;

public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds and filters clients whose attributes contain"
            + " any of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: PREFIX/KEYWORD [PREFIX/MORE_KEYWORDS]...\n"
            + "Prefixes: address: a/ADDRESS; gender: g/GENDER; tag: t/TAG; insurance plan name: i/PLAN_NAME; \n"
            + "age/[lower_bound]-[higher_bound]; age/[specific age] \n"
            + "Example: " + COMMAND_WORD + " a/Clementi g/M t/medical plan/Protecc age/30-35";

    private final AttributeContainsKeywordsPredicate predicate;

    public FilterCommand(AttributeContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.messagePersonFilteredOverview(predicate.getKeywords()),
                        model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }

}
