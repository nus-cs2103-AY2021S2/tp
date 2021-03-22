---
layout: page
title: Kumaran's Project Portfolio Page
---

## Project: StoreMando

StoreMando is a **desktop app for managing inventory, optimized for use via a Command Line Interface** (CLI) while still
having the benefits of a Graphical User Interface (GUI). If you can type fast, StoreMando can get your inventory
management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the ability to sort items in the list.
  (Pull request [\#101](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/101))
    * What it does: This feature allows the user to sort items in the displayed list by quantity or expiry date. 
      For sort by expiry date, items will be sorted by items with earliest expiry date to latest expiry date. 
      For sort by quantity, items are sorted in ascending order of quantity.
    * Justification: This feature improves the product significantly because a user can view the items with expiry dates
      nearing and items with stocks running low. Users can thus remember to finish such items quickly or restock on 
      such items.
    * Highlights: It required an in-depth analysis of design alternatives. The implementation was challenging as I did 
      not know how to constantly update the list while keeping its attribute final. I also had to update the list being 
      displayed which took in a predicate to update unlike sort which required a comparator to be updated.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=kumsssss&sort=
  groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs
  ~functional-code~test-code~other&since=2021-02-19)

* **Refactoring**:
    * Refactored all instances of Person in StoreMando to Item 
      (Pull request [\#57](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/57))

* **Enhancements to existing features**:
    * Modified the add feature to include an additional optional attribute, expiry date 
      (Pull request [\#75](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/75))
    * Updated the ExpiryDate class to take in a LocalDate attribute which stores the expiry date of an item.
      (Pull request [\#75](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/75))   
    * Wrote additional tests for existing features to increase coverage from 84% to 92% 
      (Pull request [\#119](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/119))

* **Documentation**:
    * User Guide:
        * Added the documentation for the features `add` [\#75](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/75)
        * Added the documentation for the features `sort` [\#101](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/101)
        * Updated the documentation for the features `reminder`
          [\#125](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/125)
        * Updated command summary for the User Guide [\#125](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/125)
        * Changed the formatting and layout for the User Guide and added a section on Navigating User Guide 
          [\#125](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/125)
    * Developer Guide:
        * Added NFRs to the Developer Guide (Pull request [\#41](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/41))
        * Added Use Cases to the Developer Guide 
          (Pull request [\#43](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/43))
    * About Us:
        * Updated Kumaran's part (Pull request [\#18](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/18))

* **Community**:
    * PRs reviewed (with non-trivial review comments): (Pull requests 
      [\#73](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/73)
      , [\#76](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/76)
      , [\#82](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/82)
      , [\#93](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/93)
      , [\#98](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/98)
      , [\#99](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/99)
      , [\#100](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/100)
    

