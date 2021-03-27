package seedu.student.testutil;

import static seedu.student.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.student.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.student.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.student.logic.parser.CliSyntax.PREFIX_MATRICULATION_NUMBER;
import static seedu.student.logic.parser.CliSyntax.PREFIX_MEDICAL_DETAILS;
import static seedu.student.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.student.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.student.logic.parser.CliSyntax.PREFIX_SCHOOL_RESIDENCE;
import static seedu.student.logic.parser.CliSyntax.PREFIX_VACCINATION_STATUS;

import seedu.student.logic.commands.AddCommand;
import seedu.student.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.student.model.student.Student;

/**
 * A utility class for Student.
 */
public class StudentUtil {

    /**
     * Returns an add command string for adding the {@code student}.
     */
    public static String getAddCommand(Student student) {
        return AddCommand.COMMAND_WORD + " " + getStudentDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + student.getName().fullName + " ");
        sb.append(PREFIX_MATRICULATION_NUMBER + student.getMatriculationNumber().value + " ");
        sb.append(PREFIX_FACULTY + student.getFaculty().value + " ");
        sb.append(PREFIX_PHONE + student.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + student.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + student.getAddress().value + " ");
        sb.append(PREFIX_VACCINATION_STATUS + student.getVaccinationStatus().textUI + " ");
        sb.append(PREFIX_MEDICAL_DETAILS + student.getMedicalDetails().value + " ");
        sb.append(PREFIX_SCHOOL_RESIDENCE + student.getSchoolResidence().toSavingString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getMatriculationNumber().ifPresent(matriculationNumber -> sb.append(PREFIX_MATRICULATION_NUMBER)
                .append(matriculationNumber.value).append(" "));
        descriptor.getFaculty().ifPresent(faculty -> sb.append(PREFIX_FACULTY).append(faculty.value).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getVaccinationStatus().ifPresent(vaccinationStatus -> sb.append(PREFIX_VACCINATION_STATUS)
                .append(vaccinationStatus.textUI).append(" "));
        descriptor.getMedicalDetails().ifPresent(medicalDetails -> sb.append(PREFIX_MEDICAL_DETAILS)
                .append(medicalDetails.value).append(" "));
        descriptor.getSchoolResidence().ifPresent(schoolResidence -> sb.append(PREFIX_SCHOOL_RESIDENCE)
                .append(schoolResidence.toSavingString()).append(" "));
        return sb.toString();
    }
}
