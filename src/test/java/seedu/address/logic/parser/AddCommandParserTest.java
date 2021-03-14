package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DETAILS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DETAILS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.FACULTY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FACULTY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DETAILS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FACULTY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MATRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RESIDENCE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MATRIC_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MATRIC_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RESIDENCE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.RESIDENCE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DETAILS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACULTY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.MedicalDetails;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.SchoolResidence;
import seedu.address.model.person.VaccinationStatus;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).build();
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + MATRIC_DESC_BOB + FACULTY_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_BOB,
                new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + MATRIC_DESC_BOB + FACULTY_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_BOB,
                new AddCommand(expectedPerson));

        // multiple matriculation numbers - last matric number accepted
        assertParseSuccess(parser, NAME_DESC_BOB + FACULTY_DESC_BOB + MATRIC_DESC_AMY + MATRIC_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_BOB,
                new AddCommand(expectedPerson));

        // multiple faculties - last faculty accepted
        assertParseSuccess(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + FACULTY_DESC_AMY + FACULTY_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_BOB,
                new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_AMY
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_BOB,
                new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_AMY + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_BOB,
                new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_BOB,
                new AddCommand(expectedPerson));

        // multiple vaccination statuses - last vaccination status accepted
        assertParseSuccess(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + FACULTY_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_AMY + STATUS_DESC_BOB
                        + DETAILS_DESC_BOB + RESIDENCE_DESC_BOB,
                new AddCommand(expectedPerson));

        // multiple medical details  - last medical details accepted
        assertParseSuccess(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + FACULTY_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_AMY
                        + DETAILS_DESC_BOB + RESIDENCE_DESC_BOB,
                new AddCommand(expectedPerson));

        // multiple school residences  - last school residences accepted
        assertParseSuccess(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + FACULTY_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_AMY + RESIDENCE_DESC_BOB,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Person expectedPerson = new PersonBuilder(AMY).build();
        assertParseSuccess(parser, NAME_DESC_AMY + MATRIC_DESC_AMY + FACULTY_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + STATUS_DESC_AMY + DETAILS_DESC_AMY + RESIDENCE_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + MATRIC_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB, expectedMessage);

        // missing matriculation number prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_MATRIC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB, expectedMessage);

        // missing faculty prefix
        assertParseFailure(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + VALID_FACULTY_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + FACULTY_DESC_BOB + VALID_PHONE_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + VALID_EMAIL_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + VALID_ADDRESS_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB, expectedMessage);

        // missing vaccination status prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_MATRIC_BOB + FACULTY_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + VALID_STATUS_BOB + DETAILS_DESC_BOB,
                expectedMessage);

        // missing medical details prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_MATRIC_BOB + FACULTY_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + VALID_DETAILS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_MATRIC_BOB + FACULTY_DESC_BOB + VALID_PHONE_BOB
                + VALID_EMAIL_BOB + VALID_ADDRESS_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + MATRIC_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB + RESIDENCE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid matriculation number
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_MATRIC_DESC + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB + RESIDENCE_DESC_BOB,
                MatriculationNumber.MESSAGE_CONSTRAINTS);

        // invalid faculty
        assertParseFailure(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + INVALID_FACULTY_DESC + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB + RESIDENCE_DESC_BOB,
                Faculty.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + FACULTY_DESC_BOB + INVALID_PHONE_DESC
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB + RESIDENCE_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_BOB, Address.MESSAGE_CONSTRAINTS);

        // invalid vaccination status
        assertParseFailure(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + INVALID_STATUS_DESC + DETAILS_DESC_BOB
                        + RESIDENCE_DESC_BOB, VaccinationStatus.MESSAGE_CONSTRAINTS);

        // invalid medical details
        assertParseFailure(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + INVALID_DETAILS_DESC
                        + RESIDENCE_DESC_BOB,
                MedicalDetails.MESSAGE_CONSTRAINTS);

        // invalid school residence
        assertParseFailure(parser, NAME_DESC_BOB + MATRIC_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + INVALID_RESIDENCE_DESC, SchoolResidence.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + MATRIC_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + STATUS_DESC_BOB + DETAILS_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + MATRIC_DESC_BOB + FACULTY_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + STATUS_DESC_BOB + DETAILS_DESC_BOB
                        + DETAILS_DESC_BOB, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
