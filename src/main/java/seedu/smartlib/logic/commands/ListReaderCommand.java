package seedu.smartlib.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.model.Model.PREDICATE_SHOW_ALL_READERS;

import seedu.smartlib.model.Model;

/**
 * Lists all readers in SmartLib to the user.
 */
public class ListReaderCommand extends Command {

    public static final String COMMAND_WORD = "listreader";

    public static final String MESSAGE_SUCCESS = "Listed all readers.";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredReaderList(PREDICATE_SHOW_ALL_READERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
