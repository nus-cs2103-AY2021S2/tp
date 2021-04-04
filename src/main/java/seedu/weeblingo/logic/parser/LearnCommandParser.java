package seedu.weeblingo.logic.parser;

import static seedu.weeblingo.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.weeblingo.logic.commands.LearnCommand;
import seedu.weeblingo.logic.parser.exceptions.ParseException;
import seedu.weeblingo.model.tag.Tag;

public class LearnCommandParser implements Parser<LearnCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of LearnCommand and returns the LearnCommand
     * object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public LearnCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        List<String> tagsList = argMultimap.getAllValues(PREFIX_TAG);
        Set<Tag> tagsSet = new HashSet<>();

        try {
            for (String tag : tagsList) {
                tagsSet.add(new Tag(tag));
            }
        } catch (IllegalArgumentException exception) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }

        return new LearnCommand(tagsSet);
    }
}
