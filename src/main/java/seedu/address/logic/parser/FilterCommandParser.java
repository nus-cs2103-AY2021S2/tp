package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.flashcard.FlashcardFilterPredicate;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a correct FilterCommand object execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_QUESTION,
                PREFIX_CATEGORY, PREFIX_PRIORITY, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_QUESTION, PREFIX_PRIORITY, PREFIX_CATEGORY, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        return getFilterCommand(argMultimap);
    }

    private boolean questionIsEmpty(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_QUESTION).isEmpty();
    }

    private boolean categoryIsEmpty(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_CATEGORY).isEmpty();
    }

    private boolean priorityIsEmpty(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_PRIORITY).isEmpty();
    }

    private boolean tagIsEmpty(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_TAG).isEmpty();
    }

    /**
     * Returns true if at least one of prefixes does not contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private FilterCommand getFilterCommand(ArgumentMultimap argMultimap) throws ParseException {
        List<String> questionKeywords = null, categoryKeywords = null, priorityKeywords = null, tagKeywords = null;

        if (!questionIsEmpty(argMultimap)) {
            questionKeywords = ParserUtil.parseKeywordsToStringList(argMultimap.getValue(PREFIX_QUESTION).get());
        }

        if (!categoryIsEmpty(argMultimap)) {
            categoryKeywords = ParserUtil.parseKeywordsToStringList(argMultimap.getValue(PREFIX_CATEGORY).get());
        }

        if (!priorityIsEmpty(argMultimap)) {
            priorityKeywords = ParserUtil.parseKeywordsToStringList(argMultimap.getValue(PREFIX_PRIORITY).get());
        }

        if (!tagIsEmpty(argMultimap)) {
            tagKeywords = ParserUtil.parseKeywordsToStringList(argMultimap.getValue(PREFIX_TAG).get());
        }

        return new FilterCommand(new FlashcardFilterPredicate(questionKeywords, categoryKeywords,
                priorityKeywords, tagKeywords));
    }
}
