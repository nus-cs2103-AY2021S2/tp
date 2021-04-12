package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.contact.Contact;

/**
 * Deletes a contact identified using it's displayed index from the contacts list.
 */
public class DeleteContactCommand extends Command {

    public static final String COMMAND_WORD = "deletecontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the contact identified by the index number used in the displayed contacts list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CONTACT_SUCCESS = "Deleted contact: %1$s";

    private final Index targetIndex;

    /**
     * Creates an DeleteContactCommand to delete contact at a specified {@code Index}.
     */
    public DeleteContactCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteContact(contactToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_CONTACT_SUCCESS, contactToDelete),
            UiAction.OPEN, UiActionOption.CONTACT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteContactCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteContactCommand) other).targetIndex)); // state check
    }
}
