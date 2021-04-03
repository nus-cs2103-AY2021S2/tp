package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLACKLIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODE_OF_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.PersonTagContainsKeywordsPredicate;
import seedu.address.model.person.predicates.ReturnTruePredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive, except for phone number.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names and/or"
            + "tags contain any of "
            + "the specified keywords (case-insensitive, except for phone, blacklist and mode of contact) and displays"
            + "them as a list with index numbers.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME_KEYWORD [MORE_NAME_KEYWORDS]... "
            + PREFIX_TAG + "TAG_KEYWORD [MORE_TAG_KEYWORDS]..."
            + PREFIX_ADDRESS + "ADDRESS_KEYWORDS [MORE_ADDRESS_KEYWORDS]"
            + PREFIX_EMAIL + "EMAIL_KEYWORDS [MORE_EMAIL_KEYWORDS]"
            + PREFIX_PHONE + "NUMBER [MORE_NUMBERS]"
            + PREFIX_BLACKLIST + "true or false"
            + PREFIX_MODE_OF_CONTACT + "phone, email or address. \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "alice bob charlie "
            + PREFIX_TAG + "friends neighbours"
            + PREFIX_ADDRESS + "Singapore"
            + PREFIX_EMAIL + "gmail yahoo"
            + PREFIX_PHONE + "69 420"
            + PREFIX_BLACKLIST + "true"
            + PREFIX_MODE_OF_CONTACT + "phone";

    private final Predicate<Person> namePredicate;
    private final Predicate<Person> tagPredicate;
    private final Predicate<Person> addressPredicate;
    private final Predicate<Person> emailPredicate;
    private final Predicate<Person> phonePredicate;
    private final Predicate<Person> blacklistPredicate;
    private final Predicate<Person> modeOfContactPredicate;

    /**
     * Creates a FindCommand to find the {@code Person}s with matching keywords.
     * @param namePredicate Predicate made up of names to match.
     * @param tagPredicate Predicate made up of tags to match.
     * @param addressPredicate Predicate made up of addresses to match.
     * @param emailPredicate Predicate made up of email keywords to match.
     * @param phonePredicate Predicate made up of numbers to match.
     * @param blacklistPredicate Predicate made up of blacklist status to match.
     * @param modeOfContactPredicate Predicate made up of mode of contact to match.
     */
    public FindCommand(Predicate<Person> namePredicate,
                       Predicate<Person> tagPredicate,
                       Predicate<Person> addressPredicate,
                       Predicate<Person> emailPredicate,
                       Predicate<Person> phonePredicate,
                       Predicate<Person> blacklistPredicate,
                       Predicate<Person> modeOfContactPredicate) {
        assert (namePredicate instanceof NameContainsKeywordsPredicate
                || namePredicate instanceof ReturnTruePredicate);
        assert (tagPredicate instanceof PersonTagContainsKeywordsPredicate
                || tagPredicate instanceof ReturnTruePredicate);
        assert (addressPredicate instanceof AddressContainsKeywordsPredicate
                || addressPredicate instanceof ReturnTruePredicate);
        assert (emailPredicate instanceof AddressContainsKeywordsPredicate
                || emailPredicate instanceof ReturnTruePredicate);
        assert (phonePredicate instanceof AddressContainsKeywordsPredicate
                || phonePredicate instanceof ReturnTruePredicate);
        assert (blacklistPredicate instanceof AddressContainsKeywordsPredicate
                || blacklistPredicate instanceof ReturnTruePredicate);
        assert (modeOfContactPredicate instanceof AddressContainsKeywordsPredicate
                || modeOfContactPredicate instanceof ReturnTruePredicate);

        this.namePredicate = namePredicate;
        this.tagPredicate = tagPredicate;
        this.addressPredicate = addressPredicate;
        this.emailPredicate = emailPredicate;
        this.phonePredicate = phonePredicate;
        this.blacklistPredicate = blacklistPredicate;
        this.modeOfContactPredicate = modeOfContactPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Person> finalPredicate = namePredicate
                .and(tagPredicate)
                .and(addressPredicate)
                .and(emailPredicate)
                .and(phonePredicate)
                .and(blacklistPredicate)
                .and(modeOfContactPredicate);
        model.updateFilteredPersonList(finalPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && namePredicate.equals(((FindCommand) other).namePredicate)
                && tagPredicate.equals(((FindCommand) other).tagPredicate)
                && addressPredicate.equals(((FindCommand) other).addressPredicate)
                && emailPredicate.equals(((FindCommand) other).emailPredicate)
                && phonePredicate.equals(((FindCommand) other).phonePredicate)
                && blacklistPredicate.equals(((FindCommand) other).blacklistPredicate)
                && modeOfContactPredicate.equals(((FindCommand) other).modeOfContactPredicate)); // state check
    }
}
