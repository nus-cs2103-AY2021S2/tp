package seedu.partyplanet.logic.parser;

import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;

import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.logic.commands.EDeleteEventCommand;
import seedu.partyplanet.logic.commands.EDeleteClearCommand;
import seedu.partyplanet.logic.commands.EDeleteCommand;
import seedu.partyplanet.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EDeleteCommand object
 */
public class EDeleteCommandParser implements Parser<EDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EDeleteCommand
     * and returns a EDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EDeleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        boolean idxIsPresent = !argMultimap.getPreamble().isEmpty();

        if (!(idxIsPresent)) {
            return new EDeleteClearCommand();
        }

        try {

            return createEDeleteEventCommand(argMultimap.getPreamble());

        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EDeleteCommand.MESSAGE_USAGE), pe);
        }
    }


    private EDeleteEventCommand createEDeleteEventCommand(String args) throws ParseException {
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

        return new EDeleteEventCommand(validIndexes, invalidIndexes);
    }
}
