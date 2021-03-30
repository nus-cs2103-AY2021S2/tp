package seedu.address.logic.parser;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FLAG;

import java.util.List;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.flashcard.SortOptions;

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
