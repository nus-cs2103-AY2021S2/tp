package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.Alias;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.alias.AliasCommand;
import seedu.address.logic.commands.commandhistory.ViewHistoryCommand;
import seedu.address.logic.commands.issue.AddIssueCommand;
import seedu.address.logic.commands.issue.DeleteIssueCommand;
import seedu.address.logic.commands.issue.EditIssueCommand;
import seedu.address.logic.commands.issue.FindIssueCommand;
import seedu.address.logic.commands.issue.ListIssueCommand;
import seedu.address.logic.commands.resident.AddResidentCommand;
import seedu.address.logic.commands.resident.DeleteResidentCommand;
import seedu.address.logic.commands.resident.EditResidentCommand;
import seedu.address.logic.commands.resident.FindResidentCommand;
import seedu.address.logic.commands.resident.ListResidentCommand;
import seedu.address.logic.commands.residentroom.AllocateResidentRoomCommand;
import seedu.address.logic.commands.residentroom.DeallocateResidentRoomCommand;
import seedu.address.logic.commands.room.AddRoomCommand;
import seedu.address.logic.commands.room.DeleteRoomCommand;
import seedu.address.logic.commands.room.EditRoomCommand;
import seedu.address.logic.commands.room.FindRoomCommand;
import seedu.address.logic.commands.room.ListRoomCommand;
import seedu.address.logic.parser.commandhistory.ViewHistoryCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.issue.AddIssueCommandParser;
import seedu.address.logic.parser.issue.DeleteIssueCommandParser;
import seedu.address.logic.parser.issue.EditIssueCommandParser;
import seedu.address.logic.parser.issue.FindIssueCommandParser;
import seedu.address.logic.parser.resident.AddResidentCommandParser;
import seedu.address.logic.parser.resident.DeleteResidentCommandParser;
import seedu.address.logic.parser.resident.EditResidentCommandParser;
import seedu.address.logic.parser.resident.FindResidentCommandParser;
import seedu.address.logic.parser.residentroom.AllocateResidentRoomCommandParser;
import seedu.address.logic.parser.residentroom.DeallocateResidentRoomCommandParser;
import seedu.address.logic.parser.room.AddRoomCommandParser;
import seedu.address.logic.parser.room.DeleteRoomCommandParser;
import seedu.address.logic.parser.room.EditRoomCommandParser;
import seedu.address.logic.parser.room.FindRoomCommandParser;
import seedu.address.model.ReadOnlyUserPrefs;

/**
 * Parses user input.
 */
public class AddressBookParser {

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
    public Command parseCommand(String userInput, ReadOnlyUserPrefs readOnlyUserPrefs) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        // ====== Resident Commands ======

        case AddResidentCommand.COMMAND_WORD:
            return new AddResidentCommandParser().parse(arguments);

        case ListResidentCommand.COMMAND_WORD:
            return new ListResidentCommand();

        case FindResidentCommand.COMMAND_WORD:
            return new FindResidentCommandParser().parse(arguments);

        case EditResidentCommand.COMMAND_WORD:
            return new EditResidentCommandParser().parse(arguments);

        case DeleteResidentCommand.COMMAND_WORD:
            return new DeleteResidentCommandParser().parse(arguments);

        // ====== Room Commands ======
        case AddRoomCommand.COMMAND_WORD:
            return new AddRoomCommandParser().parse(arguments);

        case FindRoomCommand.COMMAND_WORD:
            return new FindRoomCommandParser().parse(arguments);

        case ListRoomCommand.COMMAND_WORD:
            return new ListRoomCommand();

        case EditRoomCommand.COMMAND_WORD:
            return new EditRoomCommandParser().parse(arguments);

        case DeleteRoomCommand.COMMAND_WORD:
            return new DeleteRoomCommandParser().parse(arguments);

        // ====== ResidentRoom Commands ======
        case AllocateResidentRoomCommand.COMMAND_WORD:
            return new AllocateResidentRoomCommandParser().parse(arguments);

        case DeallocateResidentRoomCommand.COMMAND_WORD:
            return new DeallocateResidentRoomCommandParser().parse(arguments);

        // ====== Issue Commands ======
        case AddIssueCommand.COMMAND_WORD:
            return new AddIssueCommandParser().parse(arguments);

        case ListIssueCommand.COMMAND_WORD:
            return new ListIssueCommand();

        case FindIssueCommand.COMMAND_WORD:
            return new FindIssueCommandParser().parse(arguments);

        case EditIssueCommand.COMMAND_WORD:
            return new EditIssueCommandParser().parse(arguments);

        case DeleteIssueCommand.COMMAND_WORD:
            return new DeleteIssueCommandParser().parse(arguments);

        // ====== System Commands ======
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AliasCommand.COMMAND_WORD:
            return new AliasCommandParser().parse(arguments);

        case ViewHistoryCommand.COMMAND_WORD:
            return new ViewHistoryCommandParser().parse(arguments);

        default:
            if (readOnlyUserPrefs.containsAlias(commandWord)) {
                Alias alias = readOnlyUserPrefs.getAlias(commandWord);
                return parseCommand(alias.getCommand() + " " + arguments, readOnlyUserPrefs);
            }
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
