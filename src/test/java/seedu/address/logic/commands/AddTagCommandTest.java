package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.TagCommandParser.tagsToString;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCommandAliases.getTypicalAliasMap;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTags.getCommonTags;
import static seedu.address.testutil.TypicalTags.getTypicalTags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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

public class AddTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalAliasMap());

    @Test
    public void createWithShownIndex_nullTags_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> AddTagCommand.createWithShownIndex(null));
    }

    @Test
    public void createWithSelectedIndex_nullTags_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> AddTagCommand.createWithSelectedIndex(null));
    }

    @Test
    public void createWithIndexes_nullTags_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> AddTagCommand.createWithTargetIndexes(new ArrayList<>(), null));
    }

    @Test
    public void createWithIndexes_nullTargetIndexes_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> AddTagCommand.createWithTargetIndexes(null, new TreeSet<>()));
    }

    @Test
    public void execute_selectedIndexTags_addSuccessful() throws Exception {
        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getAliasMap());
        Set<Tag> tags = getTypicalTags();

        List<Person> selectedPersonList = new ArrayList<>();
        selectedPersonList.add(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));
        selectedPersonList.add(model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()));
        model.updateSelectedPersonList(selectedPersonList);
        expectedModel.updateSelectedPersonList(selectedPersonList);

        for (Person person : selectedPersonList) {
            Person editedPerson = new PersonBuilder(person).addTags(tags).build();
            expectedModel.setPerson(person, editedPerson);
        }

        AddTagCommand addTagCommand = AddTagCommand.createWithSelectedIndex(tags);

        String singularOrPlural = selectedPersonList.size() > 1 ? "s" : "";
        int updateCount = selectedPersonList.size();

        String expectedMessage = String.format(AddTagCommand.MESSAGE_SUCCESS, singularOrPlural,
                updateCount, tagsToString(tags));
        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_shownIndexTags_addSuccessful() throws Exception {
        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getAliasMap());
        Set<Tag> tags = getTypicalTags();

        List<Person> personList = model.getFilteredPersonList();
        for (Person person : personList) {
            Person editedPerson = new PersonBuilder(person).addTags(tags).build();
            expectedModel.setPerson(person, editedPerson);
        }

        AddTagCommand addTagCommand = AddTagCommand.createWithShownIndex(tags);

        String singularOrPlural = personList.size() > 1 ? "s" : "";
        int updateCount = personList.size();

        String expectedMessage = String.format(AddTagCommand.MESSAGE_SUCCESS, singularOrPlural,
                updateCount, tagsToString(tags));
        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_targetIndexesTags_addSuccessful() throws Exception {
        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getAliasMap());
        Set<Tag> tags = getTypicalTags();

        List<Index> indexes = TypicalIndexes.VALID_INDEXES;
        List<Person> personList = model.getFilteredPersonList();

        for (Index index : indexes) {
            Person person = personList.get(index.getZeroBased());
            Person editedPerson = new PersonBuilder(person).addTags(tags).build();
            expectedModel.setPerson(person, editedPerson);
        }

        AddTagCommand addTagCommand = AddTagCommand.createWithTargetIndexes(indexes, tags);

        String singularOrPlural = indexes.size() > 1 ? "s" : "";
        int updateCount = indexes.size();

        String expectedMessage = String.format(AddTagCommand.MESSAGE_SUCCESS, singularOrPlural,
                updateCount, tagsToString(tags));
        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noShownPerson_throwsCommandException() throws Exception {
        model.updateFilteredPersonList(filter -> false);
        Set<Tag> tags = getTypicalTags();
        assertCommandFailure(AddTagCommand.createWithShownIndex(getTypicalTags()),
                model, AddTagCommand.MESSAGE_NO_SHOWN_PERSON);
    }

    @Test
    public void execute_noSelectedPerson_throwsCommandException() throws Exception {
        model.updateSelectedPersonList(new ArrayList<>());
        model.applySelectedPredicate();
        assertCommandFailure(AddTagCommand.createWithSelectedIndex(getTypicalTags()),
                model, AddTagCommand.MESSAGE_NO_SELECTED_PERSON);
    }

    @Test
    public void execute_personIndexOutOfRange_throwsCommandException() throws Exception {
        int outOfRangeIndex = model.getFilteredPersonList().size() + 1;
        List<Index> indexes = new ArrayList<>();
        indexes.add(Index.fromOneBased(outOfRangeIndex));
        assertCommandFailure(AddTagCommand.createWithTargetIndexes(indexes, getTypicalTags()),
                model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AddTagCommand addTagToTargetIndexes = AddTagCommand.createWithTargetIndexes(
                new ArrayList<>(), getTypicalTags());
        AddTagCommand addTagToShownIndex = AddTagCommand.createWithShownIndex(getTypicalTags());
        AddTagCommand addTagToSelectedIndex = AddTagCommand.createWithSelectedIndex(getTypicalTags());

        // same object -> equals
        assertEquals(addTagToTargetIndexes, addTagToTargetIndexes);

        // same values -> equals
        AddTagCommand addTagToTargetIndexesCopy = AddTagCommand.createWithTargetIndexes(
                new ArrayList<>(), getTypicalTags());
        assertEquals(addTagToTargetIndexesCopy, addTagToTargetIndexes);

        // different AddTagCommand types -> not equals
        assertNotEquals(addTagToTargetIndexes, addTagToShownIndex);
        assertNotEquals(addTagToTargetIndexes, addTagToSelectedIndex);
        assertNotEquals(addTagToSelectedIndex, addTagToShownIndex);

        // different indexes -> not equals
        assertNotEquals(
                AddTagCommand.createWithTargetIndexes(Collections.singletonList(INDEX_FIRST_PERSON), getTypicalTags()),
                AddTagCommand.createWithTargetIndexes(TypicalIndexes.VALID_INDEXES, getTypicalTags()));

        // different tags -> not equals
        assertNotEquals(
                AddTagCommand.createWithTargetIndexes(TypicalIndexes.VALID_INDEXES, getCommonTags()),
                AddTagCommand.createWithTargetIndexes(TypicalIndexes.VALID_INDEXES, getTypicalTags()));

        // null -> not equals
        assertNotEquals(addTagToTargetIndexes, null);
    }

}
