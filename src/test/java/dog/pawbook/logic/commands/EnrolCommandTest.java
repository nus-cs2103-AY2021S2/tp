package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;
import static dog.pawbook.testutil.TypicalId.ID_FIFTEEN;
import static dog.pawbook.testutil.TypicalId.ID_FOUR;
import static dog.pawbook.testutil.TypicalId.ID_SIX;
import static dog.pawbook.testutil.TypicalId.ID_SIXTEEN;
import static dog.pawbook.testutil.TypicalId.ID_TWO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

public class EnrolCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalDatabase(), new UserPrefs());
        expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
    }

    @Test
    public void constructor_nullEnrol_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EnrolCommand(null, null));
    }

    @Test
    public void execute_enrolOneDogOneProgram_success() {
        Set<Integer> dogIdSet = new HashSet<>();
        Set<Integer> programIdSet = new HashSet<>();
        dogIdSet.add(ID_TWO);
        programIdSet.add(ID_FIFTEEN);

        Program editedProgram = (Program) expectedModel.getEntity(ID_FIFTEEN);

        HashSet<Integer> updatedEnrolledDogs = new HashSet<>(editedProgram.getDogIdSet());
        updatedEnrolledDogs.add(ID_TWO);

        expectedModel.setEntity(ID_FIFTEEN, new Program(editedProgram.getName(),
                editedProgram.getSessions(), editedProgram.getTags(), updatedEnrolledDogs));

        expectedModel.updateFilteredEntityList(new IdMatchPredicate(programIdSet));

        EnrolCommand enrolCommand = new EnrolCommand(dogIdSet, programIdSet);
        String expectedMessage = String.format(EnrolCommand.MESSAGE_SUCCESS_FORMAT, ID_TWO, ID_FIFTEEN);
        assertCommandSuccess(enrolCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_enrolOneDogManyPrograms_success() {
        Set<Integer> dogIdSet = new HashSet<>();
        dogIdSet.add(ID_TWO);
        List<Integer> programIdList = Arrays.asList(ID_FIFTEEN, ID_SIXTEEN);
        Set<Integer> programIdSet = new HashSet<>(programIdList);

        Program editedProgramFifteen = (Program) expectedModel.getEntity(ID_FIFTEEN);
        Program editedProgramSixteen = (Program) expectedModel.getEntity(ID_SIXTEEN);

        HashSet<Integer> updatedEnrolledDogsFifteen = new HashSet<>(editedProgramFifteen.getDogIdSet());
        updatedEnrolledDogsFifteen.add(ID_TWO);

        expectedModel.setEntity(ID_FIFTEEN, new Program(editedProgramFifteen.getName(),
                editedProgramFifteen.getSessions(), editedProgramFifteen.getTags(), updatedEnrolledDogsFifteen));

        HashSet<Integer> updatedEnrolledDogsSixteen = new HashSet<>(editedProgramFifteen.getDogIdSet());
        updatedEnrolledDogsSixteen.add(ID_TWO);

        expectedModel.setEntity(ID_SIXTEEN, new Program(editedProgramSixteen.getName(),
                editedProgramSixteen.getSessions(), editedProgramSixteen.getTags(), updatedEnrolledDogsSixteen));

        expectedModel.updateFilteredEntityList(new IdMatchPredicate(programIdSet));

        EnrolCommand enrolCommand = new EnrolCommand(dogIdSet, programIdSet);
        String expectedMessage = String.format(EnrolCommand.MESSAGE_SUCCESS_FORMAT,
                ID_TWO,
                programIdSet.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ")));

        assertCommandSuccess(enrolCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_enrolManyDogsOneProgram_success() {
        Set<Integer> programIdSet = new HashSet<>();
        List<Integer> dogIdList = Arrays.asList(ID_TWO, ID_FOUR, ID_SIX);
        Set<Integer> dogIdSet = new HashSet<>(dogIdList);
        programIdSet.add(ID_FIFTEEN);

        Program editedProgram = (Program) expectedModel.getEntity(ID_FIFTEEN);

        HashSet<Integer> updatedEnrolledDogs = new HashSet<>(editedProgram.getDogIdSet());
        updatedEnrolledDogs.addAll(dogIdSet);

        expectedModel.setEntity(ID_FIFTEEN, new Program(editedProgram.getName(),
                editedProgram.getSessions(), editedProgram.getTags(), updatedEnrolledDogs));

        expectedModel.updateFilteredEntityList(new IdMatchPredicate(programIdSet));

        EnrolCommand enrolCommand = new EnrolCommand(dogIdSet, programIdSet);
        String expectedMessage = String.format(EnrolCommand.MESSAGE_SUCCESS_FORMAT,
                dogIdSet.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ")),
                ID_FIFTEEN);

        assertCommandSuccess(enrolCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_enrolManyDogsManyPrograms_failure() {
        List<Integer> dogIdList = Arrays.asList(ID_TWO, ID_FOUR, ID_SIX);
        List<Integer> programIdList = Arrays.asList(ID_FIFTEEN, ID_SIXTEEN);
        Set<Integer> dogIdSet = new HashSet<>(dogIdList);
        Set<Integer> programIdSet = new HashSet<>(programIdList);

        Program editedProgramFifteen = (Program) expectedModel.getEntity(ID_FIFTEEN);
        Program editedProgramSixteen = (Program) expectedModel.getEntity(ID_SIXTEEN);

        HashSet<Integer> updatedEnrolledDogsFifteen = new HashSet<>(editedProgramFifteen.getDogIdSet());
        updatedEnrolledDogsFifteen.add(ID_TWO);

        expectedModel.setEntity(ID_FIFTEEN, new Program(editedProgramFifteen.getName(),
                editedProgramFifteen.getSessions(), editedProgramFifteen.getTags(), updatedEnrolledDogsFifteen));

        HashSet<Integer> updatedEnrolledDogsSixteen = new HashSet<>(editedProgramSixteen.getDogIdSet());
        updatedEnrolledDogsSixteen.add(ID_TWO);

        expectedModel.setEntity(ID_SIXTEEN, new Program(editedProgramSixteen.getName(),
                editedProgramSixteen.getSessions(), editedProgramSixteen.getTags(), updatedEnrolledDogsSixteen));

        expectedModel.updateFilteredEntityList(new IdMatchPredicate(programIdSet));

        EnrolCommand enrolCommand = new EnrolCommand(dogIdSet, programIdSet);

        assertThrows(AssertionError.class, () -> enrolCommand.execute(model));
    }

    @Test
    public void equals() {
        Set<Integer> dogIdSet1 = new HashSet<>();
        Set<Integer> programIdSet1 = new HashSet<>();
        dogIdSet1.add(ID_TWO);
        programIdSet1.add(ID_FIFTEEN);

        Set<Integer> dogIdSet2 = new HashSet<>();
        Set<Integer> programIdSet2 = new HashSet<>();
        dogIdSet1.add(ID_FOUR);
        programIdSet1.add(ID_SIXTEEN);

        EnrolCommand enrolFirstCommand = new EnrolCommand(dogIdSet1, programIdSet1);

        // same object -> returns true
        assertTrue(enrolFirstCommand.equals(enrolFirstCommand));

        // same values -> returns true
        EnrolCommand enrolFirstCommandCopy = new EnrolCommand(dogIdSet1, programIdSet1);
        assertTrue(enrolFirstCommand.equals(enrolFirstCommandCopy));

        // different types -> returns false
        assertFalse(enrolFirstCommand.equals(1));

        // null -> returns false
        assertFalse(enrolFirstCommand.equals(null));

        // different dogIdSet -> returns false
        assertFalse(enrolFirstCommand.equals(new EnrolCommand(dogIdSet2, programIdSet1)));

        // different programIdSet -> returns false
        assertFalse(enrolFirstCommand.equals(new EnrolCommand(dogIdSet1, programIdSet2)));

        // different dogIdSet and programIdSet -> returns false
        assertFalse(enrolFirstCommand.equals(new EnrolCommand(dogIdSet2, programIdSet2)));
    }
}
