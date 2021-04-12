package seedu.cakecollate.logic.parser;

import static seedu.cakecollate.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.cakecollate.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.logic.commands.AddOrderItemCommand;
import seedu.cakecollate.model.orderitem.OrderItem;
import seedu.cakecollate.model.orderitem.Type;

public class AddOrderItemCommandParserTest {

    private AddOrderItemCommandParser parser = new AddOrderItemCommandParser();

    @Test
    public void parse_validArgs_returnsAddOrderItemCommand() {
        Type chocolate = new Type("Chocolate");
        OrderItem chocolateItem = new OrderItem(chocolate);
        assertParseSuccess(parser, "Chocolate", new AddOrderItemCommand(chocolateItem));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "123", "Order description should only contain alphabets.");
    }
}
