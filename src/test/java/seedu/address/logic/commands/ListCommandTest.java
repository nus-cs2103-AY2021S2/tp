package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showPersonsWithGroup;
import static seedu.address.testutil.TestDataUtil.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
import seedu.address.testutil.GroupBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;
    private Group group;
    private Optional<Name> defaultName = Optional.empty();

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        group = new GroupBuilder().withPersons(model.getFilteredPersonList().get(1)).build();
        model.addGroup(group);
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(defaultName), model, ListCommand.MESSAGE_SUCCESS_DEFAULT, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(defaultName), model, ListCommand.MESSAGE_SUCCESS_DEFAULT, expectedModel);
    }

    @Test
    public void execute_listWithGroupName_showsGroupList() {
        showPersonsWithGroup(expectedModel, group);

        String expectedMessage = String.format(ListCommand.MESSAGE_SUCCESS_GROUP, group.getName());
        assertCommandSuccess(new ListCommand(Optional.of(group.getName())), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_listWithInvalidGroupName_failure() {
        model.deleteGroup(group);

        String expectedMessage = String.format(Messages.MESSAGE_UNKNOWN_GROUP);
        assertCommandFailure(new ListCommand(Optional.of(group.getName())), model, expectedMessage);
    }
}
