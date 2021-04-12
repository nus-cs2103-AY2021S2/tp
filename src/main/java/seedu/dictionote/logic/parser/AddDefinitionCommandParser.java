package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_DEFINITION;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_TERM;

import java.util.stream.Stream;

import seedu.dictionote.logic.commands.AddDefinitionCommand;
import seedu.dictionote.logic.parser.exceptions.ParseException;
import seedu.dictionote.model.dictionary.Definition;

/**
 * Parses input arguments and creates a new AddNoteCommand object.
 */
public class AddDefinitionCommandParser implements Parser<AddDefinitionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddContentCommand
     * and returns an AddContentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDefinitionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TERM, PREFIX_DEFINITION);

        if (!arePrefixesPresent(argMultimap, PREFIX_TERM, PREFIX_DEFINITION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDefinitionCommand.MESSAGE_USAGE));
        }

        String term = ParserUtil.parseWeek(argMultimap.getValue(PREFIX_TERM).get());
        String defs = ParserUtil.parseHeader(argMultimap.getValue(PREFIX_DEFINITION).get());

        Definition definition = new Definition(term, defs);

        return new AddDefinitionCommand(definition);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

