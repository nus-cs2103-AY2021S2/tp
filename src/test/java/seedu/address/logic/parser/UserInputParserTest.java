package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_IN_LIST;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.appointment.AddAppointmentCommand;
import seedu.address.logic.commands.appointment.ClearAppointmentCommand;
import seedu.address.logic.commands.appointment.DeleteAppointmentCommand;
import seedu.address.logic.commands.appointment.EditAppointmentCommand;
import seedu.address.logic.commands.appointment.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.logic.commands.appointment.FindAppointmentCommand;
import seedu.address.logic.commands.appointment.ListAppointmentCommand;
import seedu.address.logic.commands.doctor.AddDoctorCommand;
import seedu.address.logic.commands.doctor.ClearDoctorCommand;
import seedu.address.logic.commands.doctor.DeleteDoctorCommand;
import seedu.address.logic.commands.doctor.EditDoctorCommand;
import seedu.address.logic.commands.doctor.EditDoctorCommand.EditDoctorDescriptor;
import seedu.address.logic.commands.doctor.FindDoctorCommand;
import seedu.address.logic.commands.doctor.ListDoctorCommand;
import seedu.address.logic.commands.patient.AddPatientCommand;
import seedu.address.logic.commands.patient.ClearPatientCommand;
import seedu.address.logic.commands.patient.DeletePatientCommand;
import seedu.address.logic.commands.patient.EditPatientCommand;
import seedu.address.logic.commands.patient.EditPatientCommand.EditPatientDescriptor;
import seedu.address.logic.commands.patient.FindPatientCommand;
import seedu.address.logic.commands.patient.ListPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Patient;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.AppointmentUtil;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.DoctorUtil;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;
import seedu.address.testutil.EditDoctorDescriptorBuilder;
import seedu.address.testutil.EditPatientDescriptorBuilder;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.PatientUtil;

public class UserInputParserTest {

    private final UserInputParser parser = new UserInputParser();

    // Patient Tests
    @Test
    public void parseCommand_addPatient() throws Exception {
        Patient patient = new PatientBuilder().build();
        AddPatientCommand command = (AddPatientCommand) parser.parseCommand(PatientUtil.getAddCommand(patient));
        assertEquals(new AddPatientCommand(patient), command);
    }

    @Test
    public void parseCommand_clearPatient() throws Exception {
        assertTrue(parser.parseCommand(ClearPatientCommand.COMMAND_WORD) instanceof ClearPatientCommand);
        assertTrue(parser.parseCommand(ClearPatientCommand.COMMAND_WORD + " 3") instanceof ClearPatientCommand);
    }

    @Test
    public void parseCommand_deletePatient() throws Exception {
        DeletePatientCommand command = (DeletePatientCommand) parser.parseCommand(
                DeletePatientCommand.COMMAND_WORD + " " + INDEX_FIRST_IN_LIST.getOneBased());
        assertEquals(new DeletePatientCommand(INDEX_FIRST_IN_LIST, false), command);
    }

    @Test
    public void parseCommand_editPatient() throws Exception {
        Patient patient = new PatientBuilder().build();
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(patient).build();
        EditPatientCommand command = (EditPatientCommand) parser.parseCommand(EditPatientCommand.COMMAND_WORD + " "
                + INDEX_FIRST_IN_LIST.getOneBased() + " " + PatientUtil.getEditPatientDescriptorDetails(descriptor));
        assertEquals(new EditPatientCommand(INDEX_FIRST_IN_LIST, descriptor), command);
    }

    @Test
    public void parseCommand_findPatient() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindPatientCommand command = (FindPatientCommand) parser.parseCommand(
                FindPatientCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindPatientCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_listPatient() throws Exception {
        assertTrue(parser.parseCommand(ListPatientCommand.COMMAND_WORD) instanceof ListPatientCommand);
        assertTrue(parser.parseCommand(ListPatientCommand.COMMAND_WORD + " 3") instanceof ListPatientCommand);
    }

    // Doctor Tests
    @Test
    public void parseCommand_addDoctor() throws Exception {
        Doctor doctor = new DoctorBuilder().build();
        AddDoctorCommand command = (AddDoctorCommand) parser.parseCommand(DoctorUtil.getAddCommand(doctor));
        assertEquals(new AddDoctorCommand(doctor), command);
    }

    @Test
    public void parseCommand_clearDoctor() throws Exception {
        assertTrue(parser.parseCommand(ClearDoctorCommand.COMMAND_WORD) instanceof ClearDoctorCommand);
        assertTrue(parser.parseCommand(ClearDoctorCommand.COMMAND_WORD + " 3") instanceof ClearDoctorCommand);
    }

    @Test
    public void parseCommand_deleteDoctor() throws Exception {
        DeleteDoctorCommand command = (DeleteDoctorCommand) parser.parseCommand(
                DeleteDoctorCommand.COMMAND_WORD + " " + INDEX_FIRST_IN_LIST.getOneBased());
        assertEquals(new DeleteDoctorCommand(INDEX_FIRST_IN_LIST, false), command);
    }

    @Test
    public void parseCommand_editDoctor() throws Exception {
        Doctor doctor = new DoctorBuilder().build();
        EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder(doctor).build();
        EditDoctorCommand command = (EditDoctorCommand) parser.parseCommand(EditDoctorCommand.COMMAND_WORD + " "
                + INDEX_FIRST_IN_LIST.getOneBased() + " " + DoctorUtil.getEditDoctorDescriptorDetails(descriptor));
        assertEquals(new EditDoctorCommand(INDEX_FIRST_IN_LIST, descriptor), command);
    }

    @Test
    public void parseCommand_findDoctor() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindDoctorCommand command = (FindDoctorCommand) parser.parseCommand(
                FindDoctorCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindDoctorCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_listDoctor() throws Exception {
        assertTrue(parser.parseCommand(ListDoctorCommand.COMMAND_WORD) instanceof ListDoctorCommand);
        assertTrue(parser.parseCommand(ListDoctorCommand.COMMAND_WORD + " 3") instanceof ListDoctorCommand);
    }

    // Appointment Tests
    @Test
    public void parseCommand_addAppointment() throws Exception {
        Appointment appointment = new AppointmentBuilder().build();
        AddAppointmentCommand command = (AddAppointmentCommand) parser.parseCommand(
                AppointmentUtil.getAddCommand(appointment));
        assertEquals(new AddAppointmentCommand(Index.fromOneBased(1), Index.fromOneBased(1),
                appointment.getTimeslot(), appointment.getTags()), command);
    }

    @Test
    public void parseCommand_clearAppointment() throws Exception {
        assertTrue(parser.parseCommand(ClearAppointmentCommand.COMMAND_WORD) instanceof ClearAppointmentCommand);
        assertTrue(parser.parseCommand(ClearAppointmentCommand.COMMAND_WORD + " 3") instanceof ClearAppointmentCommand);
    }

    @Test
    public void parseCommand_deleteAppointment() throws Exception {
        DeleteAppointmentCommand command = (DeleteAppointmentCommand) parser.parseCommand(
                DeleteAppointmentCommand.COMMAND_WORD + " " + INDEX_FIRST_IN_LIST.getOneBased());
        assertEquals(new DeleteAppointmentCommand(INDEX_FIRST_IN_LIST), command);
    }

    @Test
    public void parseCommand_editAppointment() throws Exception {
        Appointment appointment = new AppointmentBuilder().build();
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(appointment).build();
        EditAppointmentCommand command = (EditAppointmentCommand) parser.parseCommand(
                EditAppointmentCommand.COMMAND_WORD + " " + INDEX_FIRST_IN_LIST.getOneBased() + " "
                + AppointmentUtil.getEditAppointmentDescriptorDetails(descriptor));
        assertEquals(new EditAppointmentCommand(INDEX_FIRST_IN_LIST, descriptor), command);
    }

    @Test
    public void parseCommand_findAppointment() throws Exception {
        List<String> patientNameKeywords = Arrays.asList("foo1", "bar1", "baz1");
        List<String> doctorNameKeywords = Arrays.asList("foo2", "bar2", "baz2");
        List<String> tagKeywords = Arrays.asList("foo3", "bar3", "baz3");
        List<String> timeStartKeywords = Arrays.asList("foo4", "bar4", "baz4");

        FindAppointmentCommand command = (FindAppointmentCommand) parser.parseCommand(
                AppointmentUtil.getFindCommand(
                    patientNameKeywords, doctorNameKeywords, timeStartKeywords, tagKeywords));
        AppointmentContainsKeywordsPredicate appointmentPredicate = new AppointmentContainsKeywordsPredicate(
                patientNameKeywords, doctorNameKeywords, timeStartKeywords, tagKeywords);
        assertEquals(new FindAppointmentCommand(appointmentPredicate), command);
    }

    @Test
    public void parseCommand_listAppointment() throws Exception {
        assertTrue(parser.parseCommand(ListAppointmentCommand.COMMAND_WORD) instanceof ListAppointmentCommand);
        assertTrue(parser.parseCommand(ListAppointmentCommand.COMMAND_WORD + " 3") instanceof ListAppointmentCommand);
    }


    // Overall Commands
    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_tooLongUnknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("tooLongUnknownCommand"));
    }
}
