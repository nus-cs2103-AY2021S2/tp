package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MeetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Meeting;

public class MeetCommandParser implements Parser<MeetCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MeetCommand
     * and returns a MeetCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MeetCommand parse(String args) throws ParseException {
        Index index;
        String place;
        String date;
        String time;
        String action;
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_USAGE));
        }

        try {
            String[] arg = trimmedArgs.split("\\s+", 3);
            index = ParserUtil.parseIndex(arg[0]);

            if (arg[1].equals(MeetCommand.CHECK_CLASHES)) {
                action = MeetCommand.CHECK_CLASHES;
            } else if (arg[1].equals(MeetCommand.IGNORE_CLASHES)) {
                action = MeetCommand.IGNORE_CLASHES;
            } else if (arg[1].equals(MeetCommand.DELETE_MEETING)) {
                return new MeetCommand(index, MeetCommand.DELETE_MEETING, "", "", "");
            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_USAGE));
            }

            String[] arguments = arg[2].split(";");
            place = arguments[0];
            date = arguments[1];
            time = arguments[2];
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_USAGE));
        }

        if (!date.matches(Meeting.DATE_REGEX)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_USAGE));
        }

        if (!time.matches(Meeting.TIME_REGEX)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_USAGE));
        }

        return new MeetCommand(index, action, place, date, time);
    }
}
