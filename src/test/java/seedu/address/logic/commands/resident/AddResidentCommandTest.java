package seedu.address.logic.commands.resident;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.resident.Resident;
import seedu.address.testutil.resident.ResidentBuilder;

public class AddResidentCommandTest {

    @Test
    public void constructor_nullResident_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddResidentCommand(null));
    }

    @Test
    public void execute_residentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingResidentAdded modelStub = new ModelStubAcceptingResidentAdded();
        Resident validResident = new ResidentBuilder().build();

        CommandResult commandResult = new AddResidentCommand(validResident).execute(modelStub);

        assertEquals(String.format(AddResidentCommand.MESSAGE_SUCCESS, validResident),
            commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validResident), modelStub.residentsAdded);
    }

    @Test
    public void execute_duplicateResident_throwsCommandException() {
        Resident validResident = new ResidentBuilder().build();
        AddResidentCommand addCommand = new AddResidentCommand(validResident);
        ModelStub modelStub = new ModelStubWithResident(validResident);

        assertThrows(CommandException.class, AddResidentCommand.MESSAGE_DUPLICATE_RESIDENT, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Resident alice = new ResidentBuilder().withName("Alice").build();
        Resident bob = new ResidentBuilder().withName("Bob").build();
        AddResidentCommand addAliceCommand = new AddResidentCommand(alice);
        AddResidentCommand addBobCommand = new AddResidentCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddResidentCommand addAliceCommandCopy = new AddResidentCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different resident -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }


    /**
     * A Model stub that contains a single resident.
     */
    private class ModelStubWithResident extends ModelStub {
        private final Resident resident;

        ModelStubWithResident(Resident resident) {
            requireNonNull(resident);
            this.resident = resident;
        }

        @Override
        public boolean hasResident(Resident resident) {
            requireNonNull(resident);
            return this.resident.isSameResident(resident);
        }
    }

    /**
     * A Model stub that always accept the resident being added.
     */
    private class ModelStubAcceptingResidentAdded extends ModelStub {
        final ArrayList<Resident> residentsAdded = new ArrayList<>();

        @Override
        public boolean hasResident(Resident resident) {
            requireNonNull(resident);
            return residentsAdded.stream().anyMatch(resident::isSameResident);
        }

        @Override
        public void addResident(Resident resident) {
            requireNonNull(resident);
            residentsAdded.add(resident);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
