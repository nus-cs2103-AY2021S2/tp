package dog.pawbook.logic.commands;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_BREED;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DATEOFBIRTH;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_NAME;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_OWNERID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_SEX;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.dog.Dog;

/**
 * Adds a dog to the address book.
 */
public class AddDogCommand extends AddCommand {

    public static final String ENTITY_WORD = "dog";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds dog to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_BREED + "BREED "
            + PREFIX_DATEOFBIRTH + "DATE OF BIRTH "
            + PREFIX_SEX + "SEX "
            + PREFIX_OWNERID + "OWNER ID "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + ENTITY_WORD + " "
            + PREFIX_NAME + "Bruce "
            + PREFIX_BREED + "Chihuahua "
            + PREFIX_DATEOFBIRTH + "12-02-2019 "
            + PREFIX_SEX + "Male "
            + PREFIX_TAG + "playful "
            + PREFIX_TAG + "active";

    public static final String MESSAGE_SUCCESS = String.format(MESSAGE_SUCCESS_FORMAT, ENTITY_WORD);
    public static final String MESSAGE_DUPLICATE_DOG = "This dog already exists in the address book";

    private final Dog toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Dog}
     */
    public AddDogCommand(Dog dog) {
        requireNonNull(dog);
        toAdd = dog;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDogCommand // instanceof handles nulls
                && toAdd.equals(((AddDogCommand) other).toAdd));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Work in progress, get GUI to print a response.
        return new CommandResult(AddDogCommand.MESSAGE_SUCCESS + toAdd);
    }
}
