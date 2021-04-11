package seedu.address.logic.parser.meetings;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;

import seedu.address.logic.commands.meetings.SetTimetableCommand;
import seedu.address.logic.parser.DateTimeUtil;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

public class SetTimetableCommandParser implements Parser<SetTimetableCommand> {


    @Override
    public SetTimetableCommand parse(String args) throws ParseException {
        requireNonNull(args);
        LocalDate parsedDate;
        String trimmedArgs = args.trim();
        if (trimmedArgs.equals("")) {
            parsedDate = LocalDate.now();
        } else {
            try {
                parsedDate = DateTimeUtil.parseIsoDate(trimmedArgs);
            } catch (ParseException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SetTimetableCommand.MESSAGE_USAGE));
            }
        }
        return new SetTimetableCommand(parsedDate);
    }
}
