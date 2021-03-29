---
layout: page
title: User Guide
---
# Nufash User Guide


---
## Introduction
**nufash** is a **digital wardrobe desktop app for university students and/or those looking to
better organise their clothes**. The app is **optimised for users who prefer typing**, but also has the added 
benefit of a **smooth and easy to use Graphical User Interface(GUI)**. 
Use nufash to reduce clutter and start making wiser clothing decisions today!


---
## Quickstart
1. Ensure that you have Java `11` or above installed on your Computer.

2. You can download the latest `nufash.jar` [here](https://github.com/AY2021S2-CS2103T-T12-1/tp/releases/tag/v1.3.0).

3. Copy the file to the folder you want to use as the _home folder_ for the nufash application.

4. Double-click the file to start the app. A GUI similar to one depicted below should launch in a few seconds.<br>

 insert Ui.png

5. You can type a command in the command box and press the enter key on your keyboard to execute it. For example, typing
   the **`help`** command and pressing the enter key will open the help window.<br>
   Some example commands you can try:
   
  - **`add`** `n/shirt s/45 c/blue r/casual t/upper d/stained d/torn` : Add a casual blue shirt which is stained, torn 
    and of size 45 to your wardrobe.

  - **`delete`** `6` : Deletes the 6th garment shown in the current list.

  - **`match`** `2` : Displays the available compatible matches for the 2nd garment in list.

  - **`list`** : Displays all garments in the wardrobe.

  - **`exit`** : Exits the app.

6. You may refer to the [features](#features) below for details of each command.

---
## Features

<div markdown="block" class="alert alert-info">

* Words in `UPPER_CASE` are the parameters to be supplied by the user e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/striped shirt`.
* Items in square brackets are optional e.g. `n/NAME [c/COLOUR]` can be used as `n/striped shirt c/blue` or as `n/striped shirt`.
* Parameters can be in any order e.g. if the command specifies `n/NAME c/COLOUR`, `c/COLOUR n/NAME` is also acceptable.
* Items with `…` after them can be used multiple times including zero times.<br>e.g. `[d/DESCRIPTION]...` can be used as ` ` (i.e. 0 times), `d/stained`, `d/torn d/stained` etc.
* Argument for `r/DRESSCODE` is either `casual`, `formal` or `active`.
* Argument for `t/TYPE` is either `upper`, `lower` or `footwear`.
<br><br>
</div>

### Viewing Help: `help`
Shows an in-app user guide to various commands.<br>

insert helpMessage.png

Format: `help`
<br><br>

### Adding a Garment: `add` 
Adds a garment with a name, size, colour, dress code and type into the wardrobe, along with optional descriptions.<br>

insert AddGarment.png

Format: `add n/NAME s/SIZE c/COLOUR r/DRESSCODE t/TYPE [d/DESCRIPTION]...`<br>

Tips:
* `SIZE` is a positive number.
* `DRESSCODE` is either 'formal', 'casual' or 'active'.
* `TYPE` is either 'lower', 'upper' or 'footwear'.

Example:<br>
* `add n/favourite t shirt s/30 c/blue r/casual t/upper`
  <br><br>
  
### Listing all Garments: `list`  
Shows a list of all garments in the wardrobe<br>

insert ListGarment.png

Format: `list`
<br><br>

### Deleting a Garment: `delete`  
Removes a Garment, associated with the given index, from the wardrobe.<br>

insert DeleteGarment.png

Format: `delete INDEX`

Tips:
* Deletes the article of clothing at the specified `INDEX`
* The `INDEX` refers to the index number shown in the list command
* The `INDEX` must be a positive integer 1, 2, 3, …<br>

Example:<br>
* `list` followed by `delete 7`<br>
Removes the 7th Garment in the wardrobe.
<br><br>
  
### Clearing all Garments: `clear`
Clears all existing garments in the wardrobe.

insert ClearGarment.png

Format: `clear`
<br><br>

### Editing a Garment: `edit`
Edits an existing garment in the wardrobe.<br>

insert EditGarment.png

Format: `edit INDEX [n/NAME] [s/SIZE] [c/COLOUR] [r/DRESSCODE] [t/TYPE] [d/DESCRIPTION]...`

Tips:
* Edits the garment at the specified `INDEX`.<br>
* The `INDEX` refers to the index number shown in the list command. The index must be a positive integer 1, 2, 3, …
* At least one of the optional bracketed fields must be provided.
* Existing values will be updated to the input values.<br>

Example:
* `list` followed by `edit 1 c/red s/30`<br>
  Edits the colour and size of the 1st garment in the wardrobe to be red and 30 respectively.
  <br><br>
  
### Find Garments: `find`
Finds all garments that matched specified keywords during search.<br>

insert FindGarmentA.png

insert FindGarmentB.png

Format: `find [n/NAMES] [s/SIZES] [c/COLOURS] [r/DRESSCODES] [t/TYPES] [d/DESCRIPTIONS]...`

Tips:

* At least one of the optional bracketed fields must be provided.
* The list of all garments with matching attributes will be shown.<br><br>

Example:
* `find n/worn out jeans`<br>
  Returns all garments whose name has at least one of the words in the search phrase, "worn out jeans".
* `find c/white s/36 23`<br>
  Returns all garments that are white and are either sized 36 or 23.
<br><br>
  
### Matching...: `match`
<br><br>

### Selecting a Garment: `select`
Select a garment to check out of the wardrobe.

insert SelectGarment.png

Format: `select INDEX`

Tips:
* Selects the garment at the specified `INDEX`.<br>
* The `INDEX` must be a positive integer 1, 2, 3, …
* selecting a garment indicates that you intend to wear it today.

Example:

* `select 1`
<br><br>

### Viewing a Garment set: `view`
View a set of 3 garments associated with the given indexes.<br>

insert ViewGarment.png

Format: `view INDEX INDEX INDEX`

Tips:
* Displays the garments at the selected set of `INDEX`.<br>
* The `INDEX` must be a positive integer 1, 2, 3, …
* Garments must be of different Types (i.e. `upper`, `lower` and `footwear`).<br>
* The command must have exactly 3 indexes as input, any more inputs will not be registered.

Example:
* `view 1 2 3`<br>
<br><br>

### Exiting the program: `exit`
Exits the program.<br>
Format: `exit`
<br><br>

### Saving the data
nufash data is saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.
<br><br>

### Editing the data file
nufash data is saved as a JSON file [JAR file location]/data/nufash.json. 
Advanced users are welcome to update data directly by editing that data file.

**:exclamation: Caution:**<br>
  If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
<br><br>

---
### FAQ
Q: How do I transfer my data to another Computer?<br>
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains 
the data of your previous nufash folder.

Q: Who do I contact if I face any issues?<br>
A: Tell us about your issue [here](https://github.com/AY2021S2-CS2103T-T12-1/tp), or better yet, submit a pull request with a way to solve it!

---
### Command Summary

| Action                              | Format, Examples                                                                                                                |
| ----------------------------------- | -----------------------------------------------------------------------                                                         |
| **Add a Garment**                   | `add n/NAME s/SIZE c/COLOUR r/DRESSCODE t/TYPE [d/DESCRIPTION]...`<br> Eg. `add n/sleek tux s/32 c/white r/formal t/upper`      |
| **Deleting a Garment**              | `delete INDEX`<br> Eg. `delete 4`                                                                                               |
| **Editing a Garment**               | `edit INDEX [n/NAME] [s/SIZE] [c/COLOUR] [r/DRESSCODE] [t/TYPE] [d/DESCRIPTION]...`<br>                                         |
| **Listing all Garments**            | `list`                                                                                                                          |
| **Find Garments**                   | `find t/TYPE`<br> Eg. find `t/Office`                                                                                           |
| **Match**                           | `match INDEX` <br> Eg., `match 1`                                                                                               |
| **Viewing a Garment set**           | `view INDEX INDEX INDEX` <br> Eg., `view 1 2 3`                                                                                 |
| **Select**                          | `select INDEX` <br> Eg., `select 1`                                                                                             |
| **Clearing all Garments**           | `clear`                                                                                                                         |
| **Help**                            | `help`                                                                                                                          |
| **Exit**                            | `exit`                                                                                                                          |
---
