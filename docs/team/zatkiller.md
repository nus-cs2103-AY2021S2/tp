---
layout: page
title: Dylan Ghee's Project Portfolio Page
---

## Project: HEY MATEz

HEY MATEz is a desktop application to get rid of your woes by allowing you to track members and tasks within a club efficiently and easily! 
It is a Command Line Interface (CLI) application which handles user requests that are typed into the input box as commands and 
it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to mark a task as completed or uncompleted. (Pull Requests: [\#96](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/101), [\#101](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/101))
  
  * What it does: allows the user to change the status of a task from completed or uncompleted.
  
  * Justification: This feature improves the product significantly because a user can indicate whether a task is completed or uncompleted. With this indication, users
  are able to focus on tasks that are not completed yet.  
  
  * Highlights: The implementation was challenging as I had to design the feature such that it was only restricted to 2 values - completed and uncompleted. After taking into account design 
  considerations, I decided to implement an Enum class named TaskStatus, with UNCOMPLETED and COMPLETED as the enum constants. Using this, I was able to add an additional attribute
  to the Task Class, status, while being able to restrict the allowed values of a task's status to completed and uncompleted. 

* **New Feature**: Added the ability to prioritize a task. (Pull Requests: [\#114](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/114))

  * What it does: allows the user to assign a task a certain priority.
  
  * Justification: This feature improves the product significantly because a user can indicate the priority of a task - high, medium, low or unassigned. With this priority value, users
  will be to focus on tasks which are of higher priority.
  
  * Highlights: The implementation was challenging as I had to design the feature such that it was only restricted to 4 values - high, medium, low and unassigned. After taking into account design 
  considerations, I decided to implement an Enum class named PRIORITY, with HIGH, MEDIUM, LOW and UNASSIGNED as the enum constants. Using this, I was able to add an additional attribute
  to the Task Class, priority, while being able to restrict the allowed values of a task's priority to high, medium, low or unassigned.

* **New Feature**: Added the ability to find tasks assigned to a particular member. (Pull Requests: [\#168](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/168))
  
  * What it does: allows the user to find all tasks assigned to a particular member.
  
  * Justification: This feature improves the product significantly because a user can conveniently search what tasks has been assigned to a particular member.
  
  * Highlights: The implementation was challenging as I had to think of a way to check which tasks have been assigned to a particular member. After considering the current software architecture,
  I made a decision to implement a predicate, TaskContainsAssigneePredicate. When the Task List is updated with the predicate, the predicate calls the `hasAssignee` method of the Task class to check 
  if the name specified in the command exists as an assignee and updates the Task List accordingly. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=zoom&zA=zatkiller&zR=AY2021S2-CS2103T-W14-3%2Ftp%5Bmaster%5D&zACS=233.65598705501617&zS=2021-02-19&zFS=&zU=2021-04-05&zMG=undefined&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Project management**:
  * Responsible for project direction and task delegation
  * Managed releases `v1.1, v1.2, v1.2.1, v1.3` (4 releases) on GitHub
  * Set up github organization and repository
  * Set up continuous integration
  * Create milestones and issues

* **Enhancements to existing features**:
  * Updated add and list commands to addMember and viewMember commands (Pull requests: [\#66](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/66), [\#68](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/68), [\#71](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/71))
  * Remove redundant Address class and instances (Pull requests [\#65](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/65), [\#151](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/151))
  * Add sample tasks to existing sample data which only contains sample persons (Pull requests [\#125](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/125))

* **Documentation**:
  * User Guide:
    * Added documentation for the commands `done` and `undo` (Pull requests: [\#101](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/101))
    * Added Introduction, Summary and List of Content (Pull requests: [\#154](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/154), [\#179](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/179))

  * Developer Guide:
    * Update the user stories in the Developer Guide (Pull requests: [\#105](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/105))
    * Update the Model Diagram in the Developer Guide (Pull requests: [\#188](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/188))
    * Add implementation documentation for done and undo command (Pull requests: [\#134](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/134))

* **Community**:
  * PRs reviewed (with non-trivial review comments) (Pull requests: [\#187](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/187), [\#67](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/67), [\#70](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/70))
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/117))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/zatkiller/ped/issues/4), [2](https://github.com/zatkiller/ped/issues/2), [3](https://github.com/zatkiller/ped/issues/7))


