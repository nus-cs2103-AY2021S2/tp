package seedu.student.logic.parser;

import static seedu.student.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.student.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.student.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.student.logic.commands.CommandTestUtil.DETAILS_DESC_AMY;
import static seedu.student.logic.commands.CommandTestUtil.DETAILS_DESC_BOB;
import static seedu.student.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.student.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.student.logic.commands.CommandTestUtil.FACULTY_DESC_AMY;
import static seedu.student.logic.commands.CommandTestUtil.FACULTY_DESC_BOB;
import static seedu.student.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.student.logic.commands.CommandTestUtil.INVALID_DETAILS_DESC;
import static seedu.student.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.student.logic.commands.CommandTestUtil.INVALID_FACULTY_DESC;
import static seedu.student.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.student.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.student.logic.commands.CommandTestUtil.INVALID_RESIDENCE_DESC;
import static seedu.student.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.student.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.student.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.student.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.student.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.student.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.student.logic.commands.CommandTestUtil.RESIDENCE_DESC_AMY;
import static seedu.student.logic.commands.CommandTestUtil.RESIDENCE_DESC_BOB;
import static seedu.student.logic.commands.CommandTestUtil.STATUS_DESC_AMY;
import static seedu.student.logic.commands.CommandTestUtil.STATUS_DESC_BOB;
import static seedu.student.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.student.logic.commands.CommandTestUtil.VALID_DETAILS_BOB;
import static seedu.student.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.student.logic.commands.CommandTestUtil.VALID_FACULTY_BOB;
import static seedu.student.logic.commands.CommandTestUtil.VALID_MATRIC_AMY;
import static seedu.student.logic.commands.CommandTestUtil.VALID_MATRIC_BOB;
import static seedu.student.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.student.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.student.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.student.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.student.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.student.testutil.TypicalStudents.AMY;
import static seedu.student.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.student.logic.commands.AddCommand;
import seedu.student.model.student.Address;
import seedu.student.model.student.Email;
import seedu.student.model.student.Faculty;
import seedu.student.model.student.MedicalDetails;
import seedu.student.model.student.Name;
import seedu.student.model.student.Phone;
import seedu.student.model.student.SchoolResidence;
import seedu.student.model.student.Student;
import seedu.student.model.student.VaccinationStatus;
import seedu.student.testutil.StudentBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB).build();
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_MATRIC_BOB + NAME_DESC_BOB + FACULTY_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_BOB,
                new AddCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, VALID_MATRIC_BOB + NAME_DESC_AMY + NAME_DESC_BOB + FACULTY_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_BOB,
                new AddCommand(expectedStudent));

        // multiple faculties - last faculty accepted
        assertParseSuccess(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + FACULTY_DESC_AMY + FACULTY_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_BOB,
                new AddCommand(expectedStudent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_AMY
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_BOB,
                new AddCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_AMY + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_BOB,
                new AddCommand(expectedStudent));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_BOB,
                new AddCommand(expectedStudent));

        // multiple vaccination statuses - last vaccination status accepted
        assertParseSuccess(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + FACULTY_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_AMY + STATUS_DESC_BOB
                        + DETAILS_DESC_BOB + RESIDENCE_DESC_BOB,
                new AddCommand(expectedStudent));

        // multiple medical details  - last medical details accepted
        assertParseSuccess(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + FACULTY_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_AMY
                        + DETAILS_DESC_BOB + RESIDENCE_DESC_BOB,
                new AddCommand(expectedStudent));

        // multiple school residences  - last school residences accepted
        assertParseSuccess(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + FACULTY_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_AMY + RESIDENCE_DESC_BOB,
                new AddCommand(expectedStudent));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Student expectedStudent = new StudentBuilder(AMY).build();
        assertParseSuccess(parser, VALID_MATRIC_AMY + NAME_DESC_AMY + FACULTY_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + STATUS_DESC_AMY + DETAILS_DESC_AMY + RESIDENCE_DESC_AMY,
                new AddCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_MATRIC_BOB + VALID_NAME_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB, expectedMessage);

        // missing faculty prefix
        assertParseFailure(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + VALID_FACULTY_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + FACULTY_DESC_BOB + VALID_PHONE_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + VALID_EMAIL_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + VALID_ADDRESS_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB, expectedMessage);

        // missing vaccination status prefix
        assertParseFailure(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + FACULTY_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + VALID_STATUS_BOB + DETAILS_DESC_BOB,
                expectedMessage);

        // missing medical details prefix
        assertParseFailure(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + FACULTY_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + VALID_DETAILS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_MATRIC_BOB + VALID_NAME_BOB + FACULTY_DESC_BOB + VALID_PHONE_BOB
                + VALID_EMAIL_BOB + VALID_ADDRESS_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, VALID_MATRIC_BOB + INVALID_NAME_DESC + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB + RESIDENCE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid faculty
        assertParseFailure(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + INVALID_FACULTY_DESC + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB + RESIDENCE_DESC_BOB,
                Faculty.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + FACULTY_DESC_BOB + INVALID_PHONE_DESC
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB + RESIDENCE_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_BOB, Address.MESSAGE_CONSTRAINTS);

        // invalid vaccination status
        assertParseFailure(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + INVALID_STATUS_DESC + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_BOB, VaccinationStatus.MESSAGE_CONSTRAINTS);

        // invalid medical details
        assertParseFailure(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + INVALID_DETAILS_DESC
                        + RESIDENCE_DESC_BOB,
                MedicalDetails.MESSAGE_CONSTRAINTS);

        // invalid school residence
        assertParseFailure(parser, VALID_MATRIC_BOB + NAME_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + INVALID_RESIDENCE_DESC, SchoolResidence.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, VALID_MATRIC_BOB + INVALID_NAME_DESC + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + STATUS_DESC_BOB + DETAILS_DESC_BOB, Name.MESSAGE_CONSTRAINTS);
    }
}
