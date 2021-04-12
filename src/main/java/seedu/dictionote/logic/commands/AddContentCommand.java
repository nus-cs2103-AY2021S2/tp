package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_MAINCONTENT;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_WEEK;

import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.dictionary.Content;

/**
 * Adds content to the content list.
 */
public class AddContentCommand extends Command {
    public static final String COMMAND_WORD = "addcontent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds content to the dictionote book. \n\n"
            + "Parameters: \n"
            + PREFIX_WEEK + "TITLE \n"
            + PREFIX_HEADER + "HEADER\n"
            + PREFIX_MAINCONTENT + "MAINCONTENT \n\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_WEEK + "Week 1 "
            + PREFIX_HEADER + "OOP: Class and Objects\n"
            + PREFIX_MAINCONTENT + "Object-Oriented Programming (OOP) is a programming paradigm.\n"
            + "A programming paradigm guides programmers to analyze programming problems, "
            + "and structure programming solutions, in a specific way.";

    public static final String MESSAGE_SUCCESS = "New Content added: %1$s";
    public static final String MESSAGE_DUPLICATE_CONTENT = "This content already exists in the dictionary";

    private final Content toAdd;

    /**
     * Initializes a command to add the given content.
     *
     * @param content
     */
    public AddContentCommand(Content content) {
        requireNonNull(content);
        toAdd = content;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasContent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONTENT);
        }

        model.addContent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddContentCommand // instanceof handles nulls
                && toAdd.equals(((AddContentCommand) other).toAdd));
    }

}
