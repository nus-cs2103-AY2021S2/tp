package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CHEESE_TYPE_DESC_CAMEMBERT;
import static seedu.address.logic.commands.CommandTestUtil.CHEESE_TYPE_DESC_FETA;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CHEESE_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ORDER_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.ORDER_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.ORDER_DATE_DESC_FETA;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.QUANTITY_5_DESC;
import static seedu.address.logic.commands.CommandTestUtil.QUANTITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_5;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalCustomers.AMY;
import static seedu.address.testutil.TypicalCustomers.BOB;
import static seedu.address.testutil.TypicalOrder.ORDER_CAMEMBERT;
import static seedu.address.testutil.TypicalOrder.ORDER_FETA;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.customer.Phone;
import seedu.address.model.order.OrderDate;
import seedu.address.model.order.Quantity;

public class AddOrderCommandParserTest {
    private final AddOrderCommandParser parser = new AddOrderCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        CheeseType expectedCheeseType = ORDER_CAMEMBERT.getCheeseType();
        Phone expectedPhone = AMY.getPhone();
        Quantity expectedQuantity = ORDER_CAMEMBERT.getQuantity();
        OrderDate expectedOrderDate = ORDER_CAMEMBERT.getOrderDate();

        assertParseSuccess(parser, CHEESE_TYPE_DESC_CAMEMBERT
                + PHONE_DESC_AMY + QUANTITY_DESC + ORDER_DATE_DESC,
                new AddOrderCommand(expectedCheeseType, expectedPhone, expectedQuantity, expectedOrderDate));

        expectedCheeseType = ORDER_FETA.getCheeseType();
        expectedPhone = BOB.getPhone();
        expectedQuantity = ORDER_FETA.getQuantity();
        expectedOrderDate = ORDER_FETA.getOrderDate();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + CHEESE_TYPE_DESC_FETA
                        + PHONE_DESC_BOB + QUANTITY_5_DESC + ORDER_DATE_DESC_FETA,
                new AddOrderCommand(expectedCheeseType, expectedPhone, expectedQuantity, expectedOrderDate));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no order date provided - set to LocalDate.now()
        assertParseSuccess(parser, CHEESE_TYPE_DESC_CAMEMBERT + PHONE_DESC_AMY + QUANTITY_DESC);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE);

        // missing cheese type prefix
        assertParseFailure(parser, VALID_CHEESE_TYPE_CAMEMBERT + PHONE_DESC_AMY + QUANTITY_DESC, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, CHEESE_TYPE_DESC_CAMEMBERT + VALID_PHONE_AMY + QUANTITY_DESC, expectedMessage);

        // missing quantity prefix
        assertParseFailure(parser, CHEESE_TYPE_DESC_CAMEMBERT + PHONE_DESC_AMY + VALID_QUANTITY_1, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_CHEESE_TYPE_CAMEMBERT + VALID_PHONE_AMY + VALID_QUANTITY_5, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid cheese type
        assertParseFailure(parser, INVALID_CHEESE_TYPE_DESC + PHONE_DESC_AMY + QUANTITY_DESC,
                CheeseType.MESSAGE_CONSTRAINTS);

        // invalid manufacture date
        assertParseFailure(parser, CHEESE_TYPE_DESC_CAMEMBERT + INVALID_PHONE_DESC + QUANTITY_DESC,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid quantity
        assertParseFailure(parser, CHEESE_TYPE_DESC_CAMEMBERT + PHONE_DESC_AMY + INVALID_QUANTITY_DESC,
                Quantity.MESSAGE_CONSTRAINTS);

        // invalid order date
        assertParseFailure(parser, CHEESE_TYPE_DESC_CAMEMBERT + PHONE_DESC_AMY + QUANTITY_DESC
                        + INVALID_ORDER_DATE_DESC, OrderDate.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_CHEESE_TYPE_DESC + INVALID_PHONE_DESC + INVALID_QUANTITY_DESC,
                CheeseType.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + CHEESE_TYPE_DESC_FETA
                        + PHONE_DESC_BOB + QUANTITY_5_DESC + ORDER_DATE_DESC_FETA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE));
    }
}
