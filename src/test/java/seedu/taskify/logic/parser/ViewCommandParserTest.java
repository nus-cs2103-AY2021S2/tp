package seedu.taskify.logic.parser;

import static seedu.taskify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taskify.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import seedu.taskify.logic.commands.ViewCommand;
import seedu.taskify.model.task.predicates.TaskHasSameDatePredicate;

class ViewCommandParserTest {
    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArg_returnsViewCommand() {
        LocalDate inputDate = LocalDate.parse("2021-03-24");
        ViewCommand expectedViewCommand = new ViewCommand(new TaskHasSameDatePredicate(inputDate));
        assertParseSuccess(parser, "2021-03-24", expectedViewCommand);

        // using 'view today'
        LocalDate todayDate = LocalDate.now();
        ViewCommand expectedTodayViewCommand = new ViewCommand(new TaskHasSameDatePredicate(todayDate));
        assertParseSuccess(parser, "today", expectedTodayViewCommand);

        // using 'view tomorrow'
        LocalDate tomorrowDate = LocalDate.now().plus(1, ChronoUnit.DAYS);
        ViewCommand expectedTomorrowViewCommand = new ViewCommand(new TaskHasSameDatePredicate(tomorrowDate));
        assertParseSuccess(parser, "tomorrow", expectedTomorrowViewCommand);
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        // using 'view Buy grocery' (not a valid date)
        assertParseFailure(parser, "Buy grocery",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        // using 'view 2' (not a valid date)
        assertParseFailure(parser, "2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }
}
