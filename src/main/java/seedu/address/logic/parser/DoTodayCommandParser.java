package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DoTodayCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.util.OperationFlag;

public class DoTodayCommandParser implements Parser<DoTodayCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DoTodayCommand
     * and returns a DoTodayCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DoTodayCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = splitArgs(args);
            if (splitArgs.length != 2) {
                throw new ParseException(ParserUtil.MESSAGE_INVALID_ARGS_LENGTH);
            }
            OperationFlag operationFlag = ParserUtil.parseOperationFlag(splitArgs[0]);
            Index index = ParserUtil.parseIndex(splitArgs[1]);
            return new DoTodayCommand(index, operationFlag);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoTodayCommand.MESSAGE_USAGE), pe);
        }
    }

    private String[] splitArgs(String args) {
        return args.trim().split(" ");
    }
}
