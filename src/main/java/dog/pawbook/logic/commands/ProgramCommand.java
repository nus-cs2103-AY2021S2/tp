package dog.pawbook.logic.commands;

import static dog.pawbook.commons.core.Messages.MESSAGE_NOT_DOG;
import static dog.pawbook.commons.core.Messages.MESSAGE_NOT_DOG_AND_PROGRAM;
import static dog.pawbook.commons.core.Messages.MESSAGE_NOT_PROGRAM;
import static dog.pawbook.commons.core.Messages.MESSAGE_NO_SUCH_DOG_AND_PROGRAM_ID;
import static dog.pawbook.commons.core.Messages.MESSAGE_NO_SUCH_DOG_ID;
import static dog.pawbook.commons.core.Messages.MESSAGE_NO_SUCH_PROGRAM_ID;
import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.program.Program;

public abstract class ProgramCommand extends Command {
    protected abstract int getDogId();
    protected abstract int getProgramId();

    /**
     * Checks whether the IDs entered by the user are valid.
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException if IDs are not valid.
     */
    public void checkIdValidity(Model model) throws CommandException {
        boolean isValidDogId = model.hasEntity(getDogId());
        boolean isValidProgramId = model.hasEntity(getProgramId());

        if (!isValidDogId && !isValidProgramId) {
            throw new CommandException(MESSAGE_NO_SUCH_DOG_AND_PROGRAM_ID);
        } else if (!isValidDogId && isValidProgramId) {
            throw new CommandException(MESSAGE_NO_SUCH_DOG_ID);
        } else if (isValidDogId && !isValidProgramId) {
            throw new CommandException(MESSAGE_NO_SUCH_PROGRAM_ID);
        }

        boolean isDog = model.getEntity(getDogId()) instanceof Dog;
        boolean isProgram = model.getEntity(getProgramId()) instanceof Program;

        if (isDog && !isProgram) {
            throw new CommandException(MESSAGE_NOT_PROGRAM);
        } else if (!isDog && isProgram) {
            throw new CommandException(MESSAGE_NOT_DOG);
        } else if (!isDog && !isProgram) {
            throw new CommandException(MESSAGE_NOT_DOG_AND_PROGRAM);
        }
    }

    /**
     * Handles the specifics of enrol command or drop command.
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException if dog is already enrolled or dropped from the program.
     */
    public void executeProgramCommand(Model model) throws CommandException {
        String programCommand = getProgramCommand();
        Program targetProgram = (Program) model.getEntity(getProgramId());
        Set<Integer> editedDogIdSet = new HashSet<>(targetProgram.getDogIdSet());

        if (programCommand.equals("enrol")) {
            if (targetProgram.getDogIdSet().contains(getDogId())) {
                throw new CommandException(getDuplicateMessage());
            }
            editedDogIdSet.add(getDogId());
        } else {
            if (!targetProgram.getDogIdSet().contains(getDogId())) {
                throw new CommandException(getDuplicateMessage());
            }
            editedDogIdSet.remove(getDogId());
        }

        Program editedProgram = new Program(targetProgram.getName(), targetProgram.getSessions(),
                targetProgram.getTags(), editedDogIdSet);

        model.setEntity(getProgramId(), editedProgram);
    }

    /**
     * Executes enrol or drop command.
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException if user input is not valid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        checkIdValidity(model);
        executeProgramCommand(model);
        return new CommandResult(getSuccessMessage());
    }

    protected abstract String getSuccessMessage();

    protected abstract String getDuplicateMessage();

    protected abstract String getProgramCommand();
}

