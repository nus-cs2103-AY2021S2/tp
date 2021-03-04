---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

##Adding a task
Adds a task into the task list.

Format: `add task`

Prompts: `name`, `date`, `[tags]`

Example(s):
* `add task slides 2021-02-15 CS2105T`

##Finding a task
Finds an existing task by name in the task list.

Format: `find task`
* The search is case-insensitive e.g. slides will match Slides

Prompts: `name`

Example(s):
* `find task slides`

##Listing tasks by module/day/week
Lists all the tasks by the specified module/today/this week. 

Format: `list tasks`

Prompt: `by module`, `by day`, `by week`

Example(s):
* `list tasks by CS2103T`
* `list tasks by day`
* `list tasks by week`

##Deleting a task
Deletes an existing task with the specified name in the task list.

Format: `delete task`

Prompt: `name`

Examples:
* `delete task slides`

