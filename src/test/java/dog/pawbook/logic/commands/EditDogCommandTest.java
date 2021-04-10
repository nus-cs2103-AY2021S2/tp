package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.DESC_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.DESC_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_BREED_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_FRIENDLY;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.logic.commands.CommandUtil.disconnectFromOwner;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;
import static dog.pawbook.testutil.TypicalId.ID_ONE;
import static dog.pawbook.testutil.TypicalId.ID_THREE;
import static dog.pawbook.testutil.TypicalId.ID_TWO;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.logic.commands.EditDogCommand.EditDogDescriptor;
import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Database;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.IdMatchPredicate;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.testutil.DogBuilder;
import dog.pawbook.testutil.EditDogDescriptorBuilder;
import javafx.util.Pair;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditDogCommand.
 */
public class EditDogCommandTest {

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        Pair<Integer, Entity> firstIdEntity = model.getDatabase().getEntityList().get(1);
        Dog editedDog = new DogBuilder((Dog) firstIdEntity.getValue()).build();
        EditDogCommand.EditDogDescriptor descriptor = new EditDogDescriptorBuilder(editedDog).build();
        EditDogCommand editDogCommand = new EditDogCommand(firstIdEntity.getKey(), descriptor);

        String expectedMessage = String.format(EditDogCommand.MESSAGE_EDIT_DOG_SUCCESS, editedDog);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.setEntity(firstIdEntity.getKey(), editedDog);
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(firstIdEntity.getKey()));

        assertCommandSuccess(editDogCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Dog toEditDog = (Dog) model.getEntity(ID_TWO);

        DogBuilder dogInList = new DogBuilder(toEditDog);
        Dog editedDog = dogInList.withName(VALID_NAME_BELL).withBreed(VALID_BREED_BELL)
                .withTags(VALID_TAG_FRIENDLY).build();

        EditDogCommand.EditDogDescriptor descriptor = new EditDogDescriptorBuilder().withName(VALID_NAME_BELL)
                .withBreed(VALID_BREED_BELL).withTags(VALID_TAG_FRIENDLY).build();
        EditDogCommand editEntityCommand = new EditDogCommand(ID_TWO, descriptor);

        String expectedMessage = String.format(EditDogCommand.MESSAGE_EDIT_DOG_SUCCESS, editedDog);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.setEntity(ID_TWO, editedDog);
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(ID_TWO));

        assertCommandSuccess(editEntityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_ownerIdSpecified_success() throws CommandException {
        Dog toEditDog = (Dog) model.getEntity(ID_TWO);

        DogBuilder dogInList = new DogBuilder(toEditDog);
        Dog editedDog = dogInList.withOwnerID(ID_THREE).build();

        EditDogCommand.EditDogDescriptor descriptor = new EditDogDescriptorBuilder()
                .withOwnerId(ID_THREE).build();
        EditDogCommand editEntityCommand = new EditDogCommand(ID_TWO, descriptor);

        String expectedMessage = String.format(EditDogCommand.MESSAGE_EDIT_DOG_SUCCESS, editedDog);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.setEntity(ID_TWO, editedDog);
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(ID_TWO));

        int originalOwnerId = toEditDog.getOwnerId();
        int editedOwnerId = editedDog.getOwnerId();

        if (!expectedModel.hasEntity(editedOwnerId)) {
            throw new CommandException(Messages.MESSAGE_INVALID_OWNER_ID);
        }
        Entity entity = expectedModel.getEntity(editedOwnerId);
        if (!(entity instanceof Owner)) {
            throw new CommandException(Messages.MESSAGE_INVALID_OWNER_ID);
        }
        Owner newOwner = (Owner) entity;

        // delete the ID of the dog from the owner first
        disconnectFromOwner(expectedModel, originalOwnerId, ID_TWO);

        // transfer to the new owner
        Set<Integer> editedDogIdSet = new HashSet<>(newOwner.getDogIdSet());
        editedDogIdSet.add(ID_TWO);

        Owner editedOwner = new Owner(newOwner.getName(), newOwner.getPhone(), newOwner.getEmail(),
                newOwner.getAddress(), newOwner.getTags(), editedDogIdSet);
        expectedModel.setEntity(editedOwnerId, editedOwner);
        assertCommandSuccess(editEntityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        EditDogCommand editEntityCommand = new EditDogCommand(ID_TWO, new EditDogDescriptor());
        Dog editedDog = (Dog) model.getEntity(ID_TWO);

        String expectedMessage = String.format(EditDogCommand.MESSAGE_EDIT_DOG_SUCCESS, editedDog);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(ID_TWO));

        assertCommandSuccess(editEntityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidDogId_failure() {
        Integer outOfBoundId = model.getUnfilteredEntityList().stream()
                .map(Pair::getKey).sorted().collect(toList()).get(model.getUnfilteredEntityList().size() - 1) + 1;
        EditDogDescriptor descriptor = new EditDogDescriptorBuilder().withName(VALID_NAME_BELL).build();
        EditDogCommand editEntityCommand = new EditDogCommand(outOfBoundId, descriptor);

        assertCommandFailure(editEntityCommand, model, Messages.MESSAGE_INVALID_DOG_ID);
    }

    @Test
    public void equals() {
        final EditDogCommand standardCommand = new EditDogCommand(ID_ONE, DESC_ASHER);

        // same values -> returns true
        EditDogDescriptor copyDescriptor = new EditDogDescriptor(DESC_ASHER);
        EditDogCommand commandWithSameValues = new EditDogCommand(ID_ONE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new HelpCommand()));

        // different id -> returns false
        assertFalse(standardCommand.equals(new EditDogCommand(ID_TWO, DESC_ASHER)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditDogCommand(ID_ONE, DESC_BELL)));
    }
}
