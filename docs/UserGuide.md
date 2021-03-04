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
* Argument for `t/TYPE` is either `office`, `casual`, `formal`, `sports`, or `others`.

**1. Add Clothing Item: `add`**  
Adds a clothing item with a name, a specified colour, type and size.<br>
Format: `add n/NAME c/COLOUR t/TYPE s/SIZE`<br>
Example:<br>
* add n/favourite t-shirt c/blue t/casual s/M

**2. Remove Clothing Item: `remove`**  
Removes a clothing item associated with the index.<br>
Format: `remove INDEX`
* Removes the article of clothing at the specified INDEX
* The index refers to the index number shown in the list command
* The index must be a positive integer 1, 2, 3, …<br>

Example:<br>
* `list`<br>
  `remove 2`<br>
  Removes the 2nd article of clothing in the wardrobe.

**3. Viewing Help: `help`**  
Shows an in-app user guide to various commands.<br>
Format: `help`

**4. Listing all clothing `list`**  
Shows a list of all articles of clothing in the wardrobe<br>
Format: `list`

**5. Editing an article of clothing: `edit`**
Edits an existing article of clothing in the wardrobe.<br>
Format: `edit INDEX [n/NAME] [c/COLOUR] [t/TYPE] [s/SIZE]`
* Edits the article of clothing at the specified INDEX. The index refers to the index number shown in the list command. The index must be a positive integer 1, 2, 3, …
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
---
### FAQ
Q: How do I transfer my data to another Computer?<br>
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous nü fash folder.

Q: Who do I contact if I face any issues?<br>
A: Tell us about your issue [here](https://github.com/AY2021S2-CS2103T-T12-1/tp), or better yet, submit a pull request with a way to solve it!

---
### Command Summary
*{more to be added}*

---
