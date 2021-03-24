package fooddiary.testutil;

import java.util.Set;

import fooddiary.logic.commands.AddCommand;
import fooddiary.logic.commands.EditCommand;
import fooddiary.logic.parser.CliSyntax;
import fooddiary.model.entry.Entry;
import fooddiary.model.tag.Tag;

/**
 * A utility class for Entry.
 */
public class EntryUtil {

    /**
     * Returns an add command string for adding the {@code entry}.
     */
    public static String getAddCommand(Entry entry) {
        return AddCommand.COMMAND_WORD + " " + getEntryDetails(entry);
    }

    /**
     * Returns the part of command string for the given {@code entry}'s details.
     */
    public static String getEntryDetails(Entry entry) {
        StringBuilder sb = new StringBuilder();
        sb.append(CliSyntax.PREFIX_NAME + entry.getName().fullName + " ");
        sb.append(CliSyntax.PREFIX_RATING + entry.getRating().value + " ");
        sb.append(CliSyntax.PREFIX_PRICE + entry.getPrice().value + " ");
        sb.append(CliSyntax.PREFIX_REVIEW + entry.getReview().value + " ");
        sb.append(CliSyntax.PREFIX_ADDRESS + entry.getAddress().value + " ");
        entry.getTags().stream().forEach(
            s -> sb.append(CliSyntax.PREFIX_TAG + s.tagCategory.titleCase() + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditEntryDescriptor}'s details.
     */
    public static String getEditEntryDescriptorDetails(EditCommand.EditEntryDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(CliSyntax.PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getRating().ifPresent(rating -> sb.append(CliSyntax.PREFIX_RATING).append(rating.value).append(" "));
        descriptor.getPrice().ifPresent(price -> sb.append(CliSyntax.PREFIX_PRICE).append(price.value).append(" "));
        descriptor.getReview().ifPresent(review -> sb.append(CliSyntax.PREFIX_REVIEW).append(review.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(CliSyntax.PREFIX_ADDRESS)
                .append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(CliSyntax.PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(CliSyntax.PREFIX_TAG).append(s.tagCategory.titleCase()).append(" "));
            }
        }
        return sb.toString();
    }
}
