package dog.pawbook.logic.commands;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOGID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_PROGRAMID;

public class DropCommand extends ProgramCommand {
    public static final String COMMAND_WORD = "drop";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Drops a dog from a program."
            + "Parameters: "
            + PREFIX_DOGID + "DOG_ID "
            + PREFIX_PROGRAMID + "PROGRAM_ID...\n"
            + "Example: " + COMMAND_WORD + " d/2 p/3";

    public static final String MESSAGE_SUCCESS_FORMAT = "Dog %s has been dropped from program %s!";

    public static final String MESSAGE_FAILURE_FORMAT = "Dog cannot be dropped! Dog %s was not enrolled in program %s.";

    private final int dogId;

    private final int programId;

    /**
     * Constructor for Drop command to remove the specified dog from the specified program.
     * @param dogId Id of the dog.
     * @param programId Id of the program.
     */
    public DropCommand(int dogId, int programId) {
        this.dogId = dogId;
        this.programId = programId;
    }

    @Override
    protected int getDogId() {
        return this.dogId;
    }

    @Override
    protected int getProgramId() {
        return this.programId;
    }

    @Override
    protected String getSuccessMessage() {
        return String.format(MESSAGE_SUCCESS_FORMAT, dogId, programId);
    }

    @Override
    protected String getDuplicateMessage() {
        return String.format(MESSAGE_FAILURE_FORMAT, dogId, programId);
    }

    @Override
    protected String getProgramCommand() {
        return COMMAND_WORD;
    }
}
