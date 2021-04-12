package seedu.address.logic.filters.combinator;

import seedu.address.logic.filters.Filter;
import seedu.address.model.customer.Customer;

import java.util.Objects;

/**
 * This class represents a Node in the Tree created by {@code FilterCombinator}, which is essentially an expression
 * parser Tree. Note that there is no specific Tree class -- the Tree is represented by the root node itself. The nodes
 * in the tree essentially are either logical combinators, or, the leaf nodes, which are actually using {@code Filter
 * } to evaluate whether a {@code Customer} satisfies that filtering constraint.
 */
class Node {
    private final LogicalOperator operator;
    private final Filter filter;
    private final NodeType nodeType;

    private Node left;
    private Node right;

    /**
     * Takes in a operator, and the left and right child. This constructor is used to create a node of type
     * {@code COMBINATOR}.
     *
     * @param operator - the logical operator
     * @param left     - the left child
     * @param right    - the right child
     */
    public Node(LogicalOperator operator, Node left, Node right) {
        Objects.requireNonNull(operator);

        this.operator = operator;
        this.filter = null;
        this.nodeType = NodeType.COMBINATOR;
        this.left = left;
        this.right = right;
    }

    /**
     * This creates a node of type {@code EVALUATOR}. It only takes a {@code Filter}, and has no children.
     *
     * @param filter - the {@code Filter} used to evaluate the value of this node
     */
    public Node(Filter filter) {
        Objects.requireNonNull(filter);

        this.operator = null;
        this.filter = filter;
        this.nodeType = NodeType.EVALUATOR;
        this.left = null;
        this.right = null;

    }

    /**
     * This constructor is used to create a Node from just a logical operator, with no children yet being set.
     *
     * @param operator - the operator associated with the node
     */
    public Node(LogicalOperator operator) {
        this(operator, null, null);
    }

    /**
     * This constructor is used to create a Node of potentially unary operator type, with only one child being set.
     * However, based on the operator type, another child may or may not be set. By default the node given is set as
     * the left
     * child.
     *
     * @param operator    - the operator associated with the node
     * @param singleChild - the child given. By default this is set as the left child.
     */
    public Node(LogicalOperator operator, Node singleChild) {
        this(operator, singleChild, null);
    }

    /**
     * Returns the {@code NodeType} for this node.
     *
     * @return - the {@code NodeType}
     */
    public NodeType getNodeType() {
        return this.nodeType;
    }

    /**
     * Evaluates the node's result recursively. Evaluating on the root node for a particular {@code Customer}
     * essentially results in evaluating whether the {@code Customer} satisfies the entire expression. For nodes of
     * type {@code NodeType.EVALUATOR}, it just checks against the {@code Filter} object provided, whereas for
     * {@code NodeType.COMBINATOR} nodes it returns the logical operation it is supposed to do.
     *
     * @param customer - the {@code Customer} to evaluate on
     * @return - the result of the evaluation of the subtree of this node
     */
    public boolean evaluate(Customer customer) {
        Objects.requireNonNull(customer);

        if (this.nodeType == NodeType.EVALUATOR) {
            assert filter != null;
            return filter.test(customer);
        }

        assert operator != null;
        if (operator.isBinaryOperator()) {
            return operator.apply(left.evaluate(customer), right.evaluate(customer));
        } else {
            return operator.apply(left.evaluate(customer));
        }

    }

    /**
     * Set the left child for this {@code Node}. However, a {@code NodeType.EVALUATOR} cannot have any child, and
     * attempting to calling this on such a node results in an exception being thrown.
     * @param node - the left child
     * @throws IllegalArgumentException - In case the {@code NodeType} is Evaluator
     */
    public void setLeftNode(Node node) {
        if (nodeType == NodeType.EVALUATOR) {
            throw new IllegalArgumentException("Evaluator node cannot have a child");
        }

        this.left = node;
    }

    /**
     * Set the right child for this {@code Node}. However, a {@code NodeType.EVALUATOR} cannot have any child, and
     * attempting to calling this on such a node results in an exception being thrown. Further, if it is of type
     * {@code NodeType.COMBINATOR}, if it is a unary operator then cannot set the right child as by default only the
     * left child can be set for such a node.
     * @param node - the right child
     * @throws IllegalArgumentException - In case the {@code NodeType} is Evaluator
     */
    public void setRightNode(Node node) {
        if (nodeType == NodeType.EVALUATOR) {
            throw new IllegalArgumentException("Evaluator node cannot have a child");
        }

        assert operator != null;
        if (operator.isUnaryOperator()) {
            throw new IllegalArgumentException("Cannot add second child to unary operator");
        }

        this.right = node;
    }

    /**
     * This function internally just calls {@code setLeftNode}. It is here just to make it simpler to add {@code Node}
     * to nodes which are of type {@code COMBINATOR} and have an unary operator associated with them.
     * @param node - node to set as child
     */
    public void setChild(Node node) {
        this.setLeftNode(node);
    }

    /**
     * Used to check whether the node has a Unary Operator associated with it. Note that this function returns false
     * if the NodeType is of type EVALUATOR.
     * @return - true if the logical operator is unary, false otherwise
     */
    public boolean hasUnaryOperator() {
        if (nodeType != NodeType.COMBINATOR) {
            return false;
        }
        assert operator != null;
        return operator.isUnaryOperator();
    }

    /**
     * Used to check whether the node has a Binary Operator associated with it. Note that this function returns false
     * if the NodeType is of type EVALUATOR.
     * @return - true if the logical operator is binary, false otherwise
     */
    public boolean hasBinaryOperator() {
        if (nodeType != NodeType.COMBINATOR) {
            return false;
        }
        assert operator != null;
        return operator.isBinaryOperator();
    }

    /**
     * Checks whether the node has any children at all.
     * @return - true if the node has at least one child, false otherwise
     */
    public boolean hasChildren() {
        return !(left == null && right == null);
    }

    /**
     * Gives a recursively evaluated String version of the tree structure.
     *
     * @return
     */
    public String toString() {
        return nodeType.toString() + ":" + operator + " " + filter + ": {" + left + "}{" + right + "}";
    }
}
