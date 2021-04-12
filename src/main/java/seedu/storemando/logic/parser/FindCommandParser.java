package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_PARTIAL_NAME;

import java.util.Arrays;

import seedu.storemando.logic.commands.FindCommand;
import seedu.storemando.logic.parser.exceptions.ParseException;
import seedu.storemando.model.item.ItemName;
import seedu.storemando.model.item.ItemNameContainsKeywordsPredicate;
import seedu.storemando.model.item.ItemNameContainsPartialKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @param args Parses the given {@code String} of arguments in the context of the FindCommand.
     * @return a FindCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PARTIAL_NAME);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_PARTIAL_NAME).isPresent() && !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        } else if (argMultimap.getValue(PREFIX_PARTIAL_NAME).isPresent()) {
            ItemName name = ParserUtil.parseName(argMultimap.getValue(PREFIX_PARTIAL_NAME).get());
            String[] nameKeywords = name.fullName.split("\\s+");
            for (String word : nameKeywords) {
                ParserUtil.parseName(word);
            }
            return new FindCommand(new ItemNameContainsPartialKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else {
            String[] nameKeywords = trimmedArgs.split("\\s+");
            for (String word : nameKeywords) {
                ParserUtil.parseName(word);
            }
            return new FindCommand(new ItemNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }


    }

}
