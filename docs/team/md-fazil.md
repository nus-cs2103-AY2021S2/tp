---
layout: page
title: Mohamed Fazil's Project Portfolio Page
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
  contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Md-Fazil&tabRepo=AY2021S2-CS2103T-W10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
  
* **Contributions to team-based tasks**:
    * Produced milestone v1.2 product demo demonstrating all features implemented at the end of the iteration.[Video Link](https://youtu.be/CYhKT7_DR_k)
    
* **Refactoring**:
    * Refactored all instances of Email to ExpiryDate and updated corresponding tests (Pull request [\#56](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/56))
    
* **Tests**:
    * Updated all tests in logic.model. (Pull request [\#168](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/168))

* **Features**: 
    * Added location panel to GUI. (Pull request [\#216](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/216))
    * What it does: Allowed users to view a list of all the locations of their items stored in the inventory.
    * Justification: This would allow users to keep track of locations that they had key in. This feature was implemented from
      scratch as AddressBook-3 only had one item list panel. Apart from working on the GUI, code had to be written for the backend 
      logic component to retrieve the list of locations to display.
      
* **Enhancements to existing features**:
    * Modified `Edit` to add items, changed parsing of inputs and added tests for edit command. (Pull request [\#76](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/76))
        * What it does: Allowed users to edit an item's details.
        * Justification: Editing of item's details had to be parsed differently as compared to the implementation of edit 
          feature in AddressBook-3. In addition, the existing feature was improved such that users would be notified if 
          there is no change detected unlike AddressBook-3 where duplicates values will be overwritten. This was implemented 
          to prevent users from having any confusion.
    * Modified `Edit` and `Add` to return warning when an expired item is added or edited and added additional tests for the features. (Pull request [\#99](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/99))
    * Modified `Help` to open user guide on browser if supported or display help window otherwise. (Pull request [\#100](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/100))
    * Modified `Add` and `Edit` feature to allow addition of similar item as long as name, location date and name are not all the same. (Pull request [\#161](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/161))
        * What it does: Allowed the addition of items as long as all the items differed by either name, location or expiry date.
        * Justification: This would allow users to have more flexibility in adding items. However, this also meant that additional checks and tests had
          to be implemented to allow users to do so. This was different from AddressBook-3 which did not allow contacts with
          the same name to exist.
    * Changed parsing of quantity to remove trailing zeroes. (Pull request [\#272](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/272))   
    
* **Documentation**:
    * About Us:
        * Updated Mohamed Fazil's part. (Pull requests [\#14](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/14))
        
    * Read Me:
        * Updated UI mockup of ReadMe to match StoreMando. (Pull
          request [\#19](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/19))
          
    * User Guide:
        * Updated Intro as of v1.1. (Pull request [\#31](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/31))
        * Updated Features as of v1.1. (Pull request [\#35](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/35))
        * Updated User Guide for edit feature (Pull request [\#76](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/76))  
        * Updated User Guide for help feature (Pull request [\#100](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/100))  
        * Updated the formatting of the user guide as of v1.3 and made changes to Intro, Quick Start, Features and FAQ (Pull request [\#124](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/124))
        * Added terms to glossary section and performed minor refactoring. (Pull request [\#312](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/312)) 
        * Updated User Guide layout section to reflect latest changes in GUI. (Pull request [\#320](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/320))

    * Developer Guide:
        * Added manual testing for add command. (Pull request [\#148](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/148)) 
        * Fixed typos and did minor reformatting. (Pull request [\#179](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/179))
        * Added sequence diagrams, activity diagrams and implementation for add and sort command. (Pull request [\#220](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/220))
        * Standardise implementations of reminder, add and list features. (Pull request [\#288](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/288))
        * Added effort section to summarise challenges faced and effort put in by the team. (Pull request [\#308](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/308))
    
* **Community**:
    * PRs reviewed (with non-trivial review comments): 
      [\#34](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/34)
      , [\#73](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/73)
      , [\#75](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/75)
      , [\#93](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/93)
      , [\#98](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/98)
      , [\#101](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/101)
      , [\#121](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/121)
      , [\#141](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/141)
      , [\#151](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/151)
      , [\#155](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/155)
      , [\#157](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/157)
      , [\#170](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/170)
      , [\#175](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/175)
      , [\#211](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/211)
      , [\#215](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/215)
      , [\#221](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/221)
      , [\#279](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/279)
      , [\#280](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/280)
      , [\#303](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/303)
      , [\#304](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/304)
      , [\#326](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/326)
      , [\#327](https://github.com/AY2021S2-CS2103T-W10-2/tp/pull/327)
      
    * Reported bugs and suggestions for other teams in the class:
        * Reported [11 bugs](https://github.com/Md-Fazil/ped/issues) in group W15-4's application during mock PE.

    

