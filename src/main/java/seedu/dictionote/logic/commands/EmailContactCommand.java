package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_NOTE_INDEX;

import java.util.List;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.contact.Contact;
import seedu.dictionote.model.contact.MailtoLink;
import seedu.dictionote.model.note.Note;

/**
 * Opens a new window to send an email to a contact identified using it's displayed index from the contacts list.
 */
public class EmailContactCommand extends Command {

    public static final String COMMAND_WORD = "emailcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens a new window to send an email to the contact identified by the index number used in the "
            + "displayed contacts list.\n"
            + "Parameters: CONTACT_INDEX (must be a positive integer) "
            + "[" + PREFIX_NOTE_INDEX + "NOTE_INDEX]\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_EMAIL_CONTACT_SUCCESS = "New email window open: to %1$s";

    private final Index contactIndex;
    private final Index noteIndex;

    /**
     * Single-parameter constructor is kept for legacy support.
     */
    public EmailContactCommand(Index contactIndex) {
        this(contactIndex, null);
    }

    /**
     * Double-parameter constructor for commands with both a contact index and a note index
     * as arguments.
     */
    public EmailContactCommand(Index contactIndex, Index noteIndex) {
        this.contactIndex = contactIndex;
        this.noteIndex = noteIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownContactList = model.getFilteredContactList();
        List<Note> lastShownNoteList = model.getFilteredNoteList();

        if (contactIndex.getZeroBased() >= lastShownContactList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToEmail = lastShownContactList.get(contactIndex.getZeroBased());
        MailtoLink link = new MailtoLink(contactToEmail);

        if (noteIndex != null && noteIndex.getZeroBased() >= lastShownNoteList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
        } else if (noteIndex != null) {
            Note noteToEmail = lastShownNoteList.get(noteIndex.getZeroBased());
            link.setBody(noteToEmail);
        }

        model.emailContactUsingLink(link);

        return new CommandResult(String.format(MESSAGE_EMAIL_CONTACT_SUCCESS, contactToEmail));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContactCommand // instanceof handles nulls
                && contactIndex.equals(((EmailContactCommand) other).contactIndex)); // state check
    }
}
