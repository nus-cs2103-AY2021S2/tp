package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddFoodIntakeCommand;
import seedu.address.logic.commands.AddFoodItemCommand;
import seedu.address.logic.commands.AddUserCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteFoodIntakeCommand;
import seedu.address.logic.commands.DeleteFoodItemCommand;
import seedu.address.logic.commands.EditUserCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListDietPlansCommand;
import seedu.address.logic.commands.ListFoodItemCommand;
import seedu.address.logic.commands.ListUserCommand;
import seedu.address.logic.commands.QueryFoodIntakeCommand;
import seedu.address.logic.commands.RecommendPlanCommand;
import seedu.address.logic.commands.RunProgressCalculatorCommand;
import seedu.address.logic.commands.SetActiveDietCommand;
import seedu.address.logic.commands.UpdateFoodIntakeCommand;
import seedu.address.logic.commands.UpdateFoodItemCommand;
import seedu.address.logic.commands.ViewActiveDietCommand;
import seedu.address.logic.commands.ViewPlanCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Useditial separation of command word and args.
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

        case AddFoodItemCommand.COMMAND_WORD:
            return new AddFoodItemCommandParser().parse(arguments);

        case UpdateFoodItemCommand.COMMAND_WORD:
            return new UpdateFoodItemCommandParser().parse(arguments);

        case DeleteFoodItemCommand.COMMAND_WORD:
            return new DeleteFoodItemCommandParser().parse(arguments);

        case ListFoodItemCommand.COMMAND_WORD:
            return new ListFoodItemCommand();

        case EditUserCommand.COMMAND_WORD:
            return new EditUserCommandParser().parse(arguments);

        case AddUserCommand.COMMAND_WORD:
            return new AddUserCommandParser().parse(arguments);

        case ListUserCommand.COMMAND_WORD:
            return new ListUserCommand();

        case AddFoodIntakeCommand.COMMAND_WORD:
            return new AddFoodIntakeCommandParser().parse(arguments);

        case UpdateFoodIntakeCommand.COMMAND_WORD:
            return new UpdateFoodIntakeCommandParser().parse(arguments);

        case DeleteFoodIntakeCommand.COMMAND_WORD:
            return new DeleteFoodIntakeCommandParser().parse(arguments);

        case QueryFoodIntakeCommand.COMMAND_WORD:
            return new QueryFoodIntakeCommandParser().parse(arguments);

        case ListDietPlansCommand.COMMAND_WORD:
            return new ListDietPlansCommand();

        case ViewPlanCommand.COMMAND_WORD:
            return new ViewPlanCommandParser().parse(arguments);

        case SetActiveDietCommand.COMMAND_WORD:
            return new SetActiveDietCommandParser().parse(arguments);

        case ViewActiveDietCommand.COMMAND_WORD:
            return new ViewActiveDietCommand();

        case RecommendPlanCommand.COMMAND_WORD:
            return new RecommendPlanCommand();

        case RunProgressCalculatorCommand.COMMAND_WORD:
            return new RunProgressCalculatorCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
