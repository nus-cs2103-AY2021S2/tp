---
layout: page
title: Tasha Wan's Project Portfolio Page
---

## Project: HEY MATEz

HEY MATEz is a desktop application to get rid of your woes by allowing you to track members and tasks within a club efficiently and easily!
It is a Command Line Interface (CLI) application which handles user requests that are typed into the input box as commands and
it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add and edit tasks.
  * What it does: Allows the user to add a task with a title, and description as well as edit these details.
    (Pull Requests: [\#62](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/62), [\#63](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/63))
  * Justification: This feature allows the user to add a task to the task list and edit the task based on its index.
    Should the user need to update the details of a task, they can edit it easily.
  * Highlights: The implementation was challenging as it involved creating a task list to store added tasks that had to be included in the storage as well.
    I represented each task as a JsonAdaptedTask to be added into the HeyMatez storage along with the members.

* **New Feature**: Added the ability to add a deadline to tasks. (Pull Requests: [\#107](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/107), [\#116](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/116))
  * What it does: Allows the user to add a deadline to tasks.
  * Justification: This feature improves the ability to track tasks based on their deadlines so that users can ensure tasks are completed on time.
  * Highlights: The implementation was challenging as it required me to implement a deadline parser which could parse a valid deadline provided in the
    correct format. I had to handle the errors of incorrect deadline format by comparing it to the required format as well as invalid dates which are not
    found in the calendar. This is so that the user can be notified correctly by the corresponding error messages when the deadline cannot be parsed. 

* **New Feature**: Added the ability to add a role to members and edit a member's role.
  * What it does: Allows the user to add a role to any member and edit this role. (Pull Requests: [\#109](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/109), [\#111](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/111))
  * Justification: This feature allows users to add the member's role so that they can better manage members, and assign tasks to them correspondingly.
  * Highlights: The implementation was challenging as the member role is an optional field. I had to take into account the situation where the user does not 
    specify a member role while adding a member and assign a default value to the field. I decided to assign a default value of member to all members added
    unless specified by the user.

* **New Feature**: Added the ability to find tasks due before a certain deadline.
  * What it does: Allows the user to view list of tasks with deadlines before the provided date. (Pull Requests: [\#153](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/153))
  * Justification: This feature improves the product significantly because a user can conveniently search for tasks that are due soon or due before a specified 
    date to ensure that these tasks are completed on time.
  * Highlights: The implementation was challenging as task deadlines have to be compared using a predicate that compares deadlines as a LocalDate.
    
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=w14&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=tashawan23&tabRepo=AY2021S2-CS2103T-W14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Create issues.
  * Managed milestones and issues.
  * Set up Github repository.

* **Enhancements to existing features**:
  * Reorganise project structure by renaming directories and files to our product. (Pull requests: [\#181](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/181))

* **Documentation**:
  * User Guide:
    * Added documentation for the commands `addTask`, `editTask`, `findTasksBefore` (Pull requests: [\#108](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/108), [\#119](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/119))
    * Wrote the introduction and purpose of the user guide as well as use case scenarios for each command (Pull Requests: [\#196](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/196), [\#190](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/190))

  * Developer Guide:
    * Update the use cases in the Developer Guide (Pull requests: [\#139](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/139), [\#155](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/155))
    * Update the Logic and Model UML Diagrams in the Developer Guide (Pull requests: [\#173](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/173))
    * Update FindBefore Sequence Diagram in the Developer Guide (Pull requests: [\#186](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/186))
    * Add implementation explanation for `findBefore` command (Pull requests: [\#186](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/186))

* **Community**:
  * PRs reviewed (Example pull requests: [\#185](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/185))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/tashawan23/ped/issues/3), [2](https://github.com/tashawan23/ped/issues/9), [3](https://github.com/tashawan23/ped/issues/5))
  

