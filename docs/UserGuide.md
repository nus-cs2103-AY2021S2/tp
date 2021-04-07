---
layout: page
title: User Guide

#Taskify - User Guide

---
Taskify is a **desktop app for university students** who have a seemingly endless list of tasks to finish week 
after week. Many students **struggle to track all their tasks in a systematic and efficient manner** and this is 
where Taskify comes to the rescue! With Taskify, students can 
**manage all their tasks (academics/personal/CCAs) 
effectively and seamlessly** through a beautiful interface. <br><br>Taskify is optimized for use via a Command Line 
Interface (CLI) while 
still having the 
benefits    
of a Graphical User Interface (GUI). If you can type fast, Taskify can get your task management done faster than traditional GUI apps.



This user guide is to help you learn how to use Taskify to manage your tasks efficiently.

* Table of Contents
{:toc}
  

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer. Refer to [this guide](https://java.com/en/download/help/download_options.html) to install Java.
2. Download the latest `taskify.jar` from [here](https://github.com/AY2021S2-CS2103T-W14-4/tp/releases).

3. Copy the `taskify.jar` file to the folder you want to use as the _home folder_ for Taskify.

4. Double-click the `taskify.jar` file to start the app. The GUI similar to the below should appear in a few seconds.
   
   <div markdown="span" class="alert alert-primary">:bulb: **Tip:**
    The app comes preloaded with sample data to start you off!
   </div>
   
   ![Ui](images/Ui.png)
   

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will 
   open the help window.
   Some example commands you can try can be seen [here](#command-summary).

6. Refer to the [Features](#features) below for the details of the main commands.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Finish Tutorial`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/Finish Tutorial t/CS2103T` or as `n/Finish Tutorial`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/CS2103T`, `t/ CS2103T t/Assignment` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME desc/DESCRIPTION`, `desc/DESCRIPTION n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `n/Finish Tutorial n/Watch Lecture`, only `n/Finish Tutorial` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`, `sort` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
</div>



### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a task : `add`

Adds a task to Taskify.

Format: `add n/NAME desc/DESCRIPTION [date/DATE] [t/TAG]…`

* The name of the task that is inputted is case-sensitive

* If a date is not specified in the command, the newly added Task will have its date set to the end of today (Today's date, 2359hrs).

* If you add more than 2 add commands in the command box, only need the last add command is recognized.


<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A Task can have any number of tags (including 0)
</div>

Examples:
* `add n/CS2100 Finals desc/Revise for Finals date/2021-04-13 12:00`
* `add n/Consult Professor desc/Discuss project with prof date/2021-04-04 10:30 t/project`
* `add n/Buy groceries desc/Don't forget tomatoes`

### Listing all tasks : `list`

View a list of all tasks in Taskify.

Format: `list`


### Editing a task : `edit`

Edits an existing task in Taskify.

Format: `edit INDEX [n/NAME] [desc/DESCRIPTION] [date/DATE] [s/STATUS] [t/TAG]…`

* Edits the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the task will be removed i.e adding of tags is not cumulative.
* You can remove all the task’s tags by typing `t/` without specifying any tags after it.
* There are 3 Task statuses: `expired`, `uncompleted` and `completed`. However you can cannot directly modify task's status to `expired`

Examples:
*  `edit 1 desc/my typical description` Edits the description of the 1st task to be `my typical description`.
*  `edit 2 n/Important Task t/` Edits the name of the 2nd task to be `Important Task` and clears all existing tags.
* `edit 2 s/completed` sets the status of the 2nd task in the list to `completed`.

### Deleting multiple tasks : `delete`

Delete multiple tasks at once by either:
1. Listing the indexes of the tasks to delete exhaustively
2. Stating the range of indexes to delete
3. Indicating the desired `Status` of tasks to delete

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …


* Listing the indexes exhaustively
    * Format: `delete INDEX [MORE_INDICES]`
    * Examples: `delete 1 2 3` deletes the 1st, 2nd, 3rd task as displayed when `list` is used
* Stating the range of indexes
    * Format: `delete INDEX-INDEX`
    * Examples: `delete 1-3` deletes the 1st, 2nd, 3rd task as displayed when `list` is used
    * Notes:
        * `delete 2-2` does not delete the 2nd task. Use `delete 2` instead
* Indicating the `Status` to delete by
    * Format: `delete STATUS -all`
    * Examples: `delete in expired -all` deletes **all** tasks that are in expired as their `Status`.
    * Notes:
        * All tasks have one of the 3 `Status`: `uncompleted`, `completed`, `expired`
        * Newly created tasks have `uncompleted` as their `Status`
    
    

### Locating a task by name : `find`

Find tasks whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `cs2103t` will match `CS2103T`
* The order of the keywords does not matter. e.g. `Finish CS2103T Tutorial` will match `CS2103T Tutorial Finish`
* Only the name of the task is searched.
* Only full words will be matched e.g. `CS2103` will not match `CS2103T`
* Tasks matching at least one keyword will be returned.
  e.g. `CS2103T` will return `Finish CS2103T Tutorial`, `Watch CS2103T Lecture`

Examples:
* `find CS2103T` returns `Study for CS2103T Tutorial` and `Practical for CS2103T`, but does not return `CS2103TExam`

### Searching for a task by tags : `tag-search`

Find and list all tasks containing the same tag as the specified tag.

Format: `tag-search TAG [MORE_TAGS]`

Examples:
* `tag-search tutorial CS2103T`

### Viewing a task based on date : `view`

Find and list all tasks with the same date as the specified date.

* The `DATE` format `yyyy-mm-dd`.
* The `DATE` can also be specified as `today` or `tomorrow` to search for the current day's or the next day's task 
  easily.

Format: `view DATE`

Examples:
* `view 2021-05-21`
* `view today`



### Sorting tasks by date : `sort`

Sort tasks in ascending order of their dates.

Format: `sort`



### Switching between tabs :`home` / `uncompleted` / `completed` / `expired`

Switch between the tabs in Taskify. Each tab displays tasks with the corresponding status.

Format: `home` / `uncompleted` / `completed` / `expired`

Examples: `completed` (change to the completed tab)


### Clearing all tasks : `clear`

Clears all tasks in Taskify.

Format: `clear`


### Exiting the program : `exit`

Exits the program.

Format: `exit`



--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: Does Taskify only work for university Students? <br>
**A**: No, although Taskify is catered to university students, we welcome everyone interested in Taskify to use it.<br>

**Q**: Is Taskify free?<br>
**A**: Yes! Taskify is totally free to use.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install Taskify by downloading the taskify.jar file in the other computer and replace the default data file it creates with the file that contains the data from your previous taskify home folder.

**Q**: Can I specify a status when adding a new task to Taskify?<br>
**A**: All newly added tasks have an "uncompleted" status by default. However, you can change the status of a task using the`edit` command. <br>

--------------------------------------------------------------------------------------------------------------------

## Command summary

Command | Format | Examples
--------|--------|----------
**Help** | `help` | `help`
**Add** | `add n/NAME desc/DESCRIPTION date/DATE [t/TAG]…` | `add n/Finish CS2103T Tutorial desc/another task date/2021-12-12 10:10 t/Assignment`
**List** | `list` | `list`
**Edit** | `edit INDEX [n/NAME] [desc/DESCRIPTION] [date/DATE] [t/TAG] [s/STATUS]…` | `edit 1 s/completed`
**Delete** | `delete INDEX`, `delete INDEX [MORE_INDICES]`, `delete INDEX-INDEX`, `delete STATUS -all`| `delete 3`, `delete 4 10 6`, `delete 5-8`, `delete completed -all`
**Find** | `find KEYWORD [MORE_KEYWORDS]` | `find Module Code`
**Tag-Search** | `tag-search TAG [MORE_TAGS]` | `tag-search CS2103T isFun`
**View** | `view DATE` | `view 2021-05-21`, `view today`
**Sort** | `sort` | `sort`
**Home** | `home` | `home`
**Uncompleted** | `uncompleted` | `uncompleted`
**Completed** | `completed` | `completed`
**Expired** | `expired` | `expired`
**Clear** | `clear` | `clear`
**Exit** | `exit` | `exit`

