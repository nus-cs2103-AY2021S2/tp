package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GUARDIAN_NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GUARDIAN_NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GUARDIAN_PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GUARDIAN_PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LESSON_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SCHOOL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SCHOOL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_MATH;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_SEC3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GUARDIAN_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GUARDIAN_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SEC3;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_MATH).withLessons(VALID_LESSON_BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + SCHOOL_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB
                + TAG_DESC_MATH + LESSON_DESC_BOB, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + SCHOOL_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB
                + TAG_DESC_MATH + LESSON_DESC_BOB, new AddCommand(expectedPerson));

        // multiple schools - last school accepted
        assertParseSuccess(parser, NAME_DESC_BOB + SCHOOL_DESC_AMY + SCHOOL_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB
                + TAG_DESC_MATH + LESSON_DESC_BOB, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + SCHOOL_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB
                + TAG_DESC_MATH + LESSON_DESC_BOB, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + SCHOOL_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB
                + TAG_DESC_MATH + LESSON_DESC_BOB, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + SCHOOL_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB
                + TAG_DESC_MATH + LESSON_DESC_BOB, new AddCommand(expectedPerson));

        // multiple guardian names - last guardian name accepted
        assertParseSuccess(parser, NAME_DESC_BOB + SCHOOL_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_AMY + GUARDIAN_NAME_DESC_BOB
                + GUARDIAN_PHONE_DESC_BOB + TAG_DESC_MATH + LESSON_DESC_BOB, new AddCommand(expectedPerson));

        // multiple guardian phones - last guardian phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + SCHOOL_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_AMY
                + GUARDIAN_PHONE_DESC_BOB + TAG_DESC_MATH + LESSON_DESC_BOB, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_MATH, VALID_TAG_SEC3)
                .withLessons(VALID_LESSON_BOB).build();
        assertParseSuccess(parser, NAME_DESC_BOB + SCHOOL_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB
                + TAG_DESC_SEC3 + TAG_DESC_MATH + LESSON_DESC_BOB, new AddCommand(expectedPersonMultipleTags));

        // multiple lessons - all accepted
        Person expectedPersonMultipleLessons = new PersonBuilder(BOB).withTags(VALID_TAG_MATH)
                .withLessons(VALID_LESSON_BOB, VALID_LESSON_AMY).build();
        assertParseSuccess(parser, NAME_DESC_BOB + SCHOOL_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB
                + TAG_DESC_MATH + LESSON_DESC_BOB + LESSON_DESC_AMY, new AddCommand(expectedPersonMultipleLessons));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPersonWithoutTags = new PersonBuilder(AMY).withTags().withLessons(VALID_LESSON_AMY).build();
        assertParseSuccess(parser, NAME_DESC_AMY + SCHOOL_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + ADDRESS_DESC_AMY + GUARDIAN_NAME_DESC_AMY + GUARDIAN_PHONE_DESC_AMY + LESSON_DESC_AMY,
                new AddCommand(expectedPersonWithoutTags));

        // zero lessons
        Person expectedPersonWithoutLessons = new PersonBuilder(AMY).withTags(VALID_TAG_MATH).withLessons().build();
        assertParseSuccess(parser, NAME_DESC_AMY + SCHOOL_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + ADDRESS_DESC_AMY + GUARDIAN_NAME_DESC_AMY + GUARDIAN_PHONE_DESC_AMY + TAG_DESC_MATH,
                new AddCommand(expectedPersonWithoutLessons));

        // zero tags and lessons
        Person expectedPersonWithoutOptionalFields =
                new PersonBuilder(AMY).withTags().withLessons().build();
        assertParseSuccess(parser, NAME_DESC_AMY + SCHOOL_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + ADDRESS_DESC_AMY + GUARDIAN_NAME_DESC_AMY + GUARDIAN_PHONE_DESC_AMY,
                new AddCommand(expectedPersonWithoutOptionalFields));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + SCHOOL_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB,
                expectedMessage);

        // missing school prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_SCHOOL_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + SCHOOL_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + SCHOOL_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                        + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + SCHOOL_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + VALID_ADDRESS_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB,
                expectedMessage);

        // missing guardian name prefix
        assertParseFailure(parser, NAME_DESC_BOB + SCHOOL_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + VALID_GUARDIAN_NAME_BOB + GUARDIAN_PHONE_DESC_BOB,
                expectedMessage);

        // missing guardian phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + SCHOOL_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + VALID_GUARDIAN_PHONE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_SCHOOL_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                        + VALID_ADDRESS_BOB + VALID_GUARDIAN_NAME_BOB + VALID_GUARDIAN_PHONE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + SCHOOL_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB
                + TAG_DESC_SEC3 + TAG_DESC_MATH + LESSON_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + SCHOOL_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB
                + TAG_DESC_SEC3 + TAG_DESC_MATH + LESSON_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + SCHOOL_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB
                + TAG_DESC_SEC3 + TAG_DESC_MATH + LESSON_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + SCHOOL_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB
                + TAG_DESC_SEC3 + TAG_DESC_MATH + LESSON_DESC_BOB, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + SCHOOL_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_MATH + LESSON_DESC_BOB, Tag.MESSAGE_CONSTRAINTS);

        // invalid lesson
        assertParseFailure(parser, NAME_DESC_BOB + SCHOOL_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB
                + TAG_DESC_SEC3 + TAG_DESC_MATH + INVALID_LESSON_DESC, Lesson.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + SCHOOL_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + SCHOOL_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + GUARDIAN_NAME_DESC_BOB + GUARDIAN_PHONE_DESC_BOB
                + TAG_DESC_SEC3 + TAG_DESC_MATH + LESSON_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
