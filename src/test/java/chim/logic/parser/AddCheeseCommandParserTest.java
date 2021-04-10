package chim.logic.parser;

import static chim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static chim.logic.commands.CommandTestUtil.CHEESE_TYPE_DESC_CAMEMBERT;
import static chim.logic.commands.CommandTestUtil.EXPIRY_DATE_DESC;
import static chim.logic.commands.CommandTestUtil.INVALID_CHEESE_TYPE_DESC;
import static chim.logic.commands.CommandTestUtil.INVALID_EXPIRY_DATE_DESC;
import static chim.logic.commands.CommandTestUtil.INVALID_MANUFACTURE_DATE_DESC;
import static chim.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static chim.logic.commands.CommandTestUtil.MANUFACTURE_DATE_DESC;
import static chim.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static chim.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static chim.logic.commands.CommandTestUtil.QUANTITY_DESC;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static chim.logic.commands.CommandTestUtil.VALID_QUANTITY_5;
import static chim.logic.parser.CommandParserTestUtil.assertParseFailure;
import static chim.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static chim.testutil.TypicalCheese.CAMEMBERT;

import org.junit.jupiter.api.Test;

import chim.logic.commands.AddCheeseCommand;
import chim.model.CheeseIdStub;
import chim.model.cheese.Cheese;
import chim.model.cheese.CheeseType;
import chim.model.cheese.ExpiryDate;
import chim.model.cheese.ManufactureDate;
import chim.model.order.Quantity;
import chim.testutil.CheeseBuilder;

public class AddCheeseCommandParserTest {
    private final AddCheeseCommandParser parser = new AddCheeseCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Cheese expectedCheese = new CheeseBuilder(CAMEMBERT).withId(CheeseIdStub.getNextId()).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + CHEESE_TYPE_DESC_CAMEMBERT
                + QUANTITY_DESC + MANUFACTURE_DATE_DESC + EXPIRY_DATE_DESC,
                new AddCheeseCommand(new Cheese[]{expectedCheese}));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no manufacture date provided - default to LocalDate.now()
        assertParseSuccess(parser, CHEESE_TYPE_DESC_CAMEMBERT + QUANTITY_DESC + EXPIRY_DATE_DESC);

        // no expiry date provided - set to Optional<>()
        Cheese expectedCheese = new CheeseBuilder(CAMEMBERT)
                .withId(CheeseIdStub.getNextId()).withExpiryDate(null).build();
        assertParseSuccess(parser, CHEESE_TYPE_DESC_CAMEMBERT + QUANTITY_DESC + MANUFACTURE_DATE_DESC,
                new AddCheeseCommand(new Cheese[]{expectedCheese}));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCheeseCommand.MESSAGE_USAGE);

        // missing cheese type prefix
        assertParseFailure(parser, VALID_CHEESE_TYPE_CAMEMBERT + QUANTITY_DESC, expectedMessage);

        // missing quantity prefix
        assertParseFailure(parser, CHEESE_TYPE_DESC_CAMEMBERT + VALID_QUANTITY_5, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_CHEESE_TYPE_CAMEMBERT + VALID_QUANTITY_5, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid cheese type
        assertParseFailure(parser, INVALID_CHEESE_TYPE_DESC + QUANTITY_DESC, CheeseType.MESSAGE_CONSTRAINTS);

        // invalid quantity
        assertParseFailure(parser, CHEESE_TYPE_DESC_CAMEMBERT + INVALID_QUANTITY_DESC, Quantity.MESSAGE_CONSTRAINTS);

        // invalid manufacture date
        assertParseFailure(parser, CHEESE_TYPE_DESC_CAMEMBERT + QUANTITY_DESC + INVALID_MANUFACTURE_DATE_DESC,
                ManufactureDate.MESSAGE_CONSTRAINTS);

        // invalid expiry date
        assertParseFailure(parser, CHEESE_TYPE_DESC_CAMEMBERT + QUANTITY_DESC + MANUFACTURE_DATE_DESC
                + INVALID_EXPIRY_DATE_DESC, ExpiryDate.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_CHEESE_TYPE_DESC + INVALID_QUANTITY_DESC, CheeseType.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + CHEESE_TYPE_DESC_CAMEMBERT
                        + QUANTITY_DESC + MANUFACTURE_DATE_DESC + EXPIRY_DATE_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCheeseCommand.MESSAGE_USAGE));
    }
}
