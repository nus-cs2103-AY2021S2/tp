package seedu.plan.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.plan.commons.core.Messages;
import seedu.plan.logic.commands.AddModuleCommand;
import seedu.plan.logic.commands.AddPlanCommand;
import seedu.plan.logic.commands.AddSemesterCommand;
import seedu.plan.logic.commands.ClearCommand;
import seedu.plan.logic.commands.Command;
import seedu.plan.logic.commands.CurrentSemesterCommand;
import seedu.plan.logic.commands.DeleteModuleCommand;
import seedu.plan.logic.commands.DeletePlanCommand;
import seedu.plan.logic.commands.DeleteSemesterCommand;
import seedu.plan.logic.commands.ExitCommand;
import seedu.plan.logic.commands.HelpCommand;
import seedu.plan.logic.commands.HistoryCommand;
import seedu.plan.logic.commands.InfoCommand;
import seedu.plan.logic.commands.ListCommand;
import seedu.plan.logic.commands.MasterPlanCommand;
import seedu.plan.logic.commands.ShowCommand;
import seedu.plan.logic.commands.ValidateCommand;
import seedu.plan.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ModulePlannerParser {

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
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case AddModuleCommand.COMMAND_WORD:
            return new AddModuleCommandParser().parse(arguments);

        case AddPlanCommand.COMMAND_WORD:
            return new AddPlanCommandParser().parse(arguments);

        case AddSemesterCommand.COMMAND_WORD:
            return new AddSemesterCommandParser().parse(arguments);

        case DeletePlanCommand.COMMAND_WORD:
            return new DeletePlanCommandParser().parse(arguments);

        case DeleteSemesterCommand.COMMAND_WORD:
            return new DeleteSemesterCommandParser().parse(arguments);

        case DeleteModuleCommand.COMMAND_WORD:
            return new DeleteModuleCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case CurrentSemesterCommand.COMMAND_WORD:
            return new CurrentSemesterCommandParser().parse(arguments);

        case MasterPlanCommand.COMMAND_WORD:
            return new MasterPlanCommandParser().parse(arguments);

        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommandParser().parse(arguments);

        case ShowCommand.COMMAND_WORD:
            return new ShowCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case InfoCommand.COMMAND_WORD:
            return new InfoCommandParser().parse(arguments);

        case ValidateCommand.COMMAND_WORD:
            return new ValidateCommand();

        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
