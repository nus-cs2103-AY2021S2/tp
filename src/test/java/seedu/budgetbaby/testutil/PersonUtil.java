package seedu.budgetbaby.testutil;

import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Set;

import seedu.budgetbaby.ablogic.commands.AddCommand;
import seedu.budgetbaby.ablogic.commands.EditCommand.EditPersonDescriptor;
import seedu.budgetbaby.abmodel.person.Person;
import seedu.budgetbaby.model.record.Category;


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
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_CATEGORY + s.category + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Category> categories = descriptor.getTags().get();
            if (categories.isEmpty()) {
                sb.append(PREFIX_CATEGORY);
            } else {
                categories.forEach(s -> sb.append(PREFIX_CATEGORY).append(s.category).append(" "));
            }
        }
        return sb.toString();
    }
}
