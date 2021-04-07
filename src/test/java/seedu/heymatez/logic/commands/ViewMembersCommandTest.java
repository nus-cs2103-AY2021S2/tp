package seedu.heymatez.logic.commands;

import static seedu.heymatez.commons.core.Messages.MESSAGE_EMPTY_PERSON_LIST;
import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.heymatez.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.heymatez.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.heymatez.testutil.TypicalPersons.getTypicalHeyMatez;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.heymatez.model.HeyMatez;
import seedu.heymatez.model.Model;
import seedu.heymatez.model.ModelManager;
import seedu.heymatez.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code ViewMembersCommand}.
 */
public class ViewMembersCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHeyMatez(), new UserPrefs());
        expectedModel = new ModelManager(model.getHeyMatez(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ViewMembersCommand(), model, ViewMembersCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ViewMembersCommand(), model, ViewMembersCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_emptyFilteredList_success() {
        HeyMatez heyMatez = new HeyMatez();
        model = new ModelManager(heyMatez, new UserPrefs());
        expectedModel = new ModelManager(model.getHeyMatez(), new UserPrefs());
        assertCommandSuccess(new ViewMembersCommand(), model, MESSAGE_EMPTY_PERSON_LIST, expectedModel);
    }
}
