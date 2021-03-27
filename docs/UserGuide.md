---
layout: page
title: User Guide

#User Guide

---

Taskify is a **desktop app for students manage their tasks (academics/personal/CCA) in a systematic and efficient manner, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Taskify can get your task management done faster than traditional GUI apps.

This user guide is to help you learn how to use Taskify to manage your tasks efficiently.

* Table of Contents
{:toc}
  

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `taskify.jar` from [here](https://github.com/AY2021S2-CS2103T-W14-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Taskify.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try can be seen [here](#command-summary).
    



1. Refer to the [Features](#features) below for details of more commands and details.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Finish Tutorial`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/Finish Tutorial t/CS2103T` or as `n/Finish Tutorial`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/CS2103T`, `t/Assignment` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME desc/DESCRIPTION`, `desc/DESCRIPTION n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `NAME/Finish Tutorial p/Watch Lecture`, only `p/Finish Tutorial` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`, `sort` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
</div>



### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a task : `add`

Adds a task to Taskify.

Format: `add n/NAME desc/DESCRIPTION date/DATE [t/TAG]…`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A Task can have any number of tags (including 0)
</div>

Examples:
* `add n/myTask1 desc/1st Task date/2020-04-13`
* `add n/Visit Prisoner desc/another task date/1999-12-12 t/criminal`

### Deleting multiple tasks : `delete`

Delete multiple tasks at once by either:
1. Listing the indexes of the tasks to delete exhaustively
2. Stating the range of indexes to delete
3. Indicating the desired `Status` of tasks to delete

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …


* Listing the indexes exhaustively
    * Format: `delete [INDEX...]`
    * Examples: `delete 1 2 3` deletes the 1st, 2nd, 3rd task as displayed when `list` is used
* Stating the range of indexes
    * Format: `delete INDEX-INDEX`
    * Examples: `delete 1-3` deletes the 1st, 2nd, 3rd task as displayed when `list` is used
    * Notes:
        * `delete 2-2` does not delete the 2nd task. Use `delete 2` instead
* Indicating the `Status` to delete by
    * Format: `delete STATUS -all`
    * Examples: `delete in progress -all` deletes **all** tasks that are in progress as their `Status`.
    * Notes:
        * All tasks have one of the 3 `Status`: `in progress`, `completed`, `expired`
        * Newly created tasks have `In progress` as their `Status`
    
    



### Editing a task : `edit`

Edits an existing task in Taskify.

Format: `edit INDEX [n/NAME] [desc/DESCRIPTION] [date/DATE] [t/TAG]…​`

* Edits the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the task will be removed i.e adding of tags is not cumulative.
* You can remove all the task’s tags by typing `t/` without specifying any tags after it.

Examples:
*  `edit 1 desc/my typical description` Edits the description of the 1st task to be `my typical description`.
*  `edit 2 n/Important Task t/` Edits the name of the 2nd task to be `Important Task` and clears all existing tags.


### Locating a task by name : `find`

Find tasks whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `cs2103t` will match `CS2103T`
* The order of the keywords does not matter. e.g. `Finish CS2103T Tutorial` will match `CS2103T Tutorial Finish`
* Only the name of the task is searched.
* Only full words will be matched e.g. `CS2103` will not match `CS2103T`
* Tasks matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `CS2103T` will return `Finish CS2103T Tutorial`, `Watch CS2103T Lecture`

Examples:
* `find The Task` returns `The Next Task` and `Task for tomorrow`, but does not return `TheTask`

### Switching between tabs :`home` / `inProgress` / `completed` / `expired`

Switching between different tabs in Taskify.

Format: `home` / `inProgress` / `completed` / `expired`


### Listing all tasks : `list`

Shows a list of all tasks in Taskify.

Format: `list`


### Clearing all tasks : `clear`

Clears all tasks in Taskify.

Format: `clear`


### Searching for a task using tags : `tag-search`

Searches for a task using tags (instead of name).

Format: `tag-search TAG [MORE_TAGS]`

### Setting a task's status : `edit`

Sets a task's status.

Format: `edit INDEX s/STATUS`

* A task can have 3 types of status: `in progress`, `completed`, `expired`
* A task's default status is `In progress`

Examples:
* `edit 2 s/in progress` sets the status of the 2nd task in the list to `in progress`.


### Sort tasks by date : `sort`

Sort tasks in ascending order of their dates.

Format: `sort`

### Exiting the program : `exit`

Exits the program.

Format: `exit`



--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: Does Taskify only work for NUS Students? <br>
**A**: No, although Taskify is catered to NUS students, we welcome everyone interested in Taskify to use it.<br>

**Q**: Can I specify a status when adding a new task to Taskify?<br>
**A**: All newly added tasks have an "In progress" status by default. However, you can change the status of a task using the`edit` command. <br>

--------------------------------------------------------------------------------------------------------------------

## Command summary

Command | Format | Examples
--------|--------|----------
**Add** | `add n/NAME desc/DESCRIPTION date/DATE [t/TAG]…` | `add n/Finish CS2103T Tutorial desc/another task date/2021-12-12 t/Assignment`
**Clear** | `clear` |
**Completed** | `completed` |
**Delete** | `delete INDEX` `delete INDEX INDEX ...` `delete INDEX-INDEX` `delete STATUS -all`| `delete 3` `delete 4 10 6` `delete 5-8` `delete completed -all`
**Edit** | `edit INDEX [n/NAME] [desc/DESCRIPTION] [date/DATE] [t/TAG] [s/STATUS]…` | `edit 1 s/completed`
**Exit** | `exit` |
**Expired** | `expire` |
**Find** | `find KEYWORD [MORE_KEYWORDS]` | `find Module Code`
**In Progress** | `inProgress` |
**Help** | `help` |
**Home** | `home` |
**List** | `list` |
**Sort** | `sort` |
**Tag-Search** | `tag-search TAG [MORE_TAGS]` | `tag-search CS2103T isFun`

