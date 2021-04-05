package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.OPTION_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_EMAIL;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PHONE;
import static seedu.address.logic.parser.CliSyntax.OPTION_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.predicate.AddressContainsKeywordsPredicate;
import seedu.address.model.contact.predicate.AnyContainsKeywordsPredicate;
import seedu.address.model.contact.predicate.EmailContainsKeywordsPredicate;
import seedu.address.model.contact.predicate.NameContainsKeywordsPredicate;
import seedu.address.model.contact.predicate.PhoneContainsKeywordsPredicate;
import seedu.address.model.contact.predicate.TagsMatchKeywordPredicate;
import seedu.address.model.tag.Tag;

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
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        if (trimmedArgs.contains(PREFIX_OPTION.getPrefix())) {
            // get everything after PREFIX_OPTION
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_OPTION);
            Optional<String> argsString = argMultimap.getValue(PREFIX_OPTION);
            String unboxedArgsString = argsString.get();
            return parseFindOptions(unboxedArgsString);
        } else { // find by all fields
            return parseFindAll(trimmedArgs);
        }
    }

    /**
     * Parses args in find by options context
     * @param unboxedArgsString for the rest of the args after {@code o/}
     * @return {@code FindCommand}
     */
    public FindCommand parseFindOptions(String unboxedArgsString) throws ParseException {
        // split args into option and remaining optionArgs
        String[] optionArgsArray = unboxedArgsString.split("\\s+", 2);
        String option = optionArgsArray[0];
        String optionArgs = optionArgsArray[1];
        // get keywords
        List<String> keywords = Arrays.asList(optionArgs.split("\\s+"));
        switch (option) {
        case OPTION_NAME: // find by name
            return new FindCommand(new NameContainsKeywordsPredicate(keywords));
        case OPTION_ADDRESS: // find by address
            return new FindCommand(new AddressContainsKeywordsPredicate(keywords));
        case OPTION_PHONE: // find by phone
            return new FindCommand(new PhoneContainsKeywordsPredicate(keywords));
        case OPTION_EMAIL: // find by email
            return new FindCommand(new EmailContainsKeywordsPredicate(keywords));
        case OPTION_TAG: // find by tag
            // get tags
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(unboxedArgsString, PREFIX_TAG);
            Set<Tag> tagSet = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            return new FindCommand(new TagsMatchKeywordPredicate(tagSet));
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses args in find by all context
     * @param trimmedArgs args without trailing whitespace
     * @return {@code FindCommand}
     */
    public FindCommand parseFindAll(String trimmedArgs) {
        String[] keywords = trimmedArgs.split("\\s+");
        assert keywords.length > 0 : "FindCommand keywords are empty";
        return new FindCommand(new AnyContainsKeywordsPredicate(Arrays.asList(keywords)));
    }


}
