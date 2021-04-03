package seedu.booking.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_ORIGINAL_EMAIL;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.booking.logic.parser.ParserUtil.parseTagsForEdit;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.booking.logic.commands.FindPersonCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.EmailContainsKeywordsPredicate;
import seedu.booking.model.person.Person;

/**
 * Parses input arguments and creates a new FindPersonCommand object
 */
public class FindPersonCommandParser implements Parser<FindPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindPersonCommand.
     * and returns a FindPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindPersonCommand parse(String args) throws ParseException {
        requireNonNull(args);

        List<Predicate<Person>> predicates = new ArrayList<>();

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG);

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            predicates.add(ParserUtil.parseNameContainsKeywordsPredicate(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            predicates.add(ParserUtil.parsePhoneContainsKeywordsPredicate(argMultimap.getValue(PREFIX_PHONE).get()));
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            predicates.add(ParserUtil.parseEmailContainsKeywordsPredicate(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            predicates.add(ParserUtil.parseTagContainsKeywordsPredicate(argMultimap.getValue(PREFIX_TAG).get()));
        }

        if (predicates.isEmpty()) {
            throw new ParseException(FindPersonCommand.MESSAGE_USAGE);
        }

        return new FindPersonCommand(predicates);

    }

}
