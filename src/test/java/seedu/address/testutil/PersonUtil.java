package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRICULATION_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL_RESIDENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VACCINATION_STATUS;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_MATRICULATION_NUMBER + person.getMatriculationNumber().value + " ");
        sb.append(PREFIX_FACULTY + person.getFaculty().value + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        sb.append(PREFIX_VACCINATION_STATUS + person.getVaccinationStatus().textUI + " ");
        sb.append(PREFIX_MEDICAL_DETAILS + person.getMedicalDetails().value + " ");
        sb.append(PREFIX_SCHOOL_RESIDENCE + person.getSchoolResidence().toSavingString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
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
