package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new ClearCommand object
 */
public class ClearCommandParser implements Parser<ClearCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClearCommand
     * and returns a ClearCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ClearCommand parse(String args) throws ParseException {
        if (args.isBlank()) {
            return new ClearCommand();
        } else {
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);
            Set<Tag> tagsToDelete = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            return new ClearCommand(tagsToDelete);
        }
    }
}
