package seedu.address.logic.filters.combinator;

import seedu.address.logic.filters.AbstractFilter;
import seedu.address.model.customer.Customer;

class Node {
    private final LogicalOperator operator;
    private final AbstractFilter filter;
    private final NodeType nodeType;

    private Node left;
    private Node right;

    public Node(LogicalOperator operator, Node left, Node right) {
        this.operator = operator;
        this.filter = null;
        this.nodeType = NodeType.COMBINATOR;
        this.left = left;
        this.right = right;
    }

    public Node(AbstractFilter filter) {
        this.operator = null;
        this.filter = filter;
        this.nodeType = NodeType.EVALUATOR;
        this.left = null;
        this.right = null;
    }

    public Node(LogicalOperator operator) {
        this(operator, null, null);
    }

    public Node(LogicalOperator operator, Node singleChild) {
        this(operator, singleChild, null);
    }

    public NodeType getNodeType() {
        return this.nodeType;
    }

    public boolean evaluate(Customer customer) {
        if (this.nodeType == NodeType.COMBINATOR) {
            assert operator != null;
            if (operator.isBinaryOperator()) {
                return operator.apply(left.evaluate(customer), right.evaluate(customer));
            } else {
                return operator.apply(left.evaluate(customer));
            }
        } else {
            assert filter != null;
            return filter.test(customer);
        }
    }

    public void setLeftNode(Node node) {
        if (nodeType == NodeType.EVALUATOR) {
            throw new IllegalStateException("Evaluator node cannot have a child");
        }

        this.left = node;
    }

    public void setRightNode(Node node) {
        if (nodeType == NodeType.EVALUATOR) {
            throw new IllegalStateException("Evaluator node cannot have a child");
        }

        assert operator != null;
        if (operator.isUnaryOperator()) {
            throw new IllegalStateException("Cannot add second child to unary operator");
        }

        this.right = node;
    }

    public void setChild(Node node) {
        this.setLeftNode(node);
    }

    public boolean hasUnaryOperator() {
        if (nodeType != NodeType.COMBINATOR) {
            return false;
        }
        assert operator != null;
        return operator.isUnaryOperator();
    }

    public boolean hasBinaryOperator() {
        if (nodeType != NodeType.COMBINATOR) {
            return false;
        }
        assert operator != null;
        return operator.isBinaryOperator();
    }

    public boolean hasChildren() {
        return !(left == null && right == null);
    }

    public String toString() {
        return nodeType.toString() + ":" + operator + " " + filter + ": {" + left + "}{" + right + "}";
    }
}
