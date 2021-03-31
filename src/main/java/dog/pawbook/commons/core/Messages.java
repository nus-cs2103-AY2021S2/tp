package dog.pawbook.commons.core;

import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.program.Program;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_UNKNOWN_ENTITY = "Unknown entity";
    public static final String MESSAGE_DUPLICATE_ENTITY_FORMAT = "This %s already exists in Pawbook.";
    public static final String MESSAGE_DUPLICATE_OWNER = String.format(MESSAGE_DUPLICATE_ENTITY_FORMAT,
            Owner.ENTITY_WORD);
    public static final String MESSAGE_DUPLICATE_DOG = String.format(MESSAGE_DUPLICATE_ENTITY_FORMAT,
            Dog.ENTITY_WORD);
    public static final String MESSAGE_DUPLICATE_PROGRAM = String.format(MESSAGE_DUPLICATE_ENTITY_FORMAT,
            Program.ENTITY_WORD);
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s.";
    public static final String MESSAGE_INVALID_ENTITY_ID = "The entity ID provided is invalid.";
    public static final String MESSAGE_INVALID_OWNER_ID = "The owner ID provided is invalid.";
    public static final String MESSAGE_INVALID_PROGRAM_ID = "The program ID provided is invalid.";
    public static final String MESSAGE_INVALID_DOG_ID = "The dog ID provided is invalid.";
    public static final String MESSAGE_ENTITIES_LISTED_OVERVIEW = "%1$d entities listed!";
    public static final String MESSAGE_NO_SUCH_DOG_ID = "The dog ID provided does not exist.";
    public static final String MESSAGE_NO_SUCH_PROGRAM_ID = "The program ID provided does not exist.";
    public static final String MESSAGE_NO_SUCH_DOG_AND_PROGRAM_ID = "The dog and program IDs provided do not exist.";
    public static final String MESSAGE_NOT_DOG = "The dog ID provided does not refer to a dog.";
    public static final String MESSAGE_NOT_PROGRAM = "The program ID provided does not refer to a program.";
    public static final String MESSAGE_NOT_DOG_AND_PROGRAM =
            "The dog and program IDs provided do not refer to a dog and a program.";
    public static final String MESSAGE_DOG_MISSING_OWNER_ID = "Dog to be added is missing owner ID.";
}
