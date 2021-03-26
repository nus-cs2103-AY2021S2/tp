package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.find.FindPropertyCommand;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyContainsKeywordsPredicate;
import seedu.address.model.property.PropertyPredicateList;
import seedu.address.model.property.PropertyPricePredicate;
import seedu.address.model.property.PropertyTypePredicate;

public class FindPropertyCommandParserTest {

    private FindPropertyCommandParser parser = new FindPropertyCommandParser();

    @Test
    public void parseEmptyTest() {
        assertParseFailure(parser, "  ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPropertyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseValidKeywordsTest() {
        List<Predicate<Property>> predicates = new ArrayList<>();

        predicates.add(new PropertyContainsKeywordsPredicate(Arrays.asList("Mayfair", "Jurong")));

        FindPropertyCommand expectedFindCommand =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, "Mayfair Jurong", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Mayfair \n \t Jurong  \t", expectedFindCommand);
    }

    @Test
    public void parseValidPriceTest() {
        List<Predicate<Property>> predicates = new ArrayList<>();

        // price less
        predicates.add(new PropertyPricePredicate("1000000", true));

        FindPropertyCommand expectedFindCommand =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, "pl/1000000", expectedFindCommand);

        // price more
        predicates = new ArrayList<>();
        predicates.add(new PropertyPricePredicate("1000000", false));

        expectedFindCommand =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, "pm/1000000", expectedFindCommand);

        // price range
        predicates = new ArrayList<>();
        predicates.add(new PropertyPricePredicate("1000000", false));
        predicates.add(new PropertyPricePredicate("2000000", true));

        expectedFindCommand =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, "pm/1000000 pl/2000000", expectedFindCommand);

        // price with symbols
        // price less
        predicates = new ArrayList<>();
        predicates.add(new PropertyPricePredicate("$1,000,000", true));

        expectedFindCommand =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, "pl/$1,000,000", expectedFindCommand);

        // price more
        predicates = new ArrayList<>();
        predicates.add(new PropertyPricePredicate("$1,000,000", false));

        expectedFindCommand =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, "pm/$1,000,000", expectedFindCommand);
    }

    @Test
    public void validPropertyTypeTest() {
        List<Predicate<Property>> predicates = new ArrayList<>();

        predicates.add(new PropertyTypePredicate("hdb"));

        FindPropertyCommand expectedFindCommand =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        // normal
        assertParseSuccess(parser, "t/hdb", expectedFindCommand);

        // capitalised
        assertParseSuccess(parser, "t/hDb", expectedFindCommand);
    }
}
