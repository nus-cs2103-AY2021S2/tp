package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIND;

import java.util.Optional;

import seedu.address.logic.commands.TagsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class TagsCommandParser implements Parser<TagsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TagsCommand
     * and returns an TagsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TagsCommand parse(String args) throws ParseException {
        if (args.trim().equals("")) {
            return new TagsCommand(tag -> true);
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FIND);

        Optional<String> keyword = argMultimap.getValue(PREFIX_FIND);

        if (keyword.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagsCommand.MESSAGE_USAGE));
        }

        return new TagsCommand(tag -> tag.tagName.contains(keyword.get()));
    }

}
