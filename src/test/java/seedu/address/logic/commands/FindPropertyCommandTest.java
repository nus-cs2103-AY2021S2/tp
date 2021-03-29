package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_PRICE_LESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_PRICE_MORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAGS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.ParserUtil.parsePropertyAddress;
import static seedu.address.logic.parser.ParserUtil.parsePropertyDeadline;
import static seedu.address.logic.parser.ParserUtil.parsePropertyPostal;
import static seedu.address.testutil.TypicalModelManager.getTypicalModelManager;
import static seedu.address.testutil.TypicalProperties.BURGHLEY_DRIVE;
import static seedu.address.testutil.TypicalProperties.JURONG;
import static seedu.address.testutil.TypicalProperties.MAYFAIR;
import static seedu.address.testutil.TypicalProperties.WOODLANDS_CRESCENT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.FindPropertyCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyAddressPredicate;
import seedu.address.model.property.PropertyNamePredicate;
import seedu.address.model.property.PropertyDeadlinePredicate;
import seedu.address.model.property.PropertyPostalCodePredicate;
import seedu.address.model.property.PropertyPredicateList;
import seedu.address.model.property.PropertyPricePredicate;
import seedu.address.model.property.PropertyRemarksPredicate;
import seedu.address.model.property.PropertyTagsPredicate;
import seedu.address.model.property.PropertyTypePredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPropertyCommand}.
 */
public class FindPropertyCommandTest {
    private Model model = getTypicalModelManager();
    private Model expectedModel = getTypicalModelManager();

    @Test
    public void equalsKeywords() {
        PropertyNamePredicate firstPredicate =
                new PropertyNamePredicate(Collections.singletonList("first"));
        PropertyNamePredicate secondPredicate =
                new PropertyNamePredicate(Collections.singletonList("second"));

        FindPropertyCommand findFirstCommand =
                new FindPropertyCommand(
                        new PropertyPredicateList(Collections.singletonList(firstPredicate)));
        FindPropertyCommand findSecondCommand =
                new FindPropertyCommand(
                        new PropertyPredicateList(Collections.singletonList(secondPredicate)));

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindPropertyCommand findFirstCommandCopy =
                new FindPropertyCommand(new PropertyPredicateList(Collections.singletonList(firstPredicate)));
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different values -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void equalsPrice() {
        PropertyPricePredicate firstPredicate =
                new PropertyPricePredicate("10000", true);
        PropertyPricePredicate secondPredicate =
                new PropertyPricePredicate("2000", false);

        FindPropertyCommand findFirstCommand =
                new FindPropertyCommand(
                        new PropertyPredicateList(Collections.singletonList(firstPredicate)));
        FindPropertyCommand findSecondCommand =
                new FindPropertyCommand(
                        new PropertyPredicateList(Collections.singletonList(secondPredicate)));

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindPropertyCommand findFirstCommandCopy =
                new FindPropertyCommand(new PropertyPredicateList(Collections.singletonList(firstPredicate)));
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different values -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void equalsType() {
        PropertyTypePredicate firstPredicate =
                new PropertyTypePredicate("HDB");
        PropertyTypePredicate secondPredicate =
                new PropertyTypePredicate("condo");

        FindPropertyCommand findFirstCommand =
                new FindPropertyCommand(
                        new PropertyPredicateList(Collections.singletonList(firstPredicate)));
        FindPropertyCommand findSecondCommand =
                new FindPropertyCommand(
                        new PropertyPredicateList(Collections.singletonList(secondPredicate)));

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindPropertyCommand findFirstCommandCopy =
                new FindPropertyCommand(new PropertyPredicateList(Collections.singletonList(firstPredicate)));
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different values -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void noKeywordsTest() {
        Exception e = assertThrows(ParseException.class, () ->
            new FindPropertyCommandParser().parse(" ").execute(model));
        assertEquals(e.getMessage(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPropertyCommand.MESSAGE_USAGE));
    }

    @Test
    public void oneKeywordTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate = preparePredicate("Mayfair");
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(MAYFAIR), model.getFilteredPropertyList());
    }

    @Test
    public void multiKeywordsTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW, 3);
        PropertyPredicateList predicate = preparePredicate("Mayfair Woodlands Burghley");
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MAYFAIR, BURGHLEY_DRIVE, WOODLANDS_CRESCENT), model.getFilteredPropertyList());
    }

    @Test
    public void priceMoreThanTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate = preparePredicate("pm/1500000");
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(JURONG), model.getFilteredPropertyList());
    }

    @Test
    public void priceLessThanTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate = preparePredicate("pl/400000");
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(WOODLANDS_CRESCENT), model.getFilteredPropertyList());
    }

    @Test
    public void priceOutsideTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 0);
        PropertyPredicateList predicate = preparePredicate("pm/9000000");
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPropertyList());
    }

    @Test
    public void multiPriceTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate = preparePredicate("pl/50000000 pm/1500000");
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(JURONG), model.getFilteredPropertyList());
    }

    @Test
    public void typeTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW, 2);
        PropertyPredicateList predicate = preparePredicate("t/hdb");
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(WOODLANDS_CRESCENT, JURONG), model.getFilteredPropertyList());
    }

    @Test
    public void addressTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate = preparePredicate("a/1 Jurong East Street 32, #08-111");
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(MAYFAIR), model.getFilteredPropertyList());
    }

    @Test
    public void tagsTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate = preparePredicate("tags/65 square metres");
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(WOODLANDS_CRESCENT), model.getFilteredPropertyList());
    }

    @Test
    public void multipleTagsTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate = preparePredicate("tags/65 square metres, 2 bedrooms");
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(WOODLANDS_CRESCENT), model.getFilteredPropertyList());
    }

    @Test
    public void postalTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate = preparePredicate("p/731784");
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(WOODLANDS_CRESCENT), model.getFilteredPropertyList());
    }

    @Test
    public void deadlineTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate = preparePredicate("d/01-08-2021");
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(WOODLANDS_CRESCENT), model.getFilteredPropertyList());
    }

    @Test
    public void multiOptionTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate =
                preparePredicate("jurong pl/50000000 pm/300 a/Jurong Ave 1, #01-01 t/hdb d/01-04-2021 ");
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(JURONG), model.getFilteredPropertyList());
    }

    /**
     * Parses {@code userInput} into a {@code Predicate}.
     */
    private PropertyPredicateList preparePredicate(String args) throws ParseException {

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPropertyCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        List<Predicate<Property>> predicates = new ArrayList<>();
        List<String> keywords = new ArrayList<>();

        for (int i = 0; i < nameKeywords.length; i++) {
            String s = nameKeywords[i];
            if (s.contains("/")) {
                String key = s.split("/")[1];
                if (s.startsWith(String.valueOf(PREFIX_PROPERTY_PRICE_MORE))) {
                    predicates.add(new PropertyPricePredicate(key, false));
                } else if (s.startsWith(String.valueOf(PREFIX_PROPERTY_PRICE_LESS))) {
                    predicates.add(new PropertyPricePredicate(key, true));
                } else if (s.startsWith(String.valueOf(PREFIX_TYPE))) {
                    predicates.add(new PropertyTypePredicate(key));
                } else if (s.startsWith(String.valueOf(PREFIX_POSTAL))) {
                    try {
                        predicates.add(new PropertyPostalCodePredicate(parsePropertyPostal(key)));
                    } catch (ParseException e) {
                        throw new ParseException("Wrong postal code format! \n"
                                + e.getMessage()
                                + "\n"
                                + FindPropertyCommand.MESSAGE_USAGE);
                    }
                } else if (s.startsWith(String.valueOf(PREFIX_ADDRESS))) {
                    StringBuilder address = new StringBuilder(key);
                    int j = i + 1;
                    while (j < nameKeywords.length && !nameKeywords[j].contains("/")) {
                        address.append(" ");
                        address.append(nameKeywords[j].strip());
                        j++;
                    }
                    i = j - 1; //reduce by 1 for for loop increment
                    try {
                        predicates.add(new PropertyAddressPredicate(parsePropertyAddress(address.toString())));
                    } catch (ParseException e) {
                        throw new ParseException("Wrong address format! \n"
                                + e.getMessage()
                                + "\n"
                                + FindPropertyCommand.MESSAGE_USAGE);
                    }
                } else if (s.startsWith(String.valueOf(PREFIX_REMARK))) {
                    StringBuilder remarks = new StringBuilder(key);
                    int j = i + 1;
                    while (j < nameKeywords.length && !nameKeywords[j].contains("/")) {
                        remarks.append(" ");
                        remarks.append(nameKeywords[j].strip());
                        j++;
                    }
                    i = j - 1; //reduce by 1 for for loop increment
                    predicates.add(new PropertyRemarksPredicate(remarks.toString()));
                } else if (s.startsWith(String.valueOf(PREFIX_DEADLINE))) {
                    try {
                        predicates.add(new PropertyDeadlinePredicate(parsePropertyDeadline(key)));
                    } catch (ParseException e) {
                        throw new ParseException("Wrong deadline format! \n"
                                + e.getMessage()
                                + "\n"
                                + FindPropertyCommand.MESSAGE_USAGE);
                    }
                } else if (s.startsWith(String.valueOf(PREFIX_TAGS))) {
                    StringBuilder tags = new StringBuilder(key);
                    int j = i + 1;
                    while (j < nameKeywords.length && !nameKeywords[j].contains("/")) {
                        tags.append(" ");
                        tags.append(nameKeywords[j]);
                        j++;
                    }
                    i = j - 1; //reduce by 1 for for loop increment
                    predicates.add(new PropertyTagsPredicate(tags.toString()));
                } else {
                    throw new ParseException("You have used an unknown parameter! \n"
                            + FindPropertyCommand.MESSAGE_USAGE);
                }
            } else {
                keywords.add(s);
            }
        }

        if (keywords.size() > 0) {
            predicates.add(new PropertyNamePredicate(keywords));
        }

        return new PropertyPredicateList(predicates);
    }
}
