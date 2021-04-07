---
layout: page
title: Donavan's Portfolio Page
---

## Project: The Food Diary

The Food Diary - The Food Diary is a desktop app for managing food diary entries,
 optimized with a Command Line Interface (CLI) and packaged with a Graphical User Interface (GUI) using JavaFX.
 It is written in Java, and has about 7.5k LoC.

Given below are my contributions to the project.

* **UI**: Formatted the GUI, layout and colour scheme throughout the app
  * What was done: Improved the UI, layout of elements, and colour scheme to appeal to the usage and patronisation
  from NUS students.
  * Justification: The UI is pivotal in presenting to users information in a clear and readable manner, allowing them to
  interact with The Food Diary intuitively. Visual elements are not jarring or useless to the usage of the app.
  * Highlights: The UI theme is congruent throughout the different windows, in particular, the Main window, the Help
  window, the Revise window, and the View Window, are themed accordingly.
  The implementation required thorough trial and error to get the theme and feel of the UI right,
  as well as synchronise it across the different windows that can be opened in the app.

* **Overall Code Quality and Refactoring**: Refactored the codebase to fit to the functionality of The Food Diary.
  * What was done: All packages, classes, and methods have been refactored to be logical and congruent with the semantics
  and business logic of how a Food Diary should be operated on.
  Also, ensured the overall code quality of new functionalities added.
  * Justification: Inheriting a codebase and customising the code to fit the business logic of our product is essential
  for the seamless and logical continuation of development by us or other developers.
  * Highlights: No trace of the previous app implementation remains, and classes have been designed to make logical
  sense to the operations of a Food Diary. Each entry features a restaurant name, rating, price, address, review and tags.

* **Help Window**: Dedicated a resizable window for the Help guide that users can have local access to on the app.
  * What was done: Implemented a separate window that contains a succint help guide with examples for users to
  conveniently access and learn what and how each perform performs.
  * Justification: Having access to a help guide while using the app allows new users (and testers) to quickly comprehend
  the set of features in the app.
  * Highlights: The help guide features 3 main sections: A succint list of commands, all options for Food categories,
  and for School Location categories. The information provided is presented neatly, and a url to the full user guide
  is provided to users for users to get the complete guide.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=donavan&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Refactored the code base
  * Enhanced the Help Window
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())

* **Documentation**:
  * User Guide:
    * Added a section pertaining to the explanation of the User Interface to users
    * Neatened the syntax and presentation of each feature and the corresponding command to make it consistent with the
    usage of the command in our app.
    * Explained the rationale of each feature and function very clearly.
  * Developer Guide:
    * Added implementation details of the `edit` and `help` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())
