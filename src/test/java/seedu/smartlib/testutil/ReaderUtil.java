package seedu.smartlib.testutil;

import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_READER;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.smartlib.logic.commands.AddReaderCommand;
import seedu.smartlib.logic.commands.EditCommand.EditReaderDescriptor;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.tag.Tag;

/**
 * A utility class for Reader.
 */
public class ReaderUtil {

    /**
     * Returns an add command string for adding the {@code reader}.
     */
    public static String getAddCommand(Reader reader) {
        return AddReaderCommand.COMMAND_WORD + " " + getReaderDetails(reader);
    }

    /**
     * Returns the part of command string for the given {@code reader}'s details.
     */
    public static String getReaderDetails(Reader reader) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_READER + reader.getName().fullName + " ");
        sb.append(PREFIX_PHONE + reader.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + reader.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + reader.getAddress().value + " ");
        reader.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditReaderDescriptor}'s details.
     */
    public static String getEditReaderDescriptorDetails(EditReaderDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_READER).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
