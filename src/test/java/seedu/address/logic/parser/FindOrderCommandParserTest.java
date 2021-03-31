package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.StringUtil.splitToKeywordsList;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_BRIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_FETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_COMPLETE_STATUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_INCOMPLETE_STATUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_COMPLETION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModels.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindOrderCommand;
import seedu.address.model.customer.Customer;
import seedu.address.model.order.Order;
import seedu.address.model.order.predicates.OrderCheeseTypePredicate;
import seedu.address.model.order.predicates.OrderCompletionStatusPredicate;
import seedu.address.model.order.predicates.OrderNamePredicate;
import seedu.address.model.order.predicates.OrderPhonePredicate;
import seedu.address.model.util.predicate.CompositeFieldPredicate;
import seedu.address.model.util.predicate.CompositeFieldPredicateBuilder;

public class FindOrderCommandParserTest {

    private FindOrderCommandParser parser;
    private List<Customer> customerList;

    @BeforeEach
    public void setUp() {
        customerList = getTypicalAddressBook().getCustomerList();
        parser = new FindOrderCommandParser(customerList);
    }

    @Test
    public void parse_noArgs_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE);

        // No arguments
        assertParseFailure(parser, "", expectedMessage);

        // No valid prefixes
        assertParseFailure(parser, " a/brie", expectedMessage);
        assertParseFailure(parser, " b/", expectedMessage);
    }

    @Test
    public void parse_validCheeseTypeArgs_returnsFindOrderCommand() {
        // One cheese type in argument, e.g. "findorder t/brie"
        List<String> cheeseTypeKeywordSingle = Collections.singletonList(VALID_CHEESE_TYPE_BRIE);
        CompositeFieldPredicate<Order> singleCheeseTypePredicate = new CompositeFieldPredicateBuilder<Order>()
                .compose(new OrderCheeseTypePredicate(cheeseTypeKeywordSingle))
                .build();
        assertParseSuccess(
                parser,
                " " + PREFIX_CHEESE_TYPE + VALID_CHEESE_TYPE_BRIE,
                new FindOrderCommand(singleCheeseTypePredicate)
        );

        // Multiple cheese types in argument, e.g. "findcheese t/brie camembert feta"
        List<String> cheeseTypeKeywordsMultiple = Arrays.asList(
                VALID_CHEESE_TYPE_BRIE,
                VALID_CHEESE_TYPE_CAMEMBERT,
                VALID_CHEESE_TYPE_FETA
        );
        CompositeFieldPredicate<Order> multipleCheeseTypePredicate = new CompositeFieldPredicateBuilder<Order>()
                .compose(new OrderCheeseTypePredicate(cheeseTypeKeywordsMultiple))
                .build();
        assertParseSuccess(
                parser,
                " " + PREFIX_CHEESE_TYPE + String.join(" ", cheeseTypeKeywordsMultiple),
                new FindOrderCommand(multipleCheeseTypePredicate)
        );
    }

    @Test
    public void parse_validCustomerNameArgs_returnsFindOrderCommand() {
        // Inputting a valid name argument, e,g, findorder n/Bob Choo
        CompositeFieldPredicate<Order> predicate = new CompositeFieldPredicateBuilder<Order>()
                .compose(new OrderNamePredicate(splitToKeywordsList(VALID_NAME_BOB), customerList))
                .build();
        assertParseSuccess(
                parser,
                " " + PREFIX_NAME + VALID_NAME_BOB,
                new FindOrderCommand(predicate)
        );
    }

    @Test
    public void parse_validCustomerPhoneArgs_returnsFindOrderCommand() {
        // Inputting a valid phone argument, e,g, findorder p/22222222
        CompositeFieldPredicate<Order> predicate = new CompositeFieldPredicateBuilder<Order>()
                .compose(new OrderPhonePredicate(splitToKeywordsList(VALID_PHONE_BOB), customerList))
                .build();
        assertParseSuccess(
                parser,
                " " + PREFIX_PHONE + VALID_PHONE_BOB,
                new FindOrderCommand(predicate)
        );
    }

    @Test
    public void parse_validCompletionStatusArgs_returnsFindOrderCommand() {
        // Inputting a "complete" status argument
        CompositeFieldPredicate<Order> completeStatusPredicate = new CompositeFieldPredicateBuilder<Order>()
                .compose(new OrderCompletionStatusPredicate(VALID_ORDER_COMPLETE_STATUS))
                .build();
        assertParseSuccess(
                parser,
                " " + PREFIX_ORDER_COMPLETION_STATUS + VALID_ORDER_COMPLETE_STATUS,
                new FindOrderCommand(completeStatusPredicate)
        );

        // Inputting an "incomplete" status argument
        CompositeFieldPredicate<Order> incompleteStatusPredicate = new CompositeFieldPredicateBuilder<Order>()
                .compose(new OrderCompletionStatusPredicate(VALID_ORDER_INCOMPLETE_STATUS))
                .build();
        assertParseSuccess(
                parser,
                " " + PREFIX_ORDER_COMPLETION_STATUS + VALID_ORDER_INCOMPLETE_STATUS,
                new FindOrderCommand(incompleteStatusPredicate)
        );
    }

    @Test
    public void parse_validAllArgs_returnsFindOrderCommand() {
        CompositeFieldPredicate<Order> predicate = new CompositeFieldPredicateBuilder<Order>()
                .compose(new OrderCheeseTypePredicate(Collections.singletonList(VALID_CHEESE_TYPE_BRIE)))
                .compose(new OrderNamePredicate(splitToKeywordsList(VALID_NAME_BOB), customerList))
                .compose(new OrderPhonePredicate(splitToKeywordsList(VALID_PHONE_BOB), customerList))
                .compose(new OrderCompletionStatusPredicate(VALID_ORDER_COMPLETE_STATUS))
                .build();

        String arg = " "
                + PREFIX_CHEESE_TYPE + VALID_CHEESE_TYPE_BRIE + " "
                + PREFIX_NAME + VALID_NAME_BOB + " "
                + PREFIX_PHONE + VALID_PHONE_BOB + " "
                + PREFIX_ORDER_COMPLETION_STATUS + VALID_ORDER_COMPLETE_STATUS;

        assertParseSuccess(parser, arg, new FindOrderCommand(predicate));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Empty argument following cheese type prefix
        assertParseFailure(
                parser,
                " " + PREFIX_CHEESE_TYPE,
                OrderCheeseTypePredicate.MESSAGE_CONSTRAINTS
        );

        // Empty argument following name prefix
        assertParseFailure(
                parser,
                " " + PREFIX_NAME,
                OrderNamePredicate.MESSAGE_CONSTRAINTS
        );

        // Empty argument following phone prefix
        assertParseFailure(
                parser,
                " " + PREFIX_PHONE,
                OrderPhonePredicate.MESSAGE_CONSTRAINTS
        );

        // Empty or invalid argument following completion status prefix
        assertParseFailure(
                parser,
                " " + PREFIX_ORDER_COMPLETION_STATUS,
                OrderCompletionStatusPredicate.MESSAGE_CONSTRAINTS
        );
        assertParseFailure(
                parser,
                " " + PREFIX_ORDER_COMPLETION_STATUS + "an invalid string",
                OrderCompletionStatusPredicate.MESSAGE_CONSTRAINTS
        );
    }

}
