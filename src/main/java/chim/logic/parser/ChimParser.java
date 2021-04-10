package chim.logic.parser;

import static chim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static chim.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chim.logic.commands.AddCheeseCommand;
import chim.logic.commands.AddCustomerCommand;
import chim.logic.commands.AddOrderCommand;
import chim.logic.commands.ClearCommand;
import chim.logic.commands.Command;
import chim.logic.commands.DeleteCheeseCommand;
import chim.logic.commands.DeleteCustomerCommand;
import chim.logic.commands.DeleteOrderCommand;
import chim.logic.commands.DoneCommand;
import chim.logic.commands.EditCheeseCommand;
import chim.logic.commands.EditCustomerCommand;
import chim.logic.commands.EditOrderCommand;
import chim.logic.commands.ExitCommand;
import chim.logic.commands.FindCheeseCommand;
import chim.logic.commands.FindCustomerCommand;
import chim.logic.commands.FindOrderCommand;
import chim.logic.commands.HelpCommand;
import chim.logic.commands.ListCheesesCommand;
import chim.logic.commands.ListCustomersCommand;
import chim.logic.commands.ListOrdersCommand;
import chim.logic.parser.exceptions.ParseException;
import chim.model.Model;

/**
 * Parses user input.
 */
public class ChimParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private final Model model;

    public ChimParser(Model model) {
        this.model = model;
    }

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

        case AddCustomerCommand.COMMAND_WORD:
            return new AddCustomerCommandParser().parse(arguments);

        case AddOrderCommand.COMMAND_WORD:
            return new AddOrderCommandParser().parse(arguments);

        case AddCheeseCommand.COMMAND_WORD:
            return new AddCheeseCommandParser().parse(arguments);

        case EditCustomerCommand.COMMAND_WORD:
            return new EditCustomerCommandParser().parse(arguments);

        case EditCheeseCommand.COMMAND_WORD:
            return new EditCheeseCommandParser().parse(arguments);

        case EditOrderCommand.COMMAND_WORD:
            return new EditOrderCommandParser().parse(arguments);

        case DeleteCustomerCommand.COMMAND_WORD:
            return new DeleteCustomerCommandParser().parse(arguments);

        case DeleteCheeseCommand.COMMAND_WORD:
            return new DeleteCheeseCommandParser().parse(arguments);

        case DeleteOrderCommand.COMMAND_WORD:
            return new DeleteOrderCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCustomerCommand.COMMAND_WORD:
            return new FindCustomerCommandParser().parse(arguments);

        case FindCheeseCommand.COMMAND_WORD:
            return new FindCheeseCommandParser().parse(arguments);

        case FindOrderCommand.COMMAND_WORD:
            return new FindOrderCommandParser(model.getCompleteCustomerList()).parse(arguments);

        case ListCustomersCommand.COMMAND_WORD:
            return new ListCustomersCommand();

        case ListCheesesCommand.COMMAND_WORD:
            return new ListCheesesCommand();

        case ListOrdersCommand.COMMAND_WORD:
            return new ListOrdersCommand();

        case DoneCommand.COMMAND_WORD:
            return new DoneCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
