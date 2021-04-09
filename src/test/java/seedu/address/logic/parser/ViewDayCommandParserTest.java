package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
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
        assertParseFailure(parser, INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS);
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