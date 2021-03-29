package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.parsePropertyDeadline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
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
import seedu.address.model.property.client.Email;
import seedu.address.model.remark.Remark;
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
    public void parseValidPriceTest() {
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

        predicates.add(new PropertyPostalCodePredicate(new PostalCode("123456")));

        FindPropertyCommand expected =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " p/123456", expected);
    }

    @Test
    public void invalidPostalCodeTest() {
        String expected = "Wrong postal code format! \n"
                + PostalCode.MESSAGE_CONSTRAINTS
                + "\n"
                + FindPropertyCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " p/aff", expected);
    }

    @Test
    public void multiplePostalCodeTest() {
        String expected = "Too many postal codes! Please only use 1 postal code. \n"
                + FindPropertyCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " p/123124 p/124345", expected);
    }

    @Test
    public void validAddressTest() {
        List<Predicate<Property>> predicates = new ArrayList<>();

        predicates.add(new PropertyAddressPredicate(new Address("BLK 123 Kent Ridge Ave 1")));

        FindPropertyCommand expected =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " a/BLK 123 Kent Ridge Ave 1", expected);
    }

    @Test
    public void invalidAddressTest() {
        String expected = "Wrong address format! \n"
                + Address.MESSAGE_CONSTRAINTS
                + "\n"
                + FindPropertyCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " a/ ", expected);
    }

    @Test
    public void multipleAddressTest() {
        String expected = "Too many addresses! Please only use 1 address. \n"
                + FindPropertyCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " a/address 1 a/address 2 a/3rd one ", expected);
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
    public void invalidRemarksTest() {
        String expected = "r/ used but no remarks found! \n"
                + Remark.MESSAGE_CONSTRAINTS
                + "\n"
                + FindPropertyCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " r/ ", expected);
    }

    @Test
    public void validDeadlineTest() throws ParseException {
        List<Predicate<Property>> predicates = new ArrayList<>();

        predicates.add(new PropertyDeadlinePredicate(parsePropertyDeadline("12-09-2021")));

        FindPropertyCommand expected =
                new FindPropertyCommand(new PropertyPredicateList(predicates));

        assertParseSuccess(parser, " d/12-09-2021", expected);
        assertParseSuccess(parser, " d/12-9-2021", expected);
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
    public void multipleDeadlineTest() {
        String expected = "Too many deadlines! Please only use 1 deadline. \n"
                + FindPropertyCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " d/12-09-2021 d/1-1-29", expected);
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
    public void multipleClientContactTest() {
        String expected = "Too many client contacts! Please only use 1 contact. \n"
                + FindPropertyCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " cc/12345678 cc/45678901", expected);
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
        String expected = "Wrong client email format! \n"
                + Email.MESSAGE_CONSTRAINTS
                + "\n"
                + FindPropertyCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " ce/ ", expected);
    }

    @Test
    public void multipleClientEmailTest() {
        String expected = "Too many client emails! Please only use 1 client email. \n"
                + FindPropertyCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " ce/abc.example.com ce/cdf@gmail.com", expected);
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
