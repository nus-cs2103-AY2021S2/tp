package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOULD_NOT_HIDE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.model.Model;

/**
 * Sets the person list filter.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_SUCCESS = "Applied filter";

    private final ArgumentMultimap filterArguments;

    public FilterCommand(ArgumentMultimap filterArguments) {
        this.filterArguments = filterArguments;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (filterArguments.getArgumentSize() == 1) {
            model.updateDisplayFilter(PREDICATE_SHOULD_NOT_HIDE);
        } else {
            model.updateDisplayFilter(prefix -> filterArguments.getValue(prefix).isPresent());
        }
        return new CommandResult(
                String.format(MESSAGE_SUCCESS));
    }
}
