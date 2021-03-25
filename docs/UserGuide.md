---
layout: page
title: User Guide
---

_Teaching Assistant_ is a desktop application that helps **JC/Secondary school teachers** have an efficient tool to keep
track of their **schedules, contacts and tasks easily.** While it has a GUI, most of the user interactions rely on typing commands as user inputs.

This is a mockup of our UI.

![Ui](images/Ui.png)

---

## Features

**:information_source: Notes about the command format:**<br>

* Users will be prompted for further inputs upon entering any command with parameters.
* Items in the square brackets are optional. Users can choose to leave the field empty.

---

### Viewing help
Shows a message with all the commands.

Format: `help`

---

### Adding a contact
Adds a person's information into the address book.

Format: `add n/NAME p/NUMBER e/EMAIL [t/TAG]`

Example(s):
* `add n/Danny p/98765432 e/danny@email.com`
* `add n/Amy p/12345678 e/amy@email.com CS2101`

### Finding a contact
Finds an existing contact by name in the address book.

Format: `find KEYWORD [MORE_KEYWORDS]`
* The search is case-insensitive e.g. amy will match Amy

Example(s):
* `find Amy`

### Listing contacts
Lists all the contacts in the address book.

Format: `list`

### Deleting a contact
Deletes an existing contact with the specified name in the address book.

Format: `delete NAME`

Example(s):
* `delete Danny`

---
### Adding a schedule
Adds a new schedule into the schedule list.

Format: `sadd n/NAME sd/START_DATE ed/END_DATE [t/TAG]`

Example(s):
* `sadd n/meeting 2021-02-15T21:00:00 2021-02-15T23:00:00`
* `sadd n/consultation 2021-02-15T23:00:00 2021-02-15T23:59:59 important`

### Finding a schedule
Finds an existing schedule by name in the schedule list.

Format: `sfind KEYWORD [MORE_KEYWORDS]`
* `The search is case-insensitive e.g. meeting will match Meeting`

Example(s):
* `sfind meeting`

### Listing schedules today (of the same week)
Lists all the schedules by today/this week.

Format: `slist [day/week]`
- No argument: listing all schedules
- Day: listing schedules for today
- Week: listing schedules for the next 7 days

Example(s):
* `slist`
* `slist day`
* `slist week`

### Deleting a schedule
Deletes an existing schedule with the specified name in the schedule list.

Format: `sdelete NAME`

Example(s):
* `sdelete meeting`

---

### Adding a task
Adds a task into the task list.

Format: `tadd n/NAME d/DATE [t/TAG]`

Example(s):
* `tadd slides 2021-02-15 CS2100`

### Finding a task
Finds an existing task by name in the task list.

Format: `tfind KEYWORD [MORE_KEYWORDS]`
* The search is case-insensitive e.g. slides will match Slides

Example(s):
* `tfind slides`

### Listing tasks by day/week
Lists all the tasks by today/this week.

Format: `tlist [day/week]`
- No argument: listing all schedules
- Day: listing schedules for today
- Week: listing schedules for the next 7 days

Example(s):
* `tlist`
* `tlist day`
* `tlist week`

### Deleting a task
Deletes an existing task with the specified name in the task list.

Format: `tdelete NAME`

Examples:
* `tdelete slides`

---

## Command summary

### Others
Action | Format
------- | ------------------
**View all commands** | `help`

### Address Book
Action | Format
-------- | ------------------
**Add** | `add n/NAME p/NUMBER e/EMAIL [t/TAG]`
**Find and View (by name)** | `find KEYWORD [MORE_KEYWORDS]`
**List** | `list`
**Delete** | `delete NAME`

### Schedule
Action | Format
-------- | ------------------
**Add** | `sadd n/NAME sd/START_DATE ed/END_DATE [t/TAG]`
**Find and View (by name)** | `sfind KEYWORD [MORE_KEYWORDS]`
**List (by day/week)** | `slist [day/week]`
**Delete** | `sdelete NAME`

### Tasks
Action | Format
-------- | ------------------
**Add** | `tadd n/NAME d/DATE [t/TAG]`
**Find and View (by name)** | `tfind KEYWORD [MORE_KEYWORDS]`
**List (by day/week)** | `tlist [day/week]`
**Delete** | `tdelete NAME`
