package seedu.timeforwheels.logic.parser;
import static java.util.Objects.requireNonNull;
import static seedu.timeforwheels.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.timeforwheels.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.timeforwheels.commons.core.index.Index;
import seedu.timeforwheels.commons.exceptions.IllegalValueException;
import seedu.timeforwheels.logic.commands.RemarkCommand;
import seedu.timeforwheels.logic.parser.exceptions.ParseException;
import seedu.timeforwheels.model.customer.Remark;


public class RemarkCommandParser implements Parser<RemarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE), ive);
        }

        String remark = argMultimap.getValue(PREFIX_REMARK).orElse("");

        return new RemarkCommand(index, new Remark(remark));
    }

}
