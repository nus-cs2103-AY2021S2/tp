package seedu.partyplanet.logic.parser;

import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.logic.commands.DeleteClearCommand;
import seedu.partyplanet.logic.commands.DeleteCommand;
import seedu.partyplanet.logic.commands.DeleteContactCommand;
import seedu.partyplanet.logic.commands.DeleteContactWithTagCommand;
import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.tag.Tag;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        boolean tagIsPresent = argMultimap.getValue(PREFIX_TAG).isPresent();
        boolean idxIsPresent = !argMultimap.getPreamble().isEmpty();

        // Do not allow both tag and index to exist
        if (tagIsPresent && idxIsPresent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        if (!(tagIsPresent || idxIsPresent)) {
            return new DeleteClearCommand();
        }

        try {

            if (tagIsPresent) {
                return createDeleteContactWithTagCommand(argMultimap.getAllValues(PREFIX_TAG));
            }

            return createDeleteContactCommand(argMultimap.getPreamble());

        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

    private DeleteContactWithTagCommand createDeleteContactWithTagCommand(List<String> tags) throws ParseException {
        Set<Tag> tagList = ParserUtil.parseTags(tags);
        return new DeleteContactWithTagCommand(tagList);
    }

    private DeleteContactCommand createDeleteContactCommand(String args) throws ParseException {
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

        return new DeleteContactCommand(validIndexes, invalidIndexes);
    }
}
