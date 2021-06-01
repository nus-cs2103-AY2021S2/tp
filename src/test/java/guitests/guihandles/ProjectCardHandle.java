package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.address.model.project.Project;

/**
 * Provides a handle to a {@code ProjectCard}.
 */
public class ProjectCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String NAME_FIELD_ID = "#name";

    private final Label idLabel;
    private final Label nameLabel;

    /**
     * Constructs a {@code ProjectCardHandle} handler object.
     * @param cardNode Node of {@code ProjectCard}.
     */
    public ProjectCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        nameLabel = getChildNode(NAME_FIELD_ID);
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getName() {
        return nameLabel.getText();
    }

    /**
     * Returns true if this handle contains a {@code Project}.
     */
    public boolean equals(Project project) {
        return getName().equals(project.getProjectName().toString());
    }
}
