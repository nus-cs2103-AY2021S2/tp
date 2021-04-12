package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_DEFINITION;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_TERM;

import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.dictionary.Definition;

/**
 * Adds a definition to the definition book.
 */
public class AddDefinitionCommand extends Command {
    public static final String COMMAND_WORD = "adddef";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a definition to the dictionote book. \n\n"
            + "Parameters: "
            + PREFIX_TERM + "TERM "
            + PREFIX_DEFINITION + "DEFINITION \n\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TERM + "OOP "
            + PREFIX_DEFINITION + "A programming paradigm";

    public static final String MESSAGE_SUCCESS = "New definition added: %1$s";
    public static final String MESSAGE_DUPLICATE_DEFINITION = "This definition already exists in the dictionote book";

    private final Definition toAdd;

    /**
     * Initializes a command to add the given definition.
     *
     * @param definition
     */
    public AddDefinitionCommand(Definition definition) {
        requireNonNull(definition);
        toAdd = definition;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasDefinition(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DEFINITION);
        }

        model.addDefinition(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDefinitionCommand // instanceof handles nulls
                && toAdd.equals(((AddDefinitionCommand) other).toAdd));
    }

}
