package seedu.flashback.logic.parser;
import static seedu.flashback.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_FLAG;

import java.util.List;

import seedu.flashback.logic.commands.SortCommand;
import seedu.flashback.logic.parser.exceptions.ParseException;
import seedu.flashback.model.flashcard.SortOptions;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {
    public static final String FLAG_ASCENDING = "a";
    public static final String FLAG_DESCENDING = "d";

    @Override
    public SortCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_FLAG);
        List<String> flagValueList = argMultimap.getAllValues(PREFIX_FLAG);

        if (!argMultimap.getValue(PREFIX_FLAG).isPresent() || argMultimap.getPreamble().isEmpty()
            || !ParserUtil.areValidFlagValues(flagValueList, FLAG_ASCENDING, FLAG_DESCENDING)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        try {
            SortOptions sortOption = ParserUtil.parseSortOptions(argMultimap.getPreamble(),
                    argMultimap.getValue(PREFIX_FLAG).get());
            return new SortCommand(sortOption);
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE), e);
        }
    }
}
