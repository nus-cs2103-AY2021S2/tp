package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dog.pawbook.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static dog.pawbook.commons.core.Messages.MESSAGE_UNKNOWN_ENTITY;
import static dog.pawbook.model.managedentity.IsEntityPredicate.IS_DOG_PREDICATE;
import static dog.pawbook.model.managedentity.IsEntityPredicate.IS_OWNER_PREDICATE;
import static dog.pawbook.model.managedentity.IsEntityPredicate.IS_PROGRAM_PREDICATE;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dog.pawbook.logic.commands.AddCommand;
import dog.pawbook.logic.commands.Command;
import dog.pawbook.logic.commands.DeleteCommand;
import dog.pawbook.logic.commands.DropCommand;
import dog.pawbook.logic.commands.EditEntityCommand;
import dog.pawbook.logic.commands.EnrolCommand;
import dog.pawbook.logic.commands.ExitCommand;
import dog.pawbook.logic.commands.FindCommand;
import dog.pawbook.logic.commands.HelpCommand;
import dog.pawbook.logic.commands.ListCommand;
import dog.pawbook.logic.commands.ScheduleCommand;
import dog.pawbook.logic.commands.ViewCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.program.Program;
import javafx.util.Pair;

/**
 * Parses user input.
 */
public class PawbookParser {

    /**
     * Used for initial separation of command word, entity type, and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Pattern TYPED_ARGUMENT_FORMAT =
            Pattern.compile("(?<entityType>(?:owner|dog|program)+)(?<arguments>.*)");

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

        // for commands that require entity type to be specified
        final Matcher secondaryMatcher = TYPED_ARGUMENT_FORMAT.matcher(arguments.trim());
        boolean hasEntityType = secondaryMatcher.matches();
        final String entityType = hasEntityType ? secondaryMatcher.group("entityType") : "";
        final String secondaryArguments = hasEntityType ? secondaryMatcher.group("arguments") : arguments;

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return generateAddCommand(entityType, secondaryArguments);

        case EditEntityCommand.COMMAND_WORD:
            return generateEditCommand(entityType, secondaryArguments);

        case DeleteCommand.COMMAND_WORD:
            return generateDeleteCommand(entityType, secondaryArguments);

        case ListCommand.COMMAND_WORD:
            return generateListCommand(entityType, secondaryArguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case EnrolCommand.COMMAND_WORD:
            return new EnrolDropCommandParser(true).parse(arguments);

        case DropCommand.COMMAND_WORD:
            return new EnrolDropCommandParser(false).parse(arguments);

        case ScheduleCommand.COMMAND_WORD:
            return new ScheduleCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Generate an AddCommand according to type of entity.
     */
    private AddCommand<? extends Entity> generateAddCommand(String entityType, String arguments) throws ParseException {
        if (entityType.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        switch (entityType) {
        case Owner.ENTITY_WORD:
            return new AddOwnerCommandParser().parse(arguments);
        case Dog.ENTITY_WORD:
            return new AddDogCommandParser().parse(arguments);
        case Program.ENTITY_WORD:
            return new AddProgramCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_ENTITY);
        }
    }

    private EditEntityCommand generateEditCommand(String entityType, String arguments) throws ParseException {
        if (entityType.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEntityCommand.MESSAGE_USAGE));
        }

        switch (entityType) {
        case Owner.ENTITY_WORD:
            return new EditOwnerCommandParser().parse(arguments);
        case Dog.ENTITY_WORD:
            return new EditDogCommandParser().parse(arguments);
        case Program.ENTITY_WORD:
            return new EditProgramCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_ENTITY);
        }
    }

    /**
     * Generate a DeleteCommand according to type of entity.
     */
    private DeleteCommand<? extends Entity> generateDeleteCommand(String entityType, String arguments)
            throws ParseException {
        if (entityType.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        switch (entityType) {
        case Owner.ENTITY_WORD:
            return new DeleteOwnerCommandParser().parse(arguments);
        case Dog.ENTITY_WORD:
            return new DeleteDogCommandParser().parse(arguments);
        case Program.ENTITY_WORD:
            return new DeleteProgramCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_ENTITY);
        }
    }

    private ListCommand generateListCommand(String entityType, String arguments) throws ParseException {
        if (!arguments.isBlank() && !Pattern.matches("^s*\\s*$", arguments) || entityType.isEmpty()) {
            throw new ParseException(MESSAGE_UNKNOWN_ENTITY);
        }

        Predicate<Pair<Integer, Entity>> predicate;
        switch (entityType) {
        case Owner.ENTITY_WORD:
            predicate = IS_OWNER_PREDICATE;
            break;
        case Dog.ENTITY_WORD:
            predicate = IS_DOG_PREDICATE;
            break;
        case Program.ENTITY_WORD:
            predicate = IS_PROGRAM_PREDICATE;
            break;

        default:
            throw new AssertionError("This entity type is not implemented!");
        }
        return new ListCommand(predicate, entityType);
    }
}
