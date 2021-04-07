package dog.pawbook.testutil;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOGID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_NAME;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_SESSION;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_TAG;

import dog.pawbook.logic.commands.AddProgramCommand;
import dog.pawbook.model.managedentity.program.Program;

/**
 * A utility class for Program.
 */
public class ProgramUtil {

    /**
     * Returns an add command string for adding the {@code program}.
     */
    public static String getAddCommand(Program program) {
        return AddProgramCommand.COMMAND_WORD + " " + Program.ENTITY_WORD + " " + getProgramDetails(program);
    }

    /**
     * Returns the part of command string for the given {@code program}'s details.
     */
    public static String getProgramDetails(Program program) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + program.getName().fullName + " ");
        program.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " "));
        program.getDogIdSet().stream().forEach(
            s -> sb.append(PREFIX_DOGID + Integer.toString(s) + " "));
        program.getSessions().stream().forEach(
            s -> sb.append(PREFIX_SESSION + s.value + " "));
        return sb.toString();
    }
}
