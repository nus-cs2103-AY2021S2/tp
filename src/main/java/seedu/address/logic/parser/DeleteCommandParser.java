package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteContactCommand;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

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

        // Only allow either tag exist or index exist
        if (!(tagIsPresent ^ idxIsPresent)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        try {

            if (tagIsPresent) {
                return createDeleteTagCommand(argMultimap.getAllValues(PREFIX_TAG));
            }

            return createDeleteContactCommand(argMultimap.getPreamble());

        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

    private DeleteTagCommand createDeleteTagCommand(List<String> tags) throws ParseException {
        Set<Tag> tagList = ParserUtil.parseTags(tags);
        return new DeleteTagCommand(tagList);
    }

    private DeleteContactCommand createDeleteContactCommand(String args) throws ParseException {
        String[] strIndexes = args.split("\\s+");
        List<Index> indexes = new ArrayList<>();

        for (String s : strIndexes) {
            Index index = ParserUtil.parseIndex(s);
            indexes.add(index);
        }

        return new DeleteContactCommand(indexes);
    }
}
