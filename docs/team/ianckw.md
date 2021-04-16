---
layout: page
title: Ian Chan Kit Wai's Project Portfolio Page
---

## Project: JJIMY

JJIMY is a restaurant management app for managing food orders, ingredient inventory, etc. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 21.3 kLoC.

I was tasked with overseeing the testing of the project and was additionally in charge of extending various commands to operate on all the components of the app.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=IanCKW&tabRepo=AY2021S2-CS2103T-W15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: Implemented `list` command for all components of the app, which include menu dishes, orders, customers, and ingredients in inventory 
(Pull Request [\#54](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/54))
    * What it does: Allows the user to see the listed items in the GUI
    * Justification: Users are now able to track the items that they have for easy reference and straightforward management.
    * Highlights: Implemented to ensure that when other commands are used, the appropriate list is updated and displayed properly in the GUI.
    * Obstacles: Many bugs arose as more features that linked different components were added. For instance, when menu dishes and orders were linked,
    I had to ensure that changes to the menu dishes would only occur for uncompleted dishes, which were currently in the order list and not the order history.
    (Pull Request [\#54](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/54))

* **New Feature**: Implented `order complete` command, which marks orders from UNCOMPLETED to COMPLETED 
(Pull Request [\#111](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/111))
    * What it does: Allows the user to mark orders as completed so they are no longer displayed in the order list and are instead displayed in the order history.

* **New Feature**: Implemented `order history` command, which shows completed orders and cancelled orders
(Pull Request [\#113](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/113))
    * What it does: Allows the user to see the history or orders in the GUI
    * Justification: Users are now able to track the orders that have been cancelled or completed. Implemented to protect users from editing order history.
    (Pull Request [\#246](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/246))

* **Enhancements to existing features**: Order list sorted chronologically
    * What it does: Order list sorts by datetime and stays sorted when other commands are issued
    * Obstacles: Maintaining the sort when other commands were implemented. Eg, order add and order edit.
    

* **Documentation**:
    * User Guide: 
    Updated guides for the useage of the `[COMPONENT] list`, `order complete` and `order history` feature. Further, updated guide continuously and
    made it more readable (Pull Request [\#270](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/270))
        
    * Developer Guide: 
    Added in user stores (Pull Request [\#19](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/19))
    Implemented documentation for list, order complete and order history (Pull Request [\#109](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/109))
    Added documentation for other sections under the "implementions" section 
        

* **Team-Based Task**:
    * Worked with Marcus on Data Link issues (Pull Request [\#246]([https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/246))
    * Ensured that code quality was high, eg maintiaing SLAP principle so that teammates could rely on my classes and rely that everything was abstracted properly.
    (Pull Request [\#256](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/254))

* **Review/mentoring contributions**:     
    * Consistently reviewed other team members' PRs for approval.
    * Implemented several bugfixes that had to do with data link (eg, linking between menu dishes and orders)
    

