package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.UserInputParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentSchedule;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final UserInputParser userInputParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        userInputParser = new UserInputParser();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    //=========== Command Execution ==========================================================================
    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = userInputParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.savePatientRecords(model.getPatientRecords());
            storage.saveDoctorRecords(model.getDoctorRecords());
            storage.saveAppointmentSchedule(model.getAppointmentSchedule());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    //=========== PatientRecords ================================================================================
    @Override
    public ReadOnlyAddressBook<Patient> getPatientRecords() {
        return model.getPatientRecords();
    }

    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return model.getFilteredPatientList();
    }

    @Override
    public Path getPatientRecordsFilePath() {
        return model.getPatientRecordsFilePath();
    }

    //=========== DoctorRecords ================================================================================
    @Override
    public ReadOnlyAddressBook<Doctor> getDoctorRecords() {
        return model.getDoctorRecords();
    }

    @Override
    public ObservableList<Doctor> getFilteredDoctorList() {
        return model.getFilteredDoctorList();
    }

    @Override
    public Path getDoctorRecordsFilePath() {
        return model.getDoctorRecordsFilePath();
    }

    //=========== AppointmentSchedule ========================================================================
    @Override
    public ReadOnlyAppointmentSchedule getAppointmentSchedule() {
        return model.getAppointmentSchedule();
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return model.getFilteredAppointmentList();
    }

    @Override
    public Path getAppointmentScheduleFilePath() {
        return model.getAppointmentScheduleFilePath();
    }
}
