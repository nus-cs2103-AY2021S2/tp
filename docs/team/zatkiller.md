---
layout: page
title: Dylan Ghee's Project Portfolio Page
---

## Project: AddressBook Level 3

HEY MATEz is a desktop application to get rid of your woes by allowing you to track members and tasks within the club efficiently and easily! 
It is a Command Line Interface (CLI) application which handles user requests that are typed into the input box as commands and 
it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to mark a task as completed or uncompleted. Pull Requests: [[\#96](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/101), [\#101](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/101)
  * What it does: allows the user to change the status of a task from completed or uncompleted
  * Justification: This feature improves the product significantly because a user can indicate whether a task is completed or uncompleted. With this indication, users
  are able to focus on tasks that are not completed yet.  
  * Highlights: The implementation was challenging as I had to design the feature such that it was only restricted to 2 values - completed and uncompleted. After taking into design 
  considerations, I decided to implement an Enum class named TaskStatus, with UNCOMPLETED and COMPLETED as the enum constants. Using this, I was able to add an additional attribute
  to the Task Class, status, while being able to restrict the allowed values of a task's status to completed and uncompleted. 

* **New Feature**: Added the ability to priority to a task. Pull Requests: [[\#114](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/114)
  * What it does: allows the user to find tasks of a specific priority
  * Justification: This feature improves the product significantly because a user can indicate the priority of a task - high, medium, low or unassigned. With this priority value, users
  will be to focus on tasks which are of higher priority.
  * Highlights: The implementation was challenging as I had to design the feature such that it was only restricted to 4 values - high, medium, low and unassigned. After taking into design 
  considerations, I decided to implement an Enum class named PRIORITY, with HIGH, MEDIUM, LOW and UNASSIGNED as the enum constants. Using this, I was able to add an additional attribute
  to the Task Class, priority, while being able to restrict the allowed values of a task's priority to high, medium, low or unassigned.

  **New Feature**: Added the ability to find tasks assigned to a particular member. Pull Requests: [[\#168](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/168)
  * What it does: allows the user to find all tasks assigned to a particular member
  * Justification: This feature improves the product significantly because a user can conveniently search what tasks has been assigned to a particular member.
  * Highlights: The implementation was challenging as I had to think of a way to check which tasks has been assigned to this person. After considering the current software architecture,
  I made a decision to implement a checkAssignee method within the Task class, which allows one to check if the Assignee has been assigned to this task. Using the checkAssignee method, I had the command 
  scan through the list of tasks to look for all tasks that has this assignee and display it on the UI.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=zoom&zA=zatkiller&zR=AY2021S2-CS2103T-W14-3%2Ftp%5Bmaster%5D&zACS=233.65598705501617&zS=2021-02-19&zFS=&zU=2021-04-05&zMG=undefined&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Project management**:
  * Managed releases `v1.1` - `v1.3` (4 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())


