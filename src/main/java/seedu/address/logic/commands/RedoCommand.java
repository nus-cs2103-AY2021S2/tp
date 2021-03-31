package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.colabfolderhistory.exceptions.NoRedoableStateException;

/**
 * Redo the {@code model}'s colab folder to its next state.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redo success!\n%s";
    public static final String MESSAGE_FAILURE = "No more commands to redo.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            CommandResult result = model.redo();
            model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
            model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);

            return new CommandResult(String.format(MESSAGE_SUCCESS, result.getFeedbackToUser()),
                    result.getUiCommand()).setIgnoreHistory(true);
        } catch (NoRedoableStateException e) {
            throw new CommandException(MESSAGE_FAILURE);
        }
    }
}
