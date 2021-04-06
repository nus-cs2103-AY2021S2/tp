---
layout: page
title: Rachel Lim's Project Portfolio Page
---

## Project: HEY MATEz

HEY MATEz is a desktop application to get rid of your woes by allowing you to track members and tasks within the club efficiently and easily!
It is a Command Line Interface (CLI) application which handles user requests that are typed into the input box as commands and
it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to delete Tasks.
    * What it does: Allows the user to delete a specific task given a valid index one at a time.(Pull Requests: [\#67](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/67), [\#106](https://github.com/AY2021S2-CS2103T-W14-3/tp/pull/106))
    * Justification: This feature allows the user to delete an existing Task in the list based on a given index. 
      Should the user accidentally adds a task, they can delete it easily. 
    * Highlights: The implementation was challenging as it required changes to existing commands. For instance, I had to change the function of
    isNonZeroUnsignedInteger in StringUtil.java from a boolean function to an int function to take into account of the Valid Integers,
      Invalid Integers and Invalid Input such that different error messages would be thrown should the index differ from the format.
