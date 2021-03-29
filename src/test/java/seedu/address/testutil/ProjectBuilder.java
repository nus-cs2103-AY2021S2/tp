package seedu.address.testutil;

import seedu.address.model.project.DeadlineList;
import seedu.address.model.project.EventList;
import seedu.address.model.project.GroupmateList;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.TodoList;

/**
 * A utility class to help with building Project objects.
 */
public class ProjectBuilder {

    public static final String DEFAULT_PROJECT_NAME = "Test Project Name";

    private ProjectName projectName;
    private EventList eventList = new EventList();
    private TodoList todoList = new TodoList();
    private DeadlineList deadlineList = new DeadlineList();
    private GroupmateList groupmateList = new GroupmateList();

    /**
     * Creates a {@code ProjectBuilder} with the default details.
     */
    public ProjectBuilder() {
        projectName = new ProjectName(DEFAULT_PROJECT_NAME);
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public ProjectBuilder(Project projectToCopy) {
        projectName = projectToCopy.getProjectName();
        eventList = projectToCopy.getEvents().getCopy();
        todoList = projectToCopy.getTodos().getCopy();
        deadlineList = projectToCopy.getDeadlines().getCopy();
        groupmateList = projectToCopy.getGroupmates().getCopy();
    }

    /**
     * Sets the {@code ProjectName} of the {@code Project} that we are building.
     */
    public ProjectBuilder withName(String projectName) {
        this.projectName = new ProjectName(projectName);
        return this;
    }

    /**
     * Sets the {@code EventList} of the {@code Project} that we are building.
     */
    public ProjectBuilder withEventList(EventList eventList) {
        this.eventList = eventList.getCopy();
        return this;
    }

    /**
     * Sets the {@code TodoList} of the {@code Project} that we are building.
     */
    public ProjectBuilder withTodoList(TodoList todoList) {
        this.todoList = todoList.getCopy();
        return this;
    }

    /**
     * Sets the {@code DeadlineList} of the {@code Project} that we are building.
     */
    public ProjectBuilder withDeadlineList(DeadlineList deadlineList) {
        this.deadlineList = deadlineList.getCopy();
        return this;
    }

    /**
     * Sets the {@code GroupmateList} of the {@code Project} that we are building.
     */
    public ProjectBuilder withGroupmateList(GroupmateList groupmateList) {
        this.groupmateList = groupmateList.getCopy();
        return this;
    }

    /**
     * Builds the {@code Project} object.
     *
     * @return {@code Project}.
     */
    public Project build() {
        return new Project(projectName, eventList, todoList, deadlineList, groupmateList);
    }

}
