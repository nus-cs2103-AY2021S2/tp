package seedu.student.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format. \n%1$s";
    public static final String MESSAGE_NONEXISTENT_MATRIC_NUM = "The matriculation number provided does not exist.";
    public static final String MESSAGE_STUDENTS_ARE_LISTED = "All students are listed.";
    public static final String MESSAGE_NO_STUDENTS_ARE_LISTED = "No students are listed.";
    public static final String MESSAGE_STUDENTS_AND_APPOINTMENT_FOUND =
            "Found student with matriculation number %s \n"
                    + "If they have an appointment, their appointment will also be listed.";
    public static final String MESSAGE_NO_STUDENT_FOUND =
            "No student with matriculation number %s was found. \n";
    public static final String MESSAGE_NONEXISTENT_APPOINTMENT = "No appointment was found.";
    public static final String MESSAGE_OUT_OF_INDEX = "The index requested does not exist";
}
