package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.model.medical.Appointment;
import seedu.address.model.medical.MedicalRecord;
import seedu.address.model.person.Patient;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** Editor should be shown to the user. */
    private final boolean showEdit;

    /** Patient whose view box will be shown to the user. */
    private final Patient patient;

    /** MedicalRecord to be viewed/edited. */
    private final MedicalRecord medicalRecord;

    /** List of appointments that should be shown to the user. */
    private final List<Appointment> appointments;

    /** Message to be displayed. */
    private final String displayMessage;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean showEdit,
                         Patient patient, MedicalRecord medicalRecord, List<Appointment> appointments,
                         String displayMessage, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.showEdit = showEdit;
        this.patient = patient;
        this.medicalRecord = medicalRecord;
        this.appointments = appointments;
        this.displayMessage = displayMessage;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields and {@code showEdit}
     * set to default value.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, false, null, null, null, null, exit);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isShowEdit() {
        return showEdit;
    }

    public boolean isShowViewBox() {
        return patient != null;
    }

    public boolean showAppointments() {
        return appointments != null;
    }

    public boolean isDisplayMessage() {
        return displayMessage != null;
    }

    public boolean isExit() {
        return exit;
    }

    public Patient getPatient() {
        return patient;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && showEdit == otherCommandResult.showEdit
                && patient == otherCommandResult.patient
                && medicalRecord == otherCommandResult.medicalRecord
                && appointments == otherCommandResult.appointments
                && displayMessage == otherCommandResult.displayMessage
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, showEdit, patient, exit);
    }

}
