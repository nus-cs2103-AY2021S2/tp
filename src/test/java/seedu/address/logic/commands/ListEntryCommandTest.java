package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.ALL_PREDICATE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTeachingAssistant.getTypicalTeachingAssistant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class ListEntryCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTeachingAssistant(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListEntryCommand(ALL_PREDICATE), model,
                String.format(Messages.MESSAGE_ENTRIES_LISTED_OVERVIEW,
                        model.getFilteredEntryList().size()), expectedModel);
    }

}
