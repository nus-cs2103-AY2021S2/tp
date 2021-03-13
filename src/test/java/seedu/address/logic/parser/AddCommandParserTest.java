package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COLOUR_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.COLOUR_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.DRESSCODE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DRESSCODE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COLOUR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DRESSCODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SIZE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SIZE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SIZE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COLOUR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DRESSCODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SIZE_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.description.Description;
import seedu.address.model.person.Colour;
import seedu.address.model.person.DressCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Size;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withDescriptions(VALID_DESCRIPTION_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + SIZE_DESC_BOB + COLOUR_DESC_BOB
                + DRESSCODE_DESC_BOB + DESCRIPTION_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + SIZE_DESC_BOB + COLOUR_DESC_BOB
                + DRESSCODE_DESC_BOB + DESCRIPTION_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + SIZE_DESC_AMY + SIZE_DESC_BOB + COLOUR_DESC_BOB
                + DRESSCODE_DESC_BOB + DESCRIPTION_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + SIZE_DESC_BOB + COLOUR_DESC_AMY + COLOUR_DESC_BOB
                + DRESSCODE_DESC_BOB + DESCRIPTION_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + SIZE_DESC_BOB + COLOUR_DESC_BOB + DRESSCODE_DESC_AMY
                + DRESSCODE_DESC_BOB + DESCRIPTION_DESC_FRIEND, new AddCommand(expectedPerson));


        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB)
                .withDescriptions(VALID_DESCRIPTION_FRIEND, VALID_DESCRIPTION_HUSBAND)
                .build();

        assertParseSuccess(parser, NAME_DESC_BOB + SIZE_DESC_BOB + COLOUR_DESC_BOB + DRESSCODE_DESC_BOB
                + DESCRIPTION_DESC_HUSBAND + DESCRIPTION_DESC_FRIEND, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withDescriptions().build();
        assertParseSuccess(parser, NAME_DESC_AMY + SIZE_DESC_AMY + COLOUR_DESC_AMY + DRESSCODE_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + SIZE_DESC_BOB + COLOUR_DESC_BOB + DRESSCODE_DESC_BOB,
                expectedMessage);
        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_SIZE_BOB + COLOUR_DESC_BOB + DRESSCODE_DESC_BOB,
                expectedMessage);
        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + SIZE_DESC_BOB + VALID_COLOUR_BOB + DRESSCODE_DESC_BOB,
                expectedMessage);
        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + SIZE_DESC_BOB + COLOUR_DESC_BOB + VALID_DRESSCODE_BOB,
                expectedMessage);
        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_SIZE_BOB + VALID_COLOUR_BOB + VALID_DRESSCODE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + SIZE_DESC_BOB + COLOUR_DESC_BOB + DRESSCODE_DESC_BOB
                + DESCRIPTION_DESC_HUSBAND + DESCRIPTION_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_SIZE_DESC + COLOUR_DESC_BOB + DRESSCODE_DESC_BOB
                + DESCRIPTION_DESC_HUSBAND + DESCRIPTION_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, NAME_DESC_BOB + SIZE_DESC_BOB + INVALID_COLOUR_DESC + DRESSCODE_DESC_BOB
                + DESCRIPTION_DESC_HUSBAND + DESCRIPTION_DESC_FRIEND, Colour.MESSAGE_CONSTRAINTS);
        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + SIZE_DESC_BOB + COLOUR_DESC_BOB + INVALID_DRESSCODE_DESC
                + DESCRIPTION_DESC_HUSBAND + DESCRIPTION_DESC_FRIEND, DressCode.MESSAGE_CONSTRAINTS);
        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + SIZE_DESC_BOB + COLOUR_DESC_BOB + DRESSCODE_DESC_BOB
                + INVALID_DESCRIPTION_DESC + VALID_DESCRIPTION_FRIEND, Description.MESSAGE_CONSTRAINTS);
        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + SIZE_DESC_BOB + COLOUR_DESC_BOB + INVALID_DRESSCODE_DESC,
                Name.MESSAGE_CONSTRAINTS);
        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + SIZE_DESC_BOB + COLOUR_DESC_BOB
                + DRESSCODE_DESC_BOB + DESCRIPTION_DESC_HUSBAND + DESCRIPTION_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
