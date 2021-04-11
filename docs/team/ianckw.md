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
    * What it does: Allows the user to see the listed items in the GUI
    * Justification: Users are now able to track the items that they have for easy reference and straightforward management.
    * Highlights: Implemented to ensure that when other commands are used, the lists are updated and displayed properly

* **New Feature**: Implented `order complete` command, which marks orders from UNCOMPLETED to COMPLETED
    * What it does: Allows the user to mark orders as completed so they are no longer displayed in the order list and are instead displayed in the order history.


* **New Feature**: Implemented `order history` command, which shows completed orders and cancelled orders
    * What it does: Allows the user to see the history or orders in the GUI
    * Justification: Users are now able to track the orders that have been cancelled or completed

* **Enhancements to existing features**: List sorts by datetime and stays sorted when other commands are issued
    *

* **Documentation**:
    * User Guide: 
    Updated guides for the useage of the `[COMPONENT] list`, `order complete` and `order history` feature.
        *
    * Developer Guide: 
    Added in user stores
    Implemented documentation for features
        *

* **Team-Based Task**:
    *

* **Review/mentoring contributions**:     
    * Consistently reviewed other team members' PRs for approval.
    * Implemented several bugfixes.
    * 

