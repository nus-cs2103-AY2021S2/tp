package dog.pawbook.logic.commands;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOGID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_PROGRAMID;

import java.util.Set;

public class EnrolCommand extends ProgramCommand {
    public static final String COMMAND_WORD = "enrol";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Enrols a dog into a program."
            + "Parameters: "
            + PREFIX_DOGID + "DOG_ID "
            + PREFIX_PROGRAMID + "PROGRAM_ID...\n"
            + "Example: " + COMMAND_WORD + " d/2 p/3";

    public static final String MESSAGE_SUCCESS_FORMAT = "Dog %s enrolled in program %s!";

    public static final String MESSAGE_ALREADY_ENROLLED_FORMAT = "Dog %s has already been enrolled in program %s!";

    private final Set<Integer> dogIdSet;

    private final Set<Integer> programIdSet;

    /**
     * Constructor for Enrol command to add the specified dog into the specified program.
     * @param dogIdSet Id of the dog.
     * @param programIdSet Id of the program.
     */
    public EnrolCommand(Set<Integer> dogIdSet, Set<Integer> programIdSet) {
        this.dogIdSet = dogIdSet;
        this.programIdSet = programIdSet;
    }

    protected Set<Integer> retrieveDogIdSet() {
        return this.dogIdSet;
    }

    protected Set<Integer> retrieveProgramIdSet() {
        return this.programIdSet;
    }

    @Override
    protected String getProgramCommand() {
        return COMMAND_WORD;
    }

    @Override
    protected String getSuccessMessage() {
        return String.format(MESSAGE_SUCCESS_FORMAT, dogIdSet, programIdSet);
    }

    @Override
    protected String getDuplicateMessage() {
        return String.format(MESSAGE_ALREADY_ENROLLED_FORMAT, dogIdSet, programIdSet);
    }
}
