# User Guide
# nufash

---
### Introduction
nufash is for those who prefer to use a desktop application to organise and manage their wardrobe. nufash is 
optimised for users who prefer typing, but also has the added benefit of a smooth and easy to use Graphical User 
Interface(GUI). Start using nufash and never worry about keeping track of your clothes again!

---
### Quickstart
*{more to be added}*

---
### Features
**Command Format**
* Words in `UPPER_CASE` are the parameters to be supplied by the user e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/striped shirt`.
* Items in square brackets are optional e.g. `n/NAME [c/COLOUR]` can be used as `n/striped shirt c/blue` or as `n/striped shirt`.
* Parameters can be in any order e.g. if the command specifies `n/NAME c/COLOUR`, `c/COLOUR n/NAME` is also acceptable.
* Items with `…` after them can be used multiple times including zero times.<br>e.g. `[d/DESCRIPTION]...` can be used as ` ` (i.e. 0 times), `d/stained`, `d/torn d/stained` etc.
* Argument for `r/DRESSCODE` is either `casual`, `formal` or `active`.
* Argument for `t/TYPE` is either `upper`, `lower` or `footwear`.
<br><br>

**1. Viewing Help: `help`**  
Shows an in-app user guide to various commands.<br>
Format: `help`
<br><br>

**2. Adding a Garment: `add`**  
Adds a Garment with a name, size, colour, dress code and type into the wardrobe, along with optional descriptions.<br>
Format: `add n/NAME s/SIZE c/COLOUR r/DRESSCODE t/TYPE [d/DESCRIPTION]...`<br>
Example:<br>
* `add n/favourite t shirt s/30 c/blue r/casual t/upper`
  <br><br>

**3. Listing all Garments: `list`**  
Shows a list of all Garments in the wardrobe<br>
Format: `list`
<br><br>

**4. Deleting a Garment: `delete`**  
Removes a Garment, associated with the given index, from the wardrobe.<br>
Format: `delete INDEX`
* Removes the article of clothing at the specified INDEX
* The index refers to the index number shown in the list command
* The index must be a positive integer 1, 2, 3, …<br>

Example:<br>
* `list`<br>
  `remove 2`<br>
  Removes the 2nd Garment in the wardrobe.
  <br><br>
  
**5. Clearing all Garments: `clear`**<br>
Clears all existing Garments in the wardrobe.
Format: `clear`

**6. Editing a Garment: `edit`**<br>
Edits an existing Garment, associated with the given index, in the wardrobe.<br>
Format: `edit INDEX [n/NAME] [s/SIZE] [c/COLOUR] [r/DRESSCODE] [t/TYPE] [d/DESCRIPTION]...`
* Edits the Garment at the specified INDEX.<br>
  The index refers to the index number shown in the list command. The index must be a positive integer 1, 2, 3, …
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.<br>

Example:
* `list`<br>
  `edit 1 c/red s/30`<br>
  Edits the colour and size of the 1st Garment in the wardrobe to be red and 30 respectively.
  <br><br>

**7. Finding clothing that matches a certain type: `find`**<br>
Finds all articles of clothing that matches all keywords used in each search attribute.<br>
Format: `find [n/NAMES] [s/SIZES] [c/COLOURS] [r/DRESSCODES] [t/TYPES] [d/DESCRIPTIONS]...`
* At least one of the optional fields must be provided.
* The list of all Garments with matching attributes will be shown.

Examples:
* `find n/worn out jeans`<br>
  Returns all the articles of clothing whose name has at least one of the words in the search phrase, "worn out jeans".
* `find c/white s/36 23`<br>
  Returns all articles of clothing that are white in colour and have either sizes 36 or 23.
  <br><br>
  
**8. Matching multiple garments to create an outfit: `match`**<br>
[matching of multiple input garments implemented in v1.3]  
Finds all articles of clothing that match the colour and dress code,
but do not match the type(s) of a specified garment, or two specified
garments of different types.<br>
Format: `match INDEX [INDEX]`
* Number of indices provided must at least 1, and at most 2

Examples:
* `match 1`  
Returns all the articles of clothing that match the colour and dress code
  of the garment at index 1 in the list of garments on display, but do not match
  its type.
* `match 1 2`  
Returns all the articles of clothing that match the colours and dress code of
  the garments at indices 1 and 2 in the list of garments on display, but
  do not match their types.

**9. Selecting...: `select`**
<br><br>

**10. Viewing a set of Garments: `view`**<br>
Views a set of 3 garments associated with the given indexes.<br>
Garments must be of different Types (i.e. `upper`, `lower` and `footwear`).<br>
Format: `view INDEX INDEX INDEX`
* The command must have exactly 3 indexes as input, any more inputs after that will be not be taken into account.
<br><br>
  
**11. Exiting the program: `exit`**<br>
Exits the program.<br>
Format: `exit`

---
### FAQ
Q: How do I transfer my data to another Computer?<br>
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains 
the data of your previous nufash folder.

Q: Who do I contact if I face any issues?<br>
A: Tell us about your issue [here](https://github.com/AY2021S2-CS2103T-T12-1/tp), or better yet, submit a pull request with a way to solve it!

---
### Command Summary
1. Help: `help`
2. Add: `add n/NAME s/SIZE c/COLOUR r/DRESSCODE t/TYPE [d/DESCRIPTION]...`<br>
Eg. `add n/sleek tux s/32 c/white r/formal t/upper`
3. List: `list`
4. Delete: `delete INDEX`<br>
   Eg. `delete 4`
5. Clear: `clear`
6. Edit: `edit INDEX [n/NAME] [s/SIZE] [c/COLOUR] [r/DRESSCODE] [t/TYPE] [d/DESCRIPTION]...`<br>
Eg. `edit 1 n/polka dotted shirt s/28 c/red r/casual`
7. Find: `find t/TYPE`<br>
Eg. `find t/Office`
8. Match: `match INDEX [INDEX]`<br>
Eg. `match 1`
9. Select: `select INDEX`
10. View: `view INDEX INDEX INDEX`
11. Exit: `exit`
---
