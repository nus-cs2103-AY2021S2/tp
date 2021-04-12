---
layout: page
title: User Guide
---
<p align="center">
  <img src="https://raw.githubusercontent.com/AY2021S2-CS2103T-T12-1/tp/master/docs/images/NuFash-logo.png" alt="logo" width="200">
  <h1 align="center"> User Guide </h1>
</p>

<div style="page-break-after: always;"></div>

---
## Table of Contents

* [Introduction](#introduction)
* [Quickstart](#quickstart)
* [NuFash Features](#nufash-features)
  + [Wardrobe Management Features](#wardrobe-management-features)
    + [Viewing Help: `help`](#help)
    + [Adding a Garment to the Wardrobe: `add`](#add)
    + [Listing all Garments in the Wardrobe: `list`](#list)
    + [Deleting a singular Garment in the Wardrobe: `delete`](#delete)
    + [Clearing all Garments in Wardrobe : `clear`](#clear)
    + [Editing a singular Garment in Wardrobe: `edit`](#edit)
    + [Finding Garments in Wardrobe by keywords: `find`](#find)
  + [Outfit Building Features](#outfit-building-features)
    + [Matching Garments to create an outfit: `match`<br>](#match)
    + [Checking out a Garment from the Wardrobe: `select`](#select)
    + [Viewing a valid outfit: `view`](#view)
    + [Exiting the program: `exit`](#exit)
  + [Saving the data](#saving-the-data)
  + [Editing the data file](#editing-the-data-file)
* [FAQ](#faq)
* [Command Summary](#command-summary)
* [Credits](#credits)

<div style="page-break-after: always;"></div>

## Introduction

Are you tired of deciding what to wear every morning? Do you often
wonder whether your new clothing purchases would match your current wardobe? Have you ever misplaced your clothes?
Well, your journey to a better dressing experience starts today with **NuFash**.

**NuFash** is a **digital wardrobe desktop app for university students and/or those looking to
better organise their clothes**. The NuFash application allows users to input
their clothing items (garments) into their own digital wardrobe, and be able
to search through and match their various garments quickly. Some key features include:

- An easy-to-use digital wardrobe to manage your garments.
- A generated image to visually represent your garments in the wardrobe.
- Viewing functionality, to see certain pairing of garments.
- Selecting functionality, to keep track of when garments were used.
- Matching functionality, to suggest which garments could possibly be worn with each other.

The application is **optimised for use through the Command Line Interface (CLI)**, allowing users to quickly add or modify items
in their digital wardrobe. Our compact but powerful application also has a **integrated and easy to use Graphical User Interface(GUI)** 
providing a visual representation of a user's entire wardrobe catalogue in mere seconds!

This User Guide is for new users of the NuFash app to learn and understand what are the various features that NuFash 
has to offer. If you are interested in providing feedback on issues or would like to follow our development process, please visit our
[GitHub page](https://github.com/AY2021S2-CS2103T-T12-1/tp/)

**Use our application to reduce clutter and be empowered to make wiser clothing decisions today!**

Head over to our [Quickstart](#quickstart) to start learning about NuFash.

<div style="page-break-after: always;"></div>

---
## Quickstart
1. Ensure that you have Java `11` or above installed on your Computer.

2. You can download the latest `nufash.jar` [here](https://github.com/AY2021S2-CS2103T-T12-1/tp/releases/tag/v1.3.0).

3. Copy the file to the folder you want to use as the _home folder_ for the NuFash application.

4. Double-click the file to start the app. A GUI similar to one depicted below should launch in a few seconds.<br>

 <img src="https://raw.githubusercontent.com/AY2021S2-CS2103T-T12-1/tp/master/docs/images/Ui.png" alt="alt text" width="790">

5. You may refer to the [User Interface Overview](#user-interface-overview) for further details on the application's interface or
   [NuFash Features](#nufash-features) to try out our commands.

---
## User Interface Overview

The NuFash interface consists of elements which work in unison to provide a smooth user experience.
Use this section as a guide to navigate our GUI, and understand what should be displayed in your application.

## Main GUI Window
The interface of the application is split into distinct components:
1. Menu Bar
2. Command Box
3. Feedback Box
4. Wardrobe Box

 <img src="https://raw.githubusercontent.com/AY2021S2-CS2103T-T12-1/tp/master/docs/images/Ui-labelled.png" alt="ui-labelled" width="790">

### Menu Bar

The Menu Bar contains drop-down menus allows you to access useful functions. 
These functions are:
- Exit - Exits the application
- Help - Opens the Help window

### Command Box

The command box allows you to key in your commands to control your digital wardrobe. 
You can type a command in the command box and press the enter key on your keyboard to execute it. For example, typing
the **`help`** command and pressing the enter key will open the help window.<br>
Some example commands you can try:

- **`add`** `n/shirt s/45 c/blue r/casual t/upper d/stained d/torn` : Add a casual blue shirt which is stained, torn
  and of size 45 to your wardrobe.

- **`delete`** `6` : Deletes the 6th garment shown in the current list.

- **`match`** `2` : Displays the available compatible matches for the 2nd garment in list.

- **`list`** : Displays all garments in the wardrobe.

- **`exit`** : Exits the app.

You may refer to the [NuFash Features](#nufash-features) below for details of each command.


### Feedback Box

The feedback box displays the success messages when commands entered into NuFash are valid. Alternatively, it will show an
error message if the command is invalid or can't be recognised.

 <img src="https://raw.githubusercontent.com/AY2021S2-CS2103T-T12-1/tp/master/docs/images/feedback-box.png" alt="feedback-box" width="790">


### Wardrobe Box

The Wardrobe box shows the listing of all current garments in their digital wardrobe. 

Each listing is
comprised of the following components:

1. Listing Index
2. Listing Details
3. Listing Tags
4. Listing Generated Image

<img src="https://raw.githubusercontent.com/AY2021S2-CS2103T-T12-1/tp/master/docs/images/wardrobe-box.png" alt="wardrobe-box" width="790">

#### Listing Index

The listing index assigns each unique garment listed into the digital wardrobe a number: `INDEX`.
this `INDEX` is used in various commands, e.g. `select`, `edit` and `view`.

#### Listing Details

The listing details display the the garment's attributes such as name, size and the date it was last used.

#### Listing Tags
The listing tags allow users to key in various descriptions about the garments, e.g garment's location and/or garment's
condition.

#### Listing Generated Image
The listing generated Image allows for the garment to be represented in a visual form. It displays the dress-code of the
garment (either formal, casual or active-wear), its type (either upper, lower or foot-wear) and it's colour.

---
## NuFash Features
### Feature Syntax and Terminology
<div markdown="block" class="alert alert-info">

* Words in `UPPER_CASE` are the inputs to be supplied by the user e.g. in `add n/NAME`, `NAME` is a parameter which can 
  be used as `add n/striped shirt`.
* The different inputs that the user can input are the following:
  * `n/NAME`, the name of the garment, limited to 40 characters
  * `s/SIZE`, the size of the garment, which is a positive integer less than 200
  * `c/COLOUR`, the colour of the garment
  * `r/DRESSCODE`, the dresscode of the garment
  * `t/TYPE`, the type of the garment
  * `d/DESCRIPTION`, the description of the garment, with each description being limited to 15 characters
* Items in square brackets are optional e.g. `n/NAME [c/COLOUR]` can be used as `n/striped shirt c/blue` or as `n/striped shirt`.
* Inputs can be in any order e.g. if the command specifies `n/NAME c/COLOUR`, `c/COLOUR n/NAME` is also acceptable.
* Items with `…` after them can be used multiple times including zero times.<br>e.g. `[d/DESCRIPTION]...` can be 
  unused, `d/stained`, `d/torn d/stained` etc.
* Input for `c/COLOUR` is case-insensitive, and is either`beige`, `black`, `blue`, `brown`, `green`, `grey`, `orange`, 
  `pink`, `purple`, `red`, `white`, or `yellow`.
* Input for `r/DRESSCODE` is case-sensitive, and is either `casual`, `CASUAL`, `formal`, `FORMAL`, `active` or `ACTIVE`.
* Input for `t/TYPE` is case-sensitive, and is either `upper`, `lower` or `footwear`. <br><br>
NuFash is divided into two sets of features, each allowing the application to
achieve one of its goals.
</div>

### <a name="wardrobe-management-features"></a> Wardrobe Management Features
The first category of features is wardrobe management. These features
aim to achieve the goal of allowing users to better organise their wardrobes.
These include `help`, `add`, `list`, `delete`, `clear`, `edit` and `find`. <br><br>

#### <a name="help"></a> Viewing Help: `help`
Shows an in-app user guide to various commands.<br>

<img src="https://raw.githubusercontent.com/AY2021S2-CS2103T-T12-1/tp/master/docs/images/helpMessage.png" alt="alt text" width="790">

Format: `help`
<br><br>

#### <a name="add"></a> Adding a Garment to the Wardrobe: `add` 
Adds a garment with a name, size, colour, dress code and type into the wardrobe, along with optional descriptions.<br>

<img src="https://raw.githubusercontent.com/AY2021S2-CS2103T-T12-1/tp/master/docs/images/AddGarment.png" alt="alt text" width="790">


Format: `add n/NAME s/SIZE c/COLOUR r/DRESSCODE t/TYPE [d/DESCRIPTION]...`<br>

<div markdown="block" class="alert alert-primary">

**:bulb: Tips:** <br>
* `SIZE` is a positive integer.
* `DRESSCODE` is either 'formal', 'casual' or 'active'.
* `TYPE` is either 'lower', 'upper' or 'footwear'.
</div>

Example:<br>
* `add n/favourite t shirt s/30 c/blue r/casual t/upper`
  <br><br>
  
#### <a name="list"></a> Listing all Garments in the Wardrobe: `list`  
Shows a list of all garments in the wardrobe<br>

<img src="https://raw.githubusercontent.com/AY2021S2-CS2103T-T12-1/tp/master/docs/images/ListGarment.png" alt="alt text" width="790">

Format: `list`

<div markdown="block" class="alert alert-primary">

**:bulb: Tips:** <br>
* Displays the list of all garments in chronological ordering, to easily find the garments that have not been worn 
  in a long time.
  
</div>
<br><br>

#### <a name="delete"></a> Deleting a singular Garment in the Wardrobe: `delete`  
Removes a Garment, associated with the given index, from the wardrobe.<br>

<img src="https://raw.githubusercontent.com/AY2021S2-CS2103T-T12-1/tp/master/docs/images/DeleteGarment.png" alt="alt text" width="790">

Format: `delete INDEX`

<div markdown="block" class="alert alert-primary">

**:bulb: Tips:** <br>
* Deletes the article of clothing at the specified `INDEX`
* The `INDEX` refers to the index number shown in the list command
* The `INDEX` must be a positive integer 1, 2, 3, …<br>
</div>

Example:<br>
* `delete 7`<br>
Removes the 7th Garment in list of clothes currently shown.
<br><br>
  
#### <a name="clear"></a> Clearing all Garments in Wardrobe : `clear`
Clears all existing garments in the wardrobe.

<img src="https://raw.githubusercontent.com/AY2021S2-CS2103T-T12-1/tp/master/docs/images/ClearGarment.png" alt="alt text" width="790">

Format: `clear`
<br><br>

#### <a name="edit"></a> Editing a singular Garment in Wardrobe: `edit`
Edits an existing garment in the wardrobe.<br>

<img src="https://raw.githubusercontent.com/AY2021S2-CS2103T-T12-1/tp/master/docs/images/EditGarment.png" alt="alt text" width="790">

Format: `edit INDEX [n/NAME] [s/SIZE] [c/COLOUR] [r/DRESSCODE] [t/TYPE] [d/DESCRIPTION]...`

<div markdown="block" class="alert alert-primary">

**:bulb: Tips:** <br>
* Edits the garment at the specified `INDEX`.<br>
* The `INDEX` refers to the index number shown in the list command. The index must be a positive integer 1, 2, 3, …
* At least one of the optional bracketed fields must be provided.
* Existing values will be updated to the input values.<br>
</div>

Example:
* `list` followed by `edit 1 c/red s/30`<br>
  Edits the colour and size of the 1st garment in the wardrobe to be red and 30 respectively.
  <br><br>
  
#### <a name="find"></a> Finding Garments in Wardrobe by keywords: `find`
Finds all garments that matched specified keywords during search.<br>

<img src="https://raw.githubusercontent.com/AY2021S2-CS2103T-T12-1/tp/master/docs/images/FindGarmentA.png" alt="alt text" width="790">

<img src="https://raw.githubusercontent.com/AY2021S2-CS2103T-T12-1/tp/master/docs/images/FindGarmentB.png" alt="alt text" width="790">

Format: `find [n/NAMES] [s/SIZES] [c/COLOURS] [r/DRESSCODES] [t/TYPES] [d/DESCRIPTIONS]...`

<div markdown="block" class="alert alert-primary">

**:bulb: Tips:** <br>
* At least one of the optional bracketed fields must be provided.
* A set of keywords (search phrase) can be used for finding.
* At least one keyword in a search phrase should be complete.
* Keywords are not case-sensitive.
* The list of all garments with matching attributes will be shown.<br><br>
</div>
Example:

* `find n/worn out jeans`<br>
  Returns all garments whose name has at least one of the words in the search phrase, "worn out jeans".
* `find n/wOrN OUt Jeans`<br>
  Returns all garments as the previous example, as search phrase need not be case-sensitive
* `find n/worn ou jea`<br>
  Returns all garments as the previous example, as at least `worn` keyword is complete.
* `find c/white s/36 23`<br>
  Returns all garments that are white and are either sized 36 or 23.
<br><br>

#### <a name="exit"></a> Exiting the program: `exit`
Exits the program.<br>
Format: `exit`
<br><br>

### <a name="outfit-building-features"></a> Outfit Building Features
The second category of features is outfit building. These features
aim to achieve the goal of providing users with clothing suggestions, allowing
them to choose outfits that are more aesthetically appealing.
These include `match`, `select`, and `view`.<br><br>

#### <a name="match"></a> Matching Garments to create an outfit: `match`<br>
Finds all articles of clothing that match the colour and dress code,
but do not match the type(s) of a specified garment, or two specified
garments of different types.<br>

<img src="https://raw.githubusercontent.com/AY2021S2-CS2103T-T12-1/tp/master/docs/images/MatchGarment.png" alt="alt 
text" width="790">


Current Supported Format: `match INDEX`<br>
To be Implemented Format: `match INDEX [INDEX]`


Examples:
* `match 1`  
Returns all the articles of clothing that match the colour and dress code
  of the garment at index 1 in the list of garments on display, but do not match
  its type.
* `match 1 2`  **[using 2 indices to find their matching garments is to be implemented]**<br> 
Returns all the articles of clothing that match the colours and dress code of
  the garments at indices 1 and 2 in the list of garments on display, but
  do not match their types.
<br><br>

#### <a name="select"></a> Checking out a Garment from the Wardrobe: `select`
Select a garment to check out of the wardrobe, indicating that it shall be worn today.

<img src="https://raw.githubusercontent.com/AY2021S2-CS2103T-T12-1/tp/master/docs/images/SelectGarment.png" alt="alt text" width="790">

Format: `select INDEX`

<div markdown="block" class="alert alert-primary">

**:bulb: Tips:** <br>
* Selects the garment at the specified `INDEX`.<br>
* The `INDEX` must be a positive integer 1, 2, 3, …
</div>
Example:

* `select 1`<br>
Updates the Last Used date, and places this garment at the end of the list
<br><br>

#### <a name="view"></a> Viewing a valid outfit: `view`
View a set of 3 garments associated with the given indexes that create an outfit.<br>

<img src="https://raw.githubusercontent.com/AY2021S2-CS2103T-T12-1/tp/master/docs/images/ViewGarment.png" alt="alt text" width="790">

Format: `view INDEX INDEX INDEX`

<div markdown="block" class="alert alert-primary">

**:bulb: Tips:** <br>
* Displays the garments at the selected set of `INDEX`.<br>
* The `INDEX` must be a positive integer 1, 2, 3, …
* Garments must be of different Types (i.e. `upper`, `lower` and `footwear`).<br>
* The command must have exactly 3 indexes as input, any more inputs will not be registered.<br>
* Use the coloured garment previews to visualise how your whole outfit will look like.

</div>

Example:
* `view 1 2 3`<br><br>

### Saving the data
NuFash data is saved in the hard disk automatically as a JSON file under [JAR file location]/data/nufash.json
after any command that changes the data. 
There is no need to save manually.

**:exclamation: Caution:**<br>
  Remember not to delete this folder as all your saved data will be discarded.
<br><br>

### Editing the data file 
Advanced users are welcome to update data directly by editing the data file at [JAR file location]/data/nufash.json.

**:exclamation: Caution:**<br>
  If your changes to the data file makes its format invalid, NuFash will discard all data and start with an empty data file at the next run.
<br><br>

---
## FAQ
Q: How do I transfer my data to another Computer?<br>
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains 
the data of your previous NuFash folder.

Q: Who do I contact if I face any issues?<br>
A: Tell us about your issue [here](https://github.com/AY2021S2-CS2103T-T12-1/tp), or better yet, submit a pull request with a way to solve it!

---
## Command Summary


| Action                              | Format, Examples                                                                                                                |
| ----------------------------------- | -----------------------------------------------------------------------                                                         |
| **Adding a Garment**                | `add n/NAME s/SIZE c/COLOUR r/DRESSCODE t/TYPE [d/DESCRIPTION]...`<br> Eg. `add n/sleek tux s/32 c/white r/formal t/upper`      |
| **Deleting a Garment**              | `delete INDEX`<br> Eg. `delete 4`                                                                                               |
| **Editing a Garment**               | `edit INDEX [n/NAME] [s/SIZE] [c/COLOUR] [r/DRESSCODE] [t/TYPE] [d/DESCRIPTION]...`<br>                                         |
| **Listing all Garments**            | `list`                                                                                                                          |
| **Finding Garments**                | `find t/TYPE`<br> Eg. find `t/upper`                                                                                           |
| **Matching a Garment**              | `match INDEX` <br> Eg., `match 1`                                                                                               |
| **Viewing a Garment set**           | `view INDEX INDEX INDEX` <br> Eg., `view 1 2 3`                                                                                 |
| **Selecting a Garment**             | `select INDEX` <br> Eg., `select 1`                                                                                             |
| **Clearing all Garments**           | `clear`                                                                                                                         |
| **Help**                            | `help`                                                                                                                          |
| **Exiting the application**         | `exit`                                                                                                                          |

---
## Credits

This user guide format has been adapted
from [AddressBook Level 3 User Guide](https://github.com/nus-cs2103-AY1920S2/addressbook-level3/blob/master/docs/UserGuide.adoc)
