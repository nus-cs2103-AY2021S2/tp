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
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COLOUR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DRESSCODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SIZE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalGarments.AMY;
import static seedu.address.testutil.TypicalGarments.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.description.Description;
import seedu.address.model.garment.Colour;
import seedu.address.model.garment.DressCode;
import seedu.address.model.garment.Garment;
import seedu.address.model.garment.Name;
import seedu.address.model.garment.Size;
import seedu.address.testutil.GarmentBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Garment expectedGarment = new GarmentBuilder(BOB).withDescriptions(VALID_DESCRIPTION_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + SIZE_DESC_BOB + COLOUR_DESC_BOB
                + DRESSCODE_DESC_BOB + TYPE_DESC_BOB + DESCRIPTION_DESC_FRIEND, new AddCommand(expectedGarment));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + SIZE_DESC_BOB + COLOUR_DESC_BOB
                + DRESSCODE_DESC_BOB + TYPE_DESC_BOB + DESCRIPTION_DESC_FRIEND, new AddCommand(expectedGarment));

        // multiple sizes - last size accepted
        assertParseSuccess(parser, NAME_DESC_BOB + SIZE_DESC_AMY + SIZE_DESC_BOB + COLOUR_DESC_BOB
                + DRESSCODE_DESC_BOB + TYPE_DESC_BOB + DESCRIPTION_DESC_FRIEND, new AddCommand(expectedGarment));

        // multiple emails - last colour accepted
        assertParseSuccess(parser, NAME_DESC_BOB + SIZE_DESC_BOB + COLOUR_DESC_AMY + COLOUR_DESC_BOB
                + DRESSCODE_DESC_BOB + TYPE_DESC_BOB + DESCRIPTION_DESC_FRIEND, new AddCommand(expectedGarment));

        // multiple addresses - last dresscode accepted
        assertParseSuccess(parser, NAME_DESC_BOB + SIZE_DESC_BOB + COLOUR_DESC_BOB + DRESSCODE_DESC_AMY
                + DRESSCODE_DESC_BOB + TYPE_DESC_BOB + DESCRIPTION_DESC_FRIEND, new AddCommand(expectedGarment));

        // multiple tags - all accepted
        Garment expectedGarmentMultipleTags = new GarmentBuilder(BOB)
                .withDescriptions(VALID_DESCRIPTION_FRIEND, VALID_DESCRIPTION_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + SIZE_DESC_BOB + COLOUR_DESC_BOB + DRESSCODE_DESC_BOB
                + TYPE_DESC_BOB + DESCRIPTION_DESC_HUSBAND + DESCRIPTION_DESC_FRIEND, new AddCommand(expectedGarmentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero descriptions
        Garment expectedGarment = new GarmentBuilder(AMY).withDescriptions().build();
        assertParseSuccess(parser, NAME_DESC_AMY + SIZE_DESC_AMY + COLOUR_DESC_AMY + DRESSCODE_DESC_AMY + TYPE_DESC_AMY,
                new AddCommand(expectedGarment));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + SIZE_DESC_BOB + COLOUR_DESC_BOB + DRESSCODE_DESC_BOB + TYPE_DESC_BOB,
                expectedMessage);

        // missing size prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_SIZE_BOB + COLOUR_DESC_BOB + DRESSCODE_DESC_BOB + TYPE_DESC_BOB,
                expectedMessage);
        // missing colour prefix
        assertParseFailure(parser, NAME_DESC_BOB + SIZE_DESC_BOB + VALID_COLOUR_BOB + DRESSCODE_DESC_BOB + TYPE_DESC_BOB,
                expectedMessage);
        // missing dresscode prefix
        assertParseFailure(parser, NAME_DESC_BOB + SIZE_DESC_BOB + COLOUR_DESC_BOB + VALID_DRESSCODE_BOB + TYPE_DESC_BOB,
                expectedMessage);
        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_SIZE_BOB + VALID_COLOUR_BOB + VALID_DRESSCODE_BOB + VALID_TYPE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + SIZE_DESC_BOB + COLOUR_DESC_BOB + DRESSCODE_DESC_BOB + TYPE_DESC_BOB
                + DESCRIPTION_DESC_HUSBAND + DESCRIPTION_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, NAME_DESC_BOB + INVALID_SIZE_DESC + COLOUR_DESC_BOB + DRESSCODE_DESC_BOB + TYPE_DESC_BOB
                + DESCRIPTION_DESC_HUSBAND + DESCRIPTION_DESC_FRIEND, Size.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, NAME_DESC_BOB + SIZE_DESC_BOB + INVALID_COLOUR_DESC + DRESSCODE_DESC_BOB + TYPE_DESC_BOB
                + DESCRIPTION_DESC_HUSBAND + DESCRIPTION_DESC_FRIEND, Colour.MESSAGE_CONSTRAINTS);
        // invalid dresscode
        assertParseFailure(parser, NAME_DESC_BOB + SIZE_DESC_BOB + COLOUR_DESC_BOB + INVALID_DRESSCODE_DESC + TYPE_DESC_BOB
                + DESCRIPTION_DESC_HUSBAND + DESCRIPTION_DESC_FRIEND, DressCode.MESSAGE_CONSTRAINTS);
        // invalid description
        assertParseFailure(parser, NAME_DESC_BOB + SIZE_DESC_BOB + COLOUR_DESC_BOB + DRESSCODE_DESC_BOB + TYPE_DESC_BOB
                + INVALID_DESCRIPTION_DESC + VALID_DESCRIPTION_FRIEND, Description.MESSAGE_CONSTRAINTS);
        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + SIZE_DESC_BOB + COLOUR_DESC_BOB + INVALID_DRESSCODE_DESC + TYPE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);
        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + SIZE_DESC_BOB + COLOUR_DESC_BOB
                + DRESSCODE_DESC_BOB + TYPE_DESC_BOB + DESCRIPTION_DESC_HUSBAND + DESCRIPTION_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
