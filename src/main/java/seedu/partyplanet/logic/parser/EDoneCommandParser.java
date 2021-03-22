package seedu.partyplanet.logic.parser;

import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;

import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.logic.commands.EDoneCommand;
import seedu.partyplanet.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EDoneCommand object
 */
public class EDoneCommandParser implements Parser<EDoneCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EDoneCommand
     * and returns a EDoneCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EDoneCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EDoneCommand.MESSAGE_USAGE));
        }

        return createEDoneCommand(argMultimap.getPreamble());
    }

    private EDoneCommand createEDoneCommand(String args) throws ParseException {
        String[] strIndexes = args.split("\\s+");
        List<Index> validIndexes = new ArrayList<>();
        List<String> invalidIndexes = new ArrayList<>();

        for (String s : strIndexes) {
            try {
                Index index = ParserUtil.parseIndex(s);
                if (!validIndexes.contains(index)) {
                    validIndexes.add(index);
                }
            } catch (ParseException pe) {
                if (!invalidIndexes.contains(s)) {
                    invalidIndexes.add(s);
                }
            }
        }

        return new EDoneCommand(validIndexes, invalidIndexes);
    }
}
