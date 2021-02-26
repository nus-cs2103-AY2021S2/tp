---
## User Guide

SOChedule is a one-stop solution for managing tasks and events, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

## Feature List
* Deleting a task: `delete task` 
* Marking a task as done : `done task`
* Adding an event: `add event`
* Deleting an event: `delete event`
* Listing all events: `list event`


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `SOChedule.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your SOChedule.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Adding a task: `add task`
Adds a task to SOChedule Task List.

Format: `add task n/TASKNAME [d/DEADLINE] [p/PRIORITY] [c/CATEGORY]... [t/TAG]...`
* n/: is followed by the task name.
* d/: is followed by the date, with the format YYYY-MM-DD. It is optional.
* p/: is followed by the priority, with 0 being highest and 9 being lowest. Other inputs are not accepted. It is optional.
* c/: is followed by the category. It is optional.
* t/: is followed by the tag. It is optional.

Examples:
* `add task n/CS2103 assignment d/2021-02-27 p/1 c/school work t/urgent` adds a new task named "CS2103 assignment" with the respective parameters.
* `add task n/CCA admin work d/2021-02-28 p/2 c/CCA` adds a new task "CCA admin work" with the respective parameters.

### Listing all tasks: `list task`
Lists all tasks from SOChedule Task List.

Format: `list task`

### Deleting a task: `delete task`
Deletes a task from SOChedule Task List.

Format: `delete task i/ID`
* `i/`: is followed by the task id.

Examples:
* `delete task i/2` deletes the second task in the task list.

### Marking a task as done: `done task`
Marks a task from SOChedule Task List as completed.

Format: `done task i/ID`
* `i/`: is followed by the task id.

Examples:
* `done task i/1` marks the first task in the task list as completed.

### Adding an event: `add event`
Adds an event to the SOChedule Event Scheduler.
Format: `add event n/TASKNAME d/DATE (YYYY-MM-DD)`
* `n/`is followed by the task name, it is case-sensitive.
* `d/`is followed by the event date, it has to be a **valid date**
  and in the format of **YYYY-MM-DD**. 
  Here, Y is the year, M is the month, D is the day and all has to be integers.
  
Examples:
* `add event n/CS2103 meeting d/2021-02-27` adds an event with name `CS2103` and date `2021-02-27` 
to the SOChedule Event Scheduler.
  
### Deleting an event: `delete event`
Deletes an event from the SOChedule Event Scheduler.

Format: `delete event i/ID`
* `i/`: is followed by the event id.

Examples:
* `delete event i/3` deletes the third event from the Event Scheduler.

### Listing all events: `list event`
Lists all events from SOChedule Event Scheduler.
Format: `list event`

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add task n/TASKNAME [d/DEADLINE] [p/PRIORITY] [c/CATEGORY]... [t/TAG]...` <br> e.g., `add task n/CS2103 assignment d/2021-02-27 p/1 c/school work t/urgent`
**Delete** | `delete task i/2`<br>`delete event i/3`<br> e.g., `delete task i/2`
**Done** | `done task i/INDEX`<br>e.g., `done task i/1`**List** | `list`
**List** | `list task`
**Help** | `help`
**Exit** | `exit`
