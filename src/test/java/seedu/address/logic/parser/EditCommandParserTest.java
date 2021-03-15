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
import static seedu.address.logic.commands.CommandTestUtil.SIZE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SIZE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COLOUR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COLOUR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DRESSCODE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DRESSCODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SIZE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SIZE_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GARMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_GARMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_GARMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditGarmentDescriptor;
import seedu.address.model.description.Description;
import seedu.address.model.garment.Colour;
import seedu.address.model.garment.DressCode;
import seedu.address.model.garment.Name;
import seedu.address.model.garment.Size;
import seedu.address.testutil.EditGarmentDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_DESCRIPTION;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_SIZE_DESC, Size.MESSAGE_CONSTRAINTS); // invalid size
        assertParseFailure(parser, "1" + INVALID_COLOUR_DESC, Colour.MESSAGE_CONSTRAINTS); // invalid colour
        assertParseFailure(parser, "1" + INVALID_DRESSCODE_DESC, DressCode.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC,
                Description.MESSAGE_CONSTRAINTS); // invalid description

        // invalid size followed by valid colour
        assertParseFailure(parser, "1" + INVALID_SIZE_DESC + COLOUR_DESC_AMY, Size.MESSAGE_CONSTRAINTS);

        // valid size followed by invalid size. The test case for invalid size followed by valid size
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + SIZE_DESC_BOB + INVALID_SIZE_DESC, Size.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Garment} being edited,
        // parsing it together with a valid description results in error
        assertParseFailure(parser, "1"
                + DESCRIPTION_DESC_FRIEND + DESCRIPTION_DESC_HUSBAND + TAG_EMPTY, Description.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1"
                + DESCRIPTION_DESC_FRIEND + TAG_EMPTY + DESCRIPTION_DESC_HUSBAND, Description.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1"
                + TAG_EMPTY + DESCRIPTION_DESC_FRIEND + DESCRIPTION_DESC_HUSBAND, Description.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_COLOUR_DESC + VALID_DRESSCODE_AMY
                        + VALID_SIZE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_GARMENT;
        String userInput = targetIndex.getOneBased() + SIZE_DESC_BOB + DESCRIPTION_DESC_HUSBAND
                + COLOUR_DESC_AMY + DRESSCODE_DESC_AMY + NAME_DESC_AMY + DESCRIPTION_DESC_FRIEND;

        EditGarmentDescriptor descriptor = new EditGarmentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withSize(VALID_SIZE_BOB).withColour(VALID_COLOUR_AMY).withDressCode(VALID_DRESSCODE_AMY)
                .withDescriptions(VALID_DESCRIPTION_HUSBAND, VALID_DESCRIPTION_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_GARMENT;
        String userInput = targetIndex.getOneBased() + SIZE_DESC_BOB + COLOUR_DESC_AMY;

        EditGarmentDescriptor descriptor = new EditGarmentDescriptorBuilder().withSize(VALID_SIZE_BOB)
                .withColour(VALID_COLOUR_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_GARMENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditGarmentDescriptor descriptor = new EditGarmentDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + SIZE_DESC_AMY;
        descriptor = new EditGarmentDescriptorBuilder().withSize(VALID_SIZE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // colour
        userInput = targetIndex.getOneBased() + COLOUR_DESC_AMY;
        descriptor = new EditGarmentDescriptorBuilder().withColour(VALID_COLOUR_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + DRESSCODE_DESC_AMY;
        descriptor = new EditGarmentDescriptorBuilder().withDressCode(VALID_DRESSCODE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_FRIEND;
        descriptor = new EditGarmentDescriptorBuilder().withDescriptions(VALID_DESCRIPTION_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_GARMENT;
        String userInput = targetIndex.getOneBased() + SIZE_DESC_AMY + DRESSCODE_DESC_AMY + COLOUR_DESC_AMY
                + DESCRIPTION_DESC_FRIEND + SIZE_DESC_AMY + DRESSCODE_DESC_AMY
                + COLOUR_DESC_AMY + DESCRIPTION_DESC_FRIEND
                + SIZE_DESC_BOB + DRESSCODE_DESC_BOB + COLOUR_DESC_BOB + DESCRIPTION_DESC_HUSBAND;

        EditGarmentDescriptor descriptor = new EditGarmentDescriptorBuilder().withSize(VALID_SIZE_BOB)
                .withColour(VALID_COLOUR_BOB).withDressCode(VALID_DRESSCODE_BOB)
                .withDescriptions(VALID_DESCRIPTION_FRIEND, VALID_DESCRIPTION_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_GARMENT;
        String userInput = targetIndex.getOneBased() + INVALID_SIZE_DESC + SIZE_DESC_BOB;
        EditGarmentDescriptor descriptor = new EditGarmentDescriptorBuilder().withSize(VALID_SIZE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + COLOUR_DESC_BOB + INVALID_SIZE_DESC + DRESSCODE_DESC_BOB
                + SIZE_DESC_BOB;
        descriptor = new EditGarmentDescriptorBuilder().withSize(VALID_SIZE_BOB).withColour(VALID_COLOUR_BOB)
                .withDressCode(VALID_DRESSCODE_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_GARMENT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditGarmentDescriptor descriptor = new EditGarmentDescriptorBuilder().withDescriptions().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
