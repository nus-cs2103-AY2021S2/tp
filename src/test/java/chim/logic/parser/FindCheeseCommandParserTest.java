package chim.logic.parser;

import static chim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_ASSIGNED_STATUS;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_BRIE;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_FETA;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_UNASSIGNED_STATUS;
import static chim.logic.parser.CliSyntax.PREFIX_CHEESE_ASSIGNMENT_STATUS;
import static chim.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static chim.logic.parser.CommandParserTestUtil.assertParseFailure;
import static chim.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import chim.logic.commands.FindCheeseCommand;
import chim.model.cheese.Cheese;
import chim.model.cheese.predicates.CheeseAssignmentStatusPredicate;
import chim.model.cheese.predicates.CheeseCheeseTypePredicate;
import chim.model.util.predicate.CompositeFieldPredicate;
import chim.model.util.predicate.CompositeFieldPredicateBuilder;


public class FindCheeseCommandParserTest {
    private final FindCheeseCommandParser parser = new FindCheeseCommandParser();

    @Test
    public void parse_noArgs_throwsParseExeception() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCheeseCommand.MESSAGE_USAGE);

        // No arguments
        assertParseFailure(parser, "", expectedMessage);

        // No valid prefixes
        assertParseFailure(parser, "n/brie", expectedMessage);
        assertParseFailure(parser, "a", expectedMessage);
    }

    @Test
    public void parse_validCheeseTypeArgs_returnsFindCheeseCommand() {
        // One cheese type in argument, e.g. "findcheese t/brie"
        List<String> cheeseTypeKeywordSingle = Collections.singletonList(VALID_CHEESE_TYPE_BRIE);
        CompositeFieldPredicate<Cheese> singleCheeseTypePredicate =
                new CompositeFieldPredicate<>(new CheeseCheeseTypePredicate(cheeseTypeKeywordSingle));
        String singleCheeseTypeArg = " " + PREFIX_CHEESE_TYPE + VALID_CHEESE_TYPE_BRIE;
        assertParseSuccess(parser, singleCheeseTypeArg, new FindCheeseCommand(singleCheeseTypePredicate));

        // Multiple cheese types in argument, e.g. "findcheese t/brie camembert feta"
        List<String> cheeseTypeKeywordsMultiple = Arrays.asList(
                VALID_CHEESE_TYPE_BRIE,
                VALID_CHEESE_TYPE_CAMEMBERT,
                VALID_CHEESE_TYPE_FETA
        );
        CompositeFieldPredicate<Cheese> multipleCheeseTypePredicate =
                new CompositeFieldPredicate<>(new CheeseCheeseTypePredicate(cheeseTypeKeywordsMultiple));
        String multipleCheeseTypeArg = " " + PREFIX_CHEESE_TYPE + String.join(" ", cheeseTypeKeywordsMultiple);
        assertParseSuccess(parser, multipleCheeseTypeArg, new FindCheeseCommand(multipleCheeseTypePredicate));
    }

    @Test
    public void parse_validAssignmentStatusArgs_returnsFindCheeseCommand() {
        // Inputting an "assigned" status argument
        CompositeFieldPredicate<Cheese> assignedStatusPredicate =
                new CompositeFieldPredicate<>(new CheeseAssignmentStatusPredicate(VALID_CHEESE_ASSIGNED_STATUS));
        String assignedStatusArg = " " + PREFIX_CHEESE_ASSIGNMENT_STATUS + VALID_CHEESE_ASSIGNED_STATUS;
        assertParseSuccess(parser, assignedStatusArg, new FindCheeseCommand(assignedStatusPredicate));

        // Inputting an "unassigned" status argument
        CompositeFieldPredicate<Cheese> unassignedStatusPredicate =
                new CompositeFieldPredicate<>(new CheeseAssignmentStatusPredicate(VALID_CHEESE_UNASSIGNED_STATUS));
        String unassignedStatusArg = " " + PREFIX_CHEESE_ASSIGNMENT_STATUS + VALID_CHEESE_UNASSIGNED_STATUS;
        assertParseSuccess(parser, unassignedStatusArg, new FindCheeseCommand(unassignedStatusPredicate));
    }

    @Test
    public void parse_validCheeseTypeAndAssignmentStatusArgs_returnsFindCheeseCommand() {
        List<String> cheeseTypeKeywordsMultiple = Arrays.asList(
                VALID_CHEESE_TYPE_BRIE,
                VALID_CHEESE_TYPE_CAMEMBERT
        );

        CompositeFieldPredicate<Cheese> predicate = new CompositeFieldPredicateBuilder<Cheese>()
                .compose(new CheeseAssignmentStatusPredicate(VALID_CHEESE_ASSIGNED_STATUS))
                .compose(new CheeseCheeseTypePredicate(cheeseTypeKeywordsMultiple))
                .build();

        String arg = " " + PREFIX_CHEESE_ASSIGNMENT_STATUS + VALID_CHEESE_ASSIGNED_STATUS
                + " " + PREFIX_CHEESE_TYPE + VALID_CHEESE_TYPE_BRIE
                + " " + VALID_CHEESE_TYPE_CAMEMBERT;

        assertParseSuccess(parser, arg, new FindCheeseCommand(predicate));
    }

    @Test
    public void parse_invalidArgs_throwsParseExeception() {
        // Empty argument following cheese type prefix
        assertParseFailure(
                parser,
                " " + PREFIX_CHEESE_TYPE,
                CheeseCheeseTypePredicate.MESSAGE_CONSTRAINTS
        );
        assertParseFailure(
                parser,
                " " + PREFIX_CHEESE_ASSIGNMENT_STATUS + "assigned " + PREFIX_CHEESE_TYPE,
                CheeseCheeseTypePredicate.MESSAGE_CONSTRAINTS
        );

        // Invalid argument following assignment status prefix
        assertParseFailure(
                parser,
                " " + PREFIX_CHEESE_ASSIGNMENT_STATUS,
                CheeseAssignmentStatusPredicate.MESSAGE_CONSTRAINTS
        );
        assertParseFailure(
                parser,
                " " + PREFIX_CHEESE_ASSIGNMENT_STATUS + " " + PREFIX_CHEESE_TYPE + VALID_CHEESE_TYPE_BRIE,
                CheeseAssignmentStatusPredicate.MESSAGE_CONSTRAINTS
        );
        assertParseFailure(
                parser,
                " " + PREFIX_CHEESE_ASSIGNMENT_STATUS + "a",
                CheeseAssignmentStatusPredicate.MESSAGE_CONSTRAINTS
        );
    }

}
