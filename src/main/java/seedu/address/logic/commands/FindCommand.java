package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.OPTION_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_EMAIL;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PHONE;
import static seedu.address.logic.parser.CliSyntax.OPTION_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;

/**
 * Finds and lists all contacts in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts with fields containing any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Options can be specified using " + PREFIX_OPTION + "<OPTION>" + " can be used to \n"
            + "Parameters: [" + PREFIX_OPTION + "<OPTION>]"
            + " KEYWORD [MORE_KEYWORDS]...\n"
            + "Options:\n"
            + " - " + OPTION_NAME + " (to find by name)\n"
            + " - " + OPTION_ADDRESS + " (to find by address)\n"
            + " - " + OPTION_PHONE + " (to find by phone)\n"
            + " - " + OPTION_EMAIL + " (to find by email)\n"
            + " - " + OPTION_TAG + " (to find by tags)\n"
            + "Examples:\n"
            + COMMAND_WORD + " alice bob charlie\n"
            + COMMAND_WORD + " " + PREFIX_OPTION + OPTION_TAG + " " + PREFIX_TAG + "math "
            + PREFIX_TAG + "science " + PREFIX_TAG + "primary";

    public static final String MESSAGE_MISSING_FIND_OPTION = "Please provide one of the following options:\n"
            + "Options:\n"
            + " - " + OPTION_NAME + " (to find by name)\n"
            + " - " + OPTION_ADDRESS + " (to find by address)\n"
            + " - " + OPTION_PHONE + " (to find by phone)\n"
            + " - " + OPTION_EMAIL + " (to find by email)\n"
            + " - " + OPTION_TAG + " (to find by tags)\n"
            + "If you wish to search by all fields, please leave out the 'o/'";
    public static final String MESSAGE_MISSING_NAME_ARGS = "Please add some values to find contacts by name.";
    public static final String MESSAGE_MISSING_ADDRESS_ARGS = "Please add some values to find contacts by address.";
    public static final String MESSAGE_MISSING_PHONE_ARGS = "Please add some values to find contacts by phone number.";
    public static final String MESSAGE_MISSING_EMAIL_ARGS = "Please add some values to find contacts by email.";
    public static final String MESSAGE_MISSING_TAG_ARGS = "Please add some tags to find contacts by tags.";
    public static final String MESSAGE_MISSING_TAG_PREFIX = "Please include 't/' "
            + "before the tag when searching by tags.";
    private final Predicate<Contact> predicate;

    /**
     * @param predicate Predicate to find the {@code Contact} by name.
     */
    public FindCommand(Predicate<Contact> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW, model.getFilteredContactList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
