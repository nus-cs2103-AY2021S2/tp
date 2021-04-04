package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.testutil.GroupBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddGroupCommand}.
 */
public class AddGroupCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddGroupCommand(null, null));
    }

    @Test
    public void execute_validIndexesUnfilteredList_success() {
        List<Index> indexes = new ArrayList<>();
        indexes.add(INDEX_FIRST_PERSON);

        Person person1 = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Group group = new GroupBuilder().withPersons(person1).build();

        AddGroupCommand addGroupCommand = new AddGroupCommand(indexes, group.getName());

        String expectedMessage = String.format(AddGroupCommand.MESSAGE_ADD_GROUP_SUCCESS, group.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addGroup(group);
        expectedModel.updateFilteredPersonList(x->group.getPersonNames().contains(x.getName()));
        assertCommandSuccess(addGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        List<Index> indexes = new ArrayList<>();
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        indexes.add(outOfBoundIndex);

        Group group = new GroupBuilder().build();

        AddGroupCommand addGroupCommand = new AddGroupCommand(indexes, group.getName());

        assertCommandFailure(addGroupCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
