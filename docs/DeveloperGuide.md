---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2021S2-CS2103T-W14-3/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S2-CS2103T-W14-3/tp/blob/master/src/main/java/seedu/address/Main.java) 
and [`MainApp`](https://github.com/AY2021S2-CS2103T-W14-3/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
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
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `deleteTask 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S2-CS2103T-W14-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `TaskListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S2-CS2103T-W14-3/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S2-CS2103T-W14-3/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S2-CS2103T-W14-3/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `HeyMatezParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `deleteTask 1` Command](images/DeleteTaskSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteTaskCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/AY2021S2-CS2103T-W14-3/tp/blob/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the address book data.
* exposes an unmodifiable `ObservableList<Person>` and `ObservableList<Task>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.


### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/AY2021S2-CS2103T-W14-3/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the Hey Matez data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**
This section documents some of the noteworthy details on how certain features are implemented

### Mark as completed / uncompleted features

The implementation of the mark as completed and uncompleted features are facilitated by the `DoneTaskCommand` and `
UndoTaskCommand` classes respectively, both of which extends from the Command abstract class.

It is also facilitated by the following Parser Classes:
* `DoneTaskCommandParser`
* `UndoTaskCommandParser`

The above mentioned Parser classes all inherit the `#parse` method from the Parser interface.

* `DoneTaskCommandParser#parse` - checks if the arguments passed to the current DoneCommand is valid and creates an DoneTaskCommand instance if it is.
* `UndoTaskCommandParser#parse` - checks if the arguments passed to the current Undo Command is valid and creates an UndoTaskCommand instance if it is.

Subsequently, the created `DoneTaskCommand` / `UndoTaskCommand` object contains an `#execute` method which is responsible for
updating the status of the Task to "completed" or "uncompleted". This is achieved by creating a new `Task` object with the
same fields and values but updating the `TaskStatus` field according to the input.

Below is the usage scenario and how the mark the task as completed mechanism behaves.

Assumptions:
1. User has already launched the app
2. HEY MATEz application has an existing task with **uncompleted** status

Step 1. User executes the `done 1` command to mark the task with index number 1 in the task list of HEY MATEz to be completed. 
 A ` DoneTaskCommandParser` object is created and it calls `DoneTaskCommandParser#parse` on the arguments

Step 2. `DoneTaskCommandParser#parse` method will check on the validity of the arguments for a `DoneTaskCommand`. If it
is valid,  it will call the create a new `DoneTaskCommand` by calling the constructor.

Step 3. The `DoneTaskCommand#execute` is then called by the `LogicManager`. The task with the same `Index` is retrieved and
a copy of the task is created with the same attribute values. However. the `TaskStatus` value is updated to be **completed**'
in the `Model`.

Step 4. Once the execution is completed, the message `MESSAGE_DONE_TASK_SUCCESS` is used to return a new Command Result
with the attached message.

Below is the sequence diagram:

![#Interactions Inside the Logic Component for the `done 1` Command](images/DoneTaskSequenceDiagram.png)

### Find Tasks with deadline before a selected date feature

The implementation of the finding of tasks with deadline before a selected date is facilitated by the `FindTasksBeforeCommand`
class, from the Command abstract class.

It is also facilitated by the following Parser Class:
* `FindTasksBeforeCommandParser`

The above mentioned Parser class inherits the `#parse` method from the Parser interface.

* `FindTasksBeforeParser#parse` - checks if the deadline passed to the current FindTasksBeforeCommand is in the correct format and is valid, then creates a FindTasksBeforeCommand instance if they are.


Subsequently, the created `FindTasksBeforeCommand` object contains an `#execute` method which is responsible for
updating the task list to contain the tasks with deadlines before the selected date. This is achieved by calling on `Model#updateFilteredTaskList`
with the DeadlineBeforeDatePredicate which updates the task list with tasks that match the given predicate.

Below is the usage scenario and how the finding of tasks due before a selected date mechanism behaves.

Assumptions:
1. User has already launched the app
2. HEY MATEz application has existing tasks with their corresponding deadlines

Step 1. User executes the `findBefore 2021-04-04` command to show the tasks in the task list of HEY MATEz with deadline before 2021-04-04.  A
`FindTasksBeforeCommandParser` is created and it calls `FindTasksBeforeParser#parse` on the arguments

Step 2. `FindTasksBeforeCommandParser#parse` method will check on the validity of the deadline argument for a `FindTasksBeforeCommand`. If is is
valid,  it will create a new `FindTasksBeforeCommand` by calling the constructor with the DeadlineBeforeDatePredicate.

Step 3. The `FindTasksBeforeCommand#execute` is then called by the `LogicManager`. The tasks with deadline before 2021-04-04 are selected by the 
DeadlineBeforeDatePredicate.

Step 4. Once the execution is completed, the message `MESSAGE_TASKS_LISTED_OVERVIEW,` is used to return a new Command Result
with the attached message.

Below is the sequence diagram:

![#Interactions Inside the Logic Component for the `findBefore 2021-04-04` Command](images/FindBeforeSequenceDiagram.png)

### Delete a Task feature

The implementation of the deleting a Task feature is facilitated by the DeleteTaskCommand, which extends from the Command abstract class.

It is also enabled by the following Parser class:
* `DeleteTaskCommandParser`

The above mentioned Parser class inherits from the `#parse method` from the Parser interface.

* `DeleteTaskCommandParser#parser` - checks if the arguments passed to the current DeleteTaskCommand is valid. 
  If the arguments are valid, it creates a DeleteTaskCommand instance.

Subsequently, the created `DeleteTaskCommand` object contains an `#execute` method which 
is responsible for deleting the given Task with respect to the index. 

Below is the usage scenario of how delete task mechanism behaves.

Assumptions:
1. User has already launched the app
2. HEY MATEz application has an existing task

Step 1. User executes the `deleteTask 1` command to delete the task at the 1st index in the task list of HEY MATEz. 
A `DeleteTaskCommandParser` is created and calls the `DeleteTaskCommandParser#parse` on the arguments.

Step 2. `DeleteTaskCommandParser#parse` method checks on the validity of the arguments for a `DeleteTaskCommand`. 
If it is valid, it will create a new `DeleteTaskCommand` by calling the constructor.

Step 3. The `DeleteTaskCommand#execute` is then called by `Logic Manager`. The task with the same `Index` is retrieved 
and deleted from the task list using the `deleteTask` method in the `Model` class.

Step 4. Once the execution is completed, the message `MESSAGE_DELETE_TASK_SUCCESS` is used to return a 
new Command Result with the attached message.

Below is the sequence diagram: 

![#Interactions Inside the Logic Component for the `deleteTask 1` Command](images/DeleteTaskSequenceDiagram.png)

### Clear all Assignee of a Task feature

The implementation of clearing all `Assignee` of a Task feature is facilitated by the `ClearAssigneeCommand` 
class which extends from the Command abstract class.

It is also facilitated by the following Parser Classes:
* `ClearAssigneesCommandParser`

The above mentioned Parser classes all inherit the `#parse` method from the Parser interface.

* `ClearAssigneesCommandParser#parse` - checks if the arguments passed to the current ClearAssigneeCommand is valid. 
  If the arguments are valid, it creates a ClearAssigneeCommand instance.

Subsequently, the created `ClearAssigneeCommand` object contains an `#execute` method which is responsible for
clearing all `Assignee` of the Task, with respect to its index. This is achieved by creating a new 
`Task` object with the same fields and values but setting the Set<Assignee> field as a new empty HashSet. 

Below is the usage scenario of how clear all assignee of a Task mechanism behaves.

Assumptions:
1. User has already launched the app
2. HEY MATEz application has an existing task with **assignee**

Step 1. User executes the `clearAssignees 1` command to mark the task with index number 1 in the task list of 
HEY MATEz to be cleared. A ` ClearAssigneesCommandParser` object is created and it calls `ClearAssigneesCommandParser#parse` 
on the arguments.

Step 2. `ClearAssigneesCommandParser#parse` method will check on the validity of the arguments for a `ClearAssigneesCommand`. 
If it is valid,  it will call the create a new `ClearAssigneesCommand` by calling the constructor.

Step 3. The `ClearAssigneesCommand#execute` is then called by the `LogicManager`. The task with the same `Index` 
is retrieved and a copy of the task is created with the same attribute values. However. the `Set<Assignee>` field is 
updated to be a new empty HashSet in the `Model`.

Step 4. Once the execution is completed, the message `MESSAGE_CLEARED_ASSIGNEES_SUCCESS` is used to return a new Command Result
with the attached message.

Below is the sequence diagram:

![#Interactions Inside the Logic Component for the `clearAssignees 1` Command](images/ClearAssigneeSequenceDiagram.png)

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

* CCA / Club head who is managing many members
* Needs an efficient and organised method to assign tasks to these members.
* Needs to manage a significant number of members
* Prefer desktop apps over other types
* Prefers typing to mouse interactions

**Value proposition**: Manage the tracking and distribution of tasks quickly and efficiently.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | user starting to use the app| be able to add in the contacts of my CCA members | have a better overview of the members in my CCA and access their information easily|
| `* * *`  | forgetful CCA leader | be able to view who have been added into my contacts | retrieve their information and add the remaining members too |
| `* * *`  | CCA leader with many tasks to be done in the CCA | be able to add tasks into the application | better manage the tasks |
| `* * *`  | CCA leader with tasks that are constantly changing in details | be able to edit the details of the tasks accordingly | keep the task details updated |
| `* * *`  | CCA leader with tasks that are no longer relevant | be able to delete tasks that are either completed or unnecessary | keep the list organized and clear |
| `* * *`  | CCA leader with projects and tasks piling up | be able to view a list of tasks that is to be completed by members of the CCA | be updated with what needs to be done |
| `* * *`  | CCA leader who has members leaving the CCA | be able to remove details of the user from that application | better manage my members |
| `* * *`  | CCA leader whose members’ details have changed | be able to edit the details of the user in the application | keep my contacts up-to-date |
| `* * *` | CCA leader with many deadlines to meet | be able to set deadlines for specific tasks | keep track of the tasks' deadlines |
| `* * *` | CCA leader with tasks to distribute | be able to assign tasks to students in the application | track the distribution of tasks |
| `* * `  | CCA leader with tasks that are being completed every other week | be able to mark tasks as ‘completed’  | track which tasks are completed |
| `* * `  | CCA leader | be able to change the assignee of a task | easily update the member assigned to the task |
| `* * `  | CCA leader | be able to assign a priority to a task | see which tasks need to be completed first |
| `* * `  | CCA leader who has many tasks to supervise | be able to search for tasks which are of a certain priority | better manage tasks of different priorities |
| `* * `  | CCA leader whose members have different roles | be able to assign roles to the members in the application | know who has a certain role |
| `* * `  | CCA leader whose members have different roles | be able to edit the roles to the members in the application  | update the member roles when there is a change their roles |
| `* `  | CCA leader who is very forgetful | be notified if I have accidentally added duplicate items/tasks | prevent repeated tasks from being added |
| `* `  | CCA leader who has a lot of tasks to manage | search for tasks which contain a particular word  | find tasks more efficiently |
| `* `  | forgetful CCA leader who has forgotten which tasks have not been assigned | be able to get a list of tasks that have not been assigned | distribute tasks more efficiently |
| `* `  | CCA leader | be able to get a list of tasks that have not be completed | check on the progress of the tasks |
| `* `  | CCA leader | be able to get a list of tasks whose deadlines occur before a certain date | make sure I don't miss those deadlines |
| `* `  | CCA leader | be able to get a list of tasks assigned to a particular member | check how much work has been assigned to a particular member |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `HEY MATEz` and the **Actor** is the `user`, unless specified otherwise)

:pencil2: **Use case: Add a member**

**MSS**

1.  User requests to add a member
2.  HEY MATEz adds the member to the list of members

    
Use case ends.

**Extensions**

* 1a. Member already exists (Matching Name) in the list
    * 1a1. HEY MATEz shows an error message
  
Use case ends.

:pencil2: **Use case: View members**

**MSS**

1.  User requests to view the members
2.  HEY MATEz lists the members in the list, and their contact details
    
Use case ends.

:pencil2: **Use case: Delete member**

**MSS**

1.  User requests to delete a member using the member's name
2.  HEY MATEz searches for the member 
3.  HEY MATEz deletes the member from the list
    
Use case ends. 

**Extensions**

* 2a. Member does not exist in the list of members
    * 2a1. HEY MATEz shows an error message
  
Use case ends.

:pencil2: **Use case: Edit member's details**

**MSS**

1.  User requests to edit the details of a member using the member's name
2.  HEY MATEz searches for the member 
3.  HEY MATEz edits the corresponding member with the new details 
    
Use case ends. 

**Extensions**

* 2a. Member does not exist in the list of members
    * 2a1. HEY MATEz shows an error message
  
Use case ends.

:pencil2: **Use case: Find members using keywords**

**MSS**

1.  User requests to find members using the keywords specified
2.  HEY MATEz searches through each member's details 
3.  HEY MATEz lists members whose details matches any of the keywords 
    
Use case ends.

:pencil2: **Use case: Add a task**

**MSS**

1.  User requests to add a task
2.  HEY MATEz adds the task to the task board
    
Use case ends.

**Extensions**

* 2a. Task already exists (Matching Title) in the list
    * 2a1. HEY MATEz shows an error message
  
Use case ends.

:pencil2: **Use case: View Tasks**

**MSS**

1.  User requests to view the list of tasks
2.  HEY MATEz lists the tasks along with the description 
    
Use case ends.

:pencil2: **Use case: Delete Task**

**MSS**

Similar to deleting a member but user specifies task index instead of name

**Extensions**

* 2a. Task index does not exist in the list of tasks
    * 2a1. HEY MATEz shows an error message
  
Use case ends.

:pencil2: **Use case: Edit a task**

**MSS**

Similar to editing a member except that the user specifies task index instead of name

**Extensions**

* 1a. Task does not exist (Task index out of bounds)
    * 1a1. HEY MATEz shows an error message
  
Use case ends.

:pencil2: **Use case: Find tasks using keywords**

**MSS**

Similar to finding members with keywords except that HEY MATEz 
lists tasks with its title or description matching any of the keywords

:pencil2: **Use case: Find tasks with deadline before a specified date**

**MSS**

1. User requests to find tasks which deadlines before their specified date
2. HEY MATEz searches through tasks' deadline 
3. HEY MATEz lists tasks with deadlines before the specified date
   
Use case ends.

:pencil2: **Use case: Find tasks using Priority**

**MSS**

1.  User requests to find tasks using the input (A priority)
2.  HEY MATEz searches through each tasks' priority
3.  HEY MATEz lists tasks with a matching priority.
    
Use case ends.

Extensions
* 1a. The input value by the user is not a valid priority
    * 1a1. HEY MATEz shows an error message
      
Use case ends.

:pencil2: **Use case: Mark a Task as Completed**

**MSS**

1.  User requests to mark a task as completed using the task's index
2.  HEY MATEz searches for the task index and changes the status of the task in the list to be completed
    
Use case ends.

**Extensions**
* 1a. The index specified by the user does not exist
    * 1a1. HEY MATEz shows an error message
  
Use case ends.

:pencil2: **Use case: Mark a Task as Uncompleted**

**MSS**

1.  User requests to mark a task as uncompleted using the task's index
2.  HEY MATEz searches for the task index and changes the status of the task in the list to be uncompleted
    
Use case ends.

**Extensions**
* 1a. The index specified by the user does not exist
    * 1a1. HEY MATEz shows an error message
  
Use case ends.

:pencil2: **Use case: Remove all Members Assigned to a Task**

**MSS**

1.  User requests to remove all assignees from a task, using the task's index
2.  HEY MATEz searches for the task index 
3.  HEY MATEz removes all members assigned to the task

**Extensions**
* 2a. The index specified by the user does not exist
    * 2a1. HEY MATEz shows an error message

Use case ends.

:pencil2: **Use case: View Uncompleted Tasks**

**MSS**

1.  User requests to view the list of uncompleted tasks
2.  HEY MATEz lists the uncompleted tasks along with the description 
    
Use case ends.

:pencil2: **Use case: Find all Tasks assigned to a particular Members**

**MSS**

1.  User requests to find tasks using the user input
2.  HEY MATEz searches through each tasks' and checks the tasks' assignees
3.  HEY MATEz lists tasks with a matching assignee.
    
Use case ends.

**Extensions**
* 1a. The user does not enter any input as the parameters
    * 1a1. HEY MATEz shows an error message
  
Use case ends.

### Non-Functional Requirements
1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Data should be persistent after exiting and reopening the app
3. App should run locally on the user's computer

*{More to be added}*

### Glossary
* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **CCA / Club head**: A person who has a position in a Club / CCA and is required to manage the tasks and members within the club

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a Member

1. Deleting a Member while all members are being shown

   1. Prerequisites: List all members using the `viewMembers` command. Multiple members in the list. A member with the name of `Alice` exists in the list. No member with the name `Barry` exists in the list

   1. Test case: `delete Alice`<br>
      Expected: Member with the name `Alice` is deleted from the list. Details of the deleted contact shown in the status message.

   1. Test case: `delete Barry`<br>
      Expected: No person is deleted. Error details shown in the status message.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is a name which does not exist in the list)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
