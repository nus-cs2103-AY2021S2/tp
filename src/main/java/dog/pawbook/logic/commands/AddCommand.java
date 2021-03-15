package dog.pawbook.logic.commands;

import static dog.pawbook.logic.parser.CliSyntax.*;

public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a owner/dog/program to the address book. "
            + "Example for Owner: " + COMMAND_WORD + " " + "owner" + " "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example for Dog: " + COMMAND_WORD + " " + "dog" + " "
            + PREFIX_NAME + "NAME "
            + PREFIX_BREED + "BREED "
            + PREFIX_DATEOFBIRTH + "DATE OF BIRTH "
            + PREFIX_SEX + "SEX "
            + PREFIX_OWNERID + "OWNER ID "
            + "[" + PREFIX_TAG + "TAG]...\n";


    public static final String MESSAGE_SUCCESS_FORMAT = "New %s added: ";
}
