package seedu.dictionote.logic.commands;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.contact.Contact;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Opens a new window to send an email to a contact identified using it's displayed index from the contacts list.
 */
public class EmailContactCommand extends Command {

    public static final String COMMAND_WORD = "emailcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens a new window to send an email to the contact identified by the index number used in the "
            + "displayed contacts list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_EMAIL_CONTACT_SUCCESS = "New email window open: to %1$s";
    public static final String MESSAGE_INVALID_MAILTO_LINK = "Invalid email link.";

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

        Contact contactToEmail = lastShownList.get(targetIndex.getZeroBased());
        URI contactMailtoLink = URI.create("mailto:" + contactToEmail.getEmail());
        Desktop userDesktop = Desktop.getDesktop();

        // credit to TorstenH. and alexey_s from CodeProject for the URL invocation code.
        // link to the posts: https://www.codeproject.com/questions/398241/how-to-open-url-in-java
        try {
            userDesktop.browse(contactMailtoLink); // invoke user's OS default mail client.
        } catch (IOException e) {
            throw new CommandException(MESSAGE_INVALID_MAILTO_LINK);
        }

        return new CommandResult(String.format(MESSAGE_EMAIL_CONTACT_SUCCESS, contactToEmail));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContactCommand // instanceof handles nulls
                && targetIndex.equals(((EmailContactCommand) other).targetIndex)); // state check
    }
}
