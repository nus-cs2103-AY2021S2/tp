package chim.logic.parser;

import static chim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static chim.logic.commands.CommandTestUtil.CARL_PHONE_DESC;
import static chim.logic.commands.CommandTestUtil.CHEESE_TYPE_DESC_CAMEMBERT;
import static chim.logic.commands.CommandTestUtil.CHEESE_TYPE_DESC_FETA;
import static chim.logic.commands.CommandTestUtil.CHEESE_TYPE_DESC_GOUDA;
import static chim.logic.commands.CommandTestUtil.INVALID_CHEESE_TYPE_DESC;
import static chim.logic.commands.CommandTestUtil.INVALID_ORDER_DATE_DESC;
import static chim.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static chim.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static chim.logic.commands.CommandTestUtil.ORDER_DATE_DESC;
import static chim.logic.commands.CommandTestUtil.QUANTITY_5_DESC;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_BRIE;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_GOUDA;
import static chim.logic.commands.CommandTestUtil.VALID_ORDER_DATE_1;
import static chim.logic.commands.CommandTestUtil.VALID_PHONE_CARL;
import static chim.logic.commands.CommandTestUtil.VALID_QUANTITY_5;
import static chim.logic.parser.CommandParserTestUtil.assertParseFailure;
import static chim.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static chim.testutil.TypicalIndexes.INDEX_SECOND_UNCOMPLETED_ORDER;

import org.junit.jupiter.api.Test;

import chim.commons.core.index.Index;
import chim.logic.commands.EditOrderCommand;
import chim.logic.commands.EditOrderCommand.EditOrderDescriptor;
import chim.model.cheese.CheeseType;
import chim.model.customer.Phone;
import chim.model.order.OrderDate;
import chim.model.order.Quantity;
import chim.testutil.EditOrderDescriptorBuilder;

public class EditOrderCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditOrderCommand.MESSAGE_USAGE);

    private final EditOrderCommandParser parser = new EditOrderCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specific
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_CHEESE_TYPE_BRIE, MESSAGE_INVALID_FORMAT);

        // no fields specific
        assertParseFailure(parser, "1", EditOrderCommand.MESSAGE_NOT_EDITED);

        // no index , no fields
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-10", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 TEST", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 u/TEST", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid Cheese Type
        assertParseFailure(parser, "1" + INVALID_CHEESE_TYPE_DESC, CheeseType.MESSAGE_CONSTRAINTS);

        // Invalid Order Date
        assertParseFailure(parser, "1" + INVALID_ORDER_DATE_DESC, OrderDate.MESSAGE_CONSTRAINTS);

        // Invalid Quantity
        assertParseFailure(parser, "1" + INVALID_QUANTITY_DESC, Quantity.MESSAGE_CONSTRAINTS);

        // Invalid Phone
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // Valid Quantity followed by Invalid Cheese Type
        assertParseFailure(parser, "1" + QUANTITY_5_DESC + INVALID_CHEESE_TYPE_DESC,
                CheeseType.MESSAGE_CONSTRAINTS);

        // Valid Cheese Type followed by Invalid Cheese Type
        assertParseFailure(parser, "1" + CHEESE_TYPE_DESC_GOUDA + INVALID_CHEESE_TYPE_DESC,
                CheeseType.MESSAGE_CONSTRAINTS);

        // Multiple invalid values , first invalud value captured
        assertParseFailure(parser, "1" + INVALID_CHEESE_TYPE_DESC
                        + INVALID_ORDER_DATE_DESC + INVALID_QUANTITY_DESC,
                CheeseType.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_UNCOMPLETED_ORDER;
        String userInput = targetIndex.getOneBased() + QUANTITY_5_DESC
                + CHEESE_TYPE_DESC_GOUDA + ORDER_DATE_DESC + CARL_PHONE_DESC;

        EditOrderDescriptor descriptor =
                new EditOrderDescriptorBuilder().withCheeseType(VALID_CHEESE_TYPE_GOUDA)
                        .withQuantity(VALID_QUANTITY_5).withOrderDate(VALID_ORDER_DATE_1)
                        .withPhone(VALID_PHONE_CARL).build();

        EditOrderCommand expectedCommand = new EditOrderCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_UNCOMPLETED_ORDER;
        String userInput = targetIndex.getOneBased() + QUANTITY_5_DESC
                + CHEESE_TYPE_DESC_GOUDA;

        EditOrderDescriptor descriptor =
                new EditOrderDescriptorBuilder().withCheeseType(VALID_CHEESE_TYPE_GOUDA)
                        .withQuantity(VALID_QUANTITY_5).build();

        EditOrderCommand expectedCommand = new EditOrderCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Index targetIndex = INDEX_SECOND_UNCOMPLETED_ORDER;
        // cheese type
        String userInput = targetIndex.getOneBased() + CHEESE_TYPE_DESC_GOUDA;

        EditOrderDescriptor descriptor =
                new EditOrderDescriptorBuilder().withCheeseType(VALID_CHEESE_TYPE_GOUDA)
                        .build();

        EditOrderCommand expectedCommand = new EditOrderCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // order date
        userInput = targetIndex.getOneBased() + ORDER_DATE_DESC;

        descriptor = new EditOrderDescriptorBuilder().withOrderDate(VALID_ORDER_DATE_1)
                .build();

        expectedCommand = new EditOrderCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // quantity
        userInput = targetIndex.getOneBased() + QUANTITY_5_DESC;

        descriptor = new EditOrderDescriptorBuilder().withQuantity(VALID_QUANTITY_5)
                .build();

        expectedCommand = new EditOrderCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + CARL_PHONE_DESC;

        descriptor = new EditOrderDescriptorBuilder().withPhone(VALID_PHONE_CARL)
                .build();

        expectedCommand = new EditOrderCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_SECOND_UNCOMPLETED_ORDER;
        String userInput = targetIndex.getOneBased() + CHEESE_TYPE_DESC_CAMEMBERT + CHEESE_TYPE_DESC_FETA
                + CHEESE_TYPE_DESC_GOUDA + CARL_PHONE_DESC + ORDER_DATE_DESC;

        EditOrderDescriptor descriptor =
                new EditOrderDescriptorBuilder().withCheeseType(VALID_CHEESE_TYPE_GOUDA)
                        .withOrderDate(VALID_ORDER_DATE_1)
                        .withPhone(VALID_PHONE_CARL).build();

        EditOrderCommand expectedCommand = new EditOrderCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        Index targetIndex = INDEX_SECOND_UNCOMPLETED_ORDER;
        String userInput = targetIndex.getOneBased() + INVALID_CHEESE_TYPE_DESC + CHEESE_TYPE_DESC_FETA
                + CHEESE_TYPE_DESC_GOUDA + CARL_PHONE_DESC + ORDER_DATE_DESC;

        EditOrderDescriptor descriptor =
                new EditOrderDescriptorBuilder().withCheeseType(VALID_CHEESE_TYPE_GOUDA)
                        .withOrderDate(VALID_ORDER_DATE_1)
                        .withPhone(VALID_PHONE_CARL).build();

        EditOrderCommand expectedCommand = new EditOrderCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
