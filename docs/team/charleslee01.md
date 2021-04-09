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

* **New Feature**: Edit Command
  * Implemented the EditEntityCommand, EditOwnerCommand and EditDogCommand that edits owners and dogs in the database.
  * The EditOwnerCommand and EditDogCommand class are implemented such that it extends from the EditEntityCommand.
  * In the process, I had to build `EditEntityCommandParser`, `EditOwnerCommandParser` and `EditDogCommandParser`to allow the proper functioning of this command.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=CharlesLee01&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19)

* **Project management**:

* **Enhancements to existing features**:
  * Allow edit command to work for three different entities: Dog, Owner and Program.

* **Documentation**:
  * User Guide:
    * Contributed significant portions in initial layout of the user guide. Navigating the User Guide,
      Edit Command and Command Summary.
    * Maintain the consistency of the user guide. Make sure each command format is accurate.
    * Use icons such as :bulb: and :heavy_exclamation_mark: to notify readers about possible warnings and hints when using Pawbook.
  * Developer Guide:
    * Contributed to writing the User Stories and Non-Functional Requirements
    * Contributed to Edit Command, and their respective activity diagrams.
    * Update the diagrams and their usage in the developer guide.
    * Maintain the neatness of the developer guide by removing redundant commands and diagrams.

* **Community**:
  * Reviewed docs-related stuff such as user guide and developer guide frequently.
  * Reported bugs and suggestions to other teams in the class.
  
