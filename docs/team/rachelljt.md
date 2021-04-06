---
layout: page
title: Rachel Lim's Project Portfolio Page
---

## Project: HEY MATEz

HEY MATEz is a desktop application to get rid of your woes by allowing you to track members and tasks within the club efficiently and easily!
It is a Command Line Interface (CLI) application which handles user requests that are typed into the input box as commands and
it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to delete tasks.
    * What it does: Allows the user to delete a specific task given a valid index one at a time.(Pull Requests: [\#67](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/67), [\#106](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/106))
    * Justification: This feature allows the user to delete an existing Task in the list based on a given index. 
      Should the user accidentally adds a task, they can delete it easily. 
    * Highlights: The implementation was challenging as it required changes to existing commands. For instance, I had to change the function of
    isNonZeroUnsignedInteger in StringUtil.java from a boolean function to an int function so that 1 will be returned if the index is a Valid Integer,
      2 for Invalid Integers(Non-positive integers) and 3 for Invalid Input(spaces, alphanumeric) such that different error messages would be thrown 
      should the index differ from the format.

* **New Feature**: Added the ability to view a list of uncompleted tasks. (Pull Requests: [\#117](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/117))
  * What it does: Allows the user to view a list of tasks that they have not completed.
  * Justification: This feature improves the product significantly because a user can easily view a list of uncompleted tasks upon inputting the command
    which greatly saves the time of the user. 
  * Highlights: The implementation was challenging as it required me to create another predicate to filter a list of 
    uncompleted tasks. Also, I had to take into account of the different error messages thrown in different situations.
    Also, I had to update the testing to account for the different scenarios of usage.

* **New Feature**: Added the ability to view a list of unassigned tasks.
  * What it does: Allows the user to view a list of tasks that they have not assigned to any member. [\#162](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/162))
  * Justification: This feature improves the product significantly because a user can easily view a list of unassigned tasks upon inputting the command
    which greatly saves the time of the user of searching for the list of unassigned tasks.
  * Highlights: The implementation was challenging as it required me to create another predicate to filter a list of
    unassigned tasks. Also, I had to take into account of the different error messages thrown in different situations.
    Also, I had to update the testing to account for the different scenarios of usage.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=rachelljt&tabRepo=AY2021S2-CS2103T-W14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Create issues
  * Add profile pictures of group members into the team list.

* **Enhancements to existing features**:
  * Change the UI of the app  (Pull requests: [\#110](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/110), [\#128](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/128), [\#238](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/238))
