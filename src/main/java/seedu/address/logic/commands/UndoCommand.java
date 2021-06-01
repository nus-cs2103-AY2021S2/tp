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
    public static final String MESSAGE_SUCCESS = "Undo successful!\n";
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

            /*
             * TODO: Fix in v2.
             * Originally, feedback to user was meant to show more detailed feedback message.
             * However, the current message was found to be unintuitive for users.
             * Changing this would require a large modification which is not possible in v1.4 due to module guidelines.
             * I have changed the message to show a generic "Undo Successful" for now.
             */
            return new CommandResult(MESSAGE_SUCCESS, result.getUiCommand()).setIgnoreHistory(true);
        } catch (NoUndoableStateException e) {
            throw new CommandException(MESSAGE_FAILURE);
        }
    }
}
