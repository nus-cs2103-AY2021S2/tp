package fooddiary.logic.commands;

import static fooddiary.model.Model.PREDICATE_SHOW_ALL_ENTRIES;
import static java.util.Objects.requireNonNull;

import fooddiary.model.Model;

/**
 * Lists all entries in the food diary to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all entries";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEntryList(PREDICATE_SHOW_ALL_ENTRIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
