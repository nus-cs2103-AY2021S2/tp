package seedu.address.logic.parser.Doctor;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_YOHN;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ZOHN;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_INTERNAL;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_MEDICINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_INTERNAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MEDICINE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAppObjects.YOHN;
import static seedu.address.testutil.TypicalAppObjects.ZOHN;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.doctor.AddDoctorCommand;
import seedu.address.logic.parser.doctor.AddDoctorCommandParser;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.DoctorBuilder;

public class AddDoctorCommandParserTest {
    private AddDoctorCommandParser parser = new AddDoctorCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Doctor expectedDoctor = new DoctorBuilder(ZOHN).withTags(VALID_TAG_MEDICINE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_ZOHN + TAG_DESC_MEDICINE,
                new AddDoctorCommand(expectedDoctor));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_YOHN + NAME_DESC_ZOHN + TAG_DESC_MEDICINE,
                new AddDoctorCommand(expectedDoctor));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_ZOHN + TAG_DESC_MEDICINE, new AddDoctorCommand(expectedDoctor));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_ZOHN + TAG_DESC_MEDICINE, new AddDoctorCommand(expectedDoctor));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_ZOHN + TAG_DESC_MEDICINE, new AddDoctorCommand(expectedDoctor));

        // multiple tags - all accepted
        Doctor expectedDoctorMultipleTags = new DoctorBuilder(ZOHN).withTags(VALID_TAG_MEDICINE, VALID_TAG_INTERNAL)
                .build();
        assertParseSuccess(parser, NAME_DESC_ZOHN + TAG_DESC_MEDICINE + TAG_DESC_INTERNAL,
                new AddDoctorCommand(expectedDoctorMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Doctor expectedDoctor = new DoctorBuilder(YOHN).withTags().build();
        assertParseSuccess(parser, NAME_DESC_YOHN, new AddDoctorCommand(expectedDoctor));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDoctorCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + TAG_DESC_INTERNAL + TAG_DESC_MEDICINE, Name.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_ZOHN + INVALID_TAG_DESC + VALID_TAG_MEDICINE, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_ZOHN + TAG_DESC_INTERNAL + TAG_DESC_MEDICINE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDoctorCommand.MESSAGE_USAGE));
    }
}
