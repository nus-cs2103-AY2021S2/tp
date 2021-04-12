---
layout: page 
title: Kumaran's Project Portfolio Page
---

## Project: StoreMando

**StoreMando** is an integrated platform fully customised for residents in households, residential colleges and halls,
to help users manage their items effectively and efficiently. StoreMando allows users to keep track of their items'
whereabouts, quantities and expiry dates with a few simple commands. Furthermore, StoreMando also comes with an inbuilt
reminder feature to help users keep track of items that are expiring. All items' information is encapsulated clearly on
our simple and clean Graphical User Interface (GUI). Our application is optimised for use via the Command Line
Interface (CLI) and if users have quick fingers, StoreMando can help users manage their items in the blink of an eye.

Given below are my contributions to the project.

* **New Feature**: Added the ability to sort items in the list.
  (Pull request [\#101](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/101))
    * What it does: This feature allows the user to sort items in the displayed list by quantity or expiry date. For
      sort by expiry date, items will be sorted by items with earliest expiry date to latest expiry date. For sort by
      quantity, items are sorted in ascending order of quantity.
    * Justification: This feature improves the product significantly because a user can view the items with expiry dates
      nearing and items with stocks running low. Users can thus remember to finish such items quickly or restock on such
      items.
    * Highlights: It required an in-depth analysis of design alternatives. The implementation was challenging as I did
      not know how to constantly update the list while keeping its attribute final. I also had to update the list being
      displayed which took in a predicate to update unlike sort which required a comparator to be updated.
      
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=kumsssss&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19)

* **Project Management**:
    * Produced milestone v1.3 product demo demonstrating all features implemented at the end of the
      iteration. [Video Link](https://youtu.be/ci2me0pkEsY)

* **Refactoring**:
    * Refactored all instances of Person in StoreMando to Item
      (Pull request [\#57](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/57))

* **Enhancements to Existing Features**:
    * Modified the add feature to include an additional optional attribute, expiry date
      (Pull request [\#75](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/75))
    * Updated the ExpiryDate class to take in a LocalDate attribute which stores the expiry date of an item.
      (Pull request [\#75](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/75))
    * Update implementation of reminder, list and find command so that they are executed on the displayed sublist
      instead of the entire inventory. 
      (Pull request [\#158](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/158))
    * Updated sort feature implementation and added tests for the newly created
      methods. (Pull request [\#159](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/159))
    * Modified list feature to make the search
      case-sensitive (Pull request [\#201](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/201))
    

* **Documentation**:
    * User Guide:
        * Added the documentation for the features `add` (Pull request [\#75](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/75))
        * Added the documentation for the features `sort` (Pull request [\#101](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/101))
        * Updated the documentation for the features `reminder`
          (Pull request [\#125](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/125))
        * Updated command summary for the User Guide (Pull request [\#125](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/125))
        * Changed the formatting and layout for the User Guide and added a section on Navigating User Guide
          (Pull request [\#125](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/125))
        * Further formatting of User Guide and including new sections (Pull
          requests [\#180](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/180),
          [\#217](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/217),
          [\#219](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/219))
    * Developer Guide:
        * Added NFRs to the Developer Guide (Pull request [\#41](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/41))
        * Added Use Cases to the Developer Guide
          (Pull requests [\#43](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/43),
          [\#143](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/143))
        * Update NFR, Glossary and Product Scope (Pull request [\#169](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/169))
        * Added activity and sequence diagram for reminder and clear features as well as 
          explaining the implementation of the features. (Pull request [\#221](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/221))
        * Edited the implementation steps and sequence diagrams for edit, find, sort, clear 
          and help features. (Pull request [\#289](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/289))
        * Edit ambiguous explanations and unecessary tips in DG (Pull request [\#316](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/316))  
    * About Us:
        * Updated Kumaran's part (Pull request [\#18](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/18))
        * Updated team members' responsibilities. (Pull request [\#296](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/296))

* **Tests**:
    * Wrote additional tests for existing features to increase coverage
      (Pull request [\#119](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/119))
    * Added tests for classes under logic component (Pull request [\#292](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/292))

* **Community**:
    * PRs reviewed (with non-trivial review comments): (Pull requests
      [\#73](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/73)
      , [\#76](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/76)
      , [\#82](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/82)
      , [\#93](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/93)
      , [\#98](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/98)
      , [\#99](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/99)
      , [\#121](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/121)
      , [\#141](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/141)
      , [\#157](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/157)
      , [\#168](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/168)
      , [\#170](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/170)
      , [\#173](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/173))
      
