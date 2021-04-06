package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.ParserUtil.parsePropertyDeadline;
import static seedu.address.testutil.TypicalModelManager.getTypicalModelManager;
import static seedu.address.testutil.TypicalProperties.BURGHLEY_DRIVE;
import static seedu.address.testutil.TypicalProperties.JURONG;
import static seedu.address.testutil.TypicalProperties.MAYFAIR;
import static seedu.address.testutil.TypicalProperties.WOODLANDS_CRESCENT;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PropertyAddressPredicate;
import seedu.address.model.property.PropertyClientContactPredicate;
import seedu.address.model.property.PropertyClientEmailPredicate;
import seedu.address.model.property.PropertyClientNamePredicate;
import seedu.address.model.property.PropertyDeadlinePredicate;
import seedu.address.model.property.PropertyNamePredicate;
import seedu.address.model.property.PropertyPostalCodePredicate;
import seedu.address.model.property.PropertyPredicateList;
import seedu.address.model.property.PropertyPricePredicate;
import seedu.address.model.property.PropertyTagsPredicate;
import seedu.address.model.property.PropertyTypePredicate;
import seedu.address.model.util.DateTimeFormat;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPropertyCommand}.
 */
public class FindPropertyCommandTest {
    private Model model = getTypicalModelManager();
    private Model expectedModel = getTypicalModelManager();

    @Test
    public void equalsKeywords() {
        PropertyNamePredicate firstPredicate =
                new PropertyNamePredicate(Collections.singletonList("n/first"));
        PropertyNamePredicate secondPredicate =
                new PropertyNamePredicate(Collections.singletonList("n/second"));

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
    public void equalsPrice() throws ParseException {
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
    public void oneKeywordTest() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate =
                new PropertyPredicateList(
                        Collections.singletonList(new PropertyNamePredicate(Collections.singletonList("mayfair"))));
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(MAYFAIR), model.getFilteredPropertyList());
    }

    @Test
    public void multiKeywordsTest() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW, 3);
        PropertyPredicateList predicate =
                new PropertyPredicateList(
                        Collections.singletonList(new PropertyNamePredicate(
                    Arrays.asList("mayfair", "woodlands", "burghley"))));
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MAYFAIR, BURGHLEY_DRIVE, WOODLANDS_CRESCENT), model.getFilteredPropertyList());
    }

    @Test
    public void priceMoreThanTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate =
                new PropertyPredicateList(
                        Collections.singletonList(new PropertyPricePredicate("1500000", false)));
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(JURONG), model.getFilteredPropertyList());
    }

    @Test
    public void priceLessThanTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate =
                new PropertyPredicateList(
                        Collections.singletonList(new PropertyPricePredicate("400000", true)));
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(WOODLANDS_CRESCENT), model.getFilteredPropertyList());
    }

    @Test
    public void priceOutsideTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 0);
        PropertyPredicateList predicate =
                new PropertyPredicateList(
                        Collections.singletonList(new PropertyPricePredicate("9000000", false)));
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPropertyList());
    }

    @Test
    public void multiPriceTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate =
                new PropertyPredicateList(
                        Arrays.asList(new PropertyPricePredicate("1500000", false),
                                new PropertyPricePredicate("50000000", true)));
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(JURONG), model.getFilteredPropertyList());
    }

    @Test
    public void typeTest() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW, 2);
        PropertyPredicateList predicate =
                new PropertyPredicateList(
                        Collections.singletonList(new PropertyTypePredicate("hdb")));
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(WOODLANDS_CRESCENT, JURONG), model.getFilteredPropertyList());
    }

    @Test
    public void addressTest() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate =
                new PropertyPredicateList(
                        Collections.singletonList(
                                new PropertyAddressPredicate("1 Jurong East Street 32, #08-111")));
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(MAYFAIR), model.getFilteredPropertyList());
    }

    @Test
    public void tagsTest() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate =
                new PropertyPredicateList(
                        Collections.singletonList(
                                new PropertyTagsPredicate("65 square metres")));
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(WOODLANDS_CRESCENT), model.getFilteredPropertyList());
    }

    @Test
    public void multipleTagsTest() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate =
                new PropertyPredicateList(
                        Collections.singletonList(
                                new PropertyTagsPredicate("65 square metres, 2 bedrooms")));
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(WOODLANDS_CRESCENT), model.getFilteredPropertyList());
    }

    @Test
    public void postalTest() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate =
                new PropertyPredicateList(
                        Collections.singletonList(
                                new PropertyPostalCodePredicate("731784")));
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(WOODLANDS_CRESCENT), model.getFilteredPropertyList());
    }

    @Test
    public void deadlineTest() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate =
                new PropertyPredicateList(
                        Collections.singletonList(
                                new PropertyDeadlinePredicate(new Deadline(LocalDate.parse("01-08-2021",
                                        DateTimeFormat.INPUT_DATE_FORMAT)))));
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(WOODLANDS_CRESCENT), model.getFilteredPropertyList());
    }

    @Test
    public void clientContactTest() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate =
                new PropertyPredicateList(
                        Collections.singletonList(
                                new PropertyClientContactPredicate("98664535")));
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(BURGHLEY_DRIVE), model.getFilteredPropertyList());
    }

    @Test
    public void clientEmailTest() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate =
                new PropertyPredicateList(
                        Collections.singletonList(
                                new PropertyClientEmailPredicate("bob@gmail.com")));
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(BURGHLEY_DRIVE), model.getFilteredPropertyList());
    }

    @Test
    public void clientNameTest() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate =
                new PropertyPredicateList(
                        Collections.singletonList(
                                new PropertyClientNamePredicate(Collections.singletonList("bob"))));
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(BURGHLEY_DRIVE), model.getFilteredPropertyList());
    }

    @Test
    public void multiOptionTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate =
                new PropertyPredicateList(
                        Arrays.asList(
                                new PropertyNamePredicate(Collections.singletonList("jurong")),
                                new PropertyPricePredicate("300", false),
                                new PropertyPricePredicate("50000000", true),
                                new PropertyAddressPredicate("Jurong Ave 1, #01-01"),
                                new PropertyTypePredicate("hdb"),
                                new PropertyDeadlinePredicate(parsePropertyDeadline("01-04-2021"))
                                ));
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(JURONG), model.getFilteredPropertyList());
    }
}
