package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SESSIONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(PREDICATE_SHOW_ALL_PERSONS,
                PREDICATE_SHOW_ALL_SESSIONS, "persons"), model,
                ListCommand.MESSAGE_SUCCESS_PERSONS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(PREDICATE_SHOW_ALL_PERSONS,
                PREDICATE_SHOW_ALL_SESSIONS, "persons"), model,
                ListCommand.MESSAGE_SUCCESS_PERSONS, expectedModel);
    }

    @Test
    public void execute_showAllStudents_success() {
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_STUDENTS);
        assertCommandSuccess(new ListCommand(PREDICATE_SHOW_ALL_STUDENTS,
                        PREDICATE_SHOW_ALL_SESSIONS, "students"),
                model, ListCommand.MESSAGE_SUCCESS_STUDENTS, expectedModel);
    }

    @Test
    public void execute_showAllTutors_emptyList() {
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_TUTORS);
        assertCommandSuccess(new ListCommand(PREDICATE_SHOW_ALL_TUTORS,
                        PREDICATE_SHOW_ALL_SESSIONS, "tutors"),
                model, ListCommand.MESSAGE_EMPTY_TUTOR_LIST, expectedModel);
    }

    @Test
    public void execute_showAllSessions_emptyList() {
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        expectedModel.updateFilteredSessionList(PREDICATE_SHOW_ALL_SESSIONS);
        assertCommandSuccess(new ListCommand(PREDICATE_SHOW_ALL_PERSONS,
                        PREDICATE_SHOW_ALL_SESSIONS, "sessions"),
                model, ListCommand.MESSAGE_EMPTY_SESSION_LIST, expectedModel);
    }

    @Test
    public void equals() {
        ListCommand listPersonsCommand = new ListCommand(PREDICATE_SHOW_ALL_PERSONS, PREDICATE_SHOW_ALL_SESSIONS,
                                                            "persons");
        ListCommand listSessionsCommand = new ListCommand(PREDICATE_SHOW_ALL_PERSONS, PREDICATE_SHOW_ALL_SESSIONS,
                                                            "sessions");

        // same object -> returns true
        assertTrue(listPersonsCommand.equals(listPersonsCommand));

        // same values -> returns true
        ListCommand listPersonsCommandCopy = new ListCommand(PREDICATE_SHOW_ALL_PERSONS, PREDICATE_SHOW_ALL_SESSIONS,
                                                             "persons");
        assertTrue(listPersonsCommand.equals(listPersonsCommandCopy));

        // different types -> returns false
        assertFalse(listPersonsCommand.equals(1));

        // null -> returns false
        assertFalse(listPersonsCommand.equals(null));

        // different session -> returns false
        assertFalse(listPersonsCommand.equals(listSessionsCommand));
    }
}
