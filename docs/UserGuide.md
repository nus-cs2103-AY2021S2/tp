## **PAWBOOK USER GUIDE**

**Pawbook is a desktop application for dog school managers to facilitate their
bookkeeping of puppies and dogs in the school, optimized for use via a Command Line
Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
Besides maintaining dog profiles for each unique dog, managers can view daily class
schedules on Pawbook to better plan the day’s activities.**

This document is created by **CS2103T-T10-Group1**.
* Charles Lee Lin Ta
* John Alec Mendoza Branzuela
* Kou Yong Kang
* Lam Xuan Yi, Shaelyn
* Wei Yutong
* Zhang Anli
---
* **Quick start**
* **Features**
  * Adding a dog/owner/program to database: add
  * Deleting a dog/owner/program profile: delete
  * Show the list of dogs based on search: list
  * Enrol a dog to a program: enrol
  * Drop a dog from a program: drop
  * View daily schedule: schedule
  * Editing raw file
  * Application instructions: help
  * Exiting the application: exit
* **FAQ**
* **Command Summary**

### **Quick start**
1. Ensure you have **Java 11 or above** installed in your Computer.
2. Download the **latest** pawbook.jar.
3. Copy the file to the folder you want to use as the home folder for your
   Pawbook.
4. Double-click the file to start the app.
5. For new users, type help in the command box to view the instruction list
6. Type the command in the command box and press Enter to execute it. e.g.
   typing add and pressing Enter will allow you to start adding information to
   the database.

**NOTE: Please refer to the features below for details of each command.**

----
### **Features of Pawbook**
**Add a dog/owner/program to Pawbook: add**

Format:

-add dog n/DOGNAME b/BREED o/OWNERID t/TAG</br>
-add owner n/OWNERNAME p/PHONE_NUMBER e/EMAIL a/ADDRESS</br>
-add program n/PROGRAMNAME t/TIME</br>

Examples:
1) **Adds a dog named BRUCE belonging to owner with ID 1 in Pawbook**</br>
   Command: `add dog n/BRUCE b/somebreed o/1 t/brown`
2) **Adds an owner named John with the details provided in Pawbook**</br>
   Command: `add owner n/John p/91722182 e/john@gmail.com a/"No.123, Jurong West Ave 6, #06-111`
3) **Create a program titled “Program1” that occurs on 2 March 2021 from 4pm to
   5pm and on 9 March 2021 1pm to 2pm.**</br>
   Command: `add program p/Program1 t/2.3.2021 1600-1700 9.3.2021 1300-140`

-----
### **FAQ**
Q: How do I transfer my data to another Computer?</br>
A: Install the app in the other computer and overwrite the empty data file it creates with
the file that contains the data of your previous Pawbook home folder.</br>
-----
### **Command Summary**

Action | Format
--------|------------------
**Add** | 1) `add dog n/DOGNAME b/BREED o/OWNERID t/TAG`</br>2) `add  owner n/OWNERNAME p/PHONE_NUMBER e/EMAIL a/ADDRESS`</br>3)`add  program n/PROGRAMNAME t/TIME`
**Delete** | 1) `delete dog d/DOGID`</br>2) `delete owner o/OWNERID`</br>3) `delete program p/PROGRAMID`
**List** |`list n/NAME c/CLASS b/BREED t/TAG`
**View Pawbook Instructions** | `help`
**Exit** | `exit`

