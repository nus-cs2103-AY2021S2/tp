---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](developer/SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

{% include_relative developerGuide/architecture/index.md %}

The sections below give more details of each component.

{% include_relative developerGuide/architecture/Ui.md %}

{% include_relative developerGuide/architecture/Logic.md %}

{% include_relative developerGuide/architecture/Model.md %}

{% include_relative developerGuide/architecture/Storage.md %}


### Common classes

Classes used by multiple components are in the `seedu.partyplanet.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

{% include_relative developerGuide/feature/Help.md %}

{% include_relative developerGuide/feature/UndoRedo.md %}

{% include_relative developerGuide/feature/Add.md %}

{% include_relative developerGuide/feature/Edit.md %}

{% include_relative developerGuide/feature/EventDone.md %}

{% include_relative developerGuide/feature/Autocomplete.md %}

{% include_relative developerGuide/feature/Theme.md %}

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](developer/Documentation.md)
* [Testing guide](developer/Testing.md)
* [Logging guide](developer/Logging.md)
* [Configuration guide](developer/Configuration.md)
* [DevOps guide](developer/DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

{% include_relative developerGuide/requirements/ProductScope.md %}

{% include_relative developerGuide/requirements/UserStories.md %}

{% include_relative developerGuide/requirements/UseCases.md %}

{% include_relative developerGuide/requirements/NonFunctional.md %}

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

{% include_relative developerGuide/testing/testing.md %}

--------------------------------------------------------------------------------------------------------------------
## **Appendix: Effort**

The effort put into PartyPlanet was larger than the effort required to implement AB3.
{% include_relative developerGuide/effort/effort.md %}
