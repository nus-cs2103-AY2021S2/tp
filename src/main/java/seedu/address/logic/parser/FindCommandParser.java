package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.Optional;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.FieldsContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.RemarkContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

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
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        Prefix[] prefixes = {PREFIX_NAME, PREFIX_EMAIL, PREFIX_REMARK, PREFIX_TAG};

        if (Arrays.stream(prefixes).map(prefix -> args.contains(prefix.getPrefix())).filter(a -> a).count() > 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (Arrays.stream(prefixes).anyMatch(prefix -> args.contains(prefix.getPrefix()))) {
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(" " + args, prefixes);

            Optional<String> nameValue = argMultimap.getValue(PREFIX_NAME);
            if (nameValue.isPresent()) {
                NameContainsKeywordsPredicate predicateComparator =
                        new NameContainsKeywordsPredicate(Arrays.asList(nameValue.get().split("\\s+").clone()));
                return new FindCommand(predicateComparator, predicateComparator);
            }
            Optional<String> emailValue = argMultimap.getValue(PREFIX_EMAIL);
            if (emailValue.isPresent()) {
                EmailContainsKeywordsPredicate predicateComparator =
                        new EmailContainsKeywordsPredicate(Arrays.asList(emailValue.get().split("\\s+").clone()));
                return new FindCommand(predicateComparator, predicateComparator);
            }
            Optional<String> tagValue = argMultimap.getValue(PREFIX_TAG);
            if (tagValue.isPresent()) {
                TagContainsKeywordsPredicate predicateComparator =
                        new TagContainsKeywordsPredicate(Arrays.asList(tagValue.get().split("\\s+").clone()));
                return new FindCommand(predicateComparator, predicateComparator);
            }
            Optional<String> remarkValue = argMultimap.getValue(PREFIX_REMARK);
            if (remarkValue.isPresent()) {
                RemarkContainsKeywordsPredicate predicateComparator =
                        new RemarkContainsKeywordsPredicate(Arrays.asList(remarkValue.get().split("\\s+").clone()));
                return new FindCommand(predicateComparator, predicateComparator);
            }
        }

        FieldsContainsKeywordsPredicate predicateComparator =
                new FieldsContainsKeywordsPredicate(Arrays.asList(trimmedArgs.split("\\s+").clone()));
        return new FindCommand(predicateComparator, predicateComparator);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns true if arguments are valid to be aliased.
     */
    @Override
    public boolean isValidCommandToAlias(String userInput) {
        return true;
    }

}
