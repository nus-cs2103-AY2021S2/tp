package dog.pawbook.logic.commands;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_BREED;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOB;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_NAME;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_OWNERID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_PHONE;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_SESSION;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_SEX;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.program.Program;

public abstract class AddCommand<T extends Entity> extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a owner/dog/program to the address book. "
            + "Example for Owner: " + COMMAND_WORD + " " + Owner.ENTITY_WORD + " "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example for Dog: " + COMMAND_WORD + " " + Dog.ENTITY_WORD + " "
            + PREFIX_NAME + "NAME "
            + PREFIX_BREED + "BREED "
            + PREFIX_DOB + "DATE OF BIRTH "
            + PREFIX_SEX + "SEX "
            + PREFIX_OWNERID + "OWNER ID "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example for Program: " + COMMAND_WORD + " " + Program.ENTITY_WORD + " "
            + PREFIX_NAME + "NAME "
            + PREFIX_SESSION + "SESSION "
            + "[" + PREFIX_TAG + "TAG]...";


    public static final String MESSAGE_SUCCESS_FORMAT = "New %s added: ";

    protected final T toAdd;

    protected AddCommand(T toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    /**
     * Execute the adding of the entity into the model.
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException if entity already exists in the model.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        executeAdd(model);
        return new CommandResult(getSuccessMessage());
    }

    /**
     * Add the new entity into the model.
     * @param model {@code Model} which the command should operate on.
     * @return ID of the newly added entity.
     * @throws CommandException if entity already exists in the model.
     */
    protected int executeAdd(Model model) throws CommandException {
        if (model.hasEntity(toAdd)) {
            throw new CommandException(getDuplicateMessage());
        }

        return model.addEntity(toAdd);
    }

    protected abstract String getSuccessMessage();

    protected abstract String getDuplicateMessage();
}
