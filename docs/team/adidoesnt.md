---
layout: page
title: Aditya Banerjee's Project Portfolio Page 
---

## Project: NuFash

NuFash is a desktop wardrobe management application. It was built with the intention
of allowing its users to better organise their wardrobes, as well as make better sartorial choices 
through a variety of clothing organisation and selection features. 
It employs a command line interface for quick input, 
along with some graphical elements for aesthetic appeal.

This document entails my contributions to the NuFash project.

* **Code Contribution:** [RepoSense Link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=adidoesnt&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=adidoesnt&tabRepo=AY2021S2-CS2103T-T12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false) <br>
  <br>
* **Project Management:** `v1.2.1`- `v1.4`, 3 releases on GitHub. <br>
  <br>
* **Enhancement to Existing Feature: Changed colour scheme of GUI**
  [\#58](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/58)
  * What it does: Changed colour scheme of the GUI to a teal theme.
  * Justification: Being an application built upon the premise of improving
    aesthetic appeal, it was agreed that the application's interface should reflect
    the same.
  * Highlights:
    * The GUI was updated to adopt a cool teal colour theme, which was brighter and more
      interesting than the original grey monotone, while remaining subtle enough to not look
      gaudy.
<br><br>
* **Enhancement to Existing Feature: Converted Email class to Colour Class:**
  [\#46](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/46)
  * What it does: Converted the Email class to the currently implemented Colour class, allowing colour to be added as an
    attribute of a garment.
  * Justification: Allows the colour of a garment to be represented by the application.
  * Highlights: This enhancement is used by many other operations of the application
    such as adding, editing, finding, or matching items of clothing.
    <br><br>
* **New Feature: Added the ability to match garments**
  [\#67](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/67)
  * What it does: Allows users to view a list of garments that match a specified
  garment in terms of its colour and dress code (formal, casual, active), 
  but are of a different type (upper, lower, footwear).
  * Justification: Meets one of the aims of the NuFash application, which was
    to recommend garments that complement other garments in the user wardrobe.
  * Highlights: 
      * Allows the application to recommend clothes based on the attributes
        of the specified garment.
      * Required the implementation of four new classes (Match Command, Match
        Command Parser, Match Command Test, and Match Command Parser Test) to 
        increase test coverage and ensure that the operation worked the way it was intended to.
      * Required the implementation of a new predicate subclass based upon which
        matching garments were filtered from the wardrobe.
  <br><br>
* **New Feature: Added sample images for clothing items**
  [\#106](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/106)
  * What it does: Allows the application to allow auto-generated previews
    of clothing items in the wardrobe.
  * Justification: This allows users to visualise the clothes that will be
    organising and grouping together to create outfits, without having to
    provide sample images themselves, preserving the quick input of clothing
    items in the process.
  * Highlights:
    * Allows users to preview their clothing items.
    * Makes the GUI more aesthetically pleasing and less bare
    * Allows other operations such as view and select to be more effective,
      as users will be able to decide if they like the match recommendations
      provided by the application.
    * Required the modification of Garment and MainApp classes to load
      sample images from storage upon start-up.
      <br><br>
* **Documentation:**
  * **User Guide:**
    * Added documentation for `add` and `edit` commands.
      [\#28](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/28),
      [\#29](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/29),
    * Added documentation for the `match` command.
      [\#91](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/91)
  * **Developer Guide:**
    * Added target user profile and value proposition under product scope section.
      [\#17](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/17)
    * Added documentation for the `match` command.
      [\#74](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/74)
      [\#173](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/173)
      [\#176](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/176)
      [\#178](https://github.com/AY2021S2-CS2103T-T12-1/tp/pull/178)
<br><br>
* **Community:**
  
  * Reviewed another team's application and helped point out bug for them to fix in order for them to improve their product. [2](https://github.com/adidoesnt/ped/issues/2), [4](https://github.com/adidoesnt/ped/issues/4), [11](https://github.com/adidoesnt/ped/issues/11), [13](https://github.com/adidoesnt/ped/issues/13) among others.
