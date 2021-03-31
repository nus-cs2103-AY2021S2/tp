package seedu.booking.logic.parser.promptparsers;

import java.util.HashSet;
import java.util.Set;

import seedu.booking.logic.commands.PromptBookingTagsCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.Tag;

public class BookingTagPromptParser {

    /**
     * Parses user input for booking tags
     */
    public PromptBookingTagsCommand parse(String args) throws ParseException {
        final Set<Tag> tagSet = new HashSet<>();

        String[] tags = args.split(",");
        for (String tag : tags) {
            if (!Tag.isValidTagName(tag.trim())) {
                throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
            }
            tagSet.add(new Tag(tag.trim()));
        }

        return new PromptBookingTagsCommand(tagSet);
    }
}
