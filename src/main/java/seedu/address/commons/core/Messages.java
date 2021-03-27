package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    //=================================Command related messages===============================================
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    //=================================Patient related messages===============================================
    public static final String MESSAGE_PATIENTS_LISTED_OVERVIEW = "%1$d patients listed!";
    public static final String MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX = "The patient index provided is invalid";
    public static final String MESSAGE_FORCE_DELETE_REQUIRED =
            "The patient has existing appointments in appointment schedule."
            + " Force delete command format required! \n%1$s";
    public static final String MESSAGE_DELETE_PATIENT_SUCCESS = "Deleted Person: %1$s";

    //=================================Doctor related messages===============================================
    public static final String MESSAGE_DOCTORS_LISTED_OVERVIEW = "%1$d doctors listed!";
    public static final String MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX = "The doctor index provided is invalid";
    public static final String MESSAGE_FORCE_DELETE_DOCTOR_REQUIRED =
            "The doctor has existing appointments in appointment schedule."
                    + " Force delete command format required! \n%1$s";
    public static final String MESSAGE_DELETE_DOCTOR_SUCCESS = "Deleted Person: %1$s";

    //=================================Appointment related messages===========================================
    public static final String MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX =
            "The appointment index provided is invalid";
    public static final String MESSAGE_APPOINTMENTS_LISTED_OVERVIEW = "%1$d appointments listed!";
}
