package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.predicates.ReturnTruePredicate;
import seedu.address.model.state.State;

/**
 * Undoes the user operations on the address book. This command only applies to the commands
 * that make changes to the address book, such as add, delete, edit etc.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Successfully undo the command: %1$s";

    public static final String MESSAGE_NOTHING_TO_UNDO = "There is nothing to undo.";

    private final State state;

    public UndoCommand(State state) {
        this.state = state;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (this.state.getPreviousState() == null) {
            throw new CommandException(MESSAGE_NOTHING_TO_UNDO);
        }
        String previousCommand = state.getCurrentCommand();
        this.state.deleteCurrentState();
        model.setAddressBook(state.getCurrentAddressBook());
        model.updateFilteredPersonList(new ReturnTruePredicate());
        return new CommandResult(String.format(MESSAGE_SUCCESS, previousCommand));
    }
}
