package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    public static final String MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR = "%1$d property listed!";
    public static final String MESSAGE_PROPERTIES_LISTED_OVERVIEW = "%1$d properties listed!";
    public static final String MESSAGE_APPOINTMENT_LISTED_OVERVIEW_SINGULAR = "%1$d appointment listed!";
    public static final String MESSAGE_APPOINTMENT_LISTED_OVERVIEW = "%1$d appointments listed!";

    public static final String MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX = "The property index provided is invalid";
    public static final String MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX =
            "The appointment index provided is invalid";
    public static final String MESSAGE_MISSING_PROPERTY_APPOINTMENT =
            "To %1$s an appointment, use %1$s appointment. To %1$s a property, use %1$s property";
    public static final String MESSAGE_MISSING_ALL_PROPERTY_APPOINTMENT =
            "To %1$s all appointments and properties, use %1$s all. "
                    + "To %1$s all appointments, use %1$s appointment. To %1$s all properties, use %1$s property";

    /**
     * Returns the output message for a successful find client command.
     * @param propertyListSize Number of items in the property list
     * @param appointmentListSize Number of items in the appointment list
     */
    public static String getClientFindSuccessMessage(int propertyListSize, int appointmentListSize) {
        String propertyMsg = propertyListSize > 1
                ? MESSAGE_PROPERTIES_LISTED_OVERVIEW
                : MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR;

        String appointmentMsg = appointmentListSize > 1
                ? MESSAGE_APPOINTMENT_LISTED_OVERVIEW
                : MESSAGE_APPOINTMENT_LISTED_OVERVIEW_SINGULAR;

        propertyMsg = String.format(propertyMsg, propertyListSize);
        appointmentMsg = String.format(appointmentMsg, appointmentListSize);

        return appointmentMsg + "\n" + propertyMsg;
    }

    /**
     * Generates the error message when property or appointment is missing in the command.
     * @param command the command without property or appointment
     * @return error message
     */
    public static String missingPropertyAppointmentError(String command) {
        return String.format(MESSAGE_MISSING_PROPERTY_APPOINTMENT, command);
    }

    /**
     * Generates the error message when all, property or appointment is missing in the command.
     * @param command the command without all, property or appointment
     * @return error message
     */
    public static String missingAllPropertyAppointmentError(String command) {
        return String.format(MESSAGE_MISSING_ALL_PROPERTY_APPOINTMENT, command);
    }
}
