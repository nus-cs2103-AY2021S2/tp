package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ProjectsFolder;
import seedu.address.model.project.DeadlineList;
import seedu.address.model.project.EventList;
import seedu.address.model.project.ParticipantList;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;

/**
 * A utility class containing a list of {@code Project} objects to be used in tests.
 */
public class TypicalProjects {

    public static final EventList EMPTY_EVENT_LIST = new EventList();
    public static final DeadlineList EMPTY_COMPLETABLE_TASK_LIST = new DeadlineList();
    public static final ParticipantList EMPTY_PARTICIPANT_LIST = new ParticipantList();

    public static final ProjectName CS2103T_PROJECT_NAME = new ProjectName("CS2103T Project");
    public static final String CS2103T_PROJECT_NAME_STRING = "CS2103T Project";

    public static final ProjectName CS1101S_NAME = new ProjectName("CS1101S");
    public static final String CS1101S_NAME_STRING = "CS1101S";

    private TypicalProjects() {} // prevents instantiation

    /**
     * Returns a {@code ProjectsFolder} with all the typical projects.
     */
    public static ProjectsFolder getTypicalProjectsFolder() {
        ProjectsFolder pf = new ProjectsFolder();
        for (Project project : getTypicalProjects()) {
            pf.addProject(project);
        }
        return pf;
    }

    public static List<Project> getTypicalProjects() {
        Project cs2103Project = new Project(CS2103T_PROJECT_NAME);
        Project cs1101s = new Project(CS1101S_NAME);

        return new ArrayList<>(Arrays.asList(cs2103Project, cs1101s));
    }
}
