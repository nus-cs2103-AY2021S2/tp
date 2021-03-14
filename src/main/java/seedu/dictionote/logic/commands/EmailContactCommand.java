package seedu.dictionote.logic.commands;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.contact.Contact;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Deletes a contact identified using it's displayed index from the contacts list.
 */
public class EmailContactCommand extends Command {

    public static final String COMMAND_WORD = "emailcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens a new window to send an email to the contact identified by the index number used in the "
            + "displayed contacts list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_EMAIL_CONTACT_SUCCESS = "New email window open: to %1$s";

    private final Index targetIndex;

    public EmailContactCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        // TODO: add call to email client.

        Contact contactToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteContact(contactToDelete);
        return new CommandResult(String.format(MESSAGE_EMAIL_CONTACT_SUCCESS, contactToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContactCommand // instanceof handles nulls
                && targetIndex.equals(((EmailContactCommand) other).targetIndex)); // state check
    }
}
