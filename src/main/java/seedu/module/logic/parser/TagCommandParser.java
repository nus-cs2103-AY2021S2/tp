package seedu.module.logic.parser;

import seedu.module.commons.core.index.Index;
import seedu.module.commons.exceptions.IllegalValueException;
import seedu.module.logic.commands.Command;
import seedu.module.logic.commands.TagCommand;
import seedu.module.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TAG;

public class TagCommandParser implements Parser {
    @Override
    public Command parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_TAG);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TagCommand.MESSAGE_USAGE), ive);
        }

        String remark = argMultimap.getValue(PREFIX_TAG).orElse("");

        return new TagCommand(index, remark);
    }

}
