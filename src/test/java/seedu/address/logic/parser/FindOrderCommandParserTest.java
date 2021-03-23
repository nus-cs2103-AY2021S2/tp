package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_BRIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_FETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_COMPLETE_STATUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_INCOMPLETE_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_COMPLETION_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindOrderCommand;
import seedu.address.model.order.Order;
import seedu.address.model.order.predicates.OrderCheeseTypePredicate;
import seedu.address.model.order.predicates.OrderCompletionStatusPredicate;
import seedu.address.model.util.predicate.CompositeFieldPredicate;
import seedu.address.model.util.predicate.CompositeFieldPredicateBuilder;

public class FindOrderCommandParserTest {
    private final FindOrderCommandParser parser = new FindOrderCommandParser();

    @Test
    public void parse_noArgs_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE);

        // No arguments
        assertParseFailure(parser, "", expectedMessage);

        // No valid prefixes
        assertParseFailure(parser, "n/brie", expectedMessage);
        assertParseFailure(parser, "a", expectedMessage);
    }

    @Test
    public void parse_validCheeseTypeArgs_returnsFindOrderCommand() {
        // One cheese type in argument, e.g. "findorder t/brie"
        List<String> cheeseTypeKeywordSingle = Collections.singletonList(VALID_CHEESE_TYPE_BRIE);
        CompositeFieldPredicate<Order> singleCheeseTypePredicate =
                new CompositeFieldPredicate<>(new OrderCheeseTypePredicate(cheeseTypeKeywordSingle));
        String singleCheeseTypeArg = " " + PREFIX_CHEESE_TYPE + VALID_CHEESE_TYPE_BRIE;
        assertParseSuccess(parser, singleCheeseTypeArg, new FindOrderCommand(singleCheeseTypePredicate));

        // Multiple cheese types in argument, e.g. "findcheese t/brie camembert feta"
        List<String> cheeseTypeKeywordsMultiple = Arrays.asList(
                VALID_CHEESE_TYPE_BRIE,
                VALID_CHEESE_TYPE_CAMEMBERT,
                VALID_CHEESE_TYPE_FETA
        );
        CompositeFieldPredicate<Order> multipleCheeseTypePredicate =
                new CompositeFieldPredicate<>(new OrderCheeseTypePredicate(cheeseTypeKeywordsMultiple));
        String multipleCheeseTypeArg = " " + PREFIX_CHEESE_TYPE + String.join(" ", cheeseTypeKeywordsMultiple);
        assertParseSuccess(parser, multipleCheeseTypeArg, new FindOrderCommand(multipleCheeseTypePredicate));
    }

    @Test
    public void parse_validCompletionStatusArgs_returnsFindOrderCommand() {
        // Inputting a "complete" status argument
        CompositeFieldPredicate<Order> completeStatusPredicate =
                new CompositeFieldPredicate<>(new OrderCompletionStatusPredicate(VALID_ORDER_COMPLETE_STATUS));
        String completeStatusArg = " " + PREFIX_ORDER_COMPLETION_STATUS + VALID_ORDER_COMPLETE_STATUS;
        assertParseSuccess(parser, completeStatusArg, new FindOrderCommand(completeStatusPredicate));

        // Inputting an "incomplete" status argument
        CompositeFieldPredicate<Order> incompleteStatusPredicate =
                new CompositeFieldPredicate<>(new OrderCompletionStatusPredicate(VALID_ORDER_INCOMPLETE_STATUS));
        String incompleteStatusArg = " " + PREFIX_ORDER_COMPLETION_STATUS + VALID_ORDER_INCOMPLETE_STATUS;
        assertParseSuccess(parser, incompleteStatusArg, new FindOrderCommand(incompleteStatusPredicate));
    }

    @Test
    public void parse_validCheeseTypeAndCompletionStatusArgs_returnsFindOrderCommand() {
        List<String> cheeseTypeKeywordsMultiple = Arrays.asList(
                VALID_CHEESE_TYPE_BRIE,
                VALID_CHEESE_TYPE_CAMEMBERT
        );

        CompositeFieldPredicate<Order> predicate = new CompositeFieldPredicateBuilder<Order>()
                .compose(new OrderCompletionStatusPredicate(VALID_ORDER_COMPLETE_STATUS))
                .compose(new OrderCheeseTypePredicate(cheeseTypeKeywordsMultiple))
                .build();

        String arg = " " + PREFIX_ORDER_COMPLETION_STATUS + VALID_ORDER_COMPLETE_STATUS
                + " " + PREFIX_CHEESE_TYPE + VALID_CHEESE_TYPE_BRIE
                + " " + VALID_CHEESE_TYPE_CAMEMBERT;

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
        assertParseFailure(
                parser,
                " " + PREFIX_ORDER_COMPLETION_STATUS + "assigned " + PREFIX_CHEESE_TYPE,
                OrderCheeseTypePredicate.MESSAGE_CONSTRAINTS
        );

        // Invalid argument following completion status prefix
        assertParseFailure(
                parser,
                " " + PREFIX_ORDER_COMPLETION_STATUS,
                OrderCompletionStatusPredicate.MESSAGE_CONSTRAINTS
        );
        assertParseFailure(
                parser,
                " " + PREFIX_ORDER_COMPLETION_STATUS + " " + PREFIX_CHEESE_TYPE + VALID_CHEESE_TYPE_BRIE,
                OrderCompletionStatusPredicate.MESSAGE_CONSTRAINTS
        );
        assertParseFailure(
                parser,
                " " + PREFIX_ORDER_COMPLETION_STATUS + "a",
                OrderCompletionStatusPredicate.MESSAGE_CONSTRAINTS
        );
    }

}
