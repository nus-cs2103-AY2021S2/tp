package dog.pawbook.testutil;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOGID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_NAME;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_SESSION;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import dog.pawbook.logic.commands.AddProgramCommand;
import dog.pawbook.logic.commands.EditProgramCommand;
import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.model.managedentity.program.Session;
import dog.pawbook.model.managedentity.tag.Tag;

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
        sb.append(PREFIX_NAME).append(program.getName().fullName).append(" ");
        program.getTags().forEach(
            s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
        program.getDogIdSet().forEach(
            s -> sb.append(PREFIX_DOGID).append(s).append(" "));
        program.getSessions().forEach(
            s -> sb.append(PREFIX_SESSION).append(s.value).append(" "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditProgramDescriptor}'s details.
     */
    public static String getEditProgramDescriptorDetails(EditProgramCommand.EditProgramDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        if (descriptor.getSessions().isPresent()) {
            Set<Session> sessions = descriptor.getSessions().get();
            if (sessions.isEmpty()) {
                sb.append(PREFIX_SESSION).append(" ");
            } else {
                sessions.forEach(s -> sb.append(PREFIX_SESSION).append(s.value).append(" "));
            }
        }
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
