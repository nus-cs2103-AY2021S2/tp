package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_IMPORTANT_DATES;

import seedu.address.model.Model;

public class DatesCommand extends Command {

    public static final String COMMAND_WORD = "dates";

    public static final String MESSAGE_SUCCESS = "Listed all important dates";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredImportantDatesList(PREDICATE_SHOW_ALL_IMPORTANT_DATES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
