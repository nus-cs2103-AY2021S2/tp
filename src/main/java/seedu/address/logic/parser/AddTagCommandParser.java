package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddTagCommand object.
 */
public class AddTagCommandParser implements Parser<AddTagCommand> {

    public static final String SHOWN_INDEX = "shown";
    public static final String SELECTED_INDEX = "selected";

    /**
     * Parses the given {@code String} of arguments in the context of the AddTagCommand
     * and returns an AddTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTagCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        List<Index> targetIndexes;
        Set<Tag> tags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        if (argMultimap.getPreamble().equals(SHOWN_INDEX)) {
            return AddTagCommand.createWithShownIndex(tags);
        }
        if (argMultimap.getPreamble().equals(SELECTED_INDEX)) {
            return AddTagCommand.createWithSelectedIndex(tags);
        }

        targetIndexes = ParserUtil.parseIndexes(argMultimap.getPreamble());
        return AddTagCommand.createWithTargetIndexes(targetIndexes, tags);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddTagCommand
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

        try {
            ParserUtil.validateAllButLastTag(argMultimap.getAllValues(PREFIX_TAG));
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

}
