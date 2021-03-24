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
add dog n/DOGNAME b/BREED o/OWNERID t/TAG
add owner n/OWNERNAME p/PHONE_NUMBER e/EMAIL a/ADDRESS
add program n/PROGRAMNAME t/TIME
```

Examples:
1. Adds a dog named BRUCE belonging to owner with ID 1 in Pawbook.<br>
   Command: `add dog n/BRUCE b/somebreed o/1 t/brown`
2. Adds an owner named John with the details provided in Pawbook.<br>
   Command: `add owner n/John p/91722182 e/john@gmail.com a/"No.123, Jurong West Ave 6, #06-111`
3. Creates a program titled “Program1” that occurs on 2 March 2021 from 4pm to 5pm and on 9 March 2021 1pm to 2pm.<br>
   Command: `add program p/Program1 t/2.3.2021 1600-1700 9.3.2021 1300-1400`

### Delete Command

Deletes a dog/owner/program from Pawbook.

Format:

```
delete dog d/<DOG ID>
delete owner o/<OWNER ID>
delete program p/<PROGRAM ID>
```

- Deletes the dog/owner/program with the given ID.
- The ID must be a positive integer 1, 2, 3, ...

Examples:
1. Deletes the dog with ID 1 in Pawbook.<br>
   Command: `delete dog d/1`
2. Deletes the owner with ID 2 in Pawbook.<br>
   Command: `delete owner o/2`
3. Deletes the program with ID 3 in Pawbook.<br>
   Command: `delete program p/3`

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
**Help** | `help`
**Exit** | `exit`
