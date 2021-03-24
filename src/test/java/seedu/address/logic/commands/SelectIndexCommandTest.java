package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAliases.getTypicalAliases;
import static seedu.address.testutil.TypicalIndexes.VALID_INDEXES;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class SelectIndexCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalAliases());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                model.getAliases());
    }

    @Test
    public void initialize_nullIndexes_throwsException() {
        assertThrows(NullPointerException.class, () -> new SelectIndexCommand(null));
    }

    @Test
    public void execute_selectShownModelUpdated_success() {
        SelectIndexCommand command = new SelectIndexCommand();
        List<Person> personList = expectedModel.getFilteredPersonList();
        expectedModel.updateSelectedPersonList(personList);
        assertCommandSuccess(command, model, SelectIndexCommand.MESSAGE_SHOWN_SUCCESS,
                expectedModel);
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_selectIndexModelUpdated_success() {
        SelectIndexCommand command = new SelectIndexCommand(VALID_INDEXES);
        List<Person> personList = expectedModel.getFilteredPersonList().subList(0, 3);
        expectedModel.updateSelectedPersonList(personList);
        assertCommandSuccess(command, model, SelectIndexCommand.MESSAGE_INDEX_SUCCESS,
                expectedModel);
        assertEquals(expectedModel, model);
    }

    @Test
    public void equals() {
        List<Index> validIndexes = new ArrayList<>(VALID_INDEXES);
        List<Index> validIndexesReversed = new ArrayList<>(VALID_INDEXES);
        Collections.reverse(validIndexesReversed);

        // same indexes -> equals
        assertEquals(new SelectIndexCommand(validIndexes), new SelectIndexCommand(validIndexes));

        // same indexes, different order -> equals
        assertEquals(new SelectIndexCommand(validIndexes),
                new SelectIndexCommand(validIndexesReversed));

        // different instance, same values -> equals
        assertEquals(new SelectIndexCommand(), new SelectIndexCommand());

        // different command -> not equals
        assertNotEquals(new SelectIndexCommand(), new ListCommand());

        // different indexes -> not equals
        assertNotEquals(new SelectIndexCommand(), new SelectIndexCommand(validIndexes));

        // different types -> not equals
        assertNotEquals(null, new SelectIndexCommand());
        assertNotEquals(1, new SelectIndexCommand());
    }
}
