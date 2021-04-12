package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLACKLIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODE_OF_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ModeOfContact;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.ModeOfContactPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PersonBlacklistedPredicate;
import seedu.address.model.person.predicates.PersonTagContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsNumbersPredicate;
import seedu.address.model.person.predicates.ReturnTruePredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE,
                        PREFIX_BLACKLIST, PREFIX_MODE_OF_CONTACT);

        Predicate<Person> namePredicate = new ReturnTruePredicate();
        Predicate<Person> tagPredicate = new ReturnTruePredicate();
        Predicate<Person> addressPredicate = new ReturnTruePredicate();
        Predicate<Person> emailPredicate = new ReturnTruePredicate();
        Predicate<Person> phonePredicate = new ReturnTruePredicate();
        Predicate<Person> blacklistPredicate = new ReturnTruePredicate();
        Predicate<Person> modeOfContactPredicate = new ReturnTruePredicate();

        Optional<String> nameKeywords = argMultimap.getValue(PREFIX_NAME);
        Optional<String> tagKeywords = argMultimap.getValue(PREFIX_TAG);
        Optional<String> addressKeywords = argMultimap.getValue(PREFIX_ADDRESS);
        Optional<String> emailKeywords = argMultimap.getValue(PREFIX_EMAIL);
        Optional<String> phoneNumbers = argMultimap.getValue(PREFIX_PHONE);
        Optional<String> blacklistStatus = argMultimap.getValue(PREFIX_BLACKLIST);
        Optional<String> modeOfContactChoice = argMultimap.getValue(PREFIX_MODE_OF_CONTACT);

        boolean allNotSupplied = checkAllNotSupplied(nameKeywords, tagKeywords, addressKeywords,
                emailKeywords, phoneNumbers, blacklistStatus, modeOfContactChoice);

        boolean emptyFields = checkAtLeastOneEmptyField(nameKeywords, tagKeywords, addressKeywords,
                emailKeywords, phoneNumbers, blacklistStatus, modeOfContactChoice);


        if (allNotSupplied || emptyFields) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (nameKeywords.isPresent()) {
            String[] names = nameKeywords.get().split("\\s+");
            namePredicate = new NameContainsKeywordsPredicate(Arrays.asList(names));
        }
        if (tagKeywords.isPresent()) {
            String[] tags = tagKeywords.get().split("\\s+");
            tagPredicate = new PersonTagContainsKeywordsPredicate(Arrays.asList(tags));
        }
        if (addressKeywords.isPresent()) {
            String[] address = addressKeywords.get().split("\\s+");
            addressPredicate = new AddressContainsKeywordsPredicate(Arrays.asList(address));
        }
        if (emailKeywords.isPresent()) {
            String[] email = emailKeywords.get().split("\\s+");
            emailPredicate = new EmailContainsKeywordsPredicate(Arrays.asList(email));
        }
        if (phoneNumbers.isPresent()) {
            String[] phone = phoneNumbers.get().split("\\s+");
            try {
                Arrays.stream(phone).forEach(Integer::parseInt);
            } catch (NumberFormatException e) {
                throw new ParseException("Phone field only accepts numbers.");
            }
            phonePredicate = new PhoneContainsNumbersPredicate(Arrays.asList(phone));
        }
        if (blacklistStatus.isPresent()) {
            String status = blacklistStatus.get().split("\\s+")[0];
            if (status.equalsIgnoreCase("true")) {
                blacklistPredicate = new PersonBlacklistedPredicate(true);
            } else if (status.equalsIgnoreCase("false")) {
                blacklistPredicate = new PersonBlacklistedPredicate(false);
            } else {
                throw new ParseException("Blacklist field only accepts true or false.");
            }
        }
        if (modeOfContactChoice.isPresent()) {
            String modeOfContact = modeOfContactChoice.get().split("\\s+")[0].toLowerCase();
            ModeOfContact actual;
            try {
                actual = new ModeOfContact(modeOfContact);
            } catch (IllegalArgumentException e) {
                throw new ParseException("Mode of contact field only accepts phone, email or address.");
            }
            modeOfContactPredicate = new ModeOfContactPredicate(actual);
        }
        return new FindCommand(namePredicate, tagPredicate, addressPredicate,
                emailPredicate, phonePredicate, blacklistPredicate, modeOfContactPredicate);
    }

    @SafeVarargs
    private boolean checkAllNotSupplied(Optional<String> ...fields) {
        return Arrays.stream(fields).allMatch(Optional::isEmpty);
    }

    @SafeVarargs
    private boolean checkAtLeastOneEmptyField(Optional<String> ...fields) {
        return Arrays.stream(fields).anyMatch(field -> field.isPresent() && field.get().equals(""));
    }
}
