package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.booking.logic.commands.AddPersonCommand;
import seedu.booking.logic.commands.AddVenueCommand;
import seedu.booking.logic.commands.ClearCommand;
import seedu.booking.logic.commands.Command;
import seedu.booking.logic.commands.DeleteBookingCommand;
import seedu.booking.logic.commands.DeleteCommand;
import seedu.booking.logic.commands.DeletePersonCommand;
import seedu.booking.logic.commands.DeleteVenueCommand;
import seedu.booking.logic.commands.EditBookingCommand;
import seedu.booking.logic.commands.EditCommand;
import seedu.booking.logic.commands.EditPersonCommand;
import seedu.booking.logic.commands.EditVenueCommand;
import seedu.booking.logic.commands.ExitCommand;
import seedu.booking.logic.commands.FilterBookingByBookerCommand;
import seedu.booking.logic.commands.FilterBookingByDateCommand;
import seedu.booking.logic.commands.FilterBookingByTagCommand;
import seedu.booking.logic.commands.FilterBookingByVenueCommand;
import seedu.booking.logic.commands.FindBookingCommand;
import seedu.booking.logic.commands.FindCommand;
import seedu.booking.logic.commands.FindPersonByTagCommand;
import seedu.booking.logic.commands.FindPersonCommand;
import seedu.booking.logic.commands.FindVenueByTagCommand;
import seedu.booking.logic.commands.FindVenueCommand;
import seedu.booking.logic.commands.HelpCommand;
import seedu.booking.logic.commands.ListBookingCommand;
import seedu.booking.logic.commands.ListPersonCommand;
import seedu.booking.logic.commands.ListVenueCommand;
import seedu.booking.logic.commands.PromptAddBookingCommand;
import seedu.booking.logic.commands.PromptAddPersonCommand;
import seedu.booking.logic.commands.PromptAddVenueCommand;
import seedu.booking.logic.commands.PromptExitCommand;
import seedu.booking.logic.commands.states.AddBookingCommandState;
import seedu.booking.logic.commands.states.AddPersonCommandState;
import seedu.booking.logic.commands.states.AddVenueCommandState;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.logic.parser.promptparsers.BookingDescPromptParser;
import seedu.booking.logic.parser.promptparsers.BookingEndPromptParser;
import seedu.booking.logic.parser.promptparsers.BookingStartPromptParser;
import seedu.booking.logic.parser.promptparsers.BookingTagPromptParser;
import seedu.booking.logic.parser.promptparsers.EmailPromptParser;
import seedu.booking.logic.parser.promptparsers.PromptAddPersonCommandParser;
import seedu.booking.logic.parser.promptparsers.PromptAddVenueCommandParser;
import seedu.booking.logic.parser.promptparsers.VenueNamePromptParser;
import seedu.booking.model.ModelManager;

/**
 * Parses user input.
 */
public class BookingSystemParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");


    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {

        if (ModelManager.isStateActive()) {

            if (userInput.equals(PromptExitCommand.COMMAND_WORD)) {
                return new PromptExitCommand();
            } else {
                String currentState = ModelManager.getState();
                switch (currentState) {

                case AddBookingCommandState.STATE_EMAIL:
                    return new EmailPromptParser().parse(userInput);

                case AddBookingCommandState.STATE_VENUE:
                    return new VenueNamePromptParser().parse(userInput);

                case AddBookingCommandState.STATE_DESC:
                    return new BookingDescPromptParser().parse(userInput);

                case AddBookingCommandState.STATE_TAG:
                    return new BookingTagPromptParser().parse(userInput);

                case AddBookingCommandState.STATE_START:
                    return new BookingStartPromptParser().parse(userInput);

                case AddBookingCommandState.STATE_END:
                    return new BookingEndPromptParser().parse(userInput);

                case AddVenueCommandState.STATE_CAPACITY:
                    return new PromptAddVenueCommandParser().parseCapacity(userInput);

                case AddVenueCommandState.STATE_DESC:
                    return new PromptAddVenueCommandParser().parseDescription(userInput);

                case AddVenueCommandState.STATE_TAG:
                    return new PromptAddVenueCommandParser().parseTags(userInput);

                case AddPersonCommandState.STATE_EMAIL:
                    return new PromptAddPersonCommandParser().parseEmail(userInput);

                case AddPersonCommandState.STATE_NAME:
                    return new PromptAddPersonCommandParser().parseName(userInput);

                case AddPersonCommandState.STATE_PHONE:
                    return new PromptAddPersonCommandParser().parsePhone(userInput);

                case AddPersonCommandState.STATE_TAG:
                    return new PromptAddPersonCommandParser().parseTags(userInput);

                default:
                    throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
                }
            }
        }

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case EditBookingCommand.COMMAND_WORD:
        return new EditBookingCommandParser().parse(arguments);

        case EditPersonCommand.COMMAND_WORD:
            return new EditPersonCommandParser().parse(arguments);

        case EditVenueCommand.COMMAND_WORD:
            return new EditVenueCommandParser().parse(arguments);

        case DeletePersonCommand.COMMAND_WORD:
            return new DeletePersonCommandParser().parse(arguments);

        case DeleteVenueCommand.COMMAND_WORD:
            return new DeleteVenueCommandParser().parse(arguments);

        case DeleteBookingCommand.COMMAND_WORD:
            return new DeleteBookingCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindVenueCommand.COMMAND_WORD:
            return new FindVenueCommandParser().parse(arguments);

        case FindVenueByTagCommand.COMMAND_WORD:
            return new FindVenueByTagCommandParser().parse(arguments);

        case FindBookingCommand.COMMAND_WORD:
            return new FindBookingCommandParser().parse(arguments);

        case FindPersonCommand.COMMAND_WORD:
            return new FindPersonCommandParser().parse(arguments);

        case FindPersonByTagCommand.COMMAND_WORD:
            return new FindPersonByTagCommandParser().parse(arguments);

        case ListPersonCommand.COMMAND_WORD:
            return new ListPersonCommand();

        case ListVenueCommand.COMMAND_WORD:
            return new ListVenueCommand();

        case ListBookingCommand.COMMAND_WORD:
            return new ListBookingCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case FilterBookingByVenueCommand.COMMAND_WORD:
            return new FilterBookingByVenueCommandParser().parse(arguments);

        case FilterBookingByBookerCommand.COMMAND_WORD:
            return new FilterBookingByBookerCommandParser().parse(arguments);

        case FilterBookingByDateCommand.COMMAND_WORD:
            return new FilterBookingByDateCommandParser().parse(arguments);

        case FilterBookingByTagCommand.COMMAND_WORD:
            return new FilterBookingByTagCommandParser().parse(arguments);

        case PromptAddBookingCommand.COMMAND_WORD:
            return new PromptAddBookingCommand();

        case PromptAddVenueCommand.COMMAND_WORD:
            return new PromptAddVenueCommandParser().parse(arguments);

        case PromptAddPersonCommand.COMMAND_WORD:
            return new PromptAddPersonCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
