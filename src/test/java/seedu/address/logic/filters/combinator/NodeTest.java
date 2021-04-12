package seedu.address.logic.filters.combinator;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.filters.EmailFilter;

public class NodeTest {
    @Test
    public void setRightNodeForUnaryOperator() {
        Node node1 = new Node(LogicalOperator.NOT, null, null);
        assertThrows(IllegalArgumentException.class, () -> node1.setRightNode(node1));
    }

    @Test
    public void setRightNodeForEvaluator() {
        Node node1 = new Node(new EmailFilter("email@email.com"));
        assertThrows(IllegalArgumentException.class, () -> node1.setRightNode(node1));
    }

    @Test
    public void setLeftNodeForEvaluator() {
        Node node1 = new Node(new EmailFilter("Email"));
        assertThrows(IllegalArgumentException.class, () -> node1.setLeftNode(node1));
    }

    @Test
    public void notUnaryAndBinaryBoth() {
        Node node1 = new Node(LogicalOperator.OR);
        assertNotEquals(node1.hasUnaryOperator(), node1.hasBinaryOperator());
    }
}
