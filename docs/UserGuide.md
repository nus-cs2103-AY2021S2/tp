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

Format: `add contact`

Prompts: `name`, `number`, `[tags]`

Example(s):
* `add contact Danny 00000000`
* `add contact Amy 11111111 CS2101`

### Finding a contact
Finds an existing contact by name in the address book.

Format: `find contact`

Prompts: `name`

Example(s):
* `find contact Amy`

### Listing contacts
Lists all the contacts in the address book.

Format: `list contacts`

### Deleting a contact
Deletes an existing contact with the specified name in the address book.

Format: `delete contact`

Prompts: `name`

Example(s):
* `delete contact Danny`

---
### Adding a schedule
Adds a new schedule into the schedule list.

Format: `add schedule`

Prompts: `start date and time`, `end date and time`, `[tags]`

Example(s):
* `add schedule meeting 2021-02-15,2100 2021-02-15,2300`
* `add schedule consultation 2021-02-15,2300 2021-02-15,2400 consultation`

### Finding a schedule
Finds an existing schedule by name in the schedule list.

Format: `find schedule`
* `The search is case-insensitive e.g. meeting will match Meeting`

Prompt: `name`

Example(s):
* `find schedule meeting`

### Listing schedules today (of the same week)
List schedules today/this week.

Format: `list schedule`

Prompt: `by day/week`

Example(s):
* `list schedule by day`
* `list schedule by week`

### Deleting a schedule
Deletes an existing schedule with the specified name in the schedule list.

Format: `delete schedule`

Prompt: `name`

Example(s):
* `delete schedule meeting`

---

### Adding a task
Adds a task into the task list.

Format: `add task`

Prompts: `name`, `date`, `[tags]`

Example(s):
* `add task slides 2021-02-15 CS2105T`

### Finding a task
Finds an existing task by name in the task list.

Format: `find task`
* The search is case-insensitive e.g. slides will match Slides

Prompts: `name`

Example(s):
* `find task slides`

### Listing tasks by module/day/week
Lists all the tasks by the specified module/today/this week.

Format: `list tasks`

Prompts: `by module`, `by day`, `by week`

Example(s):
* `list tasks by CS2103T`
* `list tasks by day`
* `list tasks by week`

### Deleting a task
Deletes an existing task with the specified name in the task list.

Format: `delete task`

Prompt: `name`

Examples:
* `delete task slides`

---

## Command summary

### Others
Action | Format
-------|------------------
**View all commands** | `help`

### Address Book
Action | Format
--------|------------------
**Add** | `add contact`
**Find and View (by name)** | `find contact`
**List** | `list contacts`
**Delete** | `delete contact`

### Schedule
Action | Format
--------|------------------
**Add** | `add schedule`
**Find and View (by name)** | `find schedule`
**List (by day/week)** | `list schedule`
**Delete** | `delete schedule `

### Tasks
Action | Format
--------|------------------
**Add** | `add task`
**Find and View (by name)** | `find task`
**List (by day/week)** | `list tasks`
**Delete** | `delete task`
