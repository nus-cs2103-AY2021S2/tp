package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
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
        sb.append(PREFIX_NAME + " " + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + " " + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + " " + person.getEmail().value + " ");
        sb.append(PREFIX_COMPANY + " " + person.getCompany().value + " ");
        sb.append(PREFIX_JOB_TITLE + " " + person.getJobTitle().value + " ");
        sb.append(PREFIX_ADDRESS + " " + person.getAddress().value + " ");
        sb.append(PREFIX_REMARK + " " + person.getRemark().value + " ");

        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + " " + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name ->
                sb.append(PREFIX_NAME).append(" ").append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone ->
                sb.append(PREFIX_PHONE).append(" ").append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email ->
                sb.append(PREFIX_EMAIL).append(" ").append(email.value).append(" "));
        descriptor.getCompany().ifPresent(company ->
                sb.append(PREFIX_COMPANY).append(" ").append(company.value).append(" "));
        descriptor.getJobTitle().ifPresent(jobTitle ->
                sb.append(PREFIX_JOB_TITLE).append(" ").append(jobTitle.value).append(" "));
        descriptor.getAddress().ifPresent(address ->
                sb.append(PREFIX_ADDRESS).append(" ").append(address.value).append(" "));
        descriptor.getRemark().ifPresent(remark ->
                sb.append(PREFIX_REMARK).append(" ").append(remark.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(" ").append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
