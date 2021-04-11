---
layout: page
title: Kong Jian Wei's Project Portfolio Page
---

## Project: JJIMY

JJIMY is a restaurant management app for managing food orders, ingredient inventory, etc.  
The user interacts with it using a CLI, and it has a GUI created with JavaFX.  
It is written in Java, and has about 21.3 kLoC.  
I was tasked to ensure the code quality of the project and in charge of the parser and GUI.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=Sharptail&tabRepo=AY2021S2-CS2103T-W15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: Added the ability for the app to understand different components
    * What it does: Allows user to type is the same commands but different components.  
      The different components are:  
      - `Customer` `Menu` `Order` `Inventory`  
        
      Example:  
      `customer add` and `menu add` both uses the `add` command, but it belongs to different components.  
    * Justification: This feature improves the product functionality significantly because a user can now manage multiple
      different list instead of the original `Contact` list only.  
    * Highlights: This enhancement will be able to add on even more components in the future if needed.
    
* **New Feature**: Added a new column to the GUI to view customer list with other components side by side.
    * What it does: Allows user to view customer list on the left column of the GUI together with any other components on the right column of the GUI.  
      The right column will only be affected by `menu list`, `order list` or `ìnventory list` only.
    * Justification: This feature improves the user-friendliness of the app for the user to view 2 lists at a time to minimise navigating to different component list back and forth.
    * Highlights: The tabs below the right column is also clickable with the same functionality.

* **New Feature**: Update ingredient quantity whenever order is added or deleted
    * What it does: 
      - Automatically decrease ingredient quantity when an order contains a dishes that needs that ingredient is added.
      - Similarly, it will automatically increase the ingredient if the order is deleted.
    * Justification: This will ensure the correct amount of ingredients that are stored in the inventory.
      
* **Enhancements to existing features**:
    * Complete overhaul of the app GUI (Pull requests [\#123](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/123))
        * This implementation was challenging as the window border was removed, extra logic is needed to resize the app window.
    
* **Documentation**:
    * General:
        * Improve user-friendliness of product website and personalised its color scheme (Pull requests [\#150](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/150))
            * Drop-down lists 
                * This implementation was challenging as syntax highlighting does not work in the drop-down container.
            * Dark mode and color code commands from different components
    * User Guide:
        * Added the documentation for feature `Help` and `Exit`. (Pull requests [\#144](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/144))
        * Fixed the documentation for the multiple features (Pull requests [\#224](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/224))
            *  Standardised the parameter of command format. 
            *  Updated `order add`, `order find`, `order edit`, `menu add`, `menu edit` formatting.
            *  Added 't' tag to customer add format
            *  Added '-f' and '-a' explanation
    * Developer Guide:
        * Added implementation details and UML Diagram of the different `component` parser (Pull requests [\#87](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/87))
    
* **Team-Based Task**:
    * Setting up the GitHub team organisation and repository.
    * Removed traces of AB3 and update project descriptions, product website link.
    * Enable assertion in Gradle.
    * Managed `v1.3` release on GitHub.
    * Update the correct roles and responsibilities, profile photos for the team in About Us page.
    * Created the template for Project Portfolio Page for the team and linked it with About Us page.

* **Review/mentoring contributions**:
    * PRs reviewed (with non-trivial review comments): [\#111](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/111)
    
