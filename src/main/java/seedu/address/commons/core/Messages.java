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
}
