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
<br><br>

**1. Viewing Help: `help`**  
Shows an in-app user guide to various commands.<br>
Format: `help`
<br><br>

**2. Add Clothing Item: `add`**  
Adds a clothing item with a name, a specified colour, type and size.<br>
Format: `add n/NAME c/COLOUR t/TYPE s/SIZE`<br>
Example:<br>
* `add n/favourite t-shirt c/blue t/casual s/M`
  <br><br>

**3. Listing all clothing `list`**  
Shows a list of all articles of clothing in the wardrobe<br>
Format: `list`
<br><br>

**4. Remove Clothing Item: `remove`**  
Removes a clothing item associated with the index.<br>
Format: `remove INDEX`
* Removes the article of clothing at the specified INDEX
* The index refers to the index number shown in the list command
* The index must be a positive integer 1, 2, 3, …<br>

Example:<br>
* `list`<br>
  `remove 2`<br>
  Removes the 2nd article of clothing in the wardrobe.
  <br><br>

**5. Editing an article of clothing: `edit`**<br>
Edits an existing article of clothing in the wardrobe.<br>
Format: `edit INDEX [n/NAME] [c/COLOUR] [t/TYPE] [s/SIZE]`
* Edits the article of clothing at the specified INDEX. The index refers to the index number shown in the list command. The index must be a positive integer 1, 2, 3, …
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.<br>

Example:
* `list`<br>
  `edit 1 c/red s/M`<br>
  Edits the colour and size of the 1st article of clothing to be red and M respectively.
  <br><br>

**6. Finding clothing that matches a certain type: `find`**<br>
Finds clothing that matches a certain type.<br>
Format: `find t/type`
* The search is case insensitive<br>

Examples:
* `find n/worn out jeans`<br>
  Returns the article of clothing that is named "worn out jeans".
* `find c/white s/S`<br>
  Returns all articles of S-sized clothing that are white in colour.
  <br><br>
  
**7. Exiting the program: `exit`**<br>
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
2. Add: `add n/NAME c/COLOUR t/TYPE s/SIZE`<br>
Eg. `add n/sleek tux c/white t/formal wear s/L`
3. List: `list`
4. Remove: `remove INDEX`<br>
   Eg. `remove 4`
5. Edit: `edit INDEX n/NAME c/COLOUR t/TYPE s/SIZE`<br>
Eg. `edit 1 n/polka dotted shirt c/red t/casual s/s`
6. Find: `find t/TYPE`<br>
Eg. find `t/Office`
7. Exit: `exit`
---
