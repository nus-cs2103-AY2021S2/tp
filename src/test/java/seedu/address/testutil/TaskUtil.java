package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITYTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.address.model.person.Task;
import seedu.address.model.tag.Tag;

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
        // add and edit do not have notes fields
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + task.getTaskName().fullName + " ");
        sb.append(PREFIX_CODE + task.getModuleCode().moduleCode + " ");
        sb.append(PREFIX_WEIGHTAGE + task.getWeightage().weightage.toString() + "% ");
        sb.append(PREFIX_DEADLINE_DATE + task.getDeadlineDate().toString() + " ");
        sb.append(PREFIX_DEADLINE_TIME + task.getDeadlineTime().toString() + " ");
        // cannot add notes directly using add command yet.
        // sb.append(PREFIX_NOTES + task.getNotes().value + " ");
        task.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        sb.append(PREFIX_PRIORITYTAG + task.getPriorityTag().getTagName());;
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditTaskDescriptor}'s details.
     */
    public static String getEditTaskDescriptorDetails(EditTaskDescriptor descriptor) {
        // currently edit does not support editing of remarks
        StringBuilder sb = new StringBuilder();
        descriptor.getTaskName().ifPresent(name -> sb.append(PREFIX_NAME)
                .append(name.fullName).append(" "));
        descriptor.getModuleCode().ifPresent(code -> sb.append(PREFIX_CODE)
                .append(code.moduleCode).append(" "));
        descriptor.getDeadlineDate().ifPresent(date -> sb.append(PREFIX_DEADLINE_DATE)
                .append(date.toString()).append(" "));
        descriptor.getDeadlineTime().ifPresent(time -> sb.append(PREFIX_DEADLINE_TIME)
                .append(time.toString()).append(" "));
        descriptor.getWeightage().ifPresent(weightage -> sb.append(PREFIX_WEIGHTAGE)
                .append(weightage.toString()).append(" "));
        descriptor.getNotes().ifPresent(notes -> sb.append(PREFIX_NOTES)
                .append(notes.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        descriptor.getPriorityTag().ifPresent(ptag -> sb.append(PREFIX_PRIORITYTAG)
                .append(ptag.getTagName()));
        return sb.toString();
    }
}
