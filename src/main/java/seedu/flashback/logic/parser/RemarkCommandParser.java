package seedu.flashback.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.flashback.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.flashback.commons.core.index.Index;
import seedu.flashback.commons.exceptions.IllegalValueException;
import seedu.flashback.logic.commands.RemarkCommand;
import seedu.flashback.logic.parser.exceptions.ParseException;
import seedu.flashback.model.flashcard.Remark;

public class RemarkCommandParser implements Parser<RemarkCommand> {
    @Override
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemarkCommand.MESSAGE_USAGE), ive);
        }

        String remark = argMultimap.getValue(PREFIX_REMARK).orElse("");

        return new RemarkCommand(index, new Remark(remark));
    }
}
