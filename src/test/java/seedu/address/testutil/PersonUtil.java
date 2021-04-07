package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GUARDIAN_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GUARDIAN_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.subject.Subject;



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
        //compulsory details
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        //optional details
        if (person.getSchool().isPresent()) {
            sb.append(PREFIX_SCHOOL + person.getSchool().get().fullSchoolName + " ");
        }
        if (person.getEmail().isPresent()) {
            sb.append(PREFIX_EMAIL + person.getEmail().get().value + " ");
        }
        if (person.getAddress().isPresent()) {
            sb.append(PREFIX_ADDRESS + person.getAddress().get().value + " ");
        }
        if (person.getGuardianName().isPresent()) {
            sb.append(PREFIX_GUARDIAN_NAME + person.getGuardianName().get().fullName + " ");
        }
        if (person.getGuardianPhone().isPresent()) {
            sb.append(PREFIX_GUARDIAN_PHONE + person.getGuardianPhone().get().value + " ");
        }
        if (person.getLevel().isPresent()) {
            sb.append(PREFIX_LEVEL + person.getLevel().get().getLevel() + " ");
        }
        person.getSubjects().stream().forEach(
            s -> sb.append(PREFIX_SUBJECT + s.subjectName + " ")
        );
        person.getLessons().stream().forEach(
            l -> sb.append(PREFIX_LESSON + l.formatString() + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getSchool().ifPresent(school -> sb.append(PREFIX_SCHOOL).append(school.fullSchoolName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getGuardianName().ifPresent(guardianName -> sb.append(PREFIX_GUARDIAN_NAME)
                .append(guardianName.fullName).append(" "));
        descriptor.getGuardianPhone().ifPresent(guardianPhone -> sb.append(PREFIX_GUARDIAN_PHONE)
                .append(guardianPhone.value).append(" "));
        descriptor.getLevel().ifPresent(level -> sb.append(PREFIX_LEVEL)
                .append(level.getLevel()).append(" "));
        if (descriptor.getSubjects().isPresent()) {
            Set<Subject> subjects = descriptor.getSubjects().get();
            if (subjects.isEmpty()) {
                sb.append(PREFIX_SUBJECT).append(" ");
            } else {
                subjects.forEach(s -> sb.append(PREFIX_SUBJECT).append(s.subjectName).append(" "));
            }
        }
        if (descriptor.getLessons().isPresent()) {
            Set<Lesson> lessons = descriptor.getLessons().get();
            if (lessons.isEmpty()) {
                sb.append(PREFIX_LESSON);
            } else {
                lessons.forEach(l -> sb.append(PREFIX_LESSON).append(l.formatString()).append(" "));
            }
        }
        return sb.toString();
    }
}
