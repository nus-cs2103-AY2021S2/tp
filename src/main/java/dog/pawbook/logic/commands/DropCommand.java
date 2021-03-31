package dog.pawbook.logic.commands;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOGID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_PROGRAMID;

import java.util.Set;

public class DropCommand extends ProgramCommand {
    public static final String COMMAND_WORD = "drop";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Drops a dog from a program."
            + "Parameters: "
            + PREFIX_DOGID + "DOG_ID "
            + PREFIX_PROGRAMID + "PROGRAM_ID...\n"
            + "Example: " + COMMAND_WORD + " d/2 p/3";

    public static final String MESSAGE_SUCCESS_FORMAT = "Dog %s has been dropped from program %s!";

    public static final String MESSAGE_FAILURE_FORMAT = "Dog cannot be dropped! Dog %s was not enrolled in program %s.";

    private final Set<Integer> dogIdSet;

    private final Set<Integer> programIdSet;

    /**
     * Constructor for Drop command to remove the specified dog from the specified program.
     * @param dogIdSet Id of the dog.
     * @param programIdSet Id of the program.
     */
    public DropCommand(Set<Integer> dogIdSet, Set<Integer> programIdSet) {
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
    protected String getSuccessMessage() {
        return String.format(MESSAGE_SUCCESS_FORMAT, dogIdSet, programIdSet);
    }

    @Override
    protected String getDuplicateMessage() {
        return String.format(MESSAGE_FAILURE_FORMAT, dogIdSet, programIdSet);
    }

    @Override
    protected String getProgramCommand() {
        return COMMAND_WORD;
    }
}
