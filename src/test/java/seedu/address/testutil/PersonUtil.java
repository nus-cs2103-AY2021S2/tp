package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.Set;

import seedu.address.logic.commands.tutorcommands.AddCommand;
import seedu.address.logic.commands.tutorcommands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.subject.SubjectList;
import seedu.address.model.tag.Tag;

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
        sb.append(PREFIX_GENDER + person.getGender().personGender + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        sb.append(PREFIX_NOTES + person.getNotes().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        person.getSubjectList().asUnmodifiableObservableList().stream().forEach(
            s -> {
                sb.append(PREFIX_SUBJECT_NAME + s.getName().name + " ");
                sb.append(PREFIX_EDUCATION_LEVEL + s.getLevel().level + " ");
                sb.append(PREFIX_RATE + s.getRate().rate.toString() + " ");
                sb.append(PREFIX_YEAR + s.getExperience().experience.toString() + " ");
                sb.append(PREFIX_QUALIFICATION + s.getQualification().qualification + " ");
            });
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getGender().ifPresent(gender -> sb.append(PREFIX_GENDER).append(gender.personGender).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getNotes().ifPresent(notes -> sb.append(PREFIX_NOTES).append(notes.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        if (descriptor.getSubjectList().isPresent()) {
            SubjectList subjects = descriptor.getSubjectList().get();
            subjects.asUnmodifiableObservableList().stream().forEach(
                s -> {
                    sb.append(PREFIX_SUBJECT_NAME + s.getName().name + " ");
                    sb.append(PREFIX_EDUCATION_LEVEL + s.getLevel().level + " ");
                    sb.append(PREFIX_RATE + s.getRate().rate.toString() + " ");
                    sb.append(PREFIX_YEAR + s.getExperience().experience.toString() + " ");
                    sb.append(PREFIX_QUALIFICATION + s.getQualification().qualification + " ");
                });
        }

        return sb.toString();
    }
}
