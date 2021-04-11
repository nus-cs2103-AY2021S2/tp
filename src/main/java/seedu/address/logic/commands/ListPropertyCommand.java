package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROPERTIES;

import seedu.address.model.Model;

/**
 * Lists all properties in the property book to the user.
 */
public class ListPropertyCommand extends Command {
    public static final String COMMAND_WORD = "list property";

    public static final String MESSAGE_SUCCESS = "Listed all properties";

    public static final String MESSAGE_FAILURE = "No existing properties available";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
        if (!model.hasProperty()) {
            return new CommandResult(MESSAGE_FAILURE);
        } else {
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }
}
