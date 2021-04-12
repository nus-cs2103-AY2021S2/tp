package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    //=================================Command related messages===============================================
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    //=================================Patient related messages===============================================
    public static final String MESSAGE_ADD_PATIENT_SUCCESS = "New patient added: %1$s";
    public static final String MESSAGE_ADD_DUPLICATE_PATIENT = "This patient already exists in the patient records";
    public static final String MESSAGE_FIND_PATIENT_SUCCESS = "%1$d patients found!";
    public static final String MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX = "The patient index provided is invalid";
    public static final String MESSAGE_FORCE_DELETE_PATIENT_REQUIRED =
            "The patient has existing appointments in appointment schedule."
                    + " Force delete command format required! \n%1$s";
    public static final String MESSAGE_CLEAR_APPOINTMENTS_BEFORE_PATIENTS_REQUIRED =
            "There are Appointments in the Appointment Schedule. "
                    + "As such, patient records cannot be cleared until appointment schedule is cleared.\n"
                    + "Use the command \"clear-appt\" to clear the appointment schedule.";
    public static final String MESSAGE_CLEAR_PATIENT_SUCCESS = "Patient records has been cleared!";
    public static final String MESSAGE_DELETE_PATIENT_SUCCESS = "Deleted Patient: %1$s";
    public static final String MESSAGE_LIST_PATIENT_SUCCESS = "Listed all patients";

    //=================================Doctor related messages================================================
    public static final String MESSAGE_ADD_DOCTOR_SUCCESS = "New doctor added: %1$s";
    public static final String MESSAGE_ADD_DUPLICATE_DOCTOR = "This doctor already exists in the doctor records";
    public static final String MESSAGE_FIND_DOCTOR_SUCCESS = "%1$d doctors found!";
    public static final String MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX = "The doctor index provided is invalid";
    public static final String MESSAGE_FORCE_DELETE_DOCTOR_REQUIRED =
            "The doctor has existing appointments in appointment schedule."
                    + " Force delete command format required! \n%1$s";
    public static final String MESSAGE_CLEAR_APPOINTMENTS_BEFORE_DOCTORS_REQUIRED =
            "There are Appointments in the Appointment Schedule. "
            + "As such, doctor records cannot be cleared until appointment schedule is cleared.\n"
                    + "Use the command \"clear-appt\" to clear the appointment schedule.";
    public static final String MESSAGE_CLEAR_DOCTOR_SUCCESS = "Doctor records have been cleared!";
    public static final String MESSAGE_DELETE_DOCTOR_SUCCESS = "Deleted Doctor: %1$s";
    public static final String MESSAGE_LIST_DOCTOR_SUCCESS = "Listed all doctors";

    //=================================Appointment related messages===========================================
    public static final String MESSAGE_ADD_APPOINTMENT_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_ADD_APPOINTMENT_CONFLICT = "This appointment will result in conflicts "
            + "in the appointment schedule";
    public static final String MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX =
            "The appointment index provided is invalid";
    public static final String MESSAGE_CLEAR_APPOINTMENT_SUCCESS = "Appointment Schedule has been cleared!";
    public static final String MESSAGE_LIST_APPOINTMENT_SUCCESS = "Listed all Appointments";
    public static final String MESSAGE_FIND_APPOINTMENT_SUCCESS = "%1$d appointments found!";
}
