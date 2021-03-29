---
layout: page
title: User Guide
---

# **Overview**

Welcome to the Pawbook User Guide! In this user guide, we will teach you as a user how to use Pawbook. 
This user guide contains a quick start guide, a features walkthrough and a command summary table for easy reference. 
In each feature/functionality, we also provide command formats and command examples to ensure that users can become 
proficient in using Pawbook.

## **Introduction**
Managing a business is definitely not easy, so imagine if your business includes taking care of countless energetic 
furpals! That's right, we are talking about the job of dog school managers. As dog schools and dog day cares rise in 
popularity due to the hectic work life of dog owners, the operations of dog schools can get out of hand. But no worries, 
we have Pawbook to save the day! <br>

Pawbook is a desktop application for dog school managers to facilitate their bookkeeping of puppies and dogs in the 
school, optimized for use via a **Command Line Interface (CLI)** while still having the benefits of a **Graphical User 
Interface (GUI)**. Besides maintaining dog profiles for each unique dog, managers can view daily class schedules on 
Pawbook to better plan the day’s activities.

## **Purpose**
The purpose of this user guide is to educate users on the different features/components of Pawbook and what are the 
different purpose they serve. This allow users to easily use the various features and functionalities provided by 
Pawbook. 

## **Contributors**
This document is created by **CS2103T-T10-Group1**.
* Charles Lee Lin Ta
* John Alec Mendoza Branzuela
* Kou Yong Kang
* Lam Xuan Yi, Shaelyn
* Wei Yutong
* Zhang Anli

# **About the User Guide**
In this section, you will learn what the different notations and symbols used in Pawbook. 

## **Syntax Format**
Syntax | Meaning  | Example
--------|------------------|----------
**`lower_case/`** |  Prefix | `n/`, `p/`, `t/`
**`[UPPER_CASE]/`** |  Parameter | [keyword] [entity ID]

## **GUI Layout**
In this section, you will be given an introduction to the layout of Pawbook's Graphical User Interface(GUI). This will 
help you better understand what each component that you observe on-screen represents.

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

# Commands 

### Add Command
Adds a dog/owner/program to Pawbook.

Format:

```
add dog n/DOGNAME b/BREED d/DATEOFBIRTH s/SEX o/OWNERID t/TAG
add owner n/OWNERNAME p/PHONE_NUMBER e/EMAIL a/ADDRESS
add program n/PROGRAMNAME s/TIMEANDDATEOFSESSION t/TAG
```

Examples:
1. Adds a dog named BRUCE belonging to owner with ID 1 in Pawbook.<br>
   Command: `add dog n/Bruce b/Chihuahua d/12-02-2019 s/Male o/1 t/playful t/active`
2. Adds an owner named John with the details provided in Pawbook.<br>
   Command: `add owner n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney`
3. Creates a program with Program ID 1<br>
   Command: `add program n/1 s/02-02-2020 18:00 t/puppies`

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

### Enrol Command

Adds a specified dog to a specified program that the dog was previously not enrolled in.

Format:
```
enrol d/[DOG ID] p/[PROGRAM ID]
```

Examples:
1. Enrol dog with Dog ID 1 into program with Program ID 2, assuming that Dog ID 1 was previously not enrolled in 
   Program ID 2. <br> 
   Command: `enrol d/1 p/2`
   
### Drop Command

Removes a specified dog from a specified program that the dog was previously enrolled in.

Format:
```
drop d/[DOG ID] p/[PROGRAM ID]
```

Examples:
1. Remove dog with Dog ID 1 from program with Program ID 2, assuming that Dog ID 1 was previously enrolled in 
   Program ID 2. <br> 
   Command: `drop d/1 p/2`
   
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
   
**Note**: Find is able to take in multiple keywords and returns all results as long as the name contains any one of the 
keywords. 

### View Command 

Views the list of all entities related to the searched entity. Used in cases when trying to find all the dogs enrolled 
in a program or all the dogs belonging to one owner. 

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
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the 
data of your previous Pawbook home folder.

-----

## Command Summary

Action | Format
--------|------------------
**Add** | 1. `add dog n/DOGNAME b/BREED d/DATE OF BIRTH s/SEX o/OWNERID t/TAG`<br>2. `add owner n/OWNERNAME p/PHONE e/EMAIL a/ADDRESS [t/TAG]...`<br>3. `add  program n/NAME [s/DATE OF SESSION]... [t/tag]...`
**Delete** | 1. `delete dog d/DOGID`<br>2. `delete owner o/OWNERID`<br>3. `delete program p/PROGRAMID`
**Enrol** | `enrol d/[DOG ID] p/[PROGRAM ID]`
**Drop** | `drop d/[DOG ID] p/[PROGRAM ID]`
**List** |`list n/NAME c/CLASS b/BREED t/TAG`
**Find** | `find [keyword1] [keyword2] [keyword3] ...`
**View** | `view [ID number]`
**Help** | `help`
**Exit** | `exit`

### Glossary 

Term  | Explanation
-----|------------------
CLI | Short for Command Line Interface. CLI-based applications are primarily used through processing text commands. 
GUI | Short for Graphical User Interface. GUIs work as the tangible user interface between program and user. Users interact with Pawbook through the GUI on their devices.
