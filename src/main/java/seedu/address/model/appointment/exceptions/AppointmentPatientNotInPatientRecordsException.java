package seedu.address.model.appointment.exceptions;

/**
 * Signals that the operation will result in a negative or zero duration for an appointment.
 */

public class AppointmentPatientNotInPatientRecordsException extends Exception {
    public AppointmentPatientNotInPatientRecordsException() {
        super("patient UUID in AppointmentSchedule does not exist in PatientRecords");
    }
}
