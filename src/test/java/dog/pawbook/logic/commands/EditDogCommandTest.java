package dog.pawbook.logic.commands;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_DOG_ID;
import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_OWNER_ID;
import static dog.pawbook.logic.commands.CommandTestUtil.DESC_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.DESC_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_BREED_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_FRIENDLY;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.logic.commands.CommandTestUtil.getOutOfBoundId;
import static dog.pawbook.logic.commands.EditDogCommand.MESSAGE_EDIT_DOG_SUCCESS;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;
import static dog.pawbook.testutil.TypicalId.ID_FOUR;
import static dog.pawbook.testutil.TypicalId.ID_ONE;
import static dog.pawbook.testutil.TypicalId.ID_THREE;
import static dog.pawbook.testutil.TypicalId.ID_TWO;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.logic.commands.EditDogCommand.EditDogDescriptor;
import dog.pawbook.model.Database;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.IdMatchPredicate;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.testutil.DogBuilder;
import dog.pawbook.testutil.EditDogDescriptorBuilder;
import dog.pawbook.testutil.OwnerBuilder;
import javafx.util.Pair;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditDogCommand.
 */
public class EditDogCommandTest {

    private final Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());
    private Model expectedModel;

    @Test
    public void execute_allFieldsSpecified_success() {
        Dog dog = (Dog) model.getEntity(ID_TWO);

        EditDogDescriptor descriptor = new EditDogDescriptorBuilder(dog).build();
        EditDogCommand editDogCommand = new EditDogCommand(ID_TWO, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_DOG_SUCCESS, dog);

        expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(ID_TWO));

        assertCommandSuccess(editDogCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Dog toEditDog = (Dog) model.getEntity(ID_TWO);

        DogBuilder dogInList = new DogBuilder(toEditDog);
        Dog editedDog = dogInList.withName(VALID_NAME_BELL).withBreed(VALID_BREED_BELL)
                .withTags(VALID_TAG_FRIENDLY).build();

        EditDogDescriptor descriptor = new EditDogDescriptorBuilder().withName(VALID_NAME_BELL)
                .withBreed(VALID_BREED_BELL).withTags(VALID_TAG_FRIENDLY).build();
        EditDogCommand editDogCommand = new EditDogCommand(ID_TWO, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_DOG_SUCCESS, editedDog);

        expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.setEntity(ID_TWO, editedDog);
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(ID_TWO));

        assertCommandSuccess(editDogCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validOwnerIdSpecified_success() {
        Dog toEditDog = (Dog) model.getEntity(ID_TWO);

        DogBuilder dogInList = new DogBuilder(toEditDog);
        Dog editedDog = dogInList.withOwnerID(ID_THREE).build();

        EditDogCommand.EditDogDescriptor descriptor = new EditDogDescriptorBuilder()
                .withOwnerId(ID_THREE).build();
        EditDogCommand editDogCommand = new EditDogCommand(ID_TWO, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_DOG_SUCCESS, editedDog);

        expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.setEntity(ID_TWO, editedDog);
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(ID_TWO));


        Owner oldOwner = (Owner) expectedModel.getEntity(ID_ONE);
        Set<Integer> editedDogIdSet = new HashSet<>(oldOwner.getDogIdSet());
        editedDogIdSet.remove(ID_TWO);
        Owner editedOldOwner = new OwnerBuilder(oldOwner)
                .withDogs(editedDogIdSet.toArray(Integer[]::new))
                .build();
        expectedModel.setEntity(ID_ONE, editedOldOwner);

        // transfer to the new owner
        Owner newOwner = (Owner) expectedModel.getEntity(ID_THREE);
        editedDogIdSet = new HashSet<>(newOwner.getDogIdSet());
        editedDogIdSet.add(ID_TWO);

        Owner editedNewOwner = new OwnerBuilder(newOwner)
                .withDogs(editedDogIdSet.toArray(Integer[]::new))
                .build();
        expectedModel.setEntity(ID_THREE, editedNewOwner);
        assertCommandSuccess(editDogCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_outOfBoundOwnerIdSpecified_failure() {
        int invalidOwnerId = getOutOfBoundId(model);
        EditDogCommand.EditDogDescriptor descriptor = new EditDogDescriptorBuilder()
                .withOwnerId(invalidOwnerId).build();
        EditDogCommand editDogCommand = new EditDogCommand(ID_FOUR, descriptor);

        assertCommandFailure(editDogCommand, model, MESSAGE_INVALID_OWNER_ID);
    }

    @Test
    public void execute_nonOwnerIdSpecified_failure() {
        EditDogCommand.EditDogDescriptor descriptor = new EditDogDescriptorBuilder()
                .withOwnerId(ID_TWO).build();
        EditDogCommand editDogCommand = new EditDogCommand(ID_FOUR, descriptor);

        assertCommandFailure(editDogCommand, model, MESSAGE_INVALID_OWNER_ID);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        EditDogCommand editDogCommand = new EditDogCommand(ID_FOUR, new EditDogDescriptor());
        Dog editedDog = (Dog) model.getEntity(ID_FOUR);

        String expectedMessage = String.format(MESSAGE_EDIT_DOG_SUCCESS, editedDog);

        expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(ID_FOUR));

        assertCommandSuccess(editDogCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateDog_failure() {
        Dog firstDog = (Dog) model.getEntity(ID_TWO);
        EditDogDescriptor descriptor = new EditDogDescriptorBuilder(firstDog).build();
        EditDogCommand editDogCommand = new EditDogCommand(ID_FOUR, descriptor);

        assertCommandFailure(editDogCommand, model, Messages.MESSAGE_DUPLICATE_DOG);
    }

    @Test
    public void execute_outOfBoundDogId_failure() {
        Integer outOfBoundId = model.getUnfilteredEntityList().stream()
                .map(Pair::getKey).sorted().collect(toList()).get(model.getUnfilteredEntityList().size() - 1) + 1;
        EditDogDescriptor descriptor = new EditDogDescriptorBuilder().withName(VALID_NAME_BELL).build();
        EditDogCommand editDogCommand = new EditDogCommand(outOfBoundId, descriptor);

        assertCommandFailure(editDogCommand, model, MESSAGE_INVALID_DOG_ID);
    }

    @Test
    public void execute_validIdDifferentEntityType_failure() {
        EditDogDescriptor descriptor = new EditDogDescriptorBuilder().withName(VALID_NAME_BELL).build();
        EditDogCommand editDogCommand = new EditDogCommand(ID_ONE, descriptor);

        assertCommandFailure(editDogCommand, model, MESSAGE_INVALID_DOG_ID);
    }

    @Test
    public void equals() {
        final EditDogCommand standardCommand = new EditDogCommand(ID_ONE, DESC_ASHER);

        // same values -> returns true
        EditDogDescriptor copyDescriptor = new EditDogDescriptor(DESC_ASHER);
        EditDogCommand commandWithSameValues = new EditDogCommand(ID_ONE, copyDescriptor);
        assertEquals(commandWithSameValues, standardCommand);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(standardCommand, null);

        // different types -> returns false
        assertNotEquals(new HelpCommand(), standardCommand);

        // different id -> returns false
        assertNotEquals(new EditDogCommand(ID_TWO, DESC_ASHER), standardCommand);

        // different descriptor -> returns false
        assertNotEquals(new EditDogCommand(ID_ONE, DESC_BELL), standardCommand);
    }
}
