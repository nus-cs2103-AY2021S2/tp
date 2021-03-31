---
layout: page
title: User Guide
---
## User Guide

SOChedule is a one-stop solution for managing tasks and events, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

## Feature List
* Adding a task: `add_task`
* Deleting a task: `delete_task`
* Marking one or more tasks as done : `done_task`
* Marking a task as uncompleted : `undone_task`
* Editing a task: `edit_task`
* Finding tasks: `find_task`
* Listing all tasks: `list_task`
* Getting today's tasks: `today_task`
* Sorting all tasks: `sort_task`
* Pinning a task: `pin_task`
* Unpinning a task: `unpin_task`
* Clearing completed tasks: `clear_completed_task`
* Clearing expired tasks: `clear_expired_task`
* Adding an event: `add_event`
* Deleting an event: `delete_event`
* Editing a task: `edit_event`
* Listing all events: `list_event`
* Getting today's events: `today_event`
* Finding events: `find_event`
* Clearing expired events: `clear_expired_event`
* Finding tasks and events before or on a given date: `find_schedule`


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `SOChedule.jar` from [link coming soon].

1. Copy the file to the folder you want to use as the _home folder_ for your SOChedule.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list_task`** : Lists all tasks.

   * **`add_task`**`n/CS2103 assignment d/2021-02-27 p/1 c/school work t/urgent` : Adds a task named `CS2103 assignment` to the SOChedule with its respective attributes.

   * **`delete_task`**`3` : Deletes the 3rd task shown in the current list.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Adding a task: `add_task`
Adds a task to SOChedule Task List.

Format: `add_task n/TASKNAME d/DEADLINE p/PRIORITY [c/CATEGORY]... [t/TAG]...`
* `n/` is followed by the task name.
* `d/` is followed by the date, with the format YYYY-MM-DD, deadline cannot be a past date.
* `p/` is followed by the priority, with 0 being highest and 9 being lowest. Other inputs are not accepted.
* `c/` is followed by the category. It is optional.
* `t/` is followed by the tag. It is optional.

Examples:
* `add_task n/CS2103 assignment d/2022-02-27 p/1 c/schoolwork t/urgent` adds a new task named "CS2103 assignment" with the respective parameters.
* `add_task n/CCA admin work d/2022-02-28 p/2 c/CCA` adds a new task "CCA admin work" with the respective parameters.

### Listing all tasks: `list_task`
Lists all tasks from SOChedule Task List.

Format: `list_task`

### Deleting a task: `delete_task`
Deletes a task from SOChedule Task List.

Format: `delete_task INDEX`
* Deletes the task at the specified INDEX.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive and valid integer 1, 2, 3, ...

Examples:
* `delete_task 2` deletes the second task in the task list.

### Marking a task as done: `done_task`
Marks one or more task from SOChedule Task List as completed.

Format: `done_task INDEX1 [INDEX2] ...`
* Marks the task(s) at the specified INDEX(es) as complete.
* When multiple indexes are provided, they should be seperated by a whitespace, e.g. `1 2`.
* All specified tasks must be uncompleted before calling this command.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive and valid integer 1, 2, 3, ...

Examples:
* `done_task 1` marks the first task in the task list as completed.
* `done_task 1 2` marks the first and second task in the task list as completed.

### Marking a task as uncompleted: `undone_task`
Marks a completed task from SOChedule Task List as uncompleted.

Format: `undone_task INDEX`
* Marks the task at the specified INDEX as uncompleted.
* The specified task must be completed before calling this command.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive and valid integer 1, 2, 3, ...

Examples:
* `undone_task 1` marks the first task in the task list as uncompleted.

### Editing a task: `edit_task`
Edits an **existing and uncompleted** task in SOChedule.

Format: `edit_task INDEX [n/TASKNAME] [d/DEADLINE] [p/PRIORITY] [c/CATEGORY]... [t/TAG]...`
* Edits the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
* You can only edit the details of an uncompleted task.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags/categories, the existing tags/categories of the task will be removed i.e adding of tags/categories is not cumulative.
* You can remove all the task’s tags by typing `t/` without specifying any tags after it. 
  Similarly, you can remove all the task’s categories by typing `c/` without specifying any categories after it.

Examples:
* `edit_task 1 n/editedTaskName` edits the name of the first task (if present in SOChedule) to be `editedTaskName`.
* `edit_task 2 p/3 t/` edits the priority of the second task (if present in SOChedule) to be `3` and clears all existing tags.

### Listing all tasks today: `today_task`
Lists all tasks that have deadline on today from SOChedule Task List.

Format: `today_task`

### Finding all matching tasks: `find_task`
Finds matching tasks from Task List.

Format: `find_task KEYWORDS`
* Finds the tasks whose names contain the given keywords.
* The keywords are case-insensitive.
* A list of matching tasks will be displayed with their indexes.

### Sorting all tasks: `sort_task`
Sorts SOChedule Task List.

Format: `sort_task ARGUMENT`
* Sorts task list and updates UI based on supplied argument.
* Accepted arguments (case-sensitive):
   * `name`: Sorts by task name, in increasing order
   * `deadline`: Sorts by task deadline, in increasing order
   * `completion`: Sorts by task completion status, with completed tasks at the bottom
   * `priority`: Sorts by task priority, in decreasing order

Examples:
* `sort_task completion` sorts the task list by completion status.
* `sort_task name` sorts the task list by name.

### Pinning a task: `pin_task`
Pins a task from SOChedule Task List.

Format: `pin_task INDEX`
* Pins the task at the specified INDEX.
* Already pinned tasks will be unable to be pinned a second time.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive and valid integer 1, 2, 3, ...
* After pinning, the Task List will be sorted either according to previously entered `sort_task` command, or name (by default).
* Pinned tasks are persistent over instances of SOChedule.

Examples:
* `pin_task 1` pins the first task in Task List

### Unpinning a task: `unpin_task`
Unpins a task from SOChedule Task List.

Format: `unpin_task INDEX`
* Unpins the task at the specified INDEX.
* Follows similar restrictions to `pin_task`

Examples:
* `unpin_task 1` unpins the first task in Task List

### Clearing completed tasks: `clear_completed_task`
Clear tasks marked as completed.

Format: `clear_completed_task`

### Clearing expired tasks: `clear_expired_task`
Clear tasks with past deadlines.

Format: `clear_expired_task`

### Adding an event: `add_event`
Adds an event to the SOChedule Event Scheduler.
Format: `add_event n/TASKNAME sd/STARTDATE st/STARTTIME ed/ENDDATE et/ENDTIME [c/CATEGORY]... [t/TAG]...`
* `n/` is followed by the task name, it is case-sensitive.
* `sd/` is followed by the starting date, it has to be a **valid date** and in the format of **YYYY-MM-DD**. Here, Y is the year, M is the month, D is the day and all has to be integers.
* `st/` is followed by the time in 24-hour format and in the format of **hh:mm** Here, h is the hour, m is the minute and all has to be integers.
* `ed/` is followed by the end date, it has to be a **valid date** and in the format of **YYYY-MM-DD**.
* `et/` is followed by the time in 24-hour format and in the format of **hh:mm**.
* The STARTDATE and STARTTIME provided should be earlier than ENDDATE and ENDTIME.
* The ENDDATE and ENDTIME provided cannot be a past date time.
* `c/` is followed by the category. It is optional.
* `t/` is followed by the tag. It is optional.

Examples:
* `add_event n/CS2103 meeting sd/2021-05-27 st/15:00 ed/2022-02-27 et/17:00` adds an event with name `CS2103` and its respective attributes to the SOChedule Event Scheduler.

### Deleting an event: `delete_event`
Deletes an event from the SOChedule Event Scheduler.

Format: `delete_event INDEX`
* Deletes the event at the specified INDEX.
* The index refers to the index number shown in the displayed event list.
* The index must be a positive and valid integer 1, 2, 3, ...

Examples:
* `delete_event 3` deletes the third event from the Event Scheduler.

### Editing a event: `edit_event`
Edits an **existing and uncompleted** event in SOChedule.

Format: `edit_event INDEX [n/EVENTNAME] [sd/STARTDATE] [st/STARTTIME] [ed/ENDDATE] [et/ENDTIME] [c/CATEGORY]... [t/TAG]...`
* Edits the event at the specified `INDEX`. The index refers to the index number shown in the displayed event list. The index **must be a positive integer** 1, 2, 3, …​
* You can only edit the details of an unexpired event.
* At least one of the optional fields must be provided.
* The STARTDATE and STARTTIME provided should be earlier than ENDDATE and ENDTIME.
* The ENDDATE and ENDTIME provided cannot be a past date time.
* Existing values will be updated to the input values.
* When editing tags/categories, the existing tags/categories of the event will be removed i.e adding of tags/categories is not cumulative.
* You can remove all the event’s tags by typing `t/` without specifying any tags after it. 
  Similarly, you can remove all the event’s categories by typing `c/` without specifying any categories after it.

Examples:
* `edit_event 1 n/editedEventName` edits the name of the first event (if present in SOChedule) to be `editedEventName`.
* `edit_event 2 sd/2021-03-18 t/` edits the start date of the second event (if present in SOChedule) to be `2021-03-18` and clears all existing tags.

### Listing all events: `list_event`
Lists all events from SOChedule Event Scheduler.
Format: `list_event`

### Listing all events today: `today_event`
Lists all events whose duration have overlap with today from the Event Scheduler.

Format: `today_event`

### Finding all matching events: `find_event`
Finds matching events from Event Scheduler.

Format: `find_event KEYWORDS`
* Finds the events whose names contain given keywords.
* The keywords are case-insensitive.
* A list of matching events will be displayed with their indexes.


### Clearing expired events: `clear_expired_event`
Clear tasks with past end date time.

Format: `clear_expired_event`

### Finding tasks and events before or on a given date: `find_schedule`
Finds ongoing tasks and events before or on the specified date in SOChedule.

Format: `find_schedule DATE`
* **Ongoing tasks** refer to **uncompleted tasks** with deadline before or on the specified date
* **Ongoing events** refer to events with start date before or on the specified date and end date after or on the specified date, 
  i.e., `event start date <= given date <= event end date`
* Date entered must be a valid date and in the format of `YYYY-MM-DD`, e.g. `2021-04-01`
* Only one single date can be entered. If more than one dates are supplied, program will return an error message
  indicating invalid date.
* After running `find_schedule`, if you wish to view all existing tasks and all existing events, 
  please use the `list_task` and `list_event` respectively.

Examples:
* `find_schedule 2021-06-01` finds all existing tasks with deadline and all existing events with start date 
  before or on `1st June 2021`.

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SOChedule home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

###General commands
Action | Format, Examples
--------|------------------
**Help** | `help`
**Exit** | `exit`

###Task-related commands
Action | Format, Examples
--------|------------------
**Add** | `add_task n/TASKNAME d/DEADLINE p/PRIORITY [c/CATEGORY]... [t/TAG]...` <br> e.g., `add_task n/CS2103 assignment d/2021-02-27 p/1 c/school work t/urgent`
**Delete** | `delete_task INDEX`<br>e.g., `delete_task 1`
**Done** | `done_task INDEX1 [INDEX2] ...`<br>e.g., `done_task 1 2`
**Undone** | `undone_task INDEX`<br>e.g., `undone_task 1`
**Edit** | `edit_task INDEX [n/TASKNAME] [d/DEADLINE] [p/PRIORITY] [c/CATEGORY]... [t/TAG]...` <br> e.g., `edit_task 1 n/editedTaskName`
**List** | `list_task`
**Today** | `today_task`
**Find** | `find_task KEYWORDS`<br>e.g., `find_task homework`
**Sort** | `sort_task ARGUMENT`<br>e.g., `sort_task name`
**Pin** | `pin_task INDEX`<br>e.g., `pin_task 1`
**Unpin** | `unpin_task INDEX`<br>e.g., `unpin_task 1`
**Clear Completed** | `clear_completed_task`
**Clear Expired** | `clear_expired_task`

###Event-related commands
Action | Format, Examples
--------|------------------
**Add** | `add_event n/TASKNAME sd/STARTDATE st/STARTTIME ed/ENDDATE et/ENDTIME [c/CATEGORY]... [t/TAG]...`<br> e.g., `add_event n/CS2103 meeting sd/2021-02-27 st/15:00 ed/2021-02-27 et/17:00`
**Delete** | `delete_event INDEX`<br>e.g., `delete_event 3`
**List** | `list_event`
**Today** | `today_event`
**Find** | `find_event KEYWORDS`<br>e.g., `find_event meeting`
**Clear Completed** | `clear_expired_event`

### Commands related to both task and event
Action | Format, Examples
--------|------------------
**Find Schedule** | `find_schedule DATE` <br>e.g., `find_schedule 2021-06-01`
