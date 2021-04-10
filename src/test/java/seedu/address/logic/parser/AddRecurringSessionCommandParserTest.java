package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REC_END_BEFORE_INTERVAL;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REC_SESSION_INVALID_END_BEFORE_INTERVAL;
import static seedu.address.logic.commands.CommandTestUtil.REC_SESSION_INVALID_END_BEFORE_START;
import static seedu.address.logic.commands.CommandTestUtil.REC_SESSION_INVALID_END_ON_START;
import static seedu.address.logic.commands.CommandTestUtil.REC_SESSION_INVALID_INTERVAL;
import static seedu.address.logic.commands.CommandTestUtil.REC_SESSION_VALID_END;
import static seedu.address.logic.commands.CommandTestUtil.REC_SESSION_VALID_INTERVAL;
import static seedu.address.logic.commands.CommandTestUtil.SESSION_INVALID_DURATION;
import static seedu.address.logic.commands.CommandTestUtil.SESSION_VALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.SESSION_VALID_DURATION;
import static seedu.address.logic.commands.CommandTestUtil.SESSION_VALID_FEE;
import static seedu.address.logic.commands.CommandTestUtil.SESSION_VALID_SUBJECT;
import static seedu.address.logic.commands.CommandTestUtil.SESSION_VALID_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REC_END;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REC_INTERVAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddRecurringSessionCommand;
import seedu.address.model.session.Duration;
import seedu.address.model.session.Fee;
import seedu.address.model.session.Interval;
import seedu.address.model.session.RecurringSession;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;
import seedu.address.model.session.Subject;
import seedu.address.model.student.Name;

public class AddRecurringSessionCommandParserTest {
    private AddRecurringSessionCommandParser parser = new AddRecurringSessionCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        RecurringSession recurringSession = new RecurringSession(new SessionDate(VALID_DATE, VALID_TIME),
                new Duration(VALID_DURATION), new Subject(VALID_SUBJECT),
                new Fee(VALID_FEE), new Interval(VALID_REC_INTERVAL), new SessionDate(VALID_REC_END, VALID_TIME));

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE
                        + SESSION_VALID_TIME + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE
                        + REC_SESSION_VALID_INTERVAL + REC_SESSION_VALID_END,
                new AddRecurringSessionCommand(recurringSession, new Name(VALID_NAME_AMY)));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + SESSION_VALID_DATE + SESSION_VALID_TIME
                        + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE
                        + REC_SESSION_VALID_INTERVAL + REC_SESSION_VALID_END,
                new AddRecurringSessionCommand(recurringSession, new Name(VALID_NAME_BOB)));

        //multiple rec interval - last accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                        + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE
                        + REC_SESSION_INVALID_INTERVAL + REC_SESSION_VALID_INTERVAL
                        + REC_SESSION_VALID_END,
                new AddRecurringSessionCommand(recurringSession, new Name(VALID_NAME_AMY)));

        //multiple rec end - last accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                        + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE
                        + REC_SESSION_VALID_INTERVAL
                        + REC_SESSION_INVALID_END_BEFORE_START + REC_SESSION_VALID_END,
                new AddRecurringSessionCommand(recurringSession, new Name(VALID_NAME_AMY)));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage =
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecurringSessionCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_NAME_AMY + SESSION_VALID_DATE
                + SESSION_VALID_TIME + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE
                + REC_SESSION_VALID_INTERVAL + REC_SESSION_VALID_END, expectedMessage);

        //missing interval prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE + VALID_REC_INTERVAL
                + REC_SESSION_VALID_END, expectedMessage);

        //missing end prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE + REC_SESSION_VALID_INTERVAL
                + VALID_REC_END, expectedMessage);

        //missing start date prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + VALID_DATE + SESSION_VALID_TIME
                + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE + REC_SESSION_VALID_INTERVAL
                + REC_SESSION_VALID_END, expectedMessage);

        //missing start time prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE + VALID_TIME
                + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE + REC_SESSION_VALID_INTERVAL
                + REC_SESSION_VALID_END, expectedMessage);

        //missing duration prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                + VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE + REC_SESSION_VALID_INTERVAL
                + REC_SESSION_VALID_END, expectedMessage);

        //missing subject prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                + SESSION_VALID_DURATION + VALID_SUBJECT + SESSION_VALID_FEE + REC_SESSION_VALID_INTERVAL
                + REC_SESSION_VALID_END, expectedMessage);

        //missing fee prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + VALID_FEE + REC_SESSION_VALID_INTERVAL
                + REC_SESSION_VALID_END, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid duration
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                + SESSION_INVALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE + REC_SESSION_VALID_INTERVAL
                + REC_SESSION_VALID_END, Session.MESSAGE_CONSTRAINTS);

        //invalid end before start
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE + REC_SESSION_VALID_INTERVAL
                + REC_SESSION_INVALID_END_BEFORE_START,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddRecurringSessionCommandParser.MESSAGE_LAST_BEFORE_START));

        //invalid end suggest correct end
        LocalDate possibleDate = RecurringSession.lastValidDateOnOrBefore(
                new SessionDate(INVALID_REC_END_BEFORE_INTERVAL, VALID_TIME),
                new SessionDate(VALID_DATE, VALID_TIME),
                new Interval(VALID_REC_INTERVAL));
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                        + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE
                        + REC_SESSION_VALID_INTERVAL + REC_SESSION_INVALID_END_BEFORE_INTERVAL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecurringSession.MESSAGE_CONSTRAINTS + "\n"
                        + "Did you mean " + possibleDate + " for last date?"));

        // same end and start
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                        + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE
                        + REC_SESSION_VALID_INTERVAL + REC_SESSION_INVALID_END_ON_START,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddRecurringSessionCommandParser.MESSAGE_UNUSED_RECURRENCE));

        // multiple invalid, only mention first
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                + SESSION_INVALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE + REC_SESSION_VALID_INTERVAL
                + REC_SESSION_INVALID_END_BEFORE_START, Session.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                        + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE
                        + REC_SESSION_VALID_INTERVAL + REC_SESSION_INVALID_END_ON_START,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecurringSessionCommand.MESSAGE_USAGE));
    }
}
