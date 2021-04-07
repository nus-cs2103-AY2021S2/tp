package dog.pawbook.logic.commands;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOGID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_PROGRAMID;

import java.util.Set;

//@@author wei-yutong
public class EnrolCommand extends ProgramRegistrationCommand {
    public static final String COMMAND_WORD = "enrol";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Enrols a dog into a program."
            + " Parameters: "
            + PREFIX_DOGID + "DOG_ID "
            + PREFIX_PROGRAMID + "PROGRAM_ID...\n"
            + "Example: " + COMMAND_WORD + " d/2 p/3";

    public static final String MESSAGE_SUCCESS_FORMAT = "Dog %s enrolled in program %s!";

    public static final String MESSAGE_REPEATED_ENROLMENT = "Dog has already been enrolled in this program!";

    public static final String MESSAGE_REPEATED_ENROLMENT_MULTIPLE_PROGRAMS = "Dog has already been enrolled "
            + "in one or more programs!";

    public static final String MESSAGE_REPEATED_ENROLMENT_MULTIPLE_DOGS = "One or more dogs have already "
            + "been enrolled!";

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
        return MESSAGE_REPEATED_ENROLMENT;
    }

    @Override
    protected String getFailureMessageMultipleDogs() {
        return MESSAGE_REPEATED_ENROLMENT_MULTIPLE_DOGS;
    }

    @Override
    protected String getFailureMessageMultiplePrograms() {
        return MESSAGE_REPEATED_ENROLMENT_MULTIPLE_PROGRAMS;
    }
}
