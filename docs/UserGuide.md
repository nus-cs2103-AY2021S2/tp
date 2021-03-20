## Introduction
HEY MATEz, is a desktop application that allows CCA leaders to track members of
a CCA and track the tasks that have to be carried out by the CCA. It is also
a Command Line Interface (CLI) application while still retaining a Graphical User Interface (GUI)
as this application is catered to people who are used to typing on the keyboard frequently.

## Mockup of Application
![Ui](images/Ui.png)

## Features:
* Adding Task: `addTask`
* Deleting Task: `deleteTask`
* Viewing Tasks: `viewTasks`
* Adding member: `addMember`
* Deleting members’ details: `deleteMember`
* Viewing members’ details: `viewMembers`

### 1. Adding Task: `addTask`
Adds a task and its description to the list.

Format: `addTask TITLE -d DESCRIPTION -s status`
* The status field is optional.
* If status field is not provided, the Task will be assigned a default value of uncompleted.
* status field can only take on the values completed or uncompleted

Examples: `addTask assignment -d Math quiz 2 -s completed`


### 2. Deleting Task: `deleteTask`
Deletes a task from the list.

Format: `deleteTask INDEX`

Examples: `deleteTask 1`

### 3. View Task: `viewTasks`
Views the list of tasks that the user has added into the application

Format: `viewTasks`

Examples: `viewTasks`

### 4. Edit Task: `editTask`
Edit task details

Format: `editTask INDEX -n NEW TITLE -d NEW DESCRIPTION`

Examples: `editTask 1 -n Plan meeting -d Plan board meeting`

### 5. Adding Member: `addMember`

Adds a member and his/ her contract number to contact list

Format: `addMember NAME -p PHONE NUMBER -e EMAIL`

Examples: `addMember Dylan -p 64529356 -e test@test.com`

### 6. Delete Member: `deleteMember`

Delete a member and his/ her contract number from the contact list

Format: `deleteMember NAME`

Examples: `deleteMember Rachel`

### 7. View Member: `viewMembers`

View the list of members the user has added

Format: `viewMembers `

Examples: `viewMembers `

### 8. Edit Member: `editMember`
Edit task details

Format: `editMember NAME IN LIST -n NEW NAME -p NEW PHONE NUMBER -e NEW EMAIL`
* Edits the person at the specified NAME IN LIST.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples: `editMember Alice -n Alice Lim -p 95231156 -e tasha@test.com`

### 9. Mark Task as Done: `done`

Change the status of a task from uncompleted to completed

Format: `done INDEX`

Examples: `done 1`

### 10. Mark Task as Not Done: `undo`

Change the status of a task from completed to uncompleted

Format: `undo INDEX`

Examples: `undo 1`