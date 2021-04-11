package seedu.address.logic.commands.doctor;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Doctor;
import seedu.address.testutil.DoctorBuilder;


public class AddDoctorCommandTest {

    @Test
    public void constructor_nullDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDoctorCommand(null));
    }

    @Test
    public void execute_doctorAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingDoctorAdded modelStub = new ModelStubAcceptingDoctorAdded();
        Doctor validDoctor = new DoctorBuilder().build();

        CommandResult commandResult = new AddDoctorCommand(validDoctor).execute(modelStub);

        assertEquals(String.format(Messages.MESSAGE_ADD_DOCTOR_SUCCESS, validDoctor),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validDoctor), modelStub.doctorsAdded);
    }

    @Test
    public void execute_duplicateDoctor_throwsCommandException() {
        Doctor validDoctor = new DoctorBuilder().build();
        AddDoctorCommand addCommand = new AddDoctorCommand(validDoctor);
        ModelStub modelStub = new ModelStubWithDoctor(validDoctor);

        assertThrows(CommandException.class, Messages.MESSAGE_ADD_DUPLICATE_DOCTOR, () -> addCommand
                .execute(modelStub));
    }

    @Test
    public void equals() {
        Doctor alice = new DoctorBuilder().withName("Alice").build();
        Doctor bob = new DoctorBuilder().withName("Bob").build();
        AddDoctorCommand addAliceCommand = new AddDoctorCommand(alice);
        AddDoctorCommand addBobCommand = new AddDoctorCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddDoctorCommand addAliceCommandCopy = new AddDoctorCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithDoctor extends ModelStub {
        private final Doctor doctor;

        ModelStubWithDoctor(Doctor doctor) {
            requireNonNull(doctor);
            this.doctor = doctor;
        }

        @Override
        public boolean hasDoctor(Doctor doctor) {
            requireNonNull(doctor);
            return this.doctor.isSamePerson(doctor);
        }
    }

    /**
     * A Model stub that always accept the doctor being added.
     */
    private class ModelStubAcceptingDoctorAdded extends ModelStub {
        final ArrayList<Doctor> doctorsAdded = new ArrayList<>();

        @Override
        public boolean hasDoctor(Doctor doctor) {
            requireNonNull(doctor);
            return doctorsAdded.stream().anyMatch(doctor::isSamePerson);
        }

        @Override
        public boolean hasConflictingUuid(UUID uuid) {
            return doctorsAdded.stream().anyMatch(doctor -> doctor.getUuid() == uuid);
        }

        @Override
        public void addDoctor(Doctor doctor) {
            requireNonNull(doctor);
            doctorsAdded.add(doctor);
        }

        @Override
        public ReadOnlyAddressBook<Doctor> getDoctorRecords() {
            return new AddressBook<>();
        }
    }

}
