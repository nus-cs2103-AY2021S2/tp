package dog.pawbook.logic.commands;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOGID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_PROGRAMID;
import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.program.Program;

public class EnrolCommand extends Command {

    public static final String COMMAND_WORD = "enrol";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Enrols a dog into a program."
            + "Parameters: "
            + PREFIX_DOGID + "DOG_ID "
            + PREFIX_PROGRAMID + "PROGRAM_ID...\n"
            + "Example: " + COMMAND_WORD + " d/2 p/3";

    public static final String MESSAGE_SUCCESS_FORMAT = "Dog %s enrolled in program %s!";

    private final int DogId;

    private final int ProgramId;

    public EnrolCommand(int DogId, int ProgramId) {
        this.DogId = DogId;
        this.ProgramId = ProgramId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasEntity(DogId) || !model.hasEntity(ProgramId)) {
            throw new CommandException(Messages.MESSAGE_INVALID_ENTITY_DISPLAYED_ID);
        }

        Entity targetDog = model.getEntity(DogId);

        Program targetProgram = (Program) model.getEntity(ProgramId);

        Set<Integer> editedDogIdSet = new HashSet<>(targetProgram.getDogIdSet());
        editedDogIdSet.add(DogId);

        Program editedProgram = new Program(targetProgram.getName(), targetProgram.getSessionSet(),
                targetProgram.getTags(), editedDogIdSet);

        model.setEntity(ProgramId, editedProgram);

        return new CommandResult(String.format(MESSAGE_SUCCESS_FORMAT, DogId, ProgramId));
    }
}
