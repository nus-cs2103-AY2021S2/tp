package seedu.us.among.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.commons.core.Messages.MESSAGE_ENDPOINTS_LISTED_OVERVIEW;
import static seedu.us.among.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.us.among.testutil.TypicalEndpoints.GET;
import static seedu.us.among.testutil.TypicalEndpoints.GET4;
import static seedu.us.among.testutil.TypicalEndpoints.HEAD;
import static seedu.us.among.testutil.TypicalEndpoints.OPTIONS;
import static seedu.us.among.testutil.TypicalEndpoints.PATCH;
import static seedu.us.among.testutil.TypicalEndpoints.POST;
import static seedu.us.among.testutil.TypicalEndpoints.PUT;
import static seedu.us.among.testutil.TypicalEndpoints.getTypicalEndpointList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;
import seedu.us.among.model.UserPrefs;
import seedu.us.among.model.endpoint.AddressContainsKeywordsPredicate;
import seedu.us.among.model.endpoint.DataContainsKeywordsPredicate;
import seedu.us.among.model.endpoint.EndPointContainsKeywordsPredicate;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.HeadersContainsKeywordsPredicate;
import seedu.us.among.model.endpoint.MethodContainsKeywordsPredicate;
import seedu.us.among.model.endpoint.TagsContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalEndpointList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalEndpointList(), new UserPrefs());

    @Test
    public void equals() {
        EndPointContainsKeywordsPredicate firstPredicate = new EndPointContainsKeywordsPredicate(
                Collections.singletonList("first"));
        EndPointContainsKeywordsPredicate secondPredicate = new EndPointContainsKeywordsPredicate(
                Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different endpoint -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noEndpointFound() {
        String expectedMessage = String.format(MESSAGE_ENDPOINTS_LISTED_OVERVIEW, 0);
        EndPointContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEndpointList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEndpointList());
    }

    @Test
    public void execute_multipleKeywords_multipleEndpointsFound() {
        String expectedMessage = String.format(MESSAGE_ENDPOINTS_LISTED_OVERVIEW, 3);
        EndPointContainsKeywordsPredicate predicate = preparePredicate("put head options");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEndpointList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PUT, HEAD, OPTIONS), model.getFilteredEndpointList());
    }

    @Test
    public void execute_methodField_specificSearch() {
        String expectedMessage = String.format(MESSAGE_ENDPOINTS_LISTED_OVERVIEW, 3);
        MethodContainsKeywordsPredicate predicate = preparePredicateMethod("get post");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEndpointList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GET, POST, GET4), model.getFilteredEndpointList());
    }

    @Test
    public void execute_addressField_specificSearch() {
        String expectedMessage = String.format(MESSAGE_ENDPOINTS_LISTED_OVERVIEW, 2);
        AddressContainsKeywordsPredicate predicate = preparePredicateAddress("reqres.in");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEndpointList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PUT, GET4), model.getFilteredEndpointList());
    }

    @Test
    public void execute_dataField_specificSearch() {
        String expectedMessage = String.format(MESSAGE_ENDPOINTS_LISTED_OVERVIEW, 1);
        DataContainsKeywordsPredicate predicate = preparePredicateData("newData");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEndpointList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GET4), model.getFilteredEndpointList());
    }

    @Test
    public void execute_headersField_specificSearch() {
        String expectedMessage = String.format(MESSAGE_ENDPOINTS_LISTED_OVERVIEW, 2);
        HeadersContainsKeywordsPredicate predicate = preparePredicateHeaders("cool content");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEndpointList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HEAD, OPTIONS), model.getFilteredEndpointList());
    }

    @Test
    public void execute_tagsField_specificSearch() {
        String expectedMessage = String.format(MESSAGE_ENDPOINTS_LISTED_OVERVIEW, 2);
        TagsContainsKeywordsPredicate predicate = preparePredicateTags("tag7 tag3");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEndpointList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PUT, PATCH), model.getFilteredEndpointList());
    }

    @Test
    public void execute_multipleFields_specificSearch() {
        String expectedMessage = String.format(MESSAGE_ENDPOINTS_LISTED_OVERVIEW, 1);
        TagsContainsKeywordsPredicate predicateTags = preparePredicateTags("tag7 tag3");
        MethodContainsKeywordsPredicate predicateMethod = preparePredicateMethod("patch post");

        ArrayList<Predicate<Endpoint>> predicateList = new ArrayList<>();
        predicateList.add(predicateTags);
        predicateList.add(predicateMethod);

        FindCommand command = new FindCommand(predicateList.stream().reduce(x -> true, Predicate::and));
        expectedModel.updateFilteredEndpointList(predicateList.stream().reduce(x -> true, Predicate::and));

        System.out.println(command.execute(model).getFeedbackToUser());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PATCH), model.getFilteredEndpointList());
    }

    /**
     * Parses {@code userInput} into a {@code EndPointContainsKeywordsPredicate}.
     */
    private EndPointContainsKeywordsPredicate preparePredicate(String userInput) {
        return new EndPointContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code MethodContainsKeywordsPredicate}.
     */
    private MethodContainsKeywordsPredicate preparePredicateMethod(String userInput) {
        return new MethodContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code AddressContainsKeywordsPredicate}.
     */
    private AddressContainsKeywordsPredicate preparePredicateAddress(String userInput) {
        return new AddressContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code DataContainsKeywordsPredicate}.
     */
    private DataContainsKeywordsPredicate preparePredicateData(String userInput) {
        return new DataContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code HeadersContainsKeywordsPredicate}.
     */
    private HeadersContainsKeywordsPredicate preparePredicateHeaders(String userInput) {
        return new HeadersContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code HeadersContainsKeywordsPredicate}.
     */
    private TagsContainsKeywordsPredicate preparePredicateTags(String userInput) {
        return new TagsContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
