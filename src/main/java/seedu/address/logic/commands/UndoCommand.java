package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ShowTodayUiCommand;
import seedu.address.model.Model;
import seedu.address.model.colabfolderhistory.exceptions.NoUndoableStateException;

/**
 * Undo the {@code model}'s colab folder to its previous state.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo success! %s";
    public static final String MESSAGE_FAILURE = "No more commands to undo.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            CommandResult result = model.undo();
            model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
            model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);

            if (result == null) {
                return new CommandResult(String.format(MESSAGE_SUCCESS, "Viewing Initial State."),
                        new ShowTodayUiCommand()).setIgnoreHistory(true);
            }

            return new CommandResult(String.format(MESSAGE_SUCCESS, result.getFeedbackToUser()),
                    result.getUiCommand()).setIgnoreHistory(true);
        } catch (NoUndoableStateException e) {
            throw new CommandException(MESSAGE_FAILURE);
        }
    }
}
