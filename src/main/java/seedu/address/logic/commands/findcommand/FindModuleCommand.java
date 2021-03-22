package seedu.address.logic.commands.findcommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.TitleContainsKeywordsPredicate;

public class FindModuleCommand extends FindCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all modules whose names "
            + "contain any of the specified keywords (case-insensitive) and displays them as a "
            + "list with index numbers.\n"
            + "Parameters: "
            + PREFIX_MODULE + "KEYWORD [MORE KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS2103";

    private final TitleContainsKeywordsPredicate predicate;

    public FindModuleCommand(TitleContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert predicate != null;
        model.updateFilteredModuleList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_MODULE_LISTED_OVERVIEW,
                model.getFilteredModuleList().size()));
    }

    @Override
    public boolean equals(Object object) {
        return object == this
                || (object instanceof FindModuleCommand)
                && predicate.equals(((FindModuleCommand) object).predicate);
    }
}
