package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.dictionote.logic.commands.AddContactCommand;
import seedu.dictionote.logic.commands.AddContentCommand;
import seedu.dictionote.logic.commands.AddDefinitionCommand;
import seedu.dictionote.logic.commands.AddNoteCommand;
import seedu.dictionote.logic.commands.ClearCommand;
import seedu.dictionote.logic.commands.CloseCommand;
import seedu.dictionote.logic.commands.Command;
import seedu.dictionote.logic.commands.DeleteContactCommand;
import seedu.dictionote.logic.commands.DeleteNoteCommand;
import seedu.dictionote.logic.commands.EditContactCommand;
import seedu.dictionote.logic.commands.EditModeQuitCommand;
import seedu.dictionote.logic.commands.EditModeNoteCommand;
import seedu.dictionote.logic.commands.EditNoteCommand;
import seedu.dictionote.logic.commands.EmailContactCommand;
import seedu.dictionote.logic.commands.ExitCommand;
import seedu.dictionote.logic.commands.FindContactCommand;
import seedu.dictionote.logic.commands.FindContentCommand;
import seedu.dictionote.logic.commands.FindDefinitionCommand;
import seedu.dictionote.logic.commands.HelpCommand;
import seedu.dictionote.logic.commands.ListCommandCommand;
import seedu.dictionote.logic.commands.ListContactCommand;
import seedu.dictionote.logic.commands.ListContentCommand;
import seedu.dictionote.logic.commands.ListNoteCommand;
import seedu.dictionote.logic.commands.MarkAsDoneNoteCommand;
import seedu.dictionote.logic.commands.OpenCommand;
import seedu.dictionote.logic.commands.ShowDictionaryContentCommand;
import seedu.dictionote.logic.commands.ShowNoteCommand;
import seedu.dictionote.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class DictionoteParser {

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

        case AddContactCommand.COMMAND_WORD:
            return new AddContactCommandParser().parse(arguments);

        case AddNoteCommand.COMMAND_WORD:
            return new AddNoteCommandParser().parse(arguments);

        case AddContentCommand.COMMAND_WORD:
            return new AddContentCommandParser().parse(arguments);

        case AddDefinitionCommand.COMMAND_WORD:
            return new AddDefinitionCommandParser().parse(arguments);

        case EditContactCommand.COMMAND_WORD:
            return new EditContactCommandParser().parse(arguments);

        case EditNoteCommand.COMMAND_WORD:
            return new EditNoteCommandParser().parse(arguments);


        case EditModeNoteCommand.COMMAND_WORD:
            return new EditModeNoteCommand();

        case EditModeQuitCommand.COMMAND_WORD:
            return new EditModeQuitCommand();

        case DeleteContactCommand.COMMAND_WORD:
            return new DeleteContactCommandParser().parse(arguments);

        case DeleteNoteCommand.COMMAND_WORD:
            return new DeleteNoteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindContactCommand.COMMAND_WORD:
            return new FindContactCommandParser().parse(arguments);

        case FindContentCommand.COMMAND_WORD:
            return new FindContentCommandParser().parse(arguments);

        case FindDefinitionCommand.COMMAND_WORD:
            return new FindDefinitionCommandParser().parse(arguments);

        case ListContactCommand.COMMAND_WORD:
            return new ListContactCommand();

        case EmailContactCommand.COMMAND_WORD:
            return new EmailContactCommandParser().parse(arguments);

        case ListNoteCommand.COMMAND_WORD:
            return new ListNoteCommand();

        case ListContentCommand.COMMAND_WORD:
            return new ListContentCommand();

        case MarkAsDoneNoteCommand.COMMAND_WORD:
            return new MarkAsDoneNoteCommandParser().parse(arguments);

        case ShowNoteCommand.COMMAND_WORD:
            return new ShowNoteCommandParser().parse(arguments);

        case ShowDictionaryContentCommand.COMMAND_WORD:
            return new ShowDictionaryContentCommandParser().parse(arguments);

        case OpenCommand.COMMAND_WORD:
            return new OpenCommandParser().parse(arguments);

        case CloseCommand.COMMAND_WORD:
            return new CloseCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ListCommandCommand.COMMAND_WORD:
            return new ListCommandCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
