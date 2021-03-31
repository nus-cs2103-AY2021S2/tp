package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAliases.getTypicalAliases;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTags.getCommonTags;
import static seedu.address.testutil.TypicalTags.getTypicalTags;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalIndexes;

public class DeleteTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalAliases());

    @Test
    public void createWithShownIndex_nullTags_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> DeleteTagCommand.createWithShownIndex(null));
    }

    @Test
    public void createWithSelectedIndex_nullTags_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> DeleteTagCommand.createWithSelectedIndex(null));
    }

    @Test
    public void createWithIndexes_nullTags_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> DeleteTagCommand.createWithTargetIndexes(
                new ArrayList<>(), null));
    }

    @Test
    public void createWithIndexes_nullTargetIndexes_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> DeleteTagCommand.createWithTargetIndexes(
                null, new HashSet<>()));
    }

    @Test
    public void execute_selectedIndexTags_deleteSuccessful() throws Exception {
        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getAliases());
        Set<Tag> tags = getCommonTags();

        List<Person> selectedPersonList = new ArrayList<>();
        selectedPersonList.add(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));
        selectedPersonList.add(model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()));
        model.updateSelectedPersonList(selectedPersonList);

        for (Person person : selectedPersonList) {
            Person editedPerson = new PersonBuilder(person).deleteTags(tags).build();
            expectedModel.setPerson(person, editedPerson);
        }

        DeleteTagCommand deleteTagCommand = DeleteTagCommand.createWithSelectedIndex(tags);

        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_SUCCESS,
                selectedPersonList.size(), tags.toString());
        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_shownIndexTags_addSuccessful() throws Exception {
        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getAliases());
        Set<Tag> tags = getTypicalTags();

        List<Person> personList = model.getFilteredPersonList();
        for (Person person : personList) {
            Person editedPerson = new PersonBuilder(person).deleteTags(tags).build();
            expectedModel.setPerson(person, editedPerson);
        }

        DeleteTagCommand deleteTagCommand = DeleteTagCommand.createWithShownIndex(tags);

        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_SUCCESS, personList.size(), tags.toString());
        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_targetIndexesTags_addSuccessful() throws Exception {
        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getAliases());
        Set<Tag> tags = getTypicalTags();

        List<Index> indexes = TypicalIndexes.VALID_INDEXES;

        List<Person> personList = model.getFilteredPersonList();

        for (Index index : indexes) {
            Person person = personList.get(index.getZeroBased());
            Person editedPerson = new PersonBuilder(person).deleteTags(tags).build();
            expectedModel.setPerson(person, editedPerson);
        }

        DeleteTagCommand deleteTagCommand = DeleteTagCommand.createWithTargetIndexes(indexes, tags);

        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_SUCCESS, indexes.size(), tags.toString());
        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noShownPerson_throwsCommandException() throws Exception {
        model.updateFilteredPersonList(filter -> false);
        Set<Tag> tags = getTypicalTags();
        assertCommandFailure(DeleteTagCommand.createWithShownIndex(getTypicalTags()),
                model, DeleteTagCommand.MESSAGE_NO_SHOWN_PERSON);
    }

    @Test
    public void execute_noSelectedPerson_throwsCommandException() throws Exception {
        model.updateSelectedPersonList(new ArrayList<>());
        assertCommandFailure(DeleteTagCommand.createWithSelectedIndex(getTypicalTags()),
                model, DeleteTagCommand.MESSAGE_NO_SELECTED_PERSON);
    }

    @Test
    public void execute_personIndexOutOfRange_throwsCommandException() throws Exception {
        int outOfRangeIndex = model.getFilteredPersonList().size() + 1;
        List<Index> indexes = new ArrayList<>();
        indexes.add(Index.fromOneBased(outOfRangeIndex));
        assertCommandFailure(DeleteTagCommand.createWithTargetIndexes(indexes, getTypicalTags()),
                model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteTagCommand deleteTagFromTargetIndexes = DeleteTagCommand.createWithTargetIndexes(
                new ArrayList<>(), getTypicalTags());
        DeleteTagCommand deleteTagFromShownIndex = DeleteTagCommand.createWithShownIndex(getTypicalTags());
        DeleteTagCommand deleteTagFromSelectedIndex = DeleteTagCommand.createWithSelectedIndex(getTypicalTags());

        // same object -> returns true
        assertEquals(deleteTagFromTargetIndexes, deleteTagFromTargetIndexes);

        // same values -> returns true
        DeleteTagCommand deleteTagFromTargetIndexesCopy = DeleteTagCommand.createWithTargetIndexes(
                new ArrayList<>(), getTypicalTags());
        assertEquals(deleteTagFromTargetIndexesCopy, deleteTagFromTargetIndexes);

        // different DeleteTagCommand types -> returns false
        assertNotEquals(deleteTagFromTargetIndexes, deleteTagFromShownIndex);
        assertNotEquals(deleteTagFromTargetIndexes, deleteTagFromSelectedIndex);
        assertNotEquals(deleteTagFromSelectedIndex, deleteTagFromShownIndex);

        // different types -> returns false
        assertNotEquals(deleteTagFromTargetIndexes, 1);

        // null -> returns false
        assertNotEquals(deleteTagFromTargetIndexes, null);
    }

}
