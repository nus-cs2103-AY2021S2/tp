---
layout: page
title: James Lee Hong Thern's Project Portfolio Page
---

## Project: JJIMY

JJIMY is a restaurant management app for managing food orders, ingredient inventory, etc.  
The user interacts with it using a CLI, and it has a GUI created with JavaFX.  
It is written in Java, and has about 21.3 kLoC.  

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=jamesleeht&tabRepo=AY2021S2-CS2103T-W15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


* **New Feature**: Find command for all new components
    * What it does: Allows filtering of all lists with a combination of predicates.
    * Justification: Storage needed for other components to persist even after app restarted.
    * Highlights: Allows for a list of predicates to be used in conjunction. Use of predicate and generics instead of a specific predicate implementation in the find commmand allows for easy extensibility.

* **Enhancements to existing features**:: Implement model for all new components
    * What it does: Represents the model for menu dishes, inventory ingredients and orders.
    * Justification: Model needed for other components to work well.
    * Highlights: New interfaces and generic modifications to existing interfaces / classes to make adding more components easier and more extensible.

* **Enhancements to existing features**:: Implement storage for all new components
    * What it does: Allow JSON storage to work for all new components.
    * Justification: Storage needed for other components to persist even after app restarted.
    * Highlights: Made all models JSON compatible without need for extra JsonAdapted classes.

* **Documentation**:
    * User Guide:
        * Committed initial UG in markdown
        * Standardize command format and described usage
    * Developer Guide:
        * Included general outline of team contributions for Week 10 task

* **Team-Based Task**:
    * Set up branch rules and ensure forking workflow followed
    * Enforced JDK11 compilation through gradle
  

* **Review/mentoring contributions**:
    * As team lead, ensured project was running smoothly across milestones
    * Reviewed PRs and advised teammates on code structure
    * Ensured consistency of processes and UG / DG
    
