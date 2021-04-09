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
        Program editedProgramNineteen = (Program) expectedModel.getEntity(ID_NINETEEN);
        Program editedProgramTwenty = (Program) expectedModel.getEntity(ID_TWENTY);

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

        HashSet<Integer> updatedDroppedDogsNineteen = new HashSet<>(editedProgramNineteen.getDogIdSet());
        updatedDroppedDogsNineteen.remove(ID_FOUR);

        expectedModel.setEntity(ID_NINETEEN, new Program(editedProgramNineteen.getName(),
                editedProgramNineteen.getSessions(), editedProgramNineteen.getTags(), updatedDroppedDogsNineteen));

        HashSet<Integer> updatedDroppedDogsTwenty = new HashSet<>(editedProgramTwenty.getDogIdSet());
        updatedDroppedDogsTwenty.remove(ID_FOUR);

        expectedModel.setEntity(ID_TWENTY, new Program(editedProgramTwenty.getName(),
                editedProgramTwenty.getSessions(), editedProgramTwenty.getTags(), updatedDroppedDogsTwenty));

        expectedModel.updateFilteredEntityList(new IdMatchPredicate(programIdSet));

        assertCommandSuccess(dropCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_dropManyDogsOneProgram_success() {
        Program editedProgram = (Program) expectedModel.getEntity(ID_TWENTY);

        Set<Integer> dogIdSet = editedProgram.getDogIdSet();
        Set<Integer> programIdSet = new HashSet<>();
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
        Program editedProgramTwenty = (Program) expectedModel.getEntity(ID_TWENTY);
        Program editedProgramTwentyOne = (Program) expectedModel.getEntity(ID_TWENTY_ONE);

        Set<Integer> dogIdSet1 = editedProgramTwenty.getDogIdSet();
        Set<Integer> dogIdSet2 = editedProgramTwentyOne.getDogIdSet();
        Set<Integer> dogIdSet = new HashSet<>();
        dogIdSet.addAll(dogIdSet1);
        dogIdSet.addAll(dogIdSet2);
        List<Integer> programIdList = Arrays.asList(ID_TWENTY, ID_TWENTY_ONE);
        Set<Integer> programIdSet = new HashSet<>(programIdList);
        programIdSet.addAll(programIdList);

        HashSet<Integer> updatedDroppedDogsTwenty = new HashSet<>(editedProgramTwenty.getDogIdSet());
        updatedDroppedDogsTwenty.remove(ID_FOUR);

        expectedModel.setEntity(ID_TWENTY, new Program(editedProgramTwenty.getName(),
                editedProgramTwenty.getSessions(), editedProgramTwenty.getTags(), updatedDroppedDogsTwenty));

        HashSet<Integer> updatedDroppedDogsTwentyOne = new HashSet<>(editedProgramTwentyOne.getDogIdSet());
        updatedDroppedDogsTwentyOne.remove(ID_EIGHT);

        expectedModel.setEntity(ID_TWENTY_ONE, new Program(editedProgramTwentyOne.getName(),
                editedProgramTwentyOne.getSessions(), editedProgramTwentyOne.getTags(), updatedDroppedDogsTwentyOne));

        expectedModel.updateFilteredEntityList(new IdMatchPredicate(programIdSet));

        DropCommand dropCommand = new DropCommand(dogIdSet, programIdSet);

        assertThrows(AssertionError.class, () -> dropCommand.execute(model));
    }

    @Test
    public void execute_dropUnenrolledDog_throwsCommandException() {
        Program editedProgram = (Program) expectedModel.getEntity(ID_EIGHTEEN);

        Set<Integer> dogIdSet = new HashSet<>();
        dogIdSet.add(ID_FOUR);
        Set<Integer> programIdSet = new HashSet<>();
        programIdSet.add(ID_EIGHTEEN);

        HashSet<Integer> updatedDroppedDogs = new HashSet<>(editedProgram.getDogIdSet());
        updatedDroppedDogs.remove(ID_FOUR);

        expectedModel.setEntity(ID_EIGHTEEN, new Program(editedProgram.getName(),
                editedProgram.getSessions(), editedProgram.getTags(), updatedDroppedDogs));

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
        Set<Integer> programIdSet = new HashSet<>();
        programIdSet.add(ID_TWENTY);

        HashSet<Integer> updatedDroppedDogs = new HashSet<>(editedProgram.getDogIdSet());
        updatedDroppedDogs.removeAll(dogIdSet);

        expectedModel.updateFilteredEntityList(new IdMatchPredicate(programIdSet));

        DropCommand dropCommand = new DropCommand(dogIdSet, programIdSet);

        assertCommandFailure(dropCommand, model, MESSAGE_NOT_ENROLLED_MULTIPLE_DOGS);
    }

    @Test
    public void execute_dropUnenrolledDogFromMultiplePrograms_throwsCommandException() {
        Program editedProgramNineteen = (Program) expectedModel.getEntity(ID_NINETEEN);
        Program editedProgramTwenty = (Program) expectedModel.getEntity(ID_TWENTY);
        Program editedProgramTwentyOne = (Program) expectedModel.getEntity(ID_TWENTY_ONE);

        Set<Integer> dogIdSet = new HashSet<>();
        dogIdSet.add(ID_FOUR);
        List<Integer> programIdList = Arrays.asList(ID_NINETEEN, ID_TWENTY, ID_TWENTY_ONE);
        Set<Integer> programIdSet = new HashSet<>(programIdList);
        programIdSet.addAll(programIdList);

        HashSet<Integer> updatedDroppedDogsNineteen = new HashSet<>(editedProgramNineteen.getDogIdSet());
        updatedDroppedDogsNineteen.remove(ID_FOUR);

        expectedModel.setEntity(ID_NINETEEN, new Program(editedProgramNineteen.getName(),
                editedProgramNineteen.getSessions(), editedProgramNineteen.getTags(), updatedDroppedDogsNineteen));

        HashSet<Integer> updatedDroppedDogsTwenty = new HashSet<>(editedProgramTwenty.getDogIdSet());
        updatedDroppedDogsTwenty.remove(ID_FOUR);

        expectedModel.setEntity(ID_TWENTY, new Program(editedProgramTwenty.getName(),
                editedProgramTwenty.getSessions(), editedProgramTwenty.getTags(), updatedDroppedDogsTwenty));

        HashSet<Integer> updatedDroppedDogsTwentyOne = new HashSet<>(editedProgramTwentyOne.getDogIdSet());
        updatedDroppedDogsTwentyOne.remove(ID_FOUR);

        expectedModel.setEntity(ID_TWENTY_ONE, new Program(editedProgramTwentyOne.getName(),
                editedProgramTwentyOne.getSessions(), editedProgramTwentyOne.getTags(), updatedDroppedDogsTwentyOne));

        expectedModel.updateFilteredEntityList(new IdMatchPredicate(programIdSet));

        DropCommand dropCommand = new DropCommand(dogIdSet, programIdSet);

        assertCommandFailure(dropCommand, model, MESSAGE_NOT_ENROLLED_MULTIPLE_PROGRAMS);
    }
}
