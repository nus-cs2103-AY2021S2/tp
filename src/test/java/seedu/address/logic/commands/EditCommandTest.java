package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalCommandAliases.getTypicalAliasMap;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalIndexes.VALID_INDEXES;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalAliasMap());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = EditCommand
                .buildEditIndexCommand(Collections.singletonList(INDEX_FIRST_PERSON),
                descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getAliasMap());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_bulkEditPhoneSpecifiedIndexes_success() {
        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getAliasMap());
        Person personA = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personB = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());

        // Bulk edit phone number
        final String phoneNumber = "99998888";
        Person editedPersonA = new PersonBuilder(personA).withPhone(phoneNumber).build();
        Person editedPersonB = new PersonBuilder(personB).withPhone(phoneNumber).build();
        expectedModel.setPerson(
                model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                editedPersonA);
        expectedModel.setPerson(
                model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased()),
                editedPersonB);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSONS_SUCCESS, 2);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(phoneNumber)
                .build();

        EditCommand editCommand = EditCommand.buildEditIndexCommand(List.of(INDEX_FIRST_PERSON, INDEX_THIRD_PERSON),
                descriptor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_shownEditPhone_success() {
        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getAliasMap());
        // Bulk edit phone number
        final String phoneNumber = "99998888";
        for (Person person : model.getFilteredPersonList()) {
            Person editedPerson = new PersonBuilder(person).withPhone(phoneNumber).build();
            expectedModel.setPerson(person, editedPerson);
        }

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSONS_SUCCESS,
                expectedModel.getFilteredPersonList().size());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(phoneNumber)
                .build();

        EditCommand editCommand = EditCommand.buildEditShownCommand(descriptor);
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_selectedEditPhone_success() {
        model.updateSelectedPersonList(model.getFilteredPersonList().subList(0, 1));

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getAliasMap());
        expectedModel.updateSelectedPersonList(expectedModel.getFilteredPersonList().subList(0, 1));
        // Edit phone number
        final String phoneNumber = "99998888";
        Person person = expectedModel.getFilteredPersonList()
                .get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(person).withPhone(phoneNumber).build();
        expectedModel.setPerson(person, editedPerson);

        String expectedMessage = String
                .format(EditCommand.MESSAGE_EDIT_PERSONS_SUCCESS, 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(phoneNumber)
                .build();

        EditCommand editCommand = EditCommand.buildEditSelectedCommand(descriptor);
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_selectedEditPhone_failureNoSelected() {
        // Edit phone number
        final String phoneNumber = "99998888";
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(phoneNumber)
                .build();
        EditCommand editCommand = EditCommand.buildEditSelectedCommand(descriptor);
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_NO_SELECTED);
    }

    @Test
    public void execute_shownEditName_expectFailure() {
        // Bulk edit phone number
        final String name = "test";
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(name).build();

        EditCommand editCommand = EditCommand.buildEditShownCommand(descriptor);
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON_BULK);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = EditCommand.buildEditIndexCommand(Collections.singletonList(indexLastPerson),
                descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getAliasMap());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = EditCommand.buildEditIndexCommand(Collections.singletonList(INDEX_FIRST_PERSON),
                new EditPersonDescriptor());
        Person editedPerson = model.getFilteredPersonList()
                .get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getAliasMap());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = EditCommand.buildEditIndexCommand(Collections.singletonList(INDEX_FIRST_PERSON),
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getAliasMap());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = EditCommand
                .buildEditIndexCommand(Collections.singletonList(INDEX_SECOND_PERSON), descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Person personInList = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = EditCommand.buildEditIndexCommand(Collections.singletonList(INDEX_FIRST_PERSON),
                new EditPersonDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = EditCommand.buildEditIndexCommand(Collections.singletonList(outOfBoundIndex),
                descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        EditCommand editCommand = EditCommand.buildEditIndexCommand(Collections.singletonList(outOfBoundIndex),
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = EditCommand.buildEditIndexCommand(
                Collections.singletonList(INDEX_FIRST_PERSON), DESC_AMY);

        // same values and index -> equals
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = EditCommand.buildEditIndexCommand(
                Collections.singletonList(INDEX_FIRST_PERSON), copyDescriptor);
        assertEquals(commandWithSameValues, standardCommand);

        // variant: index
        assertEquals(EditCommand.buildEditIndexCommand(VALID_INDEXES, DESC_AMY),
                EditCommand.buildEditIndexCommand(VALID_INDEXES, DESC_AMY));

        // variant: shown
        assertEquals(EditCommand.buildEditShownCommand(DESC_AMY),
                EditCommand.buildEditShownCommand(DESC_AMY));

        // variant: selected
        assertEquals(EditCommand.buildEditSelectedCommand(DESC_AMY),
                EditCommand.buildEditSelectedCommand(DESC_AMY));

        // same object -> equals
        assertEquals(standardCommand, standardCommand);
        assertEquals(EditCommand.buildEditShownCommand(DESC_AMY),
                EditCommand.buildEditShownCommand(DESC_AMY));
        assertEquals(EditCommand.buildEditSelectedCommand(DESC_AMY),
                EditCommand.buildEditSelectedCommand(DESC_AMY));

        // different indexes, same descriptor -> not equals
        assertNotEquals(EditCommand.buildEditIndexCommand(VALID_INDEXES, DESC_AMY),
                EditCommand.buildEditIndexCommand(Collections.singletonList(INDEX_FIRST_PERSON),
                        DESC_AMY));

        // null -> not equals
        assertNotEquals(standardCommand, null);

        // different types -> not equals
        assertNotEquals(new ClearCommand(), standardCommand);

        // different index -> not equals
        assertNotEquals(EditCommand.buildEditIndexCommand(Collections.singletonList(INDEX_SECOND_PERSON), DESC_AMY),
                standardCommand);

        // different descriptor -> not equals
        assertNotEquals(EditCommand.buildEditIndexCommand(Collections.singletonList(INDEX_FIRST_PERSON), DESC_BOB),
                standardCommand);
        assertNotEquals(EditCommand.buildEditIndexCommand(VALID_INDEXES, DESC_BOB),
                standardCommand);
    }

}
