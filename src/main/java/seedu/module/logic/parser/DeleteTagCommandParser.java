package seedu.module.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.module.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.module.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.module.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TASK_NAME;

import seedu.module.commons.core.index.Index;
import seedu.module.commons.exceptions.IllegalValueException;
import seedu.module.logic.commands.Command;
import seedu.module.logic.commands.DeleteTagCommand;
import seedu.module.logic.parser.exceptions.ParseException;
import seedu.module.model.tag.Tag;

/**
 * Parses input arguments and creates a new TagCommand object
 */
public class DeleteTagCommandParser implements Parser {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTagCommand
     * and returns a DeleteTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public Command parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TASK_NAME,
                PREFIX_DEADLINE, PREFIX_MODULE, PREFIX_DESCRIPTION, PREFIX_TAG);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteTagCommand.MESSAGE_USAGE), ive);
        }

        if (argMultimap.getValue(PREFIX_TAG).isEmpty()) {
            throw new ParseException(DeleteTagCommand.MESSAGE_NOT_EDITED);
        }

        String tagName = argMultimap.getValue(PREFIX_TAG).orElse("");

        Tag tag;
        try {
            tag = new Tag(tagName);
        } catch (IllegalArgumentException e) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }

        return new DeleteTagCommand(index, tag);
    }

}
