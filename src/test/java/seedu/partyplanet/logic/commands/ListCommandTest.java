package seedu.partyplanet.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.partyplanet.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.partyplanet.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.partyplanet.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.partyplanet.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.partyplanet.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.partyplanet.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.ModelManager;
import seedu.partyplanet.model.UserPrefs;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.model.person.predicates.NameContainsExactKeywordsPredicate;
import seedu.partyplanet.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.partyplanet.model.person.predicates.TagsContainsExactTagPredicate;
import seedu.partyplanet.model.person.predicates.TagsContainsTagPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;
    private final NameContainsKeywordsPredicate predicateKurz = preparePredicate("Kurz");
    private final NameContainsKeywordsPredicate predicateElle = preparePredicate("Elle");
    private final NameContainsKeywordsPredicate predicateKunz = preparePredicate("Kunz");
    private final NameContainsExactKeywordsPredicate predicateExactKurz = prepareExactPredicate("Kurz");
    private final NameContainsExactKeywordsPredicate predicateExactElle = prepareExactPredicate("Elle");
    private final NameContainsExactKeywordsPredicate predicateExactKunz = prepareExactPredicate("Kunz");

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate("first");
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate("second");

        ListCommand listFirstCommand = new ListCommand(firstPredicate);
        ListCommand listSecondCommand = new ListCommand(secondPredicate);

        // same object -> returns true
        assertEquals(listFirstCommand, listFirstCommand);

        // same values -> returns true
        ListCommand listFirstCommandCopy = new ListCommand(firstPredicate);
        assertEquals(listFirstCommand, listFirstCommandCopy);

        // different person -> returns false
        assertNotEquals(listFirstCommand, listSecondCommand);
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_allPartialMultipleNames_notFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        Predicate<Person> predicate = predicateKurz.and(predicateElle.and(predicateKunz));
        ListCommand command = new ListCommand(predicate);

        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.EMPTY_LIST, model.getFilteredPersonList());
    }

    @Test
    public void execute_anyPartialMultipleNames_found() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        Predicate<Person> predicate = predicateKurz.or(predicateElle.or(predicateKunz));
        ListCommand command = new ListCommand(predicate);

        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(3, model.getFilteredPersonList().size());
    }

    @Test
    public void execute_allExactMultipleNames_notFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        Predicate<Person> predicate = predicateExactKurz.and(predicateExactElle.and(predicateExactKunz));
        ListCommand command = new ListCommand(predicate);

        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.EMPTY_LIST, model.getFilteredPersonList());
    }

    @Test
    public void execute_anyExactMultipleNames_notFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        Predicate<Person> predicate = predicateExactKurz.or(predicateExactElle.or(predicateExactKunz));
        ListCommand command = new ListCommand(predicate);

        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.EMPTY_LIST, model.getFilteredPersonList());
    }

    @Test
    public void execute_anyExactMultipleNames_found() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        NameContainsExactKeywordsPredicate predicateExactFullElle = prepareExactPredicate("Elle Meyer");
        Predicate<Person> predicate = predicateExactKurz.or(predicateExactFullElle.or(predicateExactKunz));
        ListCommand command = new ListCommand(predicate);

        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(1, model.getFilteredPersonList().size());
    }

    @Test
    public void execute_allPartialWhitespace_multiplePersonsFound() {
        String expectedMessage = ListCommand.MESSAGE_SUCCESS;
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        ListCommand command = new ListCommand(predicate);

        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalAddressBook().getPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_allExactTags_found() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        TagsContainsExactTagPredicate predicateOwesMoney = new TagsContainsExactTagPredicate("owesMoney");
        TagsContainsExactTagPredicate predicateFriends = new TagsContainsExactTagPredicate("friends");
        Predicate<Person> predicate = predicateOwesMoney.and(predicateFriends);
        ListCommand command = new ListCommand(predicate);

        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(1, model.getFilteredPersonList().size());
    }

    @Test
    public void execute_allPartialTagName_found() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3); // Alice, Benson, Daniel
        TagsContainsTagPredicate predicateTag = new TagsContainsTagPredicate("friend");
        NameContainsKeywordsPredicate predicateName = new NameContainsKeywordsPredicate("e");
        Predicate<Person> predicate = predicateTag.and(predicateName);
        ListCommand command = new ListCommand(predicate);

        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(3, model.getFilteredPersonList().size());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(userInput);
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsExactKeywordsPredicate}.
     */
    private NameContainsExactKeywordsPredicate prepareExactPredicate(String userInput) {
        return new NameContainsExactKeywordsPredicate(userInput);
    }
}
