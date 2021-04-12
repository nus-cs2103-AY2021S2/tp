---
layout: page
title: Chew Wei Hao's Project Portfolio Page
---

## Project: StoreMando

**StoreMando** is an integrated platform fully customised for residents in households, residential colleges and halls,
to help users manage their items effectively and efficiently. StoreMando allows users to keep track of their items'
whereabouts, quantities and expiry dates with a few simple commands. Furthermore, StoreMando also comes with an inbuilt
reminder feature to help users keep track of items that are expiring. All items' information is encapsulated clearly on
our simple and clean Graphical User Interface (GUI). Our application is optimised for use via the Command Line
Interface (CLI) and if users have quick fingers, StoreMando can help users manage their items in the blink of an eye.

Given below are my contributions to the project.

* **Code
  contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=chewwh09&tabRepo=AY2021S2-CS2103T-W10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Refactoring**:
    * Refactored Name to itemName.
      (Pull request [\#53](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/53))

* **New Feature**: Added `reminder` feature to display expiring items in the list.
  (Pull request [\#98](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/98))
  (Pull request [\#131](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/131))
    * What it does: This feature allows the user to filter items in the displayed list based on the difference of their
      expiry date from today, to remind the user what items are expiring soon. It also gives the user the option of 
      filtering it by days or weeks.
    * Justification: This feature improves the product significantly because a user can view the items that are expiring
      within the given specific amount of day. Since StoreMando is an application that is used to store a large number 
      of items, this feature can help to display all the expiring items to the user.

* **Enhancements to existing features**:
    * Modified the `List` feature to filter out items in the specific location
      (Pull request [\#73](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/73))
    
* **New GUI**: Added the expiring and expired table.
  (Pull request [\#215](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/215))
  (Pull request [\#304](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/304))
  (Pull request [\#323](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/323))
    * What it does: This feature allows the user to have an overview of all expired items and item that is expiring 
      within 7 days.
    * Justification: This feature improved the product significantly because it helps the user get an overview of the 
      number of items that are expiring or has expired. This GUI pairs well with the `reminder` and `sort` command. 
      After launching the application, the user will be notified by these tables about the number of items that have 
      expired or are expiring. He/she can use the `reminder` or `sort` command to locate these items so he/she can 
      remove the item or stock up the item.
    * Highlights: This GUI implementation is a challenging task because Javafx is a new programming language for me and
      there weren't many resources I can refer to. Everything that was used to implement the expiring and expired tables
      is learned from the Internet. Furthermore, the API that was used to create these tables (TableView) wasn't part of
      AB3 or our Individual Project. Hence, the implementation of this GUI learned from scratch, and also along the way,
      I encountered many problems regarding this TableView GUI.
    
* **Enhancement to GUI**
    * Color code the expiry date field 
      (Pull request [\#160](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/160))
    * GUI styling - Resizing of scroll bars, result display and panel header size.
      (Pull request [\#281](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/281))
    * GUI theme - Change the color themes of the expiring and expired table.
      (Pull request [\#304](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/304))
    
* **Test**
    * Add `Reminder` feature's test
      (Pull request [\#98](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/98))
      (Pull request [\#131](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/131))
    * Add `List` feature's tests
      (Pull request [\#73](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/73))
      (Pull request [\#122](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/122))
    * Add `Find` feature's tests 
      (Pull request [\#122](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/122))
    * Add tests to uncovered methods and classes
      (Pull request [\#305](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/305))
    

* **Documentation**:
    * User Guide:
        * Edit initial `List` user guide 
          (Pull request [\#73](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/73))
        * Add `reminder` feature 
          (Pull request [\#98](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/98))
        * Update `reminder` feature 
          (Pull request [\#174](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/174))
        * Modify and format User Guide 
          (Pull request [\#174](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/174))
    * Developer Guide:
        * Add glossary
          (Pull request [\#42](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/42))
        * Add use cases
          (Pull request [\#44](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/44))
          (Pull request [\#98](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/98))
        * Add sequence diagrams, activity diagrams and implementation for edit and help command
          (Pull request [\#214](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/214))
        * Update the UiClassDiagram
          (Pull request [\#322](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/322))
    * About Us:
        * Update Chew Wei Hao's part. 
          (Pull request [\#13](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/13))
    
* **Addtitional Contributions**
    * HotFix a bug in Quantity class where the input exceeds Long.MAX_VALUE
      (Pull request [\#195](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/195))
    * Change our success messages into a more meaningful message.
      (Pull request [\#282](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/282))
    * Add javadocs to undocumented methods and classes
      (Pull request [\#335](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/335))

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#75](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/75)
      , [\#76](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/76)
      , [\#93](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/93)
      , [\#101](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/101)
    * Reported bugs and suggestions for other teams in the class:
      * Reported W14-1 [12 bugs](https://github.com/chewwh09/ped/issues)
