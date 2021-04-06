package seedu.booking.logic.parser.promptparsers;

import java.util.Set;

import seedu.booking.logic.commands.PromptPersonTagsCommand;
import seedu.booking.logic.parser.Parser;
import seedu.booking.logic.parser.ParserUtil;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.Tag;

public class PromptPersonTagsParser implements Parser<PromptPersonTagsCommand> {
    /**
     * Parses the given {@code String} of arguments for tags in the context of adding a person.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PromptPersonTagsCommand parse(String args) throws ParseException {
        Set<Tag> tags = ParserUtil.parseTagsForPromptCommands(args);

        return new PromptPersonTagsCommand(tags);
    }
}
