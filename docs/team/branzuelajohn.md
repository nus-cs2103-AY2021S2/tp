---
layout: page
title: John Alec Mendoza Branzuela's Project Portfolio
---

## Project: Pawbook

Pawbook is a desktop application for dog school managers to facilitate their bookkeeping of puppies and dogs in the
school, optimized for use via a **Command Line Interface (CLI)** which caters to fast-typers who prefer to use a
keyboard. You can navigate the application with ease and execute instructions by typing text-based commands in the
command box provided.

Given below are my contributions to the project.

* **New Feature**: Add Command
  * Implemented the AddDogCommand and AddProgramCommand that adds dogs and programs to the database.
  * I had to create the Dog class and Program class respectively.
  * Furthermore, I had to create the relevant identity/data field classes for these two entities such as Breed, Sex, etc.
  * In the process, I had to build `AddDogCommandParser`, `AddProgramCommandParser` to allow the proper functioning of this command.

* **New Feature**: Delete Command
  * Implemented the `DeleteProgramCommand` that deletes program from the database.
  * In the process, I had to build `DeleteProgramCommandParser`
  

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=branzuelajohn&tabRepo=AY2021S2-CS2103T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=)

* **Project management**:
  * Managed milestones `v1.2 - v1.4` on GitHub
  * Assigned and allocated issues to team members for smoother work allocation.
  * Took notes for team meetings.
  * Created [PE-D issues checklist](https://github.com/AY2021S2-CS2103T-T10-1/tp/issues/343) for members to refer to easily when
  settling the bugs from PE Dry Run.
  
* **Enhancements to existing features**:
  * Improved the GUI of the Help Window and also added the UG class to supplement the HelpWindow FXML file.

* **Code Refactoring**: Helped to remove all references to `AB3-AddressBook`
  * Assisted to remove project-wide references and usage of variables linked to `AddressBook` and changed it to `Database` to fit with our project.

* **Code Testing**: Writing test cases
  * Wrote test cases for AddProgramCommand, AddOwnerCommand, DeleteProgramCommand, DeleteOwnerCommand, UG, AddProgramCommandParser, AddOwnerCommandParser, DeleteOwnerCommandParser, DeleteOwnerCommandParser
  
* **Documentation**:
  * User Guide:
    * Contributed significant portions in Introduction, Navigating the User Guide,
      GUI Layout, Main Page View, Add Command and Delete Command and Command Summary.
  * Developer Guide:
    * Contributed to writing the Value Proposition, User Stories, Non-Functional Requirements
    * Contributed to Add and Delete Command, and their respective activity diagrams.
    * Wrote implementation details for the [Schedule feature](https://ay2021s2-cs2103t-t10-1.github.io/tp/DeveloperGuide.html#schedule-feature)
    
* **Community**:
  * Reviewed and merged pull requests of other contributors frequently. 
  *  Tested Pawbook's features and reported bugs to teammates.
  * Reported bugs and suggestions to other teams during [PE dry-run](https://github.com/branzuelajohn/ped)

* **Tools**:
  * Used JavaFX library and CSS for Graphic User Interface for HelpWindow

