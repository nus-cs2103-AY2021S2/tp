package seedu.partyplanet.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.partyplanet.commons.core.Messages.MESSAGE_EVENTS_LISTED_OVERVIEW;
import static seedu.partyplanet.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.partyplanet.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.partyplanet.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.partyplanet.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.partyplanet.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.ModelManager;
import seedu.partyplanet.model.UserPrefs;
import seedu.partyplanet.model.event.Event;
import seedu.partyplanet.model.event.predicates.EventNameContainsExactKeywordsPredicate;
import seedu.partyplanet.model.event.predicates.EventNameContainsKeywordsPredicate;
import seedu.partyplanet.model.event.predicates.EventRemarkContainsExactKeywordsPredicate;
import seedu.partyplanet.model.event.predicates.EventRemarkContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class EListCommandTest {

    private Model model;
    private Model expectedModel;
    private final EventNameContainsKeywordsPredicate predicateApr = prepareNamePredicate("Apr");
    private final EventNameContainsKeywordsPredicate predicateFeb = prepareNamePredicate("Feb");
    private final EventNameContainsKeywordsPredicate predicateJan = prepareNamePredicate("Jan");
    private final EventNameContainsExactKeywordsPredicate predicateExactApr = prepareExactNamePredicate("Apr");
    private final EventNameContainsExactKeywordsPredicate predicateExactFeb = prepareExactNamePredicate("Feb");
    private final EventNameContainsExactKeywordsPredicate predicateExactJan = prepareExactNamePredicate("Jan");
    private final EventRemarkContainsKeywordsPredicate predicatePeople = prepareRemarkPredicate("people");
    private final EventRemarkContainsKeywordsPredicate predicateSchool = prepareRemarkPredicate("school");
    private final EventRemarkContainsExactKeywordsPredicate predicateExactPeople =
        prepareExactRemarkPredicate("people");
    private final EventRemarkContainsExactKeywordsPredicate predicateExactSchool =
        prepareExactRemarkPredicate("school");
    private final EventRemarkContainsExactKeywordsPredicate predicateExactNow =
        prepareExactRemarkPredicate("NOW!");

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        EventNameContainsKeywordsPredicate firstPredicate = new EventNameContainsKeywordsPredicate("first");
        EventNameContainsKeywordsPredicate secondPredicate = new EventNameContainsKeywordsPredicate("second");

        EListCommand elistFirstCommand = new EListCommand(firstPredicate);
        EListCommand elistSecondCommand = new EListCommand(secondPredicate);

        // same object -> returns true
        assertEquals(elistFirstCommand, elistFirstCommand);

        // same values -> returns true
        EListCommand elistFirstCommandCopy = new EListCommand(firstPredicate);
        assertEquals(elistFirstCommand, elistFirstCommandCopy);

        // different event -> returns false
        assertNotEquals(elistFirstCommand, elistSecondCommand);
    }

    @Test
    public void execute_elistIsNotFiltered_showsSameList() {
        assertCommandSuccess(new EListCommand(), model, EListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_elistIsFiltered_showsEverything() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);
        assertCommandSuccess(new EListCommand(), model, EListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_allPartialMultipleEventNames_notFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0) + "No events met the requirements.";
        Predicate<Event> predicate = predicateApr.and(predicateFeb.and(predicateJan));
        EListCommand command = new EListCommand(predicate);

        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.EMPTY_LIST, model.getFilteredEventList());
    }

    @Test
    public void execute_anyPartialMultipleEventNames_found() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        Predicate<Event> predicate = predicateApr.or(predicateFeb.or(predicateJan));
        EListCommand command = new EListCommand(predicate);

        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(3, model.getFilteredEventList().size());
    }

    @Test
    public void execute_allExactMultipleEventNames_notFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0) + "No events met the requirements.";
        Predicate<Event> predicate = predicateExactApr.and(predicateExactFeb.and(predicateExactJan));
        EListCommand command = new EListCommand(predicate);

        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.EMPTY_LIST, model.getFilteredEventList());
    }

    @Test
    public void execute_anyExactMultipleEventNames_notFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0) + "No events met the requirements.";
        Predicate<Event> predicate = predicateExactApr.or(predicateExactFeb.or(predicateExactJan));
        EListCommand command = new EListCommand(predicate);

        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.EMPTY_LIST, model.getFilteredEventList());
    }

    @Test
    public void execute_anyExactMultipleEventNames_found() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 1);
        EventNameContainsExactKeywordsPredicate predicateExactFullFeb = prepareExactNamePredicate("Feb Celebration");
        Predicate<Event> predicate = predicateExactApr.or(predicateExactFullFeb.or(predicateExactJan));
        EListCommand command = new EListCommand(predicate);

        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(1, model.getFilteredEventList().size());
    }

    @Test
    public void execute_allPartialWhitespaceName_multipleEventsFound() {
        String expectedMessage = EListCommand.MESSAGE_SUCCESS;
        EventNameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        EListCommand command = new EListCommand(predicate);

        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalEventBook().getEventList(), model.getFilteredEventList());
    }

    @Test
    public void execute_allPartialMultipleEventRemarks_notFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0) + "No events met the requirements.";
        Predicate<Event> predicate = predicateApr.and(predicateFeb.and(predicateJan));
        EListCommand command = new EListCommand(predicate);

        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.EMPTY_LIST, model.getFilteredEventList());
    }

    @Test
    public void execute_anyPartialMultipleEventRemarks_found() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        Predicate<Event> predicate = predicatePeople.or(predicateSchool);
        EListCommand command = new EListCommand(predicate);

        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(3, model.getFilteredEventList().size());
    }

    @Test
    public void execute_allExactMultipleEventRemarks_notFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0) + "No events met the requirements.";
        Predicate<Event> predicate = predicateExactPeople.and(predicateExactSchool.and(predicateExactNow));
        EListCommand command = new EListCommand(predicate);

        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.EMPTY_LIST, model.getFilteredEventList());
    }

    @Test
    public void execute_anyExactMultipleEventRemarks_notFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0) + "No events met the requirements.";
        Predicate<Event> predicate = predicateExactPeople.or(predicateExactSchool.or(predicateExactNow));
        EListCommand command = new EListCommand(predicate);

        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.EMPTY_LIST, model.getFilteredEventList());
    }

    @Test
    public void execute_anyExactMultipleEventRemarks_found() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 1);
        EventRemarkContainsExactKeywordsPredicate predicateExactFullSchool =
            prepareExactRemarkPredicate("in school");
        Predicate<Event> predicate = predicateExactPeople.or(predicateExactFullSchool.or(predicateExactNow));
        EListCommand command = new EListCommand(predicate);

        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(1, model.getFilteredEventList().size());
    }

    @Test
    public void execute_allPartialWhitespaceRemark_multipleEventsFound() {
        String expectedMessage = EListCommand.MESSAGE_SUCCESS;
        EventRemarkContainsKeywordsPredicate predicate = prepareRemarkPredicate(" ");
        EListCommand command = new EListCommand(predicate);

        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalEventBook().getEventList(), model.getFilteredEventList());
    }

    /**
     * Parses {@code userInput} into a {@code EventNameContainsKeywordsPredicate}.
     */
    private EventNameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new EventNameContainsKeywordsPredicate(userInput);
    }

    /**
     * Parses {@code userInput} into a {@code EventNameContainsExactKeywordsPredicate}.
     */
    private EventNameContainsExactKeywordsPredicate prepareExactNamePredicate(String userInput) {
        return new EventNameContainsExactKeywordsPredicate(userInput);
    }

    /**
     * Parses {@code userInput} into a {@code EventRemarkContainsKeywordsPredicate}.
     */
    private EventRemarkContainsKeywordsPredicate prepareRemarkPredicate(String userInput) {
        return new EventRemarkContainsKeywordsPredicate(userInput);
    }

    /**
     * Parses {@code userInput} into a {@code EventRemarkContainsExactKeywordsPredicate}.
     */
    private EventRemarkContainsExactKeywordsPredicate prepareExactRemarkPredicate(String userInput) {
        return new EventRemarkContainsExactKeywordsPredicate(userInput);
    }


}
