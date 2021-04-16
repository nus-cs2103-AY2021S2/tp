package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new DeleteTagCommand object.
 */
public class DeleteTagCommandParser implements Parser<DeleteTagCommand> {

    public static final String SHOWN_INDEX = "shown";
    public static final String SELECTED_INDEX = "selected";

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTagCommand
     * and returns an AddTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (argMultimap.getPreamble().trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE));
        }

        List<Index> targetIndexes;
        Set<Tag> tags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        if (tags.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getPreamble().equals(SHOWN_INDEX)) {
            return DeleteTagCommand.createWithShownIndex(tags);
        }
        if (argMultimap.getPreamble().equals(SELECTED_INDEX)) {
            return DeleteTagCommand.createWithSelectedIndex(tags);
        }

        targetIndexes = ParserUtil.parseIndexes(argMultimap.getPreamble());
        return DeleteTagCommand.createWithTargetIndexes(targetIndexes, tags);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTagCommand
     * and returns true if arguments are valid to be aliased.
     */
    @Override
    public boolean isValidCommandToAlias(String userInput) {
        if (userInput.trim().isEmpty()) {
            return true;
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_TAG);

        if (argMultimap.getPreamble().trim().isEmpty()) {
            return false;
        }

        if (!argMultimap.getPreamble().equals(SHOWN_INDEX) && !argMultimap.getPreamble().equals(SELECTED_INDEX)) {
            try {
                ParserUtil.parseIndexes(argMultimap.getPreamble());
            } catch (ParseException pe) {
                return false;
            }
        }

        List<String> tags = argMultimap.getAllValues(PREFIX_TAG);
        if (tags.isEmpty()) {
            return true;
        }

        try {
            if (tags.get(tags.size() - 1).isEmpty()) {
                ParserUtil.validateAllButLastTag(tags);
                return true;
            }
            ParserUtil.parseTags(tags);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

}
