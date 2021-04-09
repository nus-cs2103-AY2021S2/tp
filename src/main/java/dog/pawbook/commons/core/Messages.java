package dog.pawbook.commons.core;

import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.program.Program;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_UNKNOWN_ENTITY = "Unknown entity, supported entities include: dog/owner/program";

    public static final String MESSAGE_DUPLICATE_ENTITY_FORMAT = "This %s already exists in Pawbook.";
    public static final String MESSAGE_DUPLICATE_OWNER = String.format(MESSAGE_DUPLICATE_ENTITY_FORMAT,
            Owner.ENTITY_WORD);
    public static final String MESSAGE_DUPLICATE_DOG = String.format(MESSAGE_DUPLICATE_ENTITY_FORMAT,
            Dog.ENTITY_WORD);
    public static final String MESSAGE_DUPLICATE_PROGRAM = String.format(MESSAGE_DUPLICATE_ENTITY_FORMAT,
            Program.ENTITY_WORD);

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    public static final String MESSAGE_INVALID_ID_GENERAL =
            String.format("ID must be a positive integer not exceeding %s!", Integer.MAX_VALUE);
    public static final String MESSAGE_INVALID_ID_FORMAT = "The %s ID provided is invalid.";
    public static final String MESSAGE_INVALID_ENTITY_ID = String.format(MESSAGE_INVALID_ID_FORMAT, "entity");
    public static final String MESSAGE_INVALID_OWNER_ID = String.format(MESSAGE_INVALID_ID_FORMAT, "owner");
    public static final String MESSAGE_INVALID_DOG_ID = String.format(MESSAGE_INVALID_ID_FORMAT, "dog");
    public static final String MESSAGE_INVALID_PROGRAM_ID = String.format(MESSAGE_INVALID_ID_FORMAT, "program");

    public static final String MESSAGE_INVALID_ID_MULTIPLE_FORMAT = "One or more of the %s ID(s) provided are invalid!";
    public static final String MESSAGE_INVALID_DOG_ID_MULTIPLE_FORMAT = String.format(
            MESSAGE_INVALID_ID_MULTIPLE_FORMAT, "dog");
    public static final String MESSAGE_INVALID_PROGRAM_ID_MULTIPLE_FORMAT = String.format(
            MESSAGE_INVALID_ID_MULTIPLE_FORMAT, "program");

    public static final String MESSAGE_ID_MISMATCH_FORMAT = "The ID provided does not belong to a %s.";

    public static final String MESSAGE_ENTITIES_LISTED_OVERVIEW_FOR_ONE = "1 entity listed!";
    public static final String MESSAGE_ENTITIES_LISTED_OVERVIEW = "%1$d entities listed!";
    public static final String MESSAGE_DOG_MISSING_OWNER_ID = "Dog to be added is missing owner ID.";

}
