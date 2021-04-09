package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;
import static dog.pawbook.testutil.TypicalId.ID_EIGHT;
import static dog.pawbook.testutil.TypicalId.ID_EIGHTEEN;
import static dog.pawbook.testutil.TypicalId.ID_FOUR;
import static dog.pawbook.testutil.TypicalId.ID_NINETEEN;
import static dog.pawbook.testutil.TypicalId.ID_SIX;
import static dog.pawbook.testutil.TypicalId.ID_TWENTY;
import static dog.pawbook.testutil.TypicalId.ID_TWENTY_ONE;
import static dog.pawbook.testutil.TypicalId.ID_TWO;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.IdMatchPredicate;
import dog.pawbook.model.managedentity.program.Program;

public class DropCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalDatabase(), new UserPrefs());
        expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
    }

    @Test
    public void constructor_nullDrop_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DropCommand(null, null));
    }

    @Test
    public void execute_dropOneDogOneProgram_success() {
        Program editedProgram = (Program) expectedModel.getEntity(ID_EIGHTEEN);

        Set<Integer> dogIdSet = editedProgram.getDogIdSet();
        Set<Integer> programIdSet = new HashSet<>();
        programIdSet.add(ID_EIGHTEEN);

        DropCommand dropCommand = new DropCommand(dogIdSet, programIdSet);
        String expectedMessage = String.format(DropCommand.MESSAGE_SUCCESS_FORMAT, ID_TWO, ID_EIGHTEEN);

        HashSet<Integer> updatedDroppedDogs = new HashSet<>(editedProgram.getDogIdSet());
        updatedDroppedDogs.remove(ID_TWO);

        expectedModel.setEntity(ID_EIGHTEEN, new Program(editedProgram.getName(),
                editedProgram.getSessions(), editedProgram.getTags(), updatedDroppedDogs));

        expectedModel.updateFilteredEntityList(new IdMatchPredicate(programIdSet));

        assertCommandSuccess(dropCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_dropOneDogManyPrograms_success() {
        Program editedProgram1 = (Program) expectedModel.getEntity(ID_NINETEEN);
        Program editedProgram2 = (Program) expectedModel.getEntity(ID_TWENTY);

        Set<Integer> dogIdSet = new HashSet<>();
        dogIdSet.add(ID_FOUR);
        List<Integer> programIdList = Arrays.asList(ID_NINETEEN, ID_TWENTY);
        Set<Integer> programIdSet = new HashSet<>(programIdList);
        programIdSet.addAll(programIdList);

        DropCommand dropCommand = new DropCommand(dogIdSet, programIdSet);
        String expectedMessage = String.format(DropCommand.MESSAGE_SUCCESS_FORMAT,
                ID_FOUR,
                programIdSet.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ")));

        HashSet<Integer> updatedDroppedDogs1 = new HashSet<>(editedProgram1.getDogIdSet());
        updatedDroppedDogs1.remove(ID_FOUR);

        expectedModel.setEntity(ID_NINETEEN, new Program(editedProgram1.getName(),
                editedProgram1.getSessions(), editedProgram1.getTags(), updatedDroppedDogs1));

        HashSet<Integer> updatedDroppedDogs2 = new HashSet<>(editedProgram2.getDogIdSet());
        updatedDroppedDogs2.remove(ID_FOUR);

        expectedModel.setEntity(ID_TWENTY, new Program(editedProgram2.getName(),
                editedProgram2.getSessions(), editedProgram2.getTags(), updatedDroppedDogs2));

        expectedModel.updateFilteredEntityList(new IdMatchPredicate(programIdSet));
        assertCommandSuccess(dropCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_dropManyDogsOneProgram_success() {
        Program editedProgram = (Program) expectedModel.getEntity(ID_TWENTY);

        Set<Integer> programIdSet = new HashSet<>();
        List<Integer> dogIdList = Arrays.asList(ID_FOUR, ID_SIX, ID_EIGHT);
        Set<Integer> dogIdSet = new HashSet<>(dogIdList);
        programIdSet.add(ID_TWENTY);

        DropCommand dropCommand = new DropCommand(dogIdSet, programIdSet);
        String expectedMessage = String.format(DropCommand.MESSAGE_SUCCESS_FORMAT,
                dogIdSet.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ")),
                ID_TWENTY);

        HashSet<Integer> updateDroppedDogs = new HashSet<>(editedProgram.getDogIdSet());
        updateDroppedDogs.removeAll(dogIdSet);

        expectedModel.setEntity(ID_TWENTY, new Program(editedProgram.getName(),
                editedProgram.getSessions(), editedProgram.getTags(), updateDroppedDogs));

        expectedModel.updateFilteredEntityList(new IdMatchPredicate(programIdSet));

        assertCommandSuccess(dropCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_dropManyDogsManyPrograms_failure() {
        Program editedProgram1 = (Program) expectedModel.getEntity(ID_TWENTY);
        Program editedProgram2 = (Program) expectedModel.getEntity(ID_TWENTY_ONE);

        Set<Integer> dogIdSet1 = editedProgram1.getDogIdSet();
        Set<Integer> dogIdSet2 = editedProgram2.getDogIdSet();
        Set<Integer> dogIdSet = new HashSet<>();
        dogIdSet.addAll(dogIdSet1);
        dogIdSet.addAll(dogIdSet2);
        List<Integer> programIdList = Arrays.asList(ID_TWENTY, ID_TWENTY_ONE);
        Set<Integer> programIdSet = new HashSet<>(programIdList);
        programIdSet.addAll(programIdList);

        HashSet<Integer> updatedDroppedDogs1 = new HashSet<>(editedProgram1.getDogIdSet());
        updatedDroppedDogs1.remove(ID_FOUR);

        expectedModel.setEntity(ID_TWENTY, new Program(editedProgram1.getName(),
                editedProgram1.getSessions(), editedProgram1.getTags(), updatedDroppedDogs1));

        HashSet<Integer> updatedDroppedDogs2 = new HashSet<>(editedProgram2.getDogIdSet());
        updatedDroppedDogs2.remove(ID_EIGHT);

        expectedModel.setEntity(ID_TWENTY_ONE, new Program(editedProgram2.getName(),
                editedProgram2.getSessions(), editedProgram2.getTags(), updatedDroppedDogs2));

        expectedModel.updateFilteredEntityList(new IdMatchPredicate(programIdSet));

        DropCommand dropCommand = new DropCommand(dogIdSet, programIdSet);

        assertThrows(AssertionError.class, () -> dropCommand.execute(model));
    }
}
