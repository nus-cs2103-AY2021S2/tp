package dog.pawbook.logic.commands;

import static dog.pawbook.commons.core.Messages.MESSAGE_NOT_DOG;
import static dog.pawbook.commons.core.Messages.MESSAGE_NOT_DOG_AND_PROGRAM;
import static dog.pawbook.commons.core.Messages.MESSAGE_NOT_PROGRAM;
import static dog.pawbook.commons.core.Messages.MESSAGE_NO_SUCH_DOG_AND_PROGRAM_ID;
import static dog.pawbook.commons.core.Messages.MESSAGE_NO_SUCH_DOG_ID;
import static dog.pawbook.commons.core.Messages.MESSAGE_NO_SUCH_PROGRAM_ID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOGID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_PROGRAMID;
import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.program.Program;

public class DropCommand extends Command {

    public static final String COMMAND_WORD = "drop";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Drops a dog from a program."
            + "Parameters: "
            + PREFIX_DOGID + "DOG_ID "
            + PREFIX_PROGRAMID + "PROGRAM_ID...\n"
            + "Example: " + COMMAND_WORD + " d/2 p/3";

    public static final String MESSAGE_SUCCESS_FORMAT = "Dog %s has been dropped from program %s!";

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
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        boolean isValidDogId = model.hasEntity(dogId);
        boolean isValidProgramId = model.hasEntity(programId);

        if (!isValidDogId && !isValidProgramId) {
            throw new CommandException(MESSAGE_NO_SUCH_DOG_AND_PROGRAM_ID);
        } else if (!isValidDogId && isValidProgramId) {
            throw new CommandException(MESSAGE_NO_SUCH_DOG_ID);
        } else if (isValidDogId && !isValidProgramId) {
            throw new CommandException(MESSAGE_NO_SUCH_PROGRAM_ID);
        }

        boolean isDog = model.getEntity(dogId) instanceof Dog;
        boolean isProgram = model.getEntity(programId) instanceof Program;

        if (isDog && !isProgram) {
            throw new CommandException(MESSAGE_NOT_PROGRAM);
        } else if (!isDog && isProgram) {
            throw new CommandException(MESSAGE_NOT_DOG);
        } else if (!isDog && !isProgram) {
            throw new CommandException(MESSAGE_NOT_DOG_AND_PROGRAM);
        }

        Program targetProgram = (Program) model.getEntity(programId);

        Set<Integer> editedDogIdSet = new HashSet<>(targetProgram.getDogIdSet());
        editedDogIdSet.remove(dogId);

        Program editedProgram = new Program(targetProgram.getName(), targetProgram.getSessionSet(),
                targetProgram.getTags(), editedDogIdSet);

        model.setEntity(programId, editedProgram);

        return new CommandResult(String.format(MESSAGE_SUCCESS_FORMAT, dogId, programId));
    }
}
