package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.BLOODTYPE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.BLOODTYPE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DOB_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DOB_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.HEIGHT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.HEIGHT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BLOODTYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DOB_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_HEIGHT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_WEIGHT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODTYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEIGHT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEIGHT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.WEIGHT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.WEIGHT_DESC_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Height;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Weight;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Patient expectedPatient = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB
                        + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPatient));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB
                        + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPatient));

        // multiple date of births - last date of birth accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DOB_DESC_AMY + DOB_DESC_BOB + GENDER_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB
                        + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPatient));

        // multiple genders - last gender accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_AMY + GENDER_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB
                        + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPatient));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB
                        + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB
                        + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPatient));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB
                        + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPatient));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB
                        + BLOODTYPE_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPatient));

        // multiple blood types - last blood type accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_AMY + BLOODTYPE_DESC_BOB
                        + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPatient));

        // multiple heights - last height accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB
                        + HEIGHT_DESC_AMY + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPatient));

        // multiple weights - last weight accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB
                        + HEIGHT_DESC_BOB + WEIGHT_DESC_AMY + WEIGHT_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPatient));

        // multiple tags - all accepted
        Patient expectedPatientMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedPatientMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Patient expectedPatient = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + DOB_DESC_AMY + GENDER_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + BLOODTYPE_DESC_AMY + HEIGHT_DESC_AMY + WEIGHT_DESC_AMY,
                new AddCommand(expectedPatient));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + DOB_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB,
                expectedMessage);

        // missing date of birth prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_DOB_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB,
                expectedMessage);

        // missing gender prefix
        assertParseFailure(parser, NAME_DESC_BOB + DOB_DESC_BOB + VALID_GENDER_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB + VALID_PHONE_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                        + VALID_EMAIL_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + VALID_ADDRESS_BOB + BLOODTYPE_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB,
                expectedMessage);

        // missing blood type prefix
        assertParseFailure(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + VALID_BLOODTYPE_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB,
                expectedMessage);

        // missing height prefix
        assertParseFailure(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB + VALID_HEIGHT_BOB + WEIGHT_DESC_BOB,
                expectedMessage);

        // missing weight prefix
        assertParseFailure(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB + HEIGHT_DESC_BOB + VALID_WEIGHT_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_DOB_BOB + VALID_GENDER_BOB + VALID_PHONE_BOB
                        + VALID_EMAIL_BOB + VALID_ADDRESS_BOB + VALID_BLOODTYPE_BOB + VALID_HEIGHT_BOB
                        + VALID_WEIGHT_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + DOB_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid date of birth
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_DOB_DESC + GENDER_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, DateOfBirth.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(parser, NAME_DESC_BOB + DOB_DESC_BOB + INVALID_GENDER_DESC + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Gender.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB + INVALID_PHONE_DESC
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + BLOODTYPE_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid blood type
        assertParseFailure(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + INVALID_BLOODTYPE_DESC + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, BloodType.MESSAGE_CONSTRAINTS);

        //invalid height
        assertParseFailure(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB + INVALID_HEIGHT_DESC + WEIGHT_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Height.MESSAGE_CONSTRAINTS);

        //invalid weight
        assertParseFailure(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB + HEIGHT_DESC_BOB + INVALID_WEIGHT_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Weight.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + DOB_DESC_BOB + GENDER_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + BLOODTYPE_DESC_BOB + HEIGHT_DESC_BOB
                        + WEIGHT_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BLOODTYPE_DESC_BOB + HEIGHT_DESC_BOB
                        + WEIGHT_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
