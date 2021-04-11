package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SESSION_ANOTHER_VALID_DATE;
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
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddSessionCommand;
import seedu.address.model.session.Duration;
import seedu.address.model.session.Fee;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;
import seedu.address.model.session.Subject;
import seedu.address.model.student.Name;

public class AddSessionCommandParserTest {
    private AddSessionCommandParser parser = new AddSessionCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Session session = new Session(new SessionDate(VALID_DATE, VALID_TIME),
                new Duration(VALID_DURATION), new Subject(VALID_SUBJECT),
                new Fee(VALID_FEE));

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE
                        + SESSION_VALID_TIME + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE,
                new AddSessionCommand(session, new Name(VALID_NAME_AMY)));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + SESSION_VALID_DATE + SESSION_VALID_TIME
                        + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE,
                new AddSessionCommand(session, new Name(VALID_NAME_BOB)));

        // multiple session date - last accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_ANOTHER_VALID_DATE
                    + SESSION_VALID_DATE + SESSION_VALID_TIME + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT
                    + SESSION_VALID_FEE, new AddSessionCommand(session, new Name(VALID_NAME_AMY)));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage =
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSessionCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_NAME_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + VALID_DATE + SESSION_VALID_TIME
                + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE, expectedMessage);

        // missing time prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE + VALID_TIME
                + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE, expectedMessage);

        // missing duration prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                + VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE, expectedMessage);

        // missing subject prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                + SESSION_VALID_DURATION + VALID_SUBJECT + SESSION_VALID_FEE, expectedMessage);

        // missing fee prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + VALID_FEE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid duration
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                + SESSION_INVALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE, Session.MESSAGE_CONSTRAINTS);


        // multiple invalid, only mention first
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                + SESSION_INVALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE, Session.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_AMY + SESSION_VALID_DATE + SESSION_VALID_TIME
                        + SESSION_VALID_DURATION + SESSION_VALID_SUBJECT + SESSION_VALID_FEE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSessionCommand.MESSAGE_USAGE));
    }
}
