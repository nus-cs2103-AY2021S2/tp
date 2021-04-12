package seedu.ta.logic.commands;

import static seedu.ta.logic.commands.CommandTestUtil.ALL_PREDICATE;
import static seedu.ta.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ta.testutil.TypicalTeachingAssistant.getTypicalTeachingAssistant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.ta.commons.core.Messages;
import seedu.ta.model.Model;
import seedu.ta.model.ModelManager;
import seedu.ta.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code ListEntryCommand}.
 */
public class ListEntryCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTeachingAssistant(), new UserPrefs());
        expectedModel = new ModelManager(model.getTeachingAssistant(), new UserPrefs());

    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListEntryCommand(ALL_PREDICATE), model,
                String.format(Messages.MESSAGE_ENTRIES_LISTED_OVERVIEW,
                        model.getFilteredEntryList().size()), expectedModel);
    }
}
