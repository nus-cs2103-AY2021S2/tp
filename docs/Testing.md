---
layout: page
title: Testing guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Running tests

There are two ways to run tests.

* **Method 1: Using IntelliJ JUnit test runner**
  * To run all tests, right-click on the `src/test/java` folder and choose `Run 'Tests in 'tp.test''`
  * To run a subset of tests, you can right-click on a test package,
    test class, or a test and choose `Run 'ABC'`
* **Method 2: Using Gradle**
  * To run all tests (including GUI tests and checkstyle), do a `gradlew clean headless check guiTests` on Windows Command Prompt or `./gradlew clean headless check guiTests` on a Mac, Linux, or Windows Power Shell
  * To run all tests except checkstyle, do a `gradlew clean headless test guiTests` on Windows Command Prompt or `./gradlew clean headless test guiTests` on a Mac, Linux, or Windows Power Shell
  * To run all tests except GUI tests, do a `gradlew clean check` on Windows Command Prompt or `./gradlew clean check` on a Mac, Linux, or Windows Power Shell
  * To run all tests except checkstyle and GUI tests, do a `gradlew clean test` on Windows Command Prompt or `./gradlew clean test` on a Mac, Linux, or Windows Power Shell

<div markdown="span" class="alert alert-secondary">:link: **Link**: Read [this Gradle Tutorial from the se-edu/guides](https://se-education.org/guides/tutorials/gradle.html) to learn more about using Gradle.
</div>

--------------------------------------------------------------------------------------------------------------------

## Types of tests

This project has four types of tests:

1. *Unit tests* targeting the lowest level methods/classes.<br>
   e.g. `seedu.address.commons.StringUtilTest`
1. *Integration tests* that are checking the integration of multiple code units (those code units are assumed to be working).<br>
   e.g. `seedu.address.storage.StorageManagerTest`
1. Hybrids of unit and integration tests. These test are checking multiple code units as well as how they are connected together.<br>
   e.g. `seedu.address.logic.LogicManagerTest`
1. *GUI tests* that test GUI features by simulating user interactions. These  include both tnit tests and integration tests.<br>
    e.g. `seedu/address/ui/MainWindowTest`
