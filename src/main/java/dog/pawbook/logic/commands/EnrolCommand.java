package dog.pawbook.logic.commands;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOGID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_PROGRAMID;

public class EnrolCommand extends ProgramCommand {
    public static final String COMMAND_WORD = "enrol";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Enrols a dog into a program."
            + "Parameters: "
            + PREFIX_DOGID + "DOG_ID "
            + PREFIX_PROGRAMID + "PROGRAM_ID...\n"
            + "Example: " + COMMAND_WORD + " d/2 p/3";

    public static final String MESSAGE_SUCCESS_FORMAT = "Dog %s enrolled in program %s!";

    public static final String MESSAGE_ALREADY_ENROLLED_FORMAT = "Dog %s has already been enrolled in program %s!";

    private final int dogId;

    private final int programId;

    /**
     * Constructor for Enrol command to add the specified dog into the specified program.
     * @param dogId Id of the dog.
     * @param programId Id of the program.
     */
    public EnrolCommand(int dogId, int programId) {
        this.dogId = dogId;
        this.programId = programId;
    }

    protected int getDogId() {
        return this.dogId;
    }

    protected int getProgramId() {
        return this.programId;
    }

    @Override
    protected String getProgramCommand() {
        return COMMAND_WORD;
    }

    @Override
    protected String getSuccessMessage() {
        return String.format(MESSAGE_SUCCESS_FORMAT, dogId, programId);
    }

    @Override
    protected String getDuplicateMessage() {
        return String.format(MESSAGE_ALREADY_ENROLLED_FORMAT, dogId, programId);
    }
}
