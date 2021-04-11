package seedu.smartlib.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.model.Model.PREDICATE_SHOW_ALL_RECORDS;

import seedu.smartlib.model.Model;

/**
 * Lists all records in SmartLib to the user.
 */
public class ListRecordCommand extends Command {

    public static final String COMMAND_WORD = "listrecord";

    public static final String MESSAGE_SUCCESS = "Listed all records.";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRecordList(PREDICATE_SHOW_ALL_RECORDS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
