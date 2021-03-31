package dog.pawbook.logic.commands;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOGID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_PROGRAMID;

import java.util.Set;

public class EnrolCommand extends ProgramRegistrationCommand {
    public static final String COMMAND_WORD = "enrol";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Enrols a dog into a program."
            + "Parameters: "
            + PREFIX_DOGID + "DOG_ID "
            + PREFIX_PROGRAMID + "PROGRAM_ID...\n"
            + "Example: " + COMMAND_WORD + " d/2 p/3";

    public static final String MESSAGE_SUCCESS_FORMAT = "Dog %s enrolled in program %s!";

    public static final String MESSAGE_REPEATED_ENROLMENT_FORMAT = "One or more dogs has already been enrolled!";

    /**
     * Constructor for Enrol command to add the specified dog into the specified program.
     * @param dogIdSet Id of the dogs.
     * @param programIdSet Id of the programs.
     */
    public EnrolCommand(Set<Integer> dogIdSet, Set<Integer> programIdSet) {
        super(dogIdSet, programIdSet, true);
    }

    @Override
    protected String getSuccessMessageFormat() {
        return MESSAGE_SUCCESS_FORMAT;
    }

    @Override
    protected String getFailureMessage() {
        return MESSAGE_REPEATED_ENROLMENT_FORMAT;
    }
}
