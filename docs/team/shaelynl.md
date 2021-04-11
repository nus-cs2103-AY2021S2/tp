---
layout: page
title: Shaelyn Lam's Project Portfolio Page
---

## Project: Pawbook

Pawbook is a desktop application for dog school managers to facilitate their bookkeeping of puppies and dogs in the
school, optimized for use via a **Command Line Interface (CLI)** which caters to fast-typers who prefer to use a keyboard.
You can navigate the application with ease and execute instructions by typing text-based commands in the command box provided.
Given below are my contributions to the project.

* **New Feature**: Drop Command
  * Implemented the Drop Command that allows users to remove dogs which were previously enrolled in a program.
  * In the process, I had to implement `EnrolDropCommandParser` and `ProgramRegistrationCommand` to allow proper functioning of this command.
  * I worked with other members of the team who were working on the Enrol Command as these two commands were largely similar.
  * An additional feature would be allowing batch dropping, such as one dog from multiple programs, or multiple dogs from one program, but not multiple dogs from multiple programs.
  * Implemented code coverage for Drop Command.

* **Code Refactoring**: Changed `UniqueOwnerList` to `UniqueEntityList`
  * Since Pawbook works with dogs and programs on top of just owners, we had to change the initial `AB3-AddressBook` to support all three entities.
  * Removed project-wide references and usage of variables linked to `UniqueOwnerList` and changed it to `UniqueEntityList` to fit with our project instead.
  * Fixed all related test cases that started failing after the major refactoring.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=shaelynl&tabRepo=AY2021S2-CS2103T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs)

* **Project management**:
  * Managed milestones `v1.2 - v1.4` on GitHub.
  * Assigned and allocated issues to team members for smoother work allocation.

* **Enhancements to existing features**: GUI Refactoring
  * Designed logos and card components for Pawbook using Canva.
  * Changed the CSS such that the UI reflects the distinct Pawbook colours as presented in our initial UI Mockup.
  
* **Documentation**:
  * User Guide:
    * Contributed to the Drop Command and consistently edited the rest of the User Guide to ensure a seamless and readable document from start to end.
    * Revamped the syntax format for the entire User Guide.
    * Added screenshots and annotations for every command, giving the user a visual representation of Pawbook's features.
    * Streamlined and cleaned up the User Guide frequently to match the current implementations of Pawbook.

  * Developer Guide:
    * Completed Drop Command and its respective activity and sequence diagrams.
    * Added on to the use cases. 

* **Community**:
  * Reviewed and merged pull requests of other contributors frequently.
  * Tested Pawbook's features regularly and reported bugs to other teammates who were in charge of those features.
  * Reported bugs and suggestions to other teams in the class.

* **Tools**:
  * Used JavaFX library and CSS and Canva extensively for the Graphic User Interface.
