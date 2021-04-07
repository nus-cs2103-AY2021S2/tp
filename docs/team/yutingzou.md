---
layout: page
title: Zou Yuting's Project Portfolio Page
---

Hi I'm Yuting, a year 4 Business Analytics student. I like to cook and explore nice food in every city I visit :)

## Project: Tutor Tracker

Given below are my contributions to the project.

* **New Feature**: Added the ability to view an individual tutor.
  * What it does: allows the user to view an individual tutor with a specified index in the Tutor Tracker.
  * Justification: This feature improves the product because a user can select to view a particular
  tutor of interest, including details such as a tutor's profile and cost of different subjects.
  * Highlights: This feature is an extension from the existing feature - list tutors. It is affected by other commands
  that change the student panel in GUI because this feature depends on the index of a tutor in the list at the time 
    when the command is entered. </br>
    
* **New Feature**: Added the ability to add a grade record.
  * What it does: allows the user to add a new grade record, including the subject, graded item and grade to the GradeBook.
  * Justification: this feature improves the product significantly because it extends the usability of the product. 
    It provides the user a convenient way to add their grade records to the digital GradeBook for reference when choosing tutors.
  * Highlights: This enhancement affects all grade-related commands to be added in the future.
    The implementation too was challenging as it required changes to the existing model, logic, storage and ui. </br>

* **New Feature**: Added the ability to delete an existing grade record.
  * What it does: allows the user to delete a grade record that is outdated.
  * Justification: This feature improves the product significantly because the grade records in the GradeBook can be outdated when the user is promoted.
    the app should provide a convenient way to keep the digital GradeBook updated.
  * Highlights: This enhancement affects all grade-related commands to be added in the future.
      The implementation too was challenging as it required changes to the existing model, logic, storage and ui. </br>

* **New Feature**: Added the ability to edit a grade record.
  * What it does: allows the user to edit an existing grade record with a specified index.
  * Justification: This feature improves the product significantly because the user can make mistakes when adding a new grade
    and the app should provide a convenient way to rectify them.
  * Highlights: The implementation was challenging as it required changes to the existing model, logic, storage and ui. </br>

* **New Feature**: Added the ability to list all existing grades.
  * What it does: allows the user to view a full list of all existing grade records.
  * Justification: This feature improves the product because the user can make mistakes when adding a new grade
    and the app should provide a convenient way to rectify them.
  * Highlights: The implementation was challenging as it required changes to the existing model, logic, storage and ui.
  * Credits: reused the code from ListCommand in AddressBook 3. </br>
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=yutingzou&tabRepo=AY2021S2-CS2103-T14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Contributed in refactoring the existing code base
  * Documented the target user profile [#7](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/7)
  * Maintain the defensiveness of the code: enable assertions in the project's gradle file.
  
* **Enhancements to existing features**:
  * Update the GUI to display subject list in each `TutorCard` [Pull request #91](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/91)
  * Update the GUI to display the newly added `GradeBook`
  
* **Documentation**:
    * User Guide:
      * Added documentation for the features `add_appointment` [#7](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/7)
      * Added documentation for the features `add_grade`, 
        `delete_grade`, `list_grade`, `edit_grade` [#188](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/188)
    * Developer Guide:
      * Added glossary
      * Added implementation details of the `add_appointment` feature [#8](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/8)
      * Added implementation details of the `view_tutor` feature [#84](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/84/)
      * Added implementation details of the `add_grade`, `delete_grade`, `list_grade`, `edit_grade` features (to be added)
  
* **Community**:
  * PRs reviewed (with non-trivial review comments): [#]()
  * Reported bugs and suggestions for other teams in the class (examples: [#]())
  
* **Tools**:

