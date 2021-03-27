package fooddiary.testutil;

import java.util.List;
import java.util.Set;

import fooddiary.logic.commands.AddCommand;
import fooddiary.logic.commands.EditCommand;
import fooddiary.logic.parser.CliSyntax;
import fooddiary.model.entry.Entry;
import fooddiary.model.entry.Review;
import fooddiary.model.tag.TagCategory;
import fooddiary.model.tag.TagSchool;

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
        entry.getReviews().stream().forEach(s -> sb.append(CliSyntax.PREFIX_REVIEW + s.value + " "));
        sb.append(CliSyntax.PREFIX_ADDRESS + entry.getAddress().value + " ");
        entry.getTagCategories().stream().forEach(
            s -> sb.append(CliSyntax.PREFIX_TAG_CATEGORY + s.getTag() + " ")
        );
        entry.getTagSchools().stream().forEach(
            s -> sb.append(CliSyntax.PREFIX_TAG_SCHOOL + s.getTag() + " ")
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
        if (descriptor.getReviews().isPresent()) {
            List<Review> reviews = descriptor.getReviews().get();
            if (reviews.isEmpty()) {
                sb.append(CliSyntax.PREFIX_REVIEW);
            } else {
                reviews.forEach(s -> sb.append(CliSyntax.PREFIX_REVIEW).append(s.value).append(" "));
            }
        }
        descriptor.getAddress().ifPresent(address -> sb.append(CliSyntax.PREFIX_ADDRESS)
                .append(address.value).append(" "));
        if (descriptor.getTagCategories().isPresent()) {
            Set<TagCategory> tags = descriptor.getTagCategories().get();
            if (tags.isEmpty()) {
                sb.append(CliSyntax.PREFIX_TAG_CATEGORY);
            } else {
                tags.forEach(s -> sb.append(CliSyntax.PREFIX_TAG_CATEGORY).append(s.getTag()).append(" "));
            }
        }

        if (descriptor.getTagSchools().isPresent()) {
            Set<TagSchool> tags = descriptor.getTagSchools().get();
            if (tags.isEmpty()) {
                sb.append(CliSyntax.PREFIX_TAG_SCHOOL);
            } else {
                tags.forEach(s -> sb.append(CliSyntax.PREFIX_TAG_SCHOOL).append(s.getTag()).append(" "));
            }
        }

        return sb.toString();
    }
}
