---
layout: page
title: Shen Yang's Project Portfolio Page
---

## Project: Teaching Assistant

Teaching Assistant is a desktop application used for managing contacts and entries for JC/Secondary school teachers.
The user interacts with it using a Command Line Interface (CLI), and it has a Graphical User Interface (GUI) created 
with JavaFX. It is written in Java, and has about 10 (tbc) kLoC.

Given below are my contributions to the project.
* **New Feature**: Added the finalised feature to allow users to list all their contacts.
  * What it does: Allows users to list of all their contacts.
  * Justification: This feature is essential as the find and filter features lists a subset of all contacts. In order
    to revert the contact list to show all contacts, this feature is needed.
    
* **New Feature**: Added the finalised feature to allow users to find their contacts by name.
  * What it does: Allows users to find their contacts by name.
  * Justification: This feature is important as without this, users have to scroll through the whole contact list to
    find the details of a contact they need.
  * Highlights: The original AB3 implementation for this feature uses a OR search to search for contacts by name, 
    which I felt needed to be changed as it does not feel intuitive, especially in the context of searching for names. 
    Hence, I changed the implementation to use an AND search instead.
    
* **New Feature**: Added the finalised feature to allow users to filter their contacts by tags.
  * What it does: Allows users to filter their contacts via tags.
  * Justification: This feature is useful when users do not remember the names of their contacts, but remember a
    characteristic of them that they have previously tagged to their contact. It is also useful for finding more than
    one contact at once.
  
* **New Feature**: Added an early version of the feature which allows users to list their entries.
  * What it does: Allows users to list all their entries, or list them by day or week.
  * Justification: This feature is useful when users have a lot of entries and only want to look at their daily or
    weekly entries to avoid confusing themselves.
    
* **New Feature**: Added an early version of the feature to allow users to find their entries by name.
  * What it does: Allows users to find their entries by name.
  * Justification: This feature is important as without this, users have to scroll through the whole entry list to 
    find the details of an entry they need.
    
* **New Feature**: Added an early version of the feature to allow users to filter their entries by tags.
  * What it does: Allows users to filter their entries via tags.
  * Justification: This feature is useful when users do not remember the names of their entries, but remember
    their category that they have previously tagged to their entry. It is also useful for finding more than 
    one entry at once.
  
* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=goatygoatygoat&tabRepo=AY2021S2-CS2103T-W13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
  
* **Project management**:
  * Managed the code quality of the project 
    (Pull request [\#249](https://github.com/AY2021S2-CS2103T-W13-4/tp/pull/249)).
  * Managed the assigning and tracking of project tasks.
    
* **Enhancements to existing features**:
  * Wrote additional tests for existing features that I had finalised 
    (Pull request [\#207](https://github.com/AY2021S2-CS2103T-W13-4/tp/pull/207)).
    
* **Documentation**:
  * User Guide:
    * Added the initial introduction of the User Guide 
      (Pull request [\#17](https://github.com/AY2021S2-CS2103T-W13-4/tp/pull/17)).
    * Fixed most documentation bugs found in the PE dry run 
      (Pull request [\#199](https://github.com/AY2021S2-CS2103T-W13-4/tp/pull/199)).
    * Added FAQ, saving and editing data features, modified command summary, breakdown of command formats, scenarios
      (Pull request [\#227](https://github.com/AY2021S2-CS2103T-W13-4/tp/pull/227)).
  * Developer Guide:
    * Added the initial Product Scope and Glossary of the Developer Guide 
      (Pull request [\#20](https://github.com/AY2021S2-CS2103T-W13-4/tp/pull/20)).
    * Added the implementation of the `elist` feature 
      (Pull requests [\#85](https://github.com/AY2021S2-CS2103T-W13-4/tp/pull/85), [\#253](https://github.com/AY2021S2-CS2103T-W13-4/tp/pull/253)).
    * Added Documentation, logging, testing, configuration, dev-ops section in the Developer Guide and updated the User Stories 
      (Pull request [\#253](https://github.com/AY2021S2-CS2103T-W13-4/tp/pull/253)).
      
* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#181](https://github.com/AY2021S2-CS2103T-W13-4/tp/pull/181), 
      [\#114](https://github.com/AY2021S2-CS2103T-W13-4/tp/pull/114)
    * Added PPP files with template for the team: [\#211](https://github.com/AY2021S2-CS2103T-W13-4/tp/pull/211)
