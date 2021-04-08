package chim.logic.parser;

import static chim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static chim.logic.commands.CommandTestUtil.CHEESE_TYPE_DESC_MOZZARELLA;
import static chim.logic.commands.CommandTestUtil.EXPIRY_DATE_5_DESC;
import static chim.logic.commands.CommandTestUtil.INVALID_CHEESE_TYPE_DESC;
import static chim.logic.commands.CommandTestUtil.INVALID_EXPIRY_DATE_DESC;
import static chim.logic.commands.CommandTestUtil.INVALID_MANUFACTURE_DATE_DESC;
import static chim.logic.commands.CommandTestUtil.MANUFACTURE_DATE_5_DESC;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_BRIE;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_MOZZARELLA;
import static chim.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_2;
import static chim.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_5;
import static chim.logic.commands.CommandTestUtil.VALID_MANUFACTURE_DATE_5;
import static chim.logic.parser.CommandParserTestUtil.assertParseFailure;
import static chim.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static chim.testutil.TypicalIndexes.INDEX_THIRD_CHEESE;

import org.junit.jupiter.api.Test;

import chim.commons.core.index.Index;
import chim.logic.commands.EditCheeseCommand;
import chim.model.cheese.CheeseType;
import chim.model.cheese.ExpiryDate;
import chim.model.cheese.ManufactureDate;
import chim.testutil.EditCheeseDescriptorBuilder;

public class EditCheeseCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCheeseCommand.MESSAGE_USAGE);

    private EditCheeseCommandParser parser = new EditCheeseCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_CHEESE_TYPE_CAMEMBERT, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCheeseCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_CHEESE_TYPE_CAMEMBERT, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_CHEESE_TYPE_CAMEMBERT, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_CHEESE_TYPE_DESC,
                CheeseType.MESSAGE_CONSTRAINTS); // invalid cheese type
        assertParseFailure(parser, "1" + INVALID_MANUFACTURE_DATE_DESC,
                ManufactureDate.MESSAGE_CONSTRAINTS); // invalid manufacture date
        assertParseFailure(parser, "1" + INVALID_EXPIRY_DATE_DESC,
                ExpiryDate.MESSAGE_CONSTRAINTS); // invalid expiry date

        // invalid cheese type followed by valid expiry date
        assertParseFailure(parser, "1" + INVALID_CHEESE_TYPE_DESC + VALID_EXPIRY_DATE_2,
                CheeseType.MESSAGE_CONSTRAINTS);

        // valid cheese type followed by invalid cheese type
        assertParseFailure(parser, "1" + VALID_CHEESE_TYPE_BRIE + INVALID_CHEESE_TYPE_DESC,
                MESSAGE_INVALID_FORMAT);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_CHEESE_TYPE_DESC + INVALID_MANUFACTURE_DATE_DESC,
                CheeseType.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_THIRD_CHEESE;
        String userInput = targetIndex.getOneBased() + CHEESE_TYPE_DESC_MOZZARELLA
                + MANUFACTURE_DATE_5_DESC + EXPIRY_DATE_5_DESC;

        EditCheeseCommand.EditCheeseDescriptor descriptor =
                new EditCheeseDescriptorBuilder().withCheeseType(CheeseType.getCheeseType(VALID_CHEESE_TYPE_MOZZARELLA))
                        .withManufactureDate(VALID_MANUFACTURE_DATE_5)
                        .withExpiryDate(VALID_EXPIRY_DATE_5)
                        .build();
        EditCheeseCommand expectedCommand = new EditCheeseCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_THIRD_CHEESE;
        String userInput = targetIndex.getOneBased() + CHEESE_TYPE_DESC_MOZZARELLA
                + MANUFACTURE_DATE_5_DESC;

        EditCheeseCommand.EditCheeseDescriptor descriptor =
                new EditCheeseDescriptorBuilder().withCheeseType(CheeseType.getCheeseType(VALID_CHEESE_TYPE_MOZZARELLA))
                        .withManufactureDate(VALID_MANUFACTURE_DATE_5).build();
        EditCheeseCommand expectedCommand = new EditCheeseCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // cheese type
        Index targetIndex = INDEX_THIRD_CHEESE;
        String userInput = targetIndex.getOneBased() + CHEESE_TYPE_DESC_MOZZARELLA;
        EditCheeseCommand.EditCheeseDescriptor descriptor =
                new EditCheeseDescriptorBuilder()
                        .withCheeseType(CheeseType.getCheeseType(VALID_CHEESE_TYPE_MOZZARELLA)).build();
        EditCheeseCommand expectedCommand = new EditCheeseCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // manufacture date
        userInput = targetIndex.getOneBased() + MANUFACTURE_DATE_5_DESC;
        descriptor = new EditCheeseDescriptorBuilder().withManufactureDate(VALID_MANUFACTURE_DATE_5).build();
        expectedCommand = new EditCheeseCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // expiry date
        userInput = targetIndex.getOneBased() + EXPIRY_DATE_5_DESC;
        descriptor = new EditCheeseDescriptorBuilder().withExpiryDate(VALID_EXPIRY_DATE_5).build();
        expectedCommand = new EditCheeseCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_THIRD_CHEESE;
        String userInput = targetIndex.getOneBased() + CHEESE_TYPE_DESC_MOZZARELLA
                + MANUFACTURE_DATE_5_DESC + CHEESE_TYPE_DESC_MOZZARELLA
                + EXPIRY_DATE_5_DESC;

        EditCheeseCommand.EditCheeseDescriptor descriptor =
                new EditCheeseDescriptorBuilder().withCheeseType(CheeseType.getCheeseType(VALID_CHEESE_TYPE_MOZZARELLA))
                        .withManufactureDate(VALID_MANUFACTURE_DATE_5)
                        .withExpiryDate(VALID_EXPIRY_DATE_5)
                        .build();
        EditCheeseCommand expectedCommand = new EditCheeseCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_THIRD_CHEESE;
        String userInput = targetIndex.getOneBased() + INVALID_CHEESE_TYPE_DESC
                + CHEESE_TYPE_DESC_MOZZARELLA;

        EditCheeseCommand.EditCheeseDescriptor descriptor =
                new EditCheeseDescriptorBuilder()
                        .withCheeseType(CheeseType.getCheeseType(VALID_CHEESE_TYPE_MOZZARELLA)).build();
        EditCheeseCommand expectedCommand = new EditCheeseCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
