package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.storemando.logic.commands.CommandTestUtil.EXPIRYDATE_DESC_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.EXPIRYDATE_DESC_CHEESE;
import static seedu.storemando.logic.commands.CommandTestUtil.INVALID_EXPIRYDATE_DESC;
import static seedu.storemando.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.storemando.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.storemando.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static seedu.storemando.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.storemando.logic.commands.CommandTestUtil.LOCATION_DESC_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.LOCATION_DESC_CHEESE;
import static seedu.storemando.logic.commands.CommandTestUtil.NAME_DESC_CHEESE;
import static seedu.storemando.logic.commands.CommandTestUtil.QUANTITY_DESC_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.QUANTITY_DESC_CHEESE;
import static seedu.storemando.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.storemando.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_CHEESE;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_LOCATION_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_LOCATION_CHEESE;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_NAME_CHEESE;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_QUANTITY_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_QUANTITY_CHEESE;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.storemando.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.storemando.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.storemando.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import org.junit.jupiter.api.Test;

import seedu.storemando.commons.core.index.Index;
import seedu.storemando.logic.commands.EditCommand;
import seedu.storemando.logic.commands.EditCommand.EditItemDescriptor;
import seedu.storemando.model.expirydate.ExpiryDate;
import seedu.storemando.model.item.ItemName;
import seedu.storemando.model.item.Location;
import seedu.storemando.model.item.Quantity;
import seedu.storemando.model.tag.Tag;
import seedu.storemando.testutil.EditItemDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_CHEESE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_CHEESE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_CHEESE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, ItemName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_QUANTITY_DESC, Quantity.MESSAGE_CONSTRAINTS); // invalid quantity
        assertParseFailure(parser, "1" + INVALID_EXPIRYDATE_DESC, ExpiryDate.MESSAGE_CONSTRAINTS); // invalid expirydate
        assertParseFailure(parser, "1" + INVALID_LOCATION_DESC, Location.MESSAGE_CONSTRAINTS); // invalid location
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid quantity followed by valid expirydate
        assertParseFailure(parser, "1" + INVALID_QUANTITY_DESC + EXPIRYDATE_DESC_CHEESE,
            Quantity.MESSAGE_CONSTRAINTS);

        // valid quantity followed by invalid quantity. The test case for invalid quantity followed by valid quantity
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + QUANTITY_DESC_BANANA + INVALID_QUANTITY_DESC,
            Quantity.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Item} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY,
            Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND,
            Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
            Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EXPIRYDATE_DESC + VALID_LOCATION_CHEESE
            + VALID_QUANTITY_CHEESE, ItemName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ITEM;

        String userInput = targetIndex.getOneBased() + QUANTITY_DESC_BANANA + TAG_DESC_HUSBAND
            + EXPIRYDATE_DESC_CHEESE + LOCATION_DESC_CHEESE + NAME_DESC_CHEESE + TAG_DESC_FRIEND;

        EditItemDescriptor descriptor = new EditItemDescriptorBuilder().withName(VALID_NAME_CHEESE)
            .withQuantity(VALID_QUANTITY_BANANA).withExpiryDate(VALID_EXPIRYDATE_CHEESE)
            .withLocation(VALID_LOCATION_CHEESE).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased() + QUANTITY_DESC_BANANA + EXPIRYDATE_DESC_CHEESE;

        EditItemDescriptor descriptor = new EditItemDescriptorBuilder().withQuantity(VALID_QUANTITY_BANANA)
            .withExpiryDate(VALID_EXPIRYDATE_CHEESE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_ITEM;
        String userInput = targetIndex.getOneBased() + NAME_DESC_CHEESE;
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder().withName(VALID_NAME_CHEESE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // quantity
        userInput = targetIndex.getOneBased() + QUANTITY_DESC_CHEESE;
        descriptor = new EditItemDescriptorBuilder().withQuantity(VALID_QUANTITY_CHEESE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // expirydate
        userInput = targetIndex.getOneBased() + EXPIRYDATE_DESC_CHEESE;
        descriptor = new EditItemDescriptorBuilder().withExpiryDate(VALID_EXPIRYDATE_CHEESE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + LOCATION_DESC_CHEESE;
        descriptor = new EditItemDescriptorBuilder().withLocation(VALID_LOCATION_CHEESE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditItemDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ITEM;

        String userInput = targetIndex.getOneBased() + QUANTITY_DESC_CHEESE + LOCATION_DESC_CHEESE
            + EXPIRYDATE_DESC_CHEESE + TAG_DESC_FRIEND + QUANTITY_DESC_CHEESE + LOCATION_DESC_CHEESE
            + EXPIRYDATE_DESC_CHEESE + TAG_DESC_FRIEND + QUANTITY_DESC_BANANA + LOCATION_DESC_BANANA
            + EXPIRYDATE_DESC_BANANA + TAG_DESC_HUSBAND;

        EditCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder().withQuantity(VALID_QUANTITY_BANANA)
            .withExpiryDate(VALID_EXPIRYDATE_BANANA).withLocation(VALID_LOCATION_BANANA).withTags(VALID_TAG_FRIEND,
                VALID_TAG_HUSBAND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased() + INVALID_QUANTITY_DESC + QUANTITY_DESC_BANANA;
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder().withQuantity(VALID_QUANTITY_BANANA).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EXPIRYDATE_DESC_BANANA + INVALID_QUANTITY_DESC + LOCATION_DESC_BANANA
            + QUANTITY_DESC_BANANA;
        descriptor = new EditItemDescriptorBuilder().withQuantity(VALID_QUANTITY_BANANA)
            .withExpiryDate(VALID_EXPIRYDATE_BANANA).withLocation(VALID_LOCATION_BANANA).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_ITEM;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditItemDescriptor descriptor = new EditItemDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
