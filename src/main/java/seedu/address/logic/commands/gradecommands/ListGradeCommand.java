package seedu.address.logic.commands.gradecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GRADE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public class ListGradeCommand extends Command {
    public static final String COMMAND_WORD = "list_grades";

    public static final String MESSAGE_SUCCESS = "Listed all grades";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredGradeList(PREDICATE_SHOW_ALL_GRADE);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
