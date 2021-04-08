package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEvents.EARLIEST_DATE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindScheduleCommand;
import seedu.address.model.common.Date;
import seedu.address.model.event.EventFindSchedulePredicate;
import seedu.address.model.task.TaskFindSchedulePredicate;

public class FindScheduleCommandParserTest {
    private FindScheduleCommandParser parser = new FindScheduleCommandParser();

    @Test
    public void parse_validArg_returnFindScheduleCommand() {
        TaskFindSchedulePredicate taskFindSchedulePredicate = new TaskFindSchedulePredicate(EARLIEST_DATE);
        EventFindSchedulePredicate eventFindSchedulePredicate = new EventFindSchedulePredicate(EARLIEST_DATE);
        FindScheduleCommand expectedFindScheduleCommand =
                new FindScheduleCommand(taskFindSchedulePredicate, eventFindSchedulePredicate);

        assertParseSuccess(parser, EARLIEST_DATE.toString(), expectedFindScheduleCommand);
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindScheduleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidDate_throwsParseException() {
        assertParseFailure(parser, "2021-02-30", Date.MESSAGE_CONSTRAINTS);
    }


    @Test
    public void parse_invalidDateFormat_throwsParseException() {
        assertParseFailure(parser, "2021--02-17", Date.MESSAGE_CONSTRAINTS_FORMAT);
        assertParseFailure(parser, "2021-002-17", Date.MESSAGE_CONSTRAINTS_FORMAT);
    }
}
