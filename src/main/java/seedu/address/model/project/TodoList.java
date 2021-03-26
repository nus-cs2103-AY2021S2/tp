package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.project.exceptions.DuplicateTodoException;
import seedu.address.model.task.CompletableTodo;

/**
 * Represents a list of Todos.
 */
public class TodoList {

    private final ObservableList<CompletableTodo> todos = FXCollections.observableArrayList();

    /**
     * Constructs a empty {@code TodoList}.
     */
    public TodoList() {}

    /**
     * Constructs a {@code TodoList}.
     *
     * @param todos A list of {@code Todos}.
     */
    public TodoList(List<CompletableTodo> todos) {
        requireNonNull(todos);

        this.todos.addAll(todos);
    }

    /**
     * Adds a todo to this {@code TodoList}. The {@code CompletableTodo} must not already exist in the {@code TodoList}.
     *
     * @param todo {@code Todo} to add.
     */
    public void addTodo(CompletableTodo todo) {
        requireNonNull(todo);

        if (hasTodo(todo)) {
            throw new DuplicateTodoException();
        }

        this.todos.add(todo);
    }

    /**
     * Returns {@code TodoList} as an {@code ObservableList<CompletableTodo>}
     *
     * @return An {@code ObservableList<CompletableTodo>}
     */
    public ObservableList<CompletableTodo> getTodos() {
        return this.todos;
    }

    /**
     * Deletes an todo from this {@code TodoList}.
     *
     * @param i Index of {@code Todo} to be deleted.
     */
    public void deleteTodo(Integer i) {
        requireNonNull(i);
        this.todos.remove((int) i);
    }

    /**
     * Marks a todo from this {@code TodoList} as done.
     *
     * @param i Index of {@code Todo} to be marked as done.
     */
    public void markAsDone(Integer i) {
        requireNonNull(i);
        CompletableTodo todo = todos.get(i);
        todo.markAsDone();

        // Force observable list to update
        this.todos.set(i, todo);
    }

    /**
     * Returns a copy of this {@code TodoList}
     *
     * @return A copy of this {@code TodoList}
     */
    public TodoList getCopy() {
        return new TodoList(getTodos());
    }

    /**
     * Returns a sequential stream with this {@code TodoList} as its source.
     *
     * @return a sequential Stream over the Todos in this {@code TodoList}.
     */
    public Stream<CompletableTodo> stream() {
        return this.todos.stream();
    }

    /**
     * Checks if the {@code TodoList} already contains the specified {@code todoToCheck}.
     *
     * @param todoToCheck The todo that is to be checked.
     * @return true if this project contains the specified todo, false otherwise.
     */
    public boolean hasTodo(CompletableTodo todoToCheck) {
        for (CompletableTodo todo: todos) {
            if (todoToCheck.equals(todo)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TodoList // instanceof handles nulls
                && this.todos.equals(((TodoList) other).todos)); // state check
    }

    @Override
    public int hashCode() {
        return todos.hashCode();
    }

}
