package seedu.address.logic.filters.combinator;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OR;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;
import java.util.function.Predicate;

import seedu.address.logic.filters.Filter;
import seedu.address.logic.filters.Filters;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.customer.Customer;

/**
 * This class encapsulates the expression tree. It both parses, and evaluates the expression tree on a {@code
 * Customer}.
 */
public class FilterCombinator implements Predicate<Customer> {
    public static final String MESSAGE_EFFECTIVELY_EMPTY = "Your string contains only space of brackets "
        + "(either the entire string, or parts inside the backets) which is invalid.";
    public static final String MESSAGE_INVALID_BRACKETING = "The bracketing of the sequence is wrong - Make sure the "
        + "sequence is well bracketed!";
    public static final String MESSAGE_OPERATOR_RULES_NOT_FOLLOW = "Make sure all binary operators have two operands,"
        + " while unary operator have a single operand!";

    private final Node rootNode;

    /**
     * Constructor for filter combinator to create a expression tree from the given argument. Note that an issue with
     * the expression is not handled at this point, and is handled only when testing a {@code Customer} // TODO
     *
     * @param argument the filter expression
     */
    public FilterCombinator(String argument) {
        Objects.requireNonNull(argument);

        Node temp = null;
        try {
            temp = createTree(argument);
        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            rootNode = temp;
        }
    }

    /**
     * Checks whether it is a valid combinator tree.
     *
     * @return true if the expression actually results in a tree, false otherwise.
     */
    public boolean isValidCombinator() {
        return rootNode != null;
    }

    /**
     * Gets the logical operator corresponding to the prefix in {@code CLISyntax}.
     *
     * @param prefix the logical operator prefix
     * @return the corresponding logical operator
     * @throws IllegalArgumentException if the prefix string doesn't match any logical operator prefix as defined in the
     *                                  {@code CLISyntax} class.
     */
    private LogicalOperator getCorrespondingLogicalOperator(Prefix prefix) {
        if (prefix.equals(PREFIX_AND)) {
            return LogicalOperator.AND;
        } else if (prefix.equals(PREFIX_OR)) {
            return LogicalOperator.OR;
        } else if (prefix.equals(PREFIX_NOT)) {
            return LogicalOperator.NOT;
        } else {
            throw new IllegalArgumentException("Unknown operator");
        }
    }

    // TODO
    private Node createTree(String description) {
        description = " " + description + " ";

        if (description.replace('[', ' ').replace(']', ' ').trim().isEmpty()) {
            throw new IllegalArgumentException("Incorrect formatting 1 " + description.length());
        }

        StringBuilder inPresentScope = new StringBuilder("");
        StringBuilder inSubtreeScope = new StringBuilder("");

        Stack<Node> nodeStack = new Stack<>();
        LinkedList<ArgumentTokenizer.PrefixPosition> allPositions =
            new LinkedList<>(ArgumentTokenizer.findAllPrefixPositions(description, PREFIX_AND,
                PREFIX_NOT, PREFIX_OR));

        allPositions.sort(Comparator.comparingInt(ArgumentTokenizer.PrefixPosition::getStartPosition));

        System.out.println(allPositions);

        int nestingLevel = 0;

        for (int i = 0; i < description.length(); i++) {
            char c = description.charAt(i);

            while (!allPositions.isEmpty() && i > allPositions.getFirst().getStartPosition()) {
                allPositions.removeFirst();
            }


            // first check if the character is part of a logical operator prefix
            if (nestingLevel == 0 && !allPositions.isEmpty() && i == allPositions.getFirst().getStartPosition()) {
                // now we try to form a Node from the previously given filter at this level.
                // only try to form a node if we have actually got some information
                if (inPresentScope.toString().trim().length() > 0) {
                    Filter filter = Filters.getCorrespondingFilter(inPresentScope.toString().trim());
                    nodeStack.push(new Node(filter));
                }

                Prefix prefix = allPositions.getFirst().getPrefix();
                nodeStack.push(new Node(getCorrespondingLogicalOperator(prefix)));
                i += prefix.getPrefix().trim().length() - 1; // since we will anyway do a +1 when the for loop updates.

                allPositions.removeFirst();
                inPresentScope = new StringBuilder("");

                continue;
            }

            // all nesting is handled here.
            if (c == '[') {
                // starting a new nesting depth
                if (nestingLevel != 0) {
                    inSubtreeScope.append(c);
                }
                nestingLevel++;

            } else if (c == ']') {
                nestingLevel--;
                if (nestingLevel > 0) {
                    inSubtreeScope.append(c);
                } else {
                    Node nextNode = createTree(inSubtreeScope.toString());
                    nodeStack.push(nextNode);
                    inSubtreeScope = new StringBuilder("");
                }
            } else {
                if (nestingLevel > 0) {
                    inSubtreeScope.append(c);
                } else {
                    inPresentScope.append(c);
                }
            }
        }

        if (inPresentScope.toString().trim().length() > 0) {
            Filter filter = Filters.getCorrespondingFilter(inPresentScope.toString().trim());
            nodeStack.push(new Node(filter));
        }

        System.out.println(nodeStack);

        return formTreeFromNodeStack(nodeStack);
    }

    // TODO
    private Node formTreeFromNodeStack(Stack<Node> nodeStack) {
        while (!nodeStack.isEmpty()) {
            if (nodeStack.size() == 1) {
                return nodeStack.pop();
            }

            if (nodeStack.size() == 2) {
                Node upper = nodeStack.pop();
                Node operator = nodeStack.pop();
                if (!operator.hasUnaryOperator()) {
                    throw new IllegalArgumentException("Incorrect formatting 3");
                }
                operator.setChild(upper);

                return operator;
            }

            Node first = nodeStack.pop();
            Node second = nodeStack.pop();
            Node third = nodeStack.pop();

            if (second.hasChildren() || second.getNodeType() == NodeType.EVALUATOR) {
                throw new IllegalArgumentException("Incorrect formatting 4 " + second.getNodeType());
            }

            if (second.hasUnaryOperator()) {
                second.setChild(first);
                nodeStack.push(third);
                nodeStack.push(second);
            } else {
                second.setLeftNode(third);
                second.setRightNode(first);
                nodeStack.push(second);
            }


        }

        throw new IllegalArgumentException("Incorrect formatting 5");
    }

    @Override
    public boolean test(Customer customer) {
        Objects.requireNonNull(customer);
        return rootNode.evaluate(customer);
    }

    @Override
    public String toString() {
        return "FilterCombinator{"
            + "rootNode=" + rootNode
            + '}';
    }
}
