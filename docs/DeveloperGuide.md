---
layout: page 
title: Developer Guide
---

* Table of Contents {:toc}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview
of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in
the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML
Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit
diagrams.

</div>

**`Main`** has two classes
called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It
is responsible for,

* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding
  API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface
and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`
, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a task).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying
   help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API
call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the address book data.
* exposes an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that
  the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique `Tag`, instead of each `Person` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>

### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,

* can save `UserPref` objects in json format and read it back.
* can save the address book data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo
history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the
following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()`
and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the
initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th task in the address book. The `delete` command
calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes
to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book
state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new task. The `add` command also calls `Model#commitAddressBook()`,
causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the task was a mistake, and decides to undo that action by executing
the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer`
once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once
to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such
as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`.
Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not
pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be
purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern
desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

![CommitActivityDiagram](images/CommitActivityDiagram.png)

#### Design consideration:

##### Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Saves the entire address book.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by itself.
    * Pros: Will use less memory (e.g. for `delete`, just save the task being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* prefer desktop apps over other types
* can type fast
* is reasonably comfortable using CLI apps

**Value proposition**: help students manage their tasks in a systematic and efficient manner

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                   | So that I can…​                                             |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | university student                         | add tasks                      | track a task that I have to do                                         |
| `* * *`  | university student                         | check off tasks                | view which tasks have been completed                                   |
| `* * *`  | university student                         | edit task details              | update any changes to the tasks                                        |
| `* * *`    | organized university student             | list all my tasks in a structured manner   | feel more in control of my schedules and tasks             |
| `* * *`  | university student                         | delete tasks                   | declutter the list of tasks                                            |
| `* * *`  | university student                         | view deadlines of tasks        | finish tasks that are more urgent                                      |
| `* * *`  | busy university student                    | tag the task based on category | organise my tasks efficiently                                          |
| `* * *`  | university student                         | set status of a task           | get an overview of my progress                         |
| `* * *`  | university student                         | view a list of completed and uncompleted tasks           | have a better understanding of my progress so far|
| `* * *`    | busy university student                    | view tasks based on category tags         | locate tasks easily                                     |
| `* *`    | busy university student                    | search for tasks based on date         | easily plan my schedule                                   |
| `* *`    | busy university student                    | delete multiple tasks at once        | clean up the task in the app easily                                   |
| `* *`    | organized university student               | view all tasks in chronological order (of date) | plan my day/week efficiently                             |


*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Taskify` and the **Actor** is the `user`, unless specified otherwise)

---
**Use case 1: Delete a Task**

**MSS**

1. User requests to list all Tasks
2. Taskify shows a list of Tasks
3. User requests to delete a specific Task in the list
4. Taskify deletes the Task

   Use case ends.

**Extensions**

* 2a. The list is empty.
    * 2a1. Taskify warns that the list is empty.
      
        Use case ends.

* 3a. The given index is invalid.

    * 3a1. Taskify warns that the index entered is invalid.

      Use case ends.

---
**Use case 2: Add a Task**

**MSS**

1. User requests to add a Task to the list
2. Taskify adds the Task to the list

   Use case ends.

**Extensions**

* 1a. The Task to be added already exists in the list
    * 1a1. Taskify warns the User that there is already such a Task
      
        Use case ends.

* 1b. The User fails to fill in any of the compulsory fields to create a new Task
    * 1b1. Taskify informs the User of the format of creating a new Task

        Use case ends.
* 1c. The User's input is unrecognisable to Taskify
    * 1c1. Taskify informs the User of the format of creating a new Task

        Use case ends.
---
**Use case 3: Set status of Task**

**MSS**

1. User requests to list all Tasks
2. Taskify shows a list of Tasks
3. User requests to set the status of the Task 
4. Taskify changes the status of the Task

   Use case ends.

**Extensions**

* 3a. The current status of the Task is the same as status the User requests to set for the same Task
    * 3a1. Taskify warns that the Task is already of the set status
      
        Use case ends.
  
* 3b. Taskify does not recognise the status that the User wants to set
    * 3b1. Taskify warns that it does not understand the type of status entered
    
        Use case ends.
    
* 3c. The given index is invalid
    * 3c1. Taskify warns that the index entered is invalid.
    
        Use case ends.

---
**Use case 4: List all Tasks**

**MSS**

1. User requests to list all Tasks
2. Taskify lists all the Tasks

**Extensions**

* 1a. There are no tasks stored
    * 1a1. Taskify informs the User there are no tasks tracked

        Use case ends.
    
* 2a. There are far too many Tasks stored (limit is customisable)
    * 2a1. Taskify informs the User that he/she should finish some tasks because there are too many piling up.
        
        Use case ends.
---
**Use case 5: Search for Tasks using tags**

**MSS**

1. User requests to search for Task(s) by using its tag.
2. Taskify shows the Task(s) with the same tag.

**Extensions**

* 1a. Taskify cannot find any Task with the tag
    * 1a1. Taskify warns that no such Task has this tag.
    
        Use case ends.
    
* 1b. The User's input is unrecognisable to Taskify
    * 1b1. Taskify informs the User on the format to do a tag-search.
    
        Use case ends.

---
**Use case 6: Sort all Tasks (in ascending order of their date & time)**

**MSS**

1. User requests to sort the Tasks.
2. Taskify shows the Tasks in their sorted order.

**Extensions**
* 1a. There are two or more Tasks that have the same date & time.
    * 1a1. Taskify sorts these Tasks in lexicographical order.
    
        Use case resumes from step 2
---
**Use case 7: Search for Tasks using keywords (excluding tags)**

**MSS**

1. User requests to find Task(s) with given keywords.
2. Taskify shows the Tasks that have passed the search.

**Extensions**
* 1a. Taskify cannot find any Task with the given keywords
    * 1a1. Taskify informs the User that no Tasks are found, and that keyword(s) must match a whole word in the Task's name.

        Use case ends.
---
**Use case 8: Modifying an existing Task**

**MSS**

1. User requests to modify an existing Task
2. Taskify shows the User the modified Task.

**Extensions**
* 1a. The User's input is unrecognisable to Taskify
    * 1a1. Taskify informs the User on the format to edit a Task.

      Use case ends.
    
* 1b. The User's input for specific fields is invalid
    * 1b1. Taskify informs the User on the correct format of the field in the User's input that failed to pass validation checks.
    
        Use case ends.
    
* 1c. The User's input does not include any fields at all.
    * 1c. Taskify warns that no modifying can take place if there are no updated fields filled in.
    
        Use case ends.
      

*{More to be added}*

---

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 100 tasks without a noticeable sluggishness in performance for typical usage.
3. A user should be able to easily see why their commands are invalid
4. The app should be able to save data locally
5. The app should be able to run with or without internet connection
6. The product should not take above 10 seconds to execute any commands.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be
       optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a task

1. Deleting a task while all tasks are being shown

    1. Prerequisites: List all tasks using the `list` command. Multiple tasks in the list.

    1. Test case: `delete 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.
       Timestamp in the status bar is updated.

    1. Test case: `delete 0`<br>
       Expected: No task is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
