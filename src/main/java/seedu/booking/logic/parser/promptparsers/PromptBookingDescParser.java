package seedu.booking.logic.parser.promptparsers;

import seedu.booking.logic.commands.PromptBookingDescCommand;
import seedu.booking.logic.parser.Parser;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.booking.Description;

public class PromptBookingDescParser implements Parser<PromptBookingDescCommand> {

    /**
     * Parses user input for booking description
     */
    public PromptBookingDescCommand parse(String args) throws ParseException {

        if (args.equals("")) {
            args = "No description provided.";
        }

        return new PromptBookingDescCommand(new Description(args));
    }
}
