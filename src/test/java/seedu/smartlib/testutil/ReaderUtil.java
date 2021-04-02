package seedu.smartlib.testutil;

import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_READER;
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
     *
     * @param reader reader to be added.
     * @return an add command string for adding the reader.
     */
    public static String getAddCommand(Reader reader) {
        return AddReaderCommand.COMMAND_WORD + " " + getReaderDetails(reader);
    }

    /**
     * Returns the part of command string for the given {@code reader}'s details.
     *
     * @param reader reader to be examined.
     * @return details of the reader that is examined.
     */
    public static String getReaderDetails(Reader reader) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_READER + reader.getName().toString() + " ");
        sb.append(PREFIX_PHONE + reader.getPhone().toString() + " ");
        sb.append(PREFIX_EMAIL + reader.getEmail().toString() + " ");
        sb.append(PREFIX_ADDRESS + reader.getAddress().toString() + " ");
        reader.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.getTagName() + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditReaderDescriptor}'s details.
     *
     * @param descriptor edit reader descriptor to be examined.
     * @return command string for the given EditReaderDescriptor's details.
     */
    public static String getEditReaderDescriptorDetails(EditReaderDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_READER).append(name.toString()).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.toString()).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.toString()).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.toString()).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.getTagName()).append(" "));
            }
        }
        return sb.toString();
    }

}
