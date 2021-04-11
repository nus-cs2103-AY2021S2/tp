package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.booking.logic.StatefulLogicManager;
import seedu.booking.logic.commands.AddPersonCommand;
import seedu.booking.logic.commands.ClearCommand;
import seedu.booking.logic.commands.Command;
import seedu.booking.logic.commands.DeleteBookingCommand;
import seedu.booking.logic.commands.DeletePersonCommand;
import seedu.booking.logic.commands.DeleteVenueCommand;
import seedu.booking.logic.commands.EditBookingCommand;
import seedu.booking.logic.commands.EditPersonCommand;
import seedu.booking.logic.commands.EditVenueCommand;
import seedu.booking.logic.commands.ExitCommand;
import seedu.booking.logic.commands.FindBookingCommand;
import seedu.booking.logic.commands.FindPersonCommand;
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
import seedu.booking.logic.parser.promptparsers.PromptBookingDescParser;
import seedu.booking.logic.parser.promptparsers.PromptBookingEmailParser;
import seedu.booking.logic.parser.promptparsers.PromptBookingEndParser;
import seedu.booking.logic.parser.promptparsers.PromptBookingStartParser;
import seedu.booking.logic.parser.promptparsers.PromptBookingTagsParser;
import seedu.booking.logic.parser.promptparsers.PromptBookingVenueParser;
import seedu.booking.logic.parser.promptparsers.PromptEmailParser;
import seedu.booking.logic.parser.promptparsers.PromptPersonEmailParser;
import seedu.booking.logic.parser.promptparsers.PromptPersonNameParser;
import seedu.booking.logic.parser.promptparsers.PromptPersonPhoneParser;
import seedu.booking.logic.parser.promptparsers.PromptPersonTagsParser;
import seedu.booking.logic.parser.promptparsers.PromptVenueCapacityParser;
import seedu.booking.logic.parser.promptparsers.PromptVenueDescParser;
import seedu.booking.logic.parser.promptparsers.PromptVenueNameParser;
import seedu.booking.logic.parser.promptparsers.PromptVenueTagsParser;

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

        if (StatefulLogicManager.isStateActive()) {

            if (userInput.equals(PromptExitCommand.COMMAND_WORD)) {
                return new PromptExitCommand();
            } else {
                String currentState = StatefulLogicManager.getState();
                switch (currentState) {
                /* booking related states */
                case AddBookingCommandState.STATE_EMAIL:
                    return new PromptEmailParser().parse(userInput);

                case AddBookingCommandState.STATE_VENUE:
                    return new PromptBookingVenueParser().parse(userInput);

                case AddBookingCommandState.STATE_DESC:
                    return new PromptBookingDescParser().parse(userInput);

                case AddBookingCommandState.STATE_TAG:
                    return new PromptBookingTagsParser().parse(userInput);

                case AddBookingCommandState.STATE_START:
                    return new PromptBookingStartParser().parse(userInput);

                case AddBookingCommandState.STATE_END:
                    return new PromptBookingEndParser().parse(userInput);

                /* venue related states */
                case AddVenueCommandState.STATE_CAPACITY:
                    return new PromptVenueCapacityParser().parse(userInput);

                case AddVenueCommandState.STATE_DESC:
                    return new PromptVenueDescParser().parse(userInput);

                case AddVenueCommandState.STATE_TAG:
                    return new PromptVenueTagsParser().parse(userInput);

                /* person related states */
                case AddPersonCommandState.STATE_EMAIL:
                    return new PromptPersonEmailParser().parse(userInput);

                case AddPersonCommandState.STATE_PHONE:
                    return new PromptPersonPhoneParser().parse(userInput);

                case AddPersonCommandState.STATE_TAG:
                    return new PromptPersonTagsParser().parse(userInput);

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

        case PromptAddBookingCommand.COMMAND_WORD:
            return new PromptBookingEmailParser().parse(arguments);

        case PromptAddVenueCommand.COMMAND_WORD:
            return new PromptVenueNameParser().parse(arguments);

        case PromptAddPersonCommand.COMMAND_WORD:
            return new PromptPersonNameParser().parse(arguments);

        case AddPersonCommand.COMMAND_WORD:
            return new AddPersonCommandParser().parse(arguments);

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

        case FindBookingCommand.COMMAND_WORD:
            return new FindBookingCommandParser().parse(arguments);

        case FindVenueCommand.COMMAND_WORD:
            return new FindVenueCommandParser().parse(arguments);

        case FindPersonCommand.COMMAND_WORD:
            return new FindPersonCommandParser().parse(arguments);

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

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
