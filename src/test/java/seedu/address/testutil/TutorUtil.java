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
import seedu.address.logic.commands.tutorcommands.EditCommand.EditTutorDescriptor;
import seedu.address.model.subject.SubjectList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutor.Tutor;

/**
 * A utility class for Tutor.
 */
public class TutorUtil {

    /**
     * Returns an add command string for adding the {@code tutor}.
     */
    public static String getAddCommand(Tutor tutor) {
        return AddCommand.COMMAND_WORD + " " + getTutorDetails(tutor);
    }

    /**
     * Returns the part of command string for the given {@code tutor}'s details.
     */
    public static String getTutorDetails(Tutor tutor) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + tutor.getName().fullName + " ");
        sb.append(PREFIX_GENDER + tutor.getGender().personGender + " ");
        sb.append(PREFIX_PHONE + tutor.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + tutor.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + tutor.getAddress().value + " ");
        sb.append(PREFIX_NOTES + tutor.getNotes().value + " ");
        tutor.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        tutor.getSubjectList().asUnmodifiableObservableList().stream().forEach(
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
     * Returns the part of command string for the given {@code EditTutorDescriptor}'s details.
     */
    public static String getEditTutorDescriptorDetails(EditTutorDescriptor descriptor) {
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
