package seedu.partyplanet.testutil;

import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.partyplanet.logic.commands.AddCommand;
import seedu.partyplanet.logic.commands.EditFieldCommand.EditPersonDescriptor;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.model.tag.Tag;

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
        sb.append(PREFIX_BIRTHDAY + " " + person.getBirthday().value + " ");
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
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME + " " + name.fullName + " "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE + " " + phone.value + " "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL + " " + email.value + " "));
        descriptor.getBirthday().ifPresent(birthday -> sb.append(PREFIX_BIRTHDAY + " " + birthday.value + " "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS + " " + address.value + " "));
        descriptor.getRemark().ifPresent(remark -> sb.append(PREFIX_REMARK + " " + remark.value + " "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(" ").append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
