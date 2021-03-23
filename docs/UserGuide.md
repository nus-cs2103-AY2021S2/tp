---
layout: page
title: User Guide
---

Pawbook is a desktop application for dog school managers to facilitate their bookkeeping of puppies and dogs in the school, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). Besides maintaining dog profiles for each unique dog, managers can view daily class schedules on Pawbook to better plan the day’s activities.

This document is created by **CS2103T-T10-Group1**.
* Charles Lee Lin Ta
* John Alec Mendoza Branzuela
* Kou Yong Kang
* Lam Xuan Yi, Shaelyn
* Wei Yutong
* Zhang Anli

---

* Table of Contents
{:toc}

---

## **Quick start**
1. Ensure you have **Java 11 or above** installed in your Computer.
2. Download the **latest** pawbook.jar.
3. Copy the file to the folder you want to use as the home folder for your
   Pawbook.
4. Double-click the file to start the app.
5. For new users, type `help` in the command box to view the instruction list
6. Type the command in the command box and press Enter to execute it. e.g.
   typing `add` and pressing Enter will allow you to start adding information to
   the database.

**NOTE:** Please refer to the features below for details of each command.

----

## Features of Pawbook

### Add Command
Adds a dog/owner/program to Pawbook.

Format:

```
add dog n/DOGNAME b/BREED d/DATEOFBIRTH s/SEX o/OWNERID t/TAG
add owner n/OWNERNAME p/PHONE_NUMBER e/EMAIL a/ADDRESS
add program n/PROGRAMNAME s/DATEOFPROGRAM t/TAG
```

Examples:
1. Adds a dog named BRUCE belonging to owner with ID 1 in Pawbook.<br>
   Command: `add dog n/Bruce b/Chihuahua d/12-02-2019 s/Male o/1 t/playful t/active`
2. Adds an owner named John with the details provided in Pawbook.<br>
   Command: `add owner n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney`
3. Creates a program titled “Program1” that occurs on 2 March 2021 from 4pm to 5pm and on 9 March 2021 1pm to 2pm.<br>
   Command: `add program n/1 s/02-02-2020 1800 t/puppies`

### Delete Command

Deletes a dog/owner/program from Pawbook.

Format:

```
delete dog [DOG ID]
delete owner [OWNER ID]
delete program [PROGRAM ID]
```

- Deletes the dog/owner/program with the given ID.
- The ID must be a positive integer 1, 2, 3, ...

Examples:
1. Deletes the dog with ID 1 in Pawbook.<br>
   Command: `delete dog 1`
2. Deletes the owner with ID 2 in Pawbook.<br>
   Command: `delete owner 2`
3. Deletes the program with ID 3 in Pawbook.<br>
   Command: `delete program 3`

### List Command

Shows a list of dogs in the specified category/program.

Format:

```
list b/golden retriever
list c/puppy
list t/brown
```
Examples:
1. List all golden retrievers in the school.<br>
   Command: `list b/golden retriever`
2. List all dogs enrolled in the puppy program.<br>
   Command: `list c/puppy`
3. List all brown dogs.<br>
   Command: `list t/brown`

**NOTE:** If the argument is left blank i.e `list`, all the dog profiles in the school will be displayed.

### Find Command 

Shows the list of search results based on one/many keywords. 

Format: 
```
find [KEYWORD1] 
find [KEYWORD1] [KEYWORD2] 
find [KEYWORD1] [KEYWORD2] [KEYWORD3] 
```
Examples: 
1. Find all entities with the name 'Alice'. <br>
   Command: `find alice`
2. Find all entities with the name 'Alice' or 'Bob' or 'Charlie'.<br>
   Command: `find alice bob charlie`
   
**Note**: Find is able to take in multiple keywords and returns all results as long as the name contains any one of the keywords. 

### View Command 

Views the list of all entities related to the searched entity. Used in cases when trying to find all the dogs enrolled in a program or all the dogs belonging to one owner. 

```
view [ENTITY ID] 
```
Examples: 
1. If entity 1 is a owner, shows a list of the owner and all his dogs. <br>
   Command: `view 1` 
2. If entity 2 is a dog, shows a list containing the dog and its owner. <br>
   Command: `view 2` 
3. If entity 3 is a program, shows a list of the program and all the dogs enrolled. <br>
   Command: `view 3`

### Help Command

If you are unsure about how to use Pawbook, execute the `help` command to view a complete list of application instructions.

Format: `help`

### Exit Command

Pawbook automatically saves the existing information of the dogs and will close the program.

Format: `exit`

-----

## FAQ
Q: How do I transfer my data to another Computer?<br>
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Pawbook home folder.

-----

## Command Summary

Action | Format
--------|------------------
**Add** | 1. `add dog n/DOGNAME b/BREED d/DATE OF BIRTH s/SEX o/OWNERID t/TAG`<br>2. `add  owner n/OWNERNAME p/PHONE e/EMAIL a/ADDRESS [t/TAG]...`<br>3. `add  program n/NAME [s/DATE OF SESSION]... [t/tag]...`
**Delete** | 1. `delete dog d/DOGID`<br>2. `delete owner o/OWNERID`<br>3. `delete program p/PROGRAMID`
**List** |`list n/NAME c/CLASS b/BREED t/TAG`
**Find** | `find [keyword1] [keyword2] [keyword3] ...`
**View** | `view [ID number]`
**Help** | `help`
**Exit** | `exit`
