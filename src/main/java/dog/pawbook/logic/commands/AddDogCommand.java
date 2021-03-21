package dog.pawbook.logic.commands;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_BREED;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DATEOFBIRTH;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_NAME;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_OWNERID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_SEX;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_TAG;
import static dog.pawbook.model.managedentity.dog.Dog.ENTITY_WORD;

import dog.pawbook.model.managedentity.dog.Dog;

/**
 * Adds a dog to the address book.
 */
public class AddDogCommand extends AddCommand<Dog> {

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
            + PREFIX_OWNERID + "1 "
            + PREFIX_TAG + "playful "
            + PREFIX_TAG + "active";

    public static final String MESSAGE_SUCCESS = String.format(MESSAGE_SUCCESS_FORMAT, ENTITY_WORD);
    public static final String MESSAGE_DUPLICATE_DOG = "This " + ENTITY_WORD + " already exists";

    /**
     * Creates an AddCommand to add the specified {@code Dog}
     */
    public AddDogCommand(Dog dog) {
        super(dog);
    }

    @Override
    protected String getSuccessMessage() {
        return MESSAGE_SUCCESS + toAdd;
    }

    @Override
    protected String getDuplicateMessage() {
        return MESSAGE_DUPLICATE_DOG;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDogCommand // instanceof handles nulls
                && toAdd.equals(((AddDogCommand) other).toAdd));
    }
}
