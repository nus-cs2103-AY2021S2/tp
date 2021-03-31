package dog.pawbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.program.Program;

public abstract class ProgramCommand extends Command {
    protected abstract Set<Integer> retrieveDogIdSet();
    protected abstract Set<Integer> retrieveProgramIdSet();

    /**
     * Checks whether the IDs entered by the user are valid.
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException if IDs are not valid.
     */
//    public void checkIdValidity(Model model) throws CommandException {
//        boolean isValidDogId = getDogIdSet().stream().allMatch(model::hasEntity);
//        boolean isValidProgramId = getProgramIdSet().stream().allMatch(model::hasEntity);
//
//        boolean isDog = getDogIdSet().stream().map(model::getEntity).allMatch(d->d instanceof Dog);
//        boolean isProgram = getProgramIdSet().stream().map(model::getEntity).allMatch(p->p instanceof Program);
//
//        if (!isValidDogId || !isValidProgramId) {
//            throw new CommandException(MESSAGE_INVALID_DOG_OR_PROGRAM_ID);
//        } else if (!isDog || !isProgram) {
//            throw new CommandException(MESSAGE_INVALID_DOG_OR_PROGRAM_ID);
//        }
//
//    }

    /**
     * Handles the specifics of enrol command or drop command.
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException if dog is already enrolled or dropped from the program.
     */
    public void executeProgramCommand(Model model) throws CommandException {
        String programCommand = getProgramCommand();
        Set<Program> targetProgramSet = retrieveProgramIdSet()
                .stream()
                .map(model::getEntity)
                .map(x -> (Program) x)
                .collect(Collectors.toSet());

        Set<Integer> editedDogIdSet = targetProgramSet
                .stream()
                .map(Program::getDogIdSet)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        if (programCommand.equals("enrol")) {
            for (Program p: targetProgramSet) {
                if (p.getDogIdSet().containsAll(retrieveDogIdSet())) {
                    throw new CommandException(getDuplicateMessage());
                }
                editedDogIdSet.addAll(retrieveDogIdSet());
            }
        } else {
            for (Program p: targetProgramSet) {
                if (!p.getDogIdSet().containsAll(retrieveDogIdSet())) {
                    throw new CommandException(getDuplicateMessage());
                }
                editedDogIdSet.remove(retrieveDogIdSet());
            }
        }

//        Program editedProgram = new Program(targetProgram.getName(), targetProgram.getSessions(),
//                targetProgram.getTags(), editedDogIdSet);
//
//        model.setEntity(getProgramId(), editedProgram);

        Set<Program> editedProgramSet = targetProgramSet
                .stream()
                .map(p -> new Program(p.getName(),
                        p.getSessions(),
                        p.getTags(),
                        editedDogIdSet))
                .collect(Collectors.toSet());

        retrieveProgramIdSet().stream()
                .map(x -> model.setEntity(x, editedProgramSet))
                .collect(Collectors.toSet());
    }

    /**
     * Executes enrol or drop command.
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException if user input is not valid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
//        checkIdValidity(model);
        executeProgramCommand(model);
        return new CommandResult(getSuccessMessage());
    }

    protected abstract String getSuccessMessage();

    protected abstract String getDuplicateMessage();

    protected abstract String getProgramCommand();
}

