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

Format: `addTask TITLE d/DESCRIPTION`

Examples: `addTask assignment d/Math quiz 2`


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

Format: `editTask INDEX n/NEW TITLE d/NEW DESCRIPTION`

Examples: `editTask 1 n/Plan meeting d/Plan board meeting`

### 5. Adding Member: `addMember`

Adds a member and his/ her contract number to contact list

Format: `addMember NAME`

Examples: `addMember Dylan 64529356`

### 6. Delete Member: `deleteMember`

Delete a member and his/ her contract number from the contact list

Format: `deleteMember NAME`

Examples: `deleteMember Rachel`

### 7. View Member: `viewMembers`

View the list of members the user has added

Format: `viewMembers `

Examples: `viewMembers `

