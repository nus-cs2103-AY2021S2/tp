package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_MAINCONTENT;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.stream.Stream;

import seedu.dictionote.logic.commands.AddContentCommand;
import seedu.dictionote.logic.parser.exceptions.ParseException;
import seedu.dictionote.model.dictionary.Content;

/**
 * Parses input arguments and creates a new AddNoteCommand object.
 */
public class AddContentCommandParser implements Parser<AddContentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddContentCommand
     * and returns an AddContentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddContentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_WEEK, PREFIX_HEADER, PREFIX_MAINCONTENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_WEEK, PREFIX_HEADER, PREFIX_MAINCONTENT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContentCommand.MESSAGE_USAGE));
        }

        String week = ParserUtil.parseWeek(argMultimap.getValue(PREFIX_WEEK).get());
        String header = ParserUtil.parseHeader(argMultimap.getValue(PREFIX_HEADER).get());
        String maincontent = ParserUtil.parseMainContent(argMultimap.getValue(PREFIX_MAINCONTENT).get());

        Content content = new Content(week, header, maincontent);

        return new AddContentCommand(content);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

