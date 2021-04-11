---
layout: page
title: Lim Rui Xiong's Project Portfolio Page
---

## Project: CakeCollate

CakeCollate is a desktop order book application used for tracking cake orders. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

## Summary of Contributions

Given below are my contributions to the project.
[Here is a link to the code I contributed.](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=RuiXiong2211&tabRepo=AY2021S2-CS2103T-T11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

### Enhancements implemented:
* **New Feature**: Added the ability to receive reminders
  * What it does: Users can now receive a list of undelivered orders that are within a certain time frame from the 
  current date.
  * Justification: This feature improves the product significantly because a user can now easily filter through 
  undelivered impending orders within a time frame they have the flexibility to be able to specify.
  * Highlights: This enhancement affects existing fields and fields to be added in future. 
  It required an in-depth analysis of design alternatives. The implementation too was challenging as it required 
  changes to existing fields.
  
* **New Feature**: Added the ability to add special requests to orders
  * What it does: Users can now add special requests or notes to specific orders
  * Justification: This feature improves the product significantly because a user can now check whether an order 
  has any important details that a customer has requested for their order. 
  
* **Backend**: Implemented the Storage for the new Model, OrderItems
  * What it does: The OrderItems model is saves automatically when the user adds or delete items from it.
  Whenever the user starts the application, data from the latest session is loaded up into the current session.
  
### Contributions to the User Guide
* Reformatted the structure of the user guide such that it flows more logically and added hyperlinks to headers 
for easy navigation to the important sections.
* Split up the tables in the command summary so that the tables present the appropriate amount of information
and not be overloaded.
* Split up the different commands into it's appropriate categories, so that it is neater to navigate.
* Added information required to use the `remind` and `request` command, with examples and screenshots to aid
in my explanation
* Maintain overall flow and structure of the user guide, adding screenshots where necessary.

### Contributions to the Developer Guide
* Updated all existing diagrams from AB3 to reflect the current codebase of CakeCollate.
* Added implementation details, design considerations and sequence diagram for the `remind` command. Here is
the sequence diagram of it. <br>
![RemindSequenceDiagram](img/RemindSequenceDiagram.png)
<br>
* Added Use Cases for Add, Edit, Delete command.

### Contributions to team-based tasks
* Maintained the issue tracker
* Updating the User/Developer guide not related to features as mentioned above.


