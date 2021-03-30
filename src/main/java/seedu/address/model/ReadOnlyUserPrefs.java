package seedu.address.model;

import java.nio.file.Path;

import seedu.address.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    public GuiSettings getGuiSettings();

    public void setGuiSettings(GuiSettings guiSettings);

    public Path getPatientRecordsFilePath();

    public void setPatientRecordsFilePath(Path patientRecordsFilePath);

    public Path getDoctorRecordsFilePath();

    public void setDoctorRecordsFilePath(Path doctorRecordsFilePath);

    public Path getAppointmentScheduleFilePath();

    public void setAppointmentScheduleFilePath(Path appointmentScheduleFilePath);

}
