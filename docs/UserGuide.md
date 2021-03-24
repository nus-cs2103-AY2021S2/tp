---
layout: page
title: User Guide

#User Guide

---

Taskify is a **desktop app for students manage their tasks (academics/personal/CCA) in a systematic and efficient manner, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Taskify can get your task management done faster than traditional GUI apps.

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
   Some example commands you can try:

    * **`list`** : Lists all tasks.

    * **`add`**`[TASK Description]` : Adds a task with the specified description to the task list

    * **`delete`**`3` : Deletes the task with the specified task number.

    * **`help`** : Show all the commands and formats.

    * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of more commands and details.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
</div>



### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a task : `add`

Adds a task to Taskify.

Format: `add n/NAME desc/DESCRIPTION a/ADDRESS date/DATE [t/TAG]…`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/myTask1 desc/1st Task a/John street, block 123, #01-01 date/2020-04-13`
* `add n/Visit Prisoner desc/another task a/Newgate Prison date/1999-12-12 t/criminal`

### Deleting a task : `delete`

Deletes the specified task from Taskify.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `delete 2` deletes the 2nd task
* `find Betsy` followed by `delete 1` deletes the 1st task in the results of the `find` command.


### Editing a task : `edit`

Edits an existing task in Taskify.

Format: `edit INDEX [n/NAME] [desc/DESCRIPTION] [a/ADDRESS] [date/DATE] [t/TAG]…​`

* Edits the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.

Examples:
*  `edit 1 desc/my typical description` Edits the description of the 1st task to be `my typical description`.
*  `edit 2 n/Important Task t/` Edits the name of the 2nd task to be `Important Task` and clears all existing tags.


### Locating a task by name : `find`

Find tasks whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find The Task` returns `The Next Task` and `Task for tomorrow`, but does not return `TheTask`


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

* A task can have 3 types of status: `not done`, `in progress`, `completed`
* A task's default status is `not done`

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

**Q**: How do I do something?<br>
**A**: Steps to do something<br>
[Coming Soon]

--------------------------------------------------------------------------------------------------------------------

## Command summary

Command | Format | Examples
--------|--------|----------
**Add** | `add n/NAME desc/DESCRIPTION a/ADDRESS date/DATE [t/TAG]…` | `add n/Visit Prisoner desc/another task a/Newgate Prison date/1999-12-12 t/criminal`
**Clear** | `clear` |
**Delete** | `delete INDEX` | `delete 3`
**Edit** | `edit INDEX [n/NAME] [desc/DESCRIPTION] [a/ADDRESS] [date/DATE] [t/TAG] [s/STATUS]…` | `edit 1 s/completed`
**Exit** | `exit` | 
**Find** | `find KEYWORD [MORE_KEYWORDS]` | `find Module Code`
**List** | `list` |
**Help** | `help` |
**Sort** | `sort` |
**Tag-Search** | `tag-search TAG [MORE_TAGS]` | `tag-search CS2103T isFun`

