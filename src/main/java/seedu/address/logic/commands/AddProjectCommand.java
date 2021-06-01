package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ViewProjectAndOverviewUiCommand;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

/**
 * Adds a project to the CoLAB.
 */
public class AddProjectCommand extends Command {

    public static final String COMMAND_WORD = "addP";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a project to CoLAB.\n"
            + "Parameters: "
            + PREFIX_NAME + "PROJECT_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2102 Group Project";

    public static final String MESSAGE_SUCCESS = "New project added: %1$s";
    public static final String MESSAGE_DUPLICATE_PROJECT = "This project already exists in CoLAB";

    private final Project toAdd;

    /**
     * Creates an AddProjectCommand to add the specified {@code Project}.
     */
    public AddProjectCommand(Project project) {
        requireNonNull(project);
        toAdd = project;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProject(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROJECT);
        }

        Index index = Index.fromZeroBased(model.getFilteredProjectList().size());

        model.addProject(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), new ViewProjectAndOverviewUiCommand(index));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddProjectCommand // instanceof handles nulls
                && toAdd.equals(((AddProjectCommand) other).toAdd));
    }
}
