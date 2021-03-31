package seedu.booking.logic.parser.promptparsers;

import seedu.booking.logic.commands.BookingDescPromptCommand;
import seedu.booking.logic.parser.Parser;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.booking.Description;

public class BookingDescPromptParser implements Parser<BookingDescPromptCommand> {

    /**
     * Parses user input for booking description
     */
    public BookingDescPromptCommand parse(String args) throws ParseException {

        if (args.equals("")) {
            args = "No description provided.";
        }

        return new BookingDescPromptCommand(new Description(args));
    }
}
