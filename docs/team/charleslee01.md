---
layout: page
title: Charles Lee Lin Ta's Project Portfolio
---
## Project: Pawbook

Pawbook is a desktop application for dog school managers to facilitate their bookkeeping of puppies and dogs in the
school, optimized for use via a **Command Line Interface (CLI)** which caters to fast-typers who prefer to use a
keyboard. You can navigate the application with ease and execute instructions by typing text-based commands in the
command box provided.

Given below are my contributions to the project.

* **Code Refactoring**: Code Quality Improvements
  * Increase defensive coding, exceptions and assertions.
  * Update Javadoc comments so that it fits the current context.
  * General housekeeping of code quality during development.
  * Ensure the MESSAGE_USAGE is synchronised for all command.
  
* **Code Testing**: Update and improve test cases
  * Contributed significantly to testing.
  * Wrote test cases related to edit command, edit command parser, descriptor test for each entities and dog class.
  * Added descriptor builders such as `EditEntityDescriptorBuilder` etc., which allows the testing of edit commands.
  * Update the comments of old testcases.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=CharlesLee01&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19)

* **Project management**:
  * Note down project discussion on collaboration document.

* **Enhancements to existing features**: Edit Command and GUI Help Window
  * Allow edit command to work for three different entities: Dog, Owner and Program.
  * Implemented the EditEntityCommand, EditOwnerCommand and EditDogCommand that edits owners and dogs in the database.
  * The EditOwnerCommand and EditDogCommand class are implemented such that it extends from the EditEntityCommand.
  * In the process, I had to build `EditEntityCommandParser`, `EditOwnerCommandParser` and `EditDogCommandParser`to allow the proper functioning of this command.
  * Ensure help window contains all possible commands for Pawbook.
  
* **Documentation**:
  * User Guide:
    * Contributed significant portions in initial layout of the user guide. Navigating the User Guide, Edit Command and Command Summary.
    * Maintain the consistency of the user guide. For instance, ensure the command formats are accurate.
    * Ensure that sufficient examples and explanations are given so that the exact features of each command are clear to users. 
    * Use icons such as :bulb: to notify readers about possible warnings and :heavy_exclamation_mark: to show the hints when using Pawbook.
    
  * Developer Guide:
    * Contributed to writing the User Stories, Use Case and Non-Functional Requirements
    * Contributed to Edit Command, and their respective activity diagrams.
    * Add new diagrams and update the old diagrams in the developer guide.
    * Maintain the neatness of the developer guide by removing redundant commands and diagrams.

* **Community**:
  * Reviewed docs-related stuff such as user guide and developer guide frequently.
  * Reported bugs and suggestions to other teams in the class.
  
