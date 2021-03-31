package seedu.cakecollate.logic.parser;

import static seedu.cakecollate.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cakecollate.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.cakecollate.logic.commands.AddCommand;
import seedu.cakecollate.logic.commands.ClearCommand;
import seedu.cakecollate.logic.commands.Command;
import seedu.cakecollate.logic.commands.DeleteCommand;
import seedu.cakecollate.logic.commands.DeliveryStatusCommand;
import seedu.cakecollate.logic.commands.EditCommand;
import seedu.cakecollate.logic.commands.ExitCommand;
import seedu.cakecollate.logic.commands.FindCommand;
import seedu.cakecollate.logic.commands.HelpCommand;
import seedu.cakecollate.logic.commands.ListCommand;
import seedu.cakecollate.logic.commands.RemindCommand;
import seedu.cakecollate.logic.commands.RequestCommand;
import seedu.cakecollate.logic.parser.exceptions.ParseException;
import seedu.cakecollate.model.order.DeliveryStatus;
import seedu.cakecollate.model.order.Status;

/**
 * Parses user input.
 */
public class CakeCollateParser {

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
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case RemindCommand.COMMAND_WORD:
            return new RemindCommandParser().parse(arguments);

        case DeliveryStatusCommand.UNDELIVERED_COMMAND_WORD:
            return new DeliveryStatusCommandParser(new DeliveryStatus(Status.UNDELIVERED)).parse(arguments);

        case DeliveryStatusCommand.DELIVERED_COMMAND_WORD:
            return new DeliveryStatusCommandParser(new DeliveryStatus(Status.DELIVERED)).parse(arguments);

        case DeliveryStatusCommand.CANCELLED_COMMAND_WORD:
            return new DeliveryStatusCommandParser(new DeliveryStatus(Status.CANCELLED)).parse(arguments);

        case RequestCommand.COMMAND_WORD:
            return new RequestCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
