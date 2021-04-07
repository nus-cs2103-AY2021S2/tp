package dog.pawbook.logic.commands;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOGID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_PROGRAMID;

import java.util.Set;

//@@author shaelynl
public class DropCommand extends ProgramRegistrationCommand {
    public static final String COMMAND_WORD = "drop";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Drops a dog from a program."
            + " Parameters: "
            + PREFIX_DOGID + "DOG_ID "
            + PREFIX_PROGRAMID + "PROGRAM_ID...\n"
            + "Example: " + COMMAND_WORD + " d/2 p/3";

    public static final String MESSAGE_SUCCESS_FORMAT = "Dog %s has been dropped from program %s!";

    public static final String MESSAGE_NOT_ENROLLED = "Dog was not enrolled in this program.";

    public static final String MESSAGE_NOT_ENROLLED_MULTIPLE_DOGS = "One or more dogs were not "
            + "enrolled in this program.";

    public static final String MESSAGE_NOT_ENROLLED_MULTIPLE_PROGRAMS = "Dog was not enrolled in one or more programs.";
    /**
     * Constructor for Drop command to remove the specified dog from the specified program.
     * @param dogIdSet Id of the dogs.
     * @param programIdSet Id of the programs.
     */
    public DropCommand(Set<Integer> dogIdSet, Set<Integer> programIdSet) {
        super(dogIdSet, programIdSet, false);
    }

    @Override
    protected String getSuccessMessageFormat() {
        return MESSAGE_SUCCESS_FORMAT;
    }

    @Override
    protected String getFailureMessage() {
        return MESSAGE_NOT_ENROLLED;
    }

    @Override
    protected String getFailureMessageMultipleDogs() {
        return MESSAGE_NOT_ENROLLED_MULTIPLE_DOGS;
    }

    @Override
    protected String getFailureMessageMultiplePrograms() {
        return MESSAGE_NOT_ENROLLED_MULTIPLE_PROGRAMS;
    }

}
