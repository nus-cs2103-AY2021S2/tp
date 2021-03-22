package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/*
@@author marc-97-reused
Reused from
https://github.com/se-edu/addressbook-level4/blob/master/src/main/java/seedu/address/logic/commands/UndoCommand.java
with minor modification
 */
/**
 * Undo an action in FlashBack.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "FlashBack has been undo!";
    public static final String MESSAGE_FAILURE = "No commands to undo!";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoFlashBack()) {
            throw new CommandException(MESSAGE_FAILURE);
        }
        model.undoFlashBack();
        model.updateFilteredFlashcardList(Model.PREDICATE_SHOW_ALL_FLASHCARDS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
//@author
