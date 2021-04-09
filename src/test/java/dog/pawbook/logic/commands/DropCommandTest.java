package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.logic.commands.DropCommand.MESSAGE_NOT_ENROLLED;
import static dog.pawbook.logic.commands.DropCommand.MESSAGE_NOT_ENROLLED_MULTIPLE_DOGS;
import static dog.pawbook.logic.commands.DropCommand.MESSAGE_NOT_ENROLLED_MULTIPLE_PROGRAMS;
import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;
import static dog.pawbook.testutil.TypicalId.ID_EIGHT;
import static dog.pawbook.testutil.TypicalId.ID_EIGHTEEN;
import static dog.pawbook.testutil.TypicalId.ID_FOUR;
import static dog.pawbook.testutil.TypicalId.ID_NINETEEN;
import static dog.pawbook.testutil.TypicalId.ID_TEN;
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

    public Program removeOneDogFromOneProgram(int dogId, int programId) {
        Program editedProgram = (Program) expectedModel.getEntity(programId);

        HashSet<Integer> updatedDroppedDogs = new HashSet<>(editedProgram.getDogIdSet());
        updatedDroppedDogs.remove(dogId);

        expectedModel.setEntity(programId, new Program(editedProgram.getName(),
                editedProgram.getSessions(), editedProgram.getTags(), updatedDroppedDogs));

        return editedProgram;
    }

    @Test
    public void execute_dropOneDogOneProgram_success() {
        Program editedProgram = removeOneDogFromOneProgram(ID_TWO, ID_EIGHTEEN);

        Set<Integer> dogIdSet = editedProgram.getDogIdSet();
        Set<Integer> programIdSet = new HashSet<>();
        programIdSet.add(ID_EIGHTEEN);

        DropCommand dropCommand = new DropCommand(dogIdSet, programIdSet);
        String expectedMessage = String.format(DropCommand.MESSAGE_SUCCESS_FORMAT, ID_TWO, ID_EIGHTEEN);
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(programIdSet));

        assertCommandSuccess(dropCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_dropOneDogManyPrograms_success() {
        removeOneDogFromOneProgram(ID_FOUR, ID_NINETEEN);
        removeOneDogFromOneProgram(ID_FOUR, ID_TWENTY);

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
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(programIdSet));

        assertCommandSuccess(dropCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_dropManyDogsOneProgram_success() {
        Program editedProgram = (Program) expectedModel.getEntity(ID_TWENTY);

        Set<Integer> dogIdSet = editedProgram.getDogIdSet();
        dogIdSet.stream().forEach(x -> removeOneDogFromOneProgram(x, ID_TWENTY));
        Set<Integer> programIdSet = new HashSet<>();
        programIdSet.add(ID_TWENTY);

        DropCommand dropCommand = new DropCommand(dogIdSet, programIdSet);
        String expectedMessage = String.format(DropCommand.MESSAGE_SUCCESS_FORMAT,
                dogIdSet.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ")),
                ID_TWENTY);
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(programIdSet));

        assertCommandSuccess(dropCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_dropManyDogsManyPrograms_failure() {
        Program editedProgramTwenty = removeOneDogFromOneProgram(ID_FOUR, ID_TWENTY);
        Program editedProgramTwentyOne = removeOneDogFromOneProgram(ID_EIGHT, ID_TWENTY_ONE);

        Set<Integer> dogIdSetTwenty = editedProgramTwenty.getDogIdSet();
        Set<Integer> dogIdSetTwentyOne = editedProgramTwentyOne.getDogIdSet();
        Set<Integer> dogIdSet = new HashSet<>();
        dogIdSet.addAll(dogIdSetTwenty);
        dogIdSet.addAll(dogIdSetTwentyOne);
        List<Integer> programIdList = Arrays.asList(ID_TWENTY, ID_TWENTY_ONE);
        Set<Integer> programIdSet = new HashSet<>(programIdList);
        programIdSet.addAll(programIdList);
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(programIdSet));

        DropCommand dropCommand = new DropCommand(dogIdSet, programIdSet);

        assertThrows(AssertionError.class, () -> dropCommand.execute(model));
    }

    @Test
    public void execute_dropUnenrolledDog_throwsCommandException() {
        removeOneDogFromOneProgram(ID_FOUR, ID_EIGHTEEN);

        Set<Integer> dogIdSet = new HashSet<>();
        dogIdSet.add(ID_FOUR);
        Set<Integer> programIdSet = new HashSet<>();
        programIdSet.add(ID_EIGHTEEN);
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(programIdSet));

        DropCommand dropCommand = new DropCommand(dogIdSet, programIdSet);

        assertCommandFailure(dropCommand, model, MESSAGE_NOT_ENROLLED);
    }

    @Test
    public void execute_dropMultipleUnenrolledDogs_throwsCommandException() {
        Program editedProgram = (Program) expectedModel.getEntity(ID_TWENTY);

        Set<Integer> dogIdSet = new HashSet<>();
        dogIdSet.addAll(editedProgram.getDogIdSet());
        dogIdSet.add(ID_TWO);
        dogIdSet.add(ID_TEN);
        dogIdSet.stream().forEach(x -> removeOneDogFromOneProgram(x, ID_TWENTY));
        Set<Integer> programIdSet = new HashSet<>();
        programIdSet.add(ID_TWENTY);

        expectedModel.updateFilteredEntityList(new IdMatchPredicate(programIdSet));

        DropCommand dropCommand = new DropCommand(dogIdSet, programIdSet);

        assertCommandFailure(dropCommand, model, MESSAGE_NOT_ENROLLED_MULTIPLE_DOGS);
    }

    @Test
    public void execute_dropUnenrolledDogFromMultiplePrograms_throwsCommandException() {
        removeOneDogFromOneProgram(ID_FOUR, ID_NINETEEN);
        removeOneDogFromOneProgram(ID_FOUR, ID_TWENTY);
        removeOneDogFromOneProgram(ID_FOUR, ID_TWENTY_ONE);

        Set<Integer> dogIdSet = new HashSet<>();
        dogIdSet.add(ID_FOUR);
        List<Integer> programIdList = Arrays.asList(ID_NINETEEN, ID_TWENTY, ID_TWENTY_ONE);
        Set<Integer> programIdSet = new HashSet<>(programIdList);
        programIdSet.addAll(programIdList);

        expectedModel.updateFilteredEntityList(new IdMatchPredicate(programIdSet));

        DropCommand dropCommand = new DropCommand(dogIdSet, programIdSet);

        assertCommandFailure(dropCommand, model, MESSAGE_NOT_ENROLLED_MULTIPLE_PROGRAMS);
    }
}
