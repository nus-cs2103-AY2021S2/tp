package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.doctor.EditDoctorCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.patient.EditPatientCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.model.person.PatientMap;
import seedu.address.model.person.SamePersonPredicate;
import seedu.address.testutil.EditDoctorDescriptorBuilder;
import seedu.address.testutil.EditPatientDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_UUID_AMY = "564ae8c3-d0e6-4597-93ae-fe88c21a819f";
    public static final String VALID_UUID_BOB = "8e80ae4c-6435-408e-918c-3d73bc3df1e4";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_UUID_YOHN = "7734ec6c-1720-4224-b8eb-13c9a30899da";
    public static final String VALID_UUID_ZOHN = "166c5888-76e5-4418-b1d1-e093a6044ec2";
    public static final String VALID_NAME_YOHN = "Yohn Bee";
    public static final String VALID_NAME_ZOHN = "Zohn Choo";
    public static final String VALID_NAME_DR_LEONARD = "Dr Leonard Hofstadter";
    public static final String VALID_NAME_DR_SHELDON = "Dr Sheldon Cooper";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_TAG_SHORT = "short";
    public static final String VALID_TAG_TALL = "tall";
    public static final String VALID_TAG_FEVER = "fevre";
    public static final String VALID_TAG_SEVERE = "severe";
    public static final String VALID_TAG_MEDICINE = "medicine";
    public static final String VALID_TAG_INTERNAL = "internal";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_YOHN = " " + PREFIX_NAME + VALID_NAME_YOHN;
    public static final String NAME_DESC_ZOHN = " " + PREFIX_NAME + VALID_NAME_ZOHN;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String TAG_DESC_MEDICINE = " " + PREFIX_TAG + VALID_TAG_MEDICINE;
    public static final String TAG_DESC_INTERNAL = " " + PREFIX_TAG + VALID_TAG_INTERNAL;


    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPatientCommand.EditPatientDescriptor DESC_AMY;
    public static final EditPatientCommand.EditPatientDescriptor DESC_BOB;
    public static final EditDoctorCommand.EditDoctorDescriptor DESC_DR_LEONARD;
    public static final EditDoctorCommand.EditDoctorDescriptor DESC_DR_SHELDON;

    static {
        DESC_AMY = new EditPatientDescriptorBuilder()
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPatientDescriptorBuilder()
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_DR_LEONARD = new EditDoctorDescriptorBuilder().withName(VALID_NAME_DR_LEONARD)
                .withTags(VALID_TAG_SHORT).build();
        DESC_DR_SHELDON = new EditDoctorDescriptorBuilder().withName(VALID_NAME_DR_SHELDON)
                .withTags(VALID_TAG_TALL).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook<Patient> expectedPatientRecords = new AddressBook<>(actualModel.getPatientRecords());
        List<Patient> expectedFilteredPatientList = new ArrayList<>(actualModel.getFilteredPatientList());

        AddressBook<Doctor> expectedDoctorRecords = new AddressBook<>(actualModel.getDoctorRecords());
        List<Doctor> expectedFilteredDoctorList = new ArrayList<>(actualModel.getFilteredDoctorList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedPatientRecords, actualModel.getPatientRecords());
        assertEquals(expectedFilteredPatientList, actualModel.getFilteredPatientList());
        assertEquals(expectedDoctorRecords, actualModel.getDoctorRecords());
        assertEquals(expectedFilteredDoctorList, actualModel.getFilteredDoctorList());
    }

    /**
     * Updates {@code model}'s filtered patient list to show only the patient at the given {@code targetIndex} in the
     * {@code model}'s patient records.
     */
    public static void showPatientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPatientList().size());

        Patient patient = model.getFilteredPatientList().get(targetIndex.getZeroBased());
        model.updateFilteredPatientList(new SamePersonPredicate(patient));

        assertEquals(1, model.getFilteredPatientList().size());
    }

    /**
     * Updates {@code model}'s filtered doctor list to show only the doctor at the given {@code targetIndex} in the
     * {@code model}'s doctor records.
     */
    public static void showDoctorAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredDoctorList().size());

        Doctor doctor = model.getFilteredDoctorList().get(targetIndex.getZeroBased());
        model.updateFilteredDoctorList(new SamePersonPredicate(doctor));

        assertEquals(1, model.getFilteredDoctorList().size());
    }


    /**
     * Updates {@code model}'s filtered list to show only the appointment at the given {@code targetIndex} in the
     * {@code model}'s appointment schedule.
     */
    public static void showAppointmentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredAppointmentList().size());

        Appointment appointment = model.getFilteredAppointmentList().get(targetIndex.getZeroBased());
        Map<UUID, Patient> patientHashMap = PatientMap.getPatientMap();
        PatientMap.updatePatientHashMap(model.getPatientRecords().getPersonList());

        UUID patientUuid = appointment.getPatientUuid();

        final String patientName = patientHashMap.get(patientUuid).getName().fullName;

        model.updateFilteredAppointmentList(
                new AppointmentContainsKeywordsPredicate(
                        Arrays.asList(patientName),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        new ArrayList<>()));
        assertEquals(1, model.getFilteredAppointmentList().size());
    }
}
