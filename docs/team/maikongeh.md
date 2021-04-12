---
layout: page
title: Michael's Project Portfolio Page
---

## Project: RemindMe

RemindMe is a desktop application for keeping track of user events and deadlines, optimised for use via Command Line 
Interface(CLI) while still having the benefits of a Graphic User Interface(GUI).
RemindMe allows students to always be aware of exams and events deadline as there will be reminder pop-ups and calendar
for students to see!

## Summary of Contributions

Given below are my contributions to the project.

* **New Feature**: Delete modules, assignments, exams and general events.
    * What it does: allows the user to manage their tasks in terms of modules, assignments, exams and general events
       by deleting tasks and events that are no longer needed or relevant.
    * Justification: This feature improves RemindMe significantly as it allows the user to delete entries
      so that the task window does not become too cluttered
    * Highlights: This enhancement affects all the other functionality of RemindMe. 
    * Credits: *{AddressBook3's Person and AddressBook3's AddressBook}*
    
* **New Feature**: Added the ability to add modules, assignments, exams and general events.
    * What it does: allows the user to add modules, assignments, exams and general events in RemindMe.
    * Justification: This feature improves RemindMe significantly as it allows the user to add entries of different natures to RemindMe
      with proper categorisation. This provides a convenient way to view the different types of tasks that the user has.
    * Highlights: This enhancement affects the existing add command, which could only add persons. It required the need to add in a parser for add commands
      to differentiate between adding persons, modules, assignments, exams and general events. Therefore, this implementation required adding of new classes and a change in the logic of RemindMe.
    * Credits: *{AddressBook3's Parser and AddressBook3's AddPersonCommand}*

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=w15&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=maikongeh&tabRepo=AY2021S2-CS2103T-W15-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` 
  
* **Enhancements to existing features**:
    * Wrote additional tests for new features (Pull requests 
    [\#122](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/122),
    [\#248](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/248),
    [\#324](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/324),
    [\#352](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/352))
    
    * Added done command for assignment. This allows the toggle the done status of an Assignment. 
    (Pull requests [\#183](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/183))
    
  
* **Documentation**:
    * User Guide:
        * Added documentations for the features `done` and `delete` 
        (Pull request [\#193](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/193))
        
        * Added documentation for UG. 
        (Pull requests  [\#46](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/46),
                        [\#174](https://github.com/AY2021S2-CS2103T-W15-1/tp/pull/174))
        
    * Developer Guide:
        * Add implementation details of the `delete` feature.


