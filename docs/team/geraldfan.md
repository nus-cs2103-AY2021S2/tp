#Overview

**semester.config is a desktop application for managing your tasks.**
While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).

#Summary Of Contributions

## Code Contributed
My code contributions can be found [here](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=geraldfan&tabRepo=AY2021S2-CS2103-T14-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false.io/tp-dashboard/#breakdown=true&search=geraldfan).

##Enhancements implemented
I implemented 2 main features:
1. ModuleCode attribute for Task which will be useful for users;
2. Undo Command, 
3. Redo Command as well as the VersionedTaskTracker which supports these commands

Enhancement 1: Added the ModuleCode attribute for Task
    
    * What it does:  Allows the user to add and edit the ModuleCode as an attribute of the Task
    * Justification: Allows the user to know the module that the Task belongs to

Enhancement 2: Implemented the Undo Command

    * What it does: Allows the user to undo the last command which altered the state of the application
    * Justification: This allows the user to undo any mistakes that were made i.e. clearing the application
    * Highlights: It was difficult to implment the functionality where only commands which altered the application 
                    could be undone as there was no way to differentiate between state modifying commands and non-state modifying 
                    commands. This was overcome by creating a NonModifyingCommand Enum to differentiate between the 
                    commands as well as overriding the toString() methods of each Command such that they could be 
                    checked against the NonModifyingCommand Enum.

Enhancement 3: Implemented the Redo Command as well as the VersionedTaskTracker

    * What it does: Allows the user to redo the last command that modified the application state
    * Justification: This allows the user to redo a command after an Undo Command was issues. 
    * Highlights: This enhancement follows the behaviour that most moderrn desktop applications follow, where if a 
                    new command that alters the application is used after a Undo Command, the redundant states will 
                    be purged. 


##Contributions to the UG
Added documentation for the features `edit`, `clear`, `undo` and `redo`

##Contributions to the DG
* I was in charge of the Model component and did the initial version of the Model component UML diagram.
* Added use cases for "Editing a deadline", "Setting a priority tag to a deadline" and "Adding a note to a deadline"
##Contributions to team-based tasks

* Refactored ReadOnlyAddressBook to ReadOnlyTaskTracker in all usages
* Refactored AddressBook to TaskTracker in all usages
* Refactored UniquePersonList to UniqueTaskList in all usages
* Refactored ModuleName to TaskName in all usages
* Approved and merged PRs where possible


##Contributions beyond the project team

