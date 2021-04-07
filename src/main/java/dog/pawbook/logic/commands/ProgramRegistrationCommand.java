package dog.pawbook.logic.commands;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_DOG_ID;
import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_DOG_ID_MULTIPLE_FORMAT;
import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_PROGRAM_ID;
import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_PROGRAM_ID_MULTIPLE_FORMAT;
import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;
import static dog.pawbook.model.Model.COMPARATOR_ID_ASCENDING_ORDER;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.IdMatchPredicate;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.program.Program;

//@@author wei-yutong
/**
 * An abstract base class for Enrol and Drop commands.
 */
public abstract class ProgramRegistrationCommand extends Command {
    private final Set<Integer> dogIdSet;
    private final Set<Integer> programIdSet;
    private final boolean enrol;

    /**
     * Construct a program registration command that enrol or drop.
     */
    public ProgramRegistrationCommand(Set<Integer> dogIdSet, Set<Integer> programIdSet, boolean enrol) {
        requireAllNonNull(dogIdSet, programIdSet);

        this.dogIdSet = dogIdSet;
        this.programIdSet = programIdSet;
        this.enrol = enrol;
    }

    /**
     * Checks whether the IDs entered by the user are valid.
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException if IDs are not valid.
     */
    private void checkIdValidity(Model model) throws CommandException {
        boolean dogIdsValid = dogIdSet.stream()
                .allMatch(id -> model.hasEntity(id) && model.getEntity(id) instanceof Dog);
        if (!dogIdsValid) {
            if (dogIdSet.size() == 1) {
                throw new CommandException(MESSAGE_INVALID_DOG_ID);
            } else {
                throw new CommandException(MESSAGE_INVALID_DOG_ID_MULTIPLE_FORMAT);
            }
        }

        boolean programIdsValid = programIdSet.stream()
                .allMatch(id -> model.hasEntity(id) && model.getEntity(id) instanceof Program);
        if (!programIdsValid) {
            if (programIdSet.size() == 1) {
                throw new CommandException(MESSAGE_INVALID_PROGRAM_ID);
            } else {
                throw new CommandException(MESSAGE_INVALID_PROGRAM_ID_MULTIPLE_FORMAT);
            }
        }
    }

    /**
     * Ensure that dogs are not already enrolled for enrol and dogs are enrolled for drop.
     */
    private boolean dogEnrollmentValid(Model model) {
        return programIdSet.stream()
                .map(model::getEntity)
                .map(Program.class::cast)
                .map(Program::getDogIdSet)
                .allMatch(enrolledIds -> enrol
                        ? Collections.disjoint(enrolledIds, dogIdSet) // none of the dogs are already enrolled
                        : enrolledIds.containsAll(dogIdSet)); // all of the dogs are already enrolled
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
        if (!dogEnrollmentValid(model)) {
            if (dogIdSet.size() == 1) {
                if (programIdSet.size() == 1) {
                    throw new CommandException(getFailureMessage());
                } else {
                    throw new CommandException(getFailureMessageMultiplePrograms());
                }
            } else if (programIdSet.size() == 1) {
                throw new CommandException(getFailureMessageMultipleDogs());
            }
        }

        // The parser should have ensured that only either dog or program is specified multiple times.
        assert dogIdSet.size() == 1 || programIdSet.size() == 1;

        for (int programId : programIdSet) {
            Program program = (Program) model.getEntity(programId);
            HashSet<Integer> updatedEnrolledDogs = new HashSet<>(program.getDogIdSet());
            if (enrol) {
                updatedEnrolledDogs.addAll(dogIdSet);
            } else {
                updatedEnrolledDogs.removeAll(dogIdSet);
            }
            model.setEntity(programId,
                    new Program(program.getName(), program.getSessions(), program.getTags(), updatedEnrolledDogs));
        }

        model.updateFilteredEntityList(new IdMatchPredicate(programIdSet));
        model.sortEntities(COMPARATOR_ID_ASCENDING_ORDER);

        return new CommandResult(
                String.format(getSuccessMessageFormat(),
                        dogIdSet.stream().map(String::valueOf).collect(Collectors.joining(", ")),
                        programIdSet.stream().map(String::valueOf).collect(Collectors.joining(", "))));
    }

    protected abstract String getSuccessMessageFormat();

    /**
     * Retrieves the failure message format, where failure is defined as repeated enrollment of dogs and removal of not
     * enrolled dogs.
     */
    protected abstract String getFailureMessage();

    protected abstract String getFailureMessageMultipleDogs();

    protected abstract String getFailureMessageMultiplePrograms();
}

