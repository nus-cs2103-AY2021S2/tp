package seedu.address.logic.parser.scheduleparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DATE_ONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalDates.APPOINTMENT_FIRST_DATE;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.schedulecommands.ViewScheduleCommand;
import seedu.address.model.schedule.ScheduleDateViewPredicate;

public class ViewScheduleCommandParserTest {

    private ViewScheduleCommandParser parser = new ViewScheduleCommandParser();

    @Test
    public void parse_validArgs_returnsViewCommand() {
        assertParseSuccess(parser, VALID_SCHEDULE_DATE_ONE,
                new ViewScheduleCommand(new ScheduleDateViewPredicate(APPOINTMENT_FIRST_DATE)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewScheduleCommand.MESSAGE_USAGE));
    }
}
