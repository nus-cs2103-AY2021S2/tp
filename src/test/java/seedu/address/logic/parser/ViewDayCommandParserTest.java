package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewDayCommand;
import seedu.address.model.task.attributes.Date;
import seedu.address.model.task.predicates.TaskOnDatePredicate;

class ViewDayCommandParserTest {
    private ViewDayCommandParser parser = new ViewDayCommandParser();

    @Test
    public void parse_invalidDate_failure() {
        // invalid format
        assertParseFailure(parser, "12/12/20002", Date.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "0/12/2002", Date.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "00/1/20002", Date.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "01/12/200", Date.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "00/12/200a", Date.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "01-12-2002", Date.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "2002/12/20", Date.MESSAGE_CONSTRAINTS);
        // invalid date
        assertParseFailure(parser, "12/12/0000", Date.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "12/00/2002", Date.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "00/12/2002", Date.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "01/12/1899", Date.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "01/12/2100", Date.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingDate_failure() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewDayCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validDate_success() {
        Date expectedDate = new Date(VALID_DATE_AMY);
        TaskOnDatePredicate expectedPredicate = new TaskOnDatePredicate(expectedDate);
        assertEquals(new ViewDayCommand(expectedPredicate, expectedDate.getDate()),
                new ViewDayCommand(expectedPredicate, expectedDate.getDate()));
        assertParseSuccess(parser, VALID_DATE_AMY, new ViewDayCommand(expectedPredicate, expectedDate.getDate()));
    }
}
