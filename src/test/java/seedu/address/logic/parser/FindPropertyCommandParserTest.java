package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.parsePropertyDeadline;
import static seedu.address.model.property.client.AskingPrice.MESSAGE_CONSTRAINTS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyAddressPredicate;
import seedu.address.model.property.PropertyClientContactPredicate;
import seedu.address.model.property.PropertyClientEmailPredicate;
import seedu.address.model.property.PropertyClientNamePredicate;
import seedu.address.model.property.PropertyDeadlinePredicate;
import seedu.address.model.property.PropertyNamePredicate;
import seedu.address.model.property.PropertyPostalCodePredicate;
import seedu.address.model.property.PropertyPredicateList;
import seedu.address.model.property.PropertyPricePredicate;
import seedu.address.model.property.PropertyRemarksPredicate;
import seedu.address.model.property.PropertyTagsPredicate;
import seedu.address.model.property.PropertyTypePredicate;
import seedu.address.model.property.client.Contact;
import seedu.address.model.tag.Tag;

public class FindPropertyCommandParserTest {

    private FindPropertyCommandParser parser = new FindPropertyCommandParser();

    @Test
    public void parseEmptyTest() {
        assertParseFailure(parser, "  ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPropertyCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " this is invalid",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPropertyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseValidKeywordsTest() {
        List<Predicate<Property>> predicates = new ArrayList<>();

        predicates.add(new PropertyNamePredicate(Arrays.asList("Mayfair", "Jurong")));

        FindPropertyCommand expectedFindCommand =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " n/Mayfair Jurong", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ Mayfair  \t   Jurong ", expectedFindCommand);
    }

    @Test
    public void parseValidPriceTest() throws ParseException {
        List<Predicate<Property>> predicates = new ArrayList<>();

        // price less
        predicates.add(new PropertyPricePredicate("1000000", true));

        FindPropertyCommand expectedFindCommand =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " pl/1000000", expectedFindCommand);

        // price more
        predicates = new ArrayList<>();
        predicates.add(new PropertyPricePredicate("1000000", false));

        expectedFindCommand =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " pm/1000000", expectedFindCommand);

        // price range
        predicates = new ArrayList<>();
        predicates.add(new PropertyPricePredicate("1000000", false));
        predicates.add(new PropertyPricePredicate("2000000", true));

        expectedFindCommand =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " pm/1000000 pl/2000000", expectedFindCommand);

        // price with symbols
        // price less
        predicates = new ArrayList<>();
        predicates.add(new PropertyPricePredicate("$1,000,000", true));

        expectedFindCommand =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " pl/$1,000,000", expectedFindCommand);

        // price more
        predicates = new ArrayList<>();
        predicates.add(new PropertyPricePredicate("$1,000,000", false));

        expectedFindCommand =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " pm/$1,000,000", expectedFindCommand);
    }

    @Test
    public void invalidPropertyPriceTest() {
        String expectedPM = "Wrong input format for pm/ !\n"
                + MESSAGE_CONSTRAINTS
                + "\n"
                + FindPropertyCommand.MESSAGE_USAGE;
        String expectedPL = "Wrong input format for pl/ !\n"
                + MESSAGE_CONSTRAINTS
                + "\n"
                + FindPropertyCommand.MESSAGE_USAGE;
        List<Predicate<Property>> predicates = new ArrayList<>();
        assertParseFailure(parser, " pm/abc", expectedPM);
        assertParseFailure(parser, " pl/abc", expectedPL);
        assertParseFailure(parser, " pm/ ", expectedPM);
        assertParseFailure(parser, " pl/ ", expectedPL);
    }

    @Test
    public void validPropertyTypeTest() {
        List<Predicate<Property>> predicates = new ArrayList<>();

        predicates.add(new PropertyTypePredicate("hdb"));

        FindPropertyCommand expectedFindCommand =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        // normal
        assertParseSuccess(parser, " t/hdb", expectedFindCommand);

        // capitalised
        assertParseSuccess(parser, " t/hDb", expectedFindCommand);
    }

    @Test
    public void validPostalCodeTest() {
        List<Predicate<Property>> predicates = new ArrayList<>();

        predicates.add(new PropertyPostalCodePredicate("123456"));

        FindPropertyCommand expected =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " p/123456", expected);
    }


    @Test
    public void validAddressTest() {
        List<Predicate<Property>> predicates = new ArrayList<>();

        predicates.add(new PropertyAddressPredicate("BLK 123 Kent Ridge Ave 1"));

        FindPropertyCommand expected =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " a/BLK 123 Kent Ridge Ave 1", expected);
    }


    @Test
    public void validRemarksTest() {
        List<Predicate<Property>> predicates = new ArrayList<>();

        predicates.add(new PropertyRemarksPredicate("this is a remark"));

        FindPropertyCommand expected =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " r/this is a remark", expected);
    }

    @Test
    public void validDeadlineTest() throws ParseException {
        List<Predicate<Property>> predicates = new ArrayList<>();

        predicates.add(new PropertyDeadlinePredicate(parsePropertyDeadline("12-09-2021")));

        FindPropertyCommand expected =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " d/12-09-2021", expected);
    }

    @Test
    public void invalidDeadlineTest() {
        String expected = "Wrong deadline format! \n"
                + Deadline.MESSAGE_CONSTRAINTS
                + "\n"
                + FindPropertyCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " d/not deadline ", expected);
    }

    @Test
    public void validTagsTest() {
        List<Predicate<Property>> predicates = new ArrayList<>();

        predicates.add(new PropertyTagsPredicate("3 bedrooms, need renovation, need this"));

        FindPropertyCommand expected =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " tags/3 bedrooms, need renovation, need this", expected);

        predicates = new ArrayList<>();
        predicates.add(new PropertyTagsPredicate("3 bedrooms"));
        expected = new FindPropertyCommand(new PropertyPredicateList(predicates));
        assertParseSuccess(parser, " tags/3 bedrooms", expected);
    }

    @Test
    public void invalidTagsTest() {
        String expected = "Wrong tag format! \n"
                + Tag.MESSAGE_CONSTRAINTS
                + "\n"
                + FindPropertyCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " tags/ ", expected);
    }

    @Test
    public void validClientContactTest() {
        List<Predicate<Property>> predicates = new ArrayList<>();

        predicates.add(new PropertyClientContactPredicate("91234567"));

        FindPropertyCommand expected =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " cc/91234567", expected);
    }

    @Test
    public void invalidClientContactTest() {
        String expected = "Wrong client contact format! \n"
                + Contact.MESSAGE_CONSTRAINTS
                + "\n"
                + FindPropertyCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " cc/ ", expected);
    }


    @Test
    public void validClientEmailTest() {
        List<Predicate<Property>> predicates = new ArrayList<>();

        predicates.add(new PropertyClientEmailPredicate("example@gmail.com"));

        FindPropertyCommand expected =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " ce/example@gmail.com", expected);
    }

    @Test
    public void invalidClientEmailTest() {
        String expected = "ce/ used but no keywords found! \n"
                + "Email given is empty. "
                + "\n"
                + FindPropertyCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " ce/ ", expected);
    }

    @Test
    public void clientNameTest() {
        List<Predicate<Property>> predicates = new ArrayList<>();

        predicates.add(new PropertyClientNamePredicate(Collections.singletonList("bob")));

        FindPropertyCommand expected =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " cn/bob", expected);
    }
}
