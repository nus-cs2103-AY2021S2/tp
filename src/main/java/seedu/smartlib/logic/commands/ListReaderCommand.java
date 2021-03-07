package seedu.smartlib.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.smartlib.model.Model;

/**
 * Lists all readers in the address book to the user.
 */
public class ListReaderCommand extends Command {

    public static final String COMMAND_WORD = "listreader";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
