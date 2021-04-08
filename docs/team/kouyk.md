---
layout: page
title: Yong Kang's Project Portfolio Page
---

## Project: Pawbook

Pawbook is a desktop application for dog school managers to facilitate their bookkeeping of puppies and dogs in the school, optimized for use via a **Command Line Interface (CLI)** which caters to fast-typers who prefer to use a keyboard. You can navigate the application with ease and execute instructions by typing text-based commands in the command box provided.

Given below are my contributions to the project.

* **New Feature**: Schedule Command
  * Create the schedule command to make it possible for users to view all ongoing programs on happening on a particular day, writing the command and its parser along the way alongside the `ProgramOccursOnDatePredicate` to perform the actual filtering.

* **Enhancements to existing features**:
  * Add/delete command:
    * Perform necessary refactoring to allow for addition/deletion of 3 different types of entities.
  * JSON data file:
    * Created all necessary adaptions of the various entities needed to be stored into a Jackson-compatible format.
    * Make use of Jackson annotations to utilize its polymorphism awareness to dynamically restore each JSON object back to the corresponding type.
  * List command:
    * Enable filtering of entities according to the actual type.

* **Code Refactoring**: Perform initial refactoring to adapt original codebase
  * Renamed the package to `dog.pawbook`.
  * Renamed most of the AddressBook references to Pawbook.
  * Rename `Person` to `Owner` and require entity keyword during add and delete.
  * Disabled a few commands while the core of the application was rebuilt.
  * Created a base `Entity` class that is parent to `Owner` and use it throughout the application.
  * Switched from using displayed index as reference to using unique universal ID.
  * Made it possible to display any type of entities with less reliance on GUI-side changes.

* **Code Refactoring**: General adherence to OOP
  * Refactor several parts of the features both old and new to conform to OOP standards and avoid boilerplate wherever possible.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=kouyk&tabRepo=AY2021S2-CS2103T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=)

* **Project management**:
  * Managed milestones `v1.1 - v1.4` on GitHub.
  * Planned out the necessary work to be allocated.
  * Reviewed most of the functional code PRs and gave constructive feedback on areas that can be improved.
  * Provided assistance to teammates when they are stuck on a particular task. 

* **Documentation**:
  * User Guide:
  * Developer Guide:

* **Community**:
  * Actively participated in the module forum on GitHub and provided help to other course mates.
  * Reported numerous valid bugs to another team during PE dry-run.
