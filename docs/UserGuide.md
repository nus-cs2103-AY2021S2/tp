---
layout: page
title: User Guide
---
## User Guide

SOChedule is a one-stop solution for managing tasks and events, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

## Feature List
* Adding a task: `add_task`
* Deleting a task: `delete_task`
* Listing all tasks: `list_task`
* Marking a task as done : `done_task`
* Sorting all tasks: `sort_task`
* Clearing completed tasks: `clear_completed_task`
* Clearing expired tasks: `clear_expired_task`
* Adding an event: `add_event`
* Deleting an event: `delete_event`
* Listing all events: `list_event`
* Clearing completed tasks: `clear_expired_event`


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
Marks a task from SOChedule Task List as completed.

Format: `done_task INDEX`
* Marks the task at the specified INDEX as complete.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive and valid integer 1, 2, 3, ...

Examples:
* `done_task 1` marks the first task in the task list as completed.

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
* `ed/` is followed by the end date, it has to be a **valid date** and in the format of **YYYY-MM-DD**, end date cannot be a past date.
* `et/` is followed by the time in 24-hour format and in the format of **hh:mm**. If end date is today, then end time cannot be past time.
* `c/` is followed by the category. It is optional.
* `t/` is followed by the tag. It is optional.

Examples:
* `add_event n/CS2103 meeting sd/2021-05-27 st/15:00 ed/2021-02-27 et/17:00` adds an event with name `CS2103` and its respective attributes to the SOChedule Event Scheduler.

### Deleting an event: `delete_event`
Deletes an event from the SOChedule Event Scheduler.

Format: `delete_event INDEX`
* Deletes the event at the specified INDEX.
* The index refers to the index number shown in the displayed event list.
* The index must be a positive and valid integer 1, 2, 3, ...

Examples:
* `delete_event 3` deletes the third event from the Event Scheduler.

### Listing all events: `list_event`
Lists all events from SOChedule Event Scheduler.
Format: `list_event`

### Clearing expired events: `clear_expired_event`
Clear tasks with past end date time.

Format: `clear_expired_event`

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
**Done** | `done_task INDEX`<br>e.g., `done_task 1`
**List** | `list_task`
**Sort** | `sort_task ARGUMENT`<br>e.g., `sort_task name`
**Clear completed** | `clear_completed_task`
**Clear expired** | `clear_expired_task`

###Event-related commands
Action | Format, Examples
--------|------------------
**Add** | `add_event n/TASKNAME sd/STARTDATE st/STARTTIME ed/ENDDATE et/ENDTIME [c/CATEGORY]... [t/TAG]...`<br> e.g., `add_event n/CS2103 meeting sd/2021-02-27 st/15:00 ed/2021-02-27 et/17:00`
**Delete** | `delete_event INDEX`<br>e.g., `delete_event 3`
**List** | `list_event`
**Clear completed** | `clear_completed_task`
