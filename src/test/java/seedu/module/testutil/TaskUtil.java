package seedu.module.testutil;

import static seedu.module.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.module.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.module.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.module.logic.parser.CliSyntax.PREFIX_WORKLOAD;

import java.util.Set;

import seedu.module.logic.commands.AddCommand;
import seedu.module.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.module.model.tag.Tag;
import seedu.module.model.task.Task;

/**
 * A utility class for Task.
 */
public class TaskUtil {

    /**
     * Returns an add command string for adding the {@code task}.
     */
    public static String getAddCommand(Task task) {
        return AddCommand.COMMAND_WORD + " " + getTaskDetails(task);
    }

    /**
     * Returns the part of command string for the given {@code task}'s details.
     */
    public static String getTaskDetails(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TASK_NAME + task.getName().fullName + " ");
        sb.append(PREFIX_MODULE + task.getModule().value + " ");
        sb.append(PREFIX_DESCRIPTION + task.getDescription().value + " ");
        sb.append(PREFIX_DEADLINE + task.getDeadline().value + " ");
        sb.append(PREFIX_WORKLOAD + Integer.toString(task.getWorkload().workloadLevel) + " ");
        task.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditTaskDescriptor}'s details.
     */
    public static String getEditTaskDescriptorDetails(EditTaskDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_TASK_NAME).append(name.fullName).append(" "));
        descriptor.getDeadline().ifPresent(deadline -> sb.append(PREFIX_DEADLINE).append(deadline.value).append(" "));
        descriptor.getModule().ifPresent(module -> sb.append(PREFIX_MODULE).append(module.value).append(" "));
        descriptor.getDescription()
            .ifPresent(description -> sb.append(PREFIX_DESCRIPTION).append(description.value).append(" "));
        descriptor.getWorkload().ifPresent(workload ->
                sb.append(PREFIX_WORKLOAD).append(workload.toString()).append(" "));
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

    /**
     * Returns the part of command string for the given {@code EditTaskDescriptor}'s details.
     */
    public static String getTagDetails(Tag tag) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TAG + tag.tagName);
        return sb.toString();
    }
}
