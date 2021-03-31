---
layout: page
title: User Guide
---

<p align="center">
  <img src="images/dietlah-slim.png" alt="DietLAH!">
</p>

# User Guide

## Table of Contents

<!--ts-->
* [Introduction](#introduction)
* [Understanding the User Guide](#understanding-the-user-guide)
* [Quick start](#quick-start)
* [Features](#features)
* [1. Body Mass Index (BMI) Tracker](#1-body-mass-index-bmi-tracker)
  * [1.1. Input weight, height and ideal weight](#11-input-weight-height-and-ideal-weight)
  * [1.2. Query weight, height and BMI](#12-query-weight-height-and-bmi)
  * [1.3. Update weight, height and ideal weight](#13-update-weight-height-and-ideal-weight)
* [2. Diet Plan Selector](#2-diet-plan-selector)
  * [2.1. Get diet recommendations based on current BMI](#21-get-diet-recommendation-based-on-current-bmi)
  * [2.2. View active diet plan](#22-view-active-diet-plan)
  * [2.3. Select active diet plan](#23-select-active-diet-plan)
* [3. Macronutrients Tracker](#3-macronutrients-tracker)
  * [3.1. Add food item](#31-add-food-item)
  * [3.2. Update food item](#32-update-food-item)
  * [3.3. List food item](#33-list-food-item)
  * [3.4. Delete food item](#34-delete-food-item)
  * [3.5. Input food intake](#35-input-food-intake)
     * [3.5.1. Input food intake (For new food items that are not created before)](#351-input-food-intake-for-new-food-items-that-are-not-created-before)
     * [3.5.2. Input food intake (For existing food items)](#352-input-food-intake-for-existing-food-items)
     * [3.5.3. Input food intake (For existing food items, using different nutrient value(s))](#353-input-food-intake-for-existing-food-items-using-different-nutrient-values)
  * [3.6. Update food intake](#36-update-food-intake)
  * [3.7. Delete food intake](#37-delete-food-intake)
  * [3.8. Query food intake](#38-query-food-intake)
     * [3.8.1. Query food intake on a day](#381-query-food-intake-on-a-day)
     * [3.8.2. Query food intake over a period of days](#382-query-food-intake-over-a-period-of-days)
  * [4. Progress report](#4-progress-report)
     * [4.1 Generate progress report](#41-generate-progress-report)
  * [5. Miscellaneous](#5-miscellaneous)
     * [5.1 Reset application to blank](#51-reset-application-to-blank)
     * [5.2 Populate application with sample template data](#52-populate-application-with-sample-template-data)
* [Command summary](#command-summary)
* [Glossary](#glossary)
<!--te-->

--------------------------------------------------------------------------------------------------------------------
## Introduction

DietLAH! is a **desktop app with a Command-Line Interface (CLI) that allows users to easily track and maintain their meals so that they are able to maintain their ideal body weight.** If you can type fast, you’ll be able to record your meals and track your weight in this app much faster than other traditional GUI-based diet tracking apps!

--------------------------------------------------------------------------------------------------------------------

## Understanding the User Guide
To make the User Guide more comprehensible, certain formatting is used in the guide. Familiarising yourself with these formattings may help you get the most out of the User Guide.
Legend | Description
--------|------------------
`Inline code` | Commands and user input
[Tip - to be updated] | Extra information that may be useful
[Important - to be updated] | Important information to take note of

## Quick start

1. Ensure that you have [Java 11](https://www.oracle.com/sg/java/technologies/javase-jdk11-downloads.html) or above installed in your computer

2. Download the [latest DietLAH.jar](https://github.com/AY2021S2-CS2103T-T12-2/tp/releases)

3. Copy the `DietLAH.jar` file to the folder you will be launching DietLAH!

4. Double-click the file to start the application. The application will be pre-loaded with some sample data. To start afresh, use the `reset t/blank` command

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Here are some quick commands to get you started:

   * **`bmi`**`h/150 w/70 i/75`: Initialises the current user BMI with height of 150cm, weight of 70kg and ideal weight of 75kg

   * **`bmi`**`query`: Queries the current user's BMI with its height and weight information

   * **`bmi`**`update h/170 w/70 i/80`: Updates the current user BMI to height of 170cm, weight of 70kg and ideal weight of 80kg

   * **`plan`**`recommended`: Queries the recommended diet plan based on user's BMI

   * **`plan`**`t/1`: Queries more information of the weight loss diet plan

   * **`food_intake_add`**`d/31 Mar 2021 n/tomatoes p/10 c/10 f/10`: Adds tomato consumption with protein of 10g, carbohydrates of 10g and fats of 10g to the food intake for 31 Mar 2021

   * **`food_intake_query`**`d/31 Mar 2021`: Queries the food intake for 31 Mar 2021

6. Refer to the [Features](#features) section below for details of each command

[TODO add screenshot of application screen]

--------------------------------------------------------------------------------------------------------------------

## Features


--------------------------------------------------------------------------------------------------------------------

## 1. Body Mass Index (BMI) Tracker

### 1.1 Input weight, height and ideal weight

On the application's first launch, you will be prompted to enter your height, weight and ideal weight.

**Note:** Other commands will be unavailable until you complete the BMI setup.

**Command Format:** `bmi g/GENDER a/AGE h/HEIGHT(CM) w/WEIGHT(KG) i/IDEAL_WEIGHT(KG)`

**Example:** `bmi g/M a/43 h/170 w/70 i/80`

**Expected output:**

<p align="center">
  <img src="images/user-guide/bmi-result.png">
</p>

### 1.2 Query weight, height and BMI

You can view your height, weight, calculated BMI and associated health risks (if any), at any time by using the BMI query command.

**Command Format:** `bmi_query`

**Expected output:**

<p align="center">
  <img src="images/user-guide/bmi-query-result.png">
</p>

### 1.3 Update weight, height and ideal weight

We recommend updating your height and weight whenever you can. This aids your personal progress tracking and ensures that your information and BMI is always up-to-date.

**Command Format:** `bmi_update g/GENDER a/AGE h/HEIGHT(CM) w/WEIGHT(KG) i/IDEAL_WEIGHT(KG)`

**Example:** `bmi_update g/M a/43 h/170 w/70 i/80`

**Expected output:**

<p align="center">
  <img src="images/user-guide/bmi-result.png">
</p>

## 2. Diet Plan Selector

### 2.1 Get diet recommendation based on current BMI

Get a recommended diet plan based on your current BMI stored in the application.

**Command Format:** `plan_recommend`

**Expected output:**

<p align="center">
  <img src="images/user-guide/plan-recommend-result.png">
</p>

### 2.2 View active diet plan

Shows you the current active diet plan you have selected.

**Command Format:** `plan_current`

**Expected output:**

<p align="center">
  <img src="images/user-guide/plan-current-result.png">
</p>

### 2.3 Select active diet plan

Change your current active diet plan to another one. It's okay to change your mind!

**Command Format:** `plan_set p/ID`

**Example:** `plan_set p/1`

**Expected output:**

<p align="center">
  <img src="images/user-guide/plan-set-result.png">
</p>

### 2.4 List all available diet plans

Shows you a list of currently available diet plans.

**Command Format:** `plan_list`

**Expected output:**

<p align="center">
  <img src="images/user-guide/plan-list-result.png">
</p>

### 2.5 View information about a particular diet plan

Interested in trying out a diet plan? Find out more about its daily nutritional requirements.

**Command Format:** `plan p/ID`

**Example:** `plan p/1`

**Expected output:**

<p align="center">
  <img src="images/user-guide/plan-info-result.png">
</p>

## 3. Macronutrients Tracker
### 3.1 Add food item

Save food items you frequently consume so you don't have to re-enter them every time! Your food list acts as a shortcut to help speed up your food intake recording.

**Command Format:** `food_add n/FOOD_NAME c/CARBOS(G) f/FATS(G) p/PROTEINS(G)`

**Example:** `food_add n/tomato c/10 f/10 p/10`

**Note:** Food names must be unique.

**Expected output:**

<p align="center">
  <img src="images/user-guide/add-food-item-result.png">
</p>

### 3.2 Update food item

Update food items in your food list with new nutritent values.

**Command Format:** `food_update n/FOOD_NAME c/CARBOS(G) f/FATS(G) p/PROTEINS(G)`

**Example:** `food_update n/tomato c/20 f/30 p/40`

**Note:** Ensure that the food item exists in the application.

**Tip:** Not all nutrient fields are compulsory. Save time from having to re-enter data and only include fields you wish to update!

**Expected output:**

<p align="center">
  <img src="images/user-guide/update-food-item-result.png">
</p>

### 3.3 List food item

Shows you all the food items stored in your food list.

**Command Format:** `food_list`

**Example:** `food_list`

**Expected output:**

<p align="center">
  <img src="images/user-guide/food-list-result.png">
</p>

### 3.4 Delete food item

Deletes the specified food item stored in the application.

**Command Format:** `food_delete n/name`

**Example:** `food_delete n/tomato`

**Note:** Deletion of a food item will not affect older food intake item records with similar name.

**Expected output:**

<p align="center">
  <img src="images/user-guide/delete-food-item.png">
</p>

### 3.5 Input food intake

For tracking your diet plan progress, you are encouraged to record your daily food intake. For your convenience, there are a few ways to input a food intake. Refer to the different scenarios outlined below!

**Note:** If there are multiple food intakes with the same date and name, the food name will be automatically renamed to include a duplicate count for easy identification. E.g. Chicken rice, Chicken rice 2


### 3.5.1 Input food intake (For new food items that are not created before)

Record your food intake for the specified date with a new food item not currently in your food list. The food will also be added to your food list for your convenience!

**Command Format:** `food_intake_add d/DATE(in d MMM yyyy format) n/FOOD_NAME c/CARBOS(G) f/FATS(G) p/PROTEINS(G)`

**Example:** `food_intake_add d/31 Mar 2021 n/tomato c/10 f/10 p/10`

**Note:** At least one nutrient value is required to create new food item. If a particular nutrient value is not provided, it will be set to 0 by default.

**Expected output:**

<p align="center">
  <img src="images/user-guide/add-food-intake-new-food-item-result.png">
</p>


### 3.5.2 Input food intake (For existing food items)

Record your food intake for the specified date using an existing food from your food list. Now you can save time having to re-enter your favourite food!

**Command Format:** `food_intake_add d/DATE(in d MMM yyyy format) n/FOOD_NAME`

**Example:** `food_intake_add d/31 Mar 2021 n/tomato`

**Expected output:**

<p align="center">
  <img src="images/user-guide/add-food-intake-new-food-item-result.png">
</p>

### 3.5.3 Input food intake (For existing food items, using different nutrient value(s))

Record your food intake for the specified date using an existing food from your food list, but with different nutrient value(s). The value(s) will also be updated in your food list.

**Command Format:** `food_intake_add d/DATE(in d MMM yyyy format) n/FOOD_NAME c/CARBOS(G) f/FATS(G) p/PROTEINS(G)`

**Example:** `food_intake_add d/31 Mar 2021 n/tomato c/20 f/35 p/50`

**Note:** Any nutrient value(s) specified for an existing food item will be overwritten and updated in the food list for future use. Older food intake record(s) associated with the same food item will retain their original values.

**Expected output:**

<p align="center">
  <img src="images/user-guide/add-food-intake-diff-food-item-result.png">
</p>

### 3.6 Update food intake

Update the nutrient value(s) of a previously entered food intake given the date and food name.

**Command Format:** `food_intake_update d/DATE(in d MMM yyyy format) n/FOOD_NAME c/CARBOS(G) f/FATS(G) p/PROTEINS(G)`

**Example:** `food_intake_update d/31 Mar 2021 n/tomato c/20 f/40 p/50`

**Tip:** Not all nutrient fields are required and only the specified nutrient fields will be updated with the new value while the other values remain unchanged.

**Expected output:**

<p align="center">
  <img src="images/user-guide/update-food-intake-item-result.png">
</p>

### 3.7 Delete food intake

Delete a food intake record from the application.

**Command Format:** `food_intake_delete d/DATE(in d MMM yyyy format) n/FOOD_NAME`

**Example:** `food_intake_delete d/31 Mar 2021 n/tomato`

**Expected output:**

<p align="center">
  <img src="images/user-guide/delete-food-intake-result.png">
</p>

### 3.8 Query food intake

View the list of food intakes on a given day or over a period of days. Refer to the different scenarios outlined below!

### 3.8.1 Query food intake on a day

View the list of food intakes on a specific day.

**Command Format:** `food_intake_query d/DATE(in d MMM yyyy format)`

**Example:** `food_intake_query d/31 Mar 2021`

**Expected output:**

<p align="center">
  <img src="images/user-guide/query-food-intake-per-day.png">
</p>

### 3.8.2 Query food intake over a period of days

View the list of food intakes over a period of days (both inclusive).

**Command Format:** `food_intake_query df/DATE_FROM(in d MMM yyyy format) dt/DATE_TO(in d MMM yyyy format)`

**Example:** `food_intake_query df/1 Mar 2021 dt/31 Mar 2021`

**Expected output:**

<p align="center">
  <img src="images/user-guide/query-food-intake-period.png">
</p>

## 4. Progress report
### 4.1 Generate progress report

Generates a progress report based on your current active diet plan. Your food intake will be calculated against the daily requirements to generate a report detailing how much you have adhered to the plan's requirements.

**Command Format:** `progress`

**Note:** An active diet plan must be selected before running this command.

**Expected output:**

<p align="center">
  <img src="images/user-guide/progress-report.png">
</p>

## 5. Miscellaneous

### 5.1 Reset application to blank

Get rid of all existing records and start afresh.

**Command Format:** `reset t/blank`

**Expected output:**

<p align="center">
  TODO: IMAGE
</p>

### 5.1 Populate application with sample template data

Get rid of all existing records and populate it with the sample template data.

**Command Format:** `reset t/template`

**Expected output:**

<p align="center">
  TODO: IMAGE
</p>

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Input weight, height and ideal weight** | `bmi h/height(cm) w/weight(kg) i/ideal_weight(kg)​` <br> e.g., `bmi h/170 w/70 i/80`
**Query weight, height and BMI** | `bmi query`
**Update weight, height and ideal weight** | `bmi update h/height(cm) w/weight(kg) i/ideal_weight(kg)`<br> e.g., `bmi update h/170 w/70 i/80`
**Get diet recommendation based on current BMI** | `plan recommended`
**View active diet plan** | `plan current`
**Select active diet plan** | `plan active p/plan`<br> e.g., `plan active p/1`
**List all available diet plans** | `plan list`
**View information about a particular diet plan** | `plan t/ID​`<br> e.g.,`plan t/1`
**Add food item** | `food_add n/FOOD_NAME c/CARBOS(G) f/FATS(G) p/PROTEINS(G)` <br> e.g., `food_add n/tomato c/10 f/10 p/10`
**Update food item** | `food_update n/FOOD_NAME c/CARBOS(G) f/FATS(G) p/PROTEINS(G)` <br> e.g., `food_update n/tomato c/20 f/30 p/40`
**List food item** | `food_list`
**Delete food item** | `food_delete n/FOOD_NAME` <br> e.g., `food_delete n/tomato`
**Input food intake (For new food items that are not created before)** | `food_intake_add d/DATE(in d MMM yyyy format) n/FOOD_NAME c/CARBOHYDRATES(G) f/FATS(G) p/PROTEINS(G)` <br> e.g.,`food_intake_add d/31 Mar 2021 n/tomato c/10 f/10 p/10`
**Input food intake (For existing food items)** | `food_intake_add d/DATE(in d MMM yyyy format) n/FOOD_NAME` <br> e.g., `food_intake_add d/31 Mar 2021 n/tomato`
**Input food intake (For existing food items, using different nutrient value(s))** | `food_intake_add d/DATE(in d MMM yyyy format) n/FOOD_NAME c/CARBOHYDRATES(G) f/FATS(G) p/PROTEINS(G)` <br> e.g., `food_intake_add d/31 Mar 2021 n/tomato c/20 f/35 p/50`
**Update food intake** | `food_intake_update d/DATE(in d MMM yyyy format) n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS` <br> e.g., `food_intake_update d/31 Mar 2021 n/tomato c/20 f/40 p/50`
**Delete food intake** | `food_intake_delete d/DATE(in d MMM yyyy format) n/FOOD_NAME` <br> e.g., `food_intake_delete d/31 Mar 2021 n/tomato`
**Query food intake on a day** | `food_intake_query d/DATE(in d MMM yyyy format)` <br> e.g., `food_intake_query d/31 Mar 2021`
**Query food intake over a period of days** | `food_intake_query df/DATE_FROM(in d MMM yyyy format) dt/DATE_TO(in d MMM yyyy format)` <br> e.g., `food_intake_query df/1 Mar 2021 dt/31 Mar 2021`
**Generate progress report** | `progress`
--------------------------------------------------------------------------------------------------------------------

## Glossary

In this glossary, you can find a list of terms that is used throughout this guide and reference their corresponding meaning.

Technical Terms | Meaning
--------|------------------
**Alias** | A term used to represent something. For instance the alias `df/` represents the date from a period.
**Command-Line Interface (CLI)** | An interface that users send commands to a computer program through the form of lines of text.
**Graphic User Interface (GUI)** | An interface that displays interactive visual components for a computer program.
**Java** | A programming language that is used to build this application.

Health Terms | Meaning
-------------|------------------
**Body Mass Index (BMI)** | A value derived from the mass and height of a person, by taking the body mass divided by the square of the body height.
**Macronutrients** | Nutrients, such as fats, proteins and carbohydrates that provide us with energy.
--------------------------------------------------------------------------------------------------------------------

## Appendix

### A1. Diet Plans

The following are diet plans options that comes with DietLAH!.

#### A1.1 Standard Ketogenic Diet

The Standard Ketogenic Diet is a high-fat, low-carb weight-loss diet.
It is designed in such a way that by reducing the intake of carbohydrates,
the body is forced to burn its fat reserves for fuel thereby resulting in weight-loss.

The Standard Ketogenic Diet is suitable for individuals suffering from Type II Diabetes where
excess carbohydrates would have been converted into glucose.

**Classification:** `Weight Loss`  
**Composition:** `70% Fat` `10% Carbohydrates` `20% Proteins`

#### A1.2 High-Protein Ketogenic Diet

The High-Protein Ketogenic Diet is a variation of the Ketogenic Diet (high-fat, low-carb)
which increases the protein intake. This variation is designed to help athletes and
bodybuilders maintain their muscle mass whilst burning fat.

**Classification:** `Weight Loss`  
**Composition:** `60% Fat` `5% Carbohydrates` `35% Proteins`

#### A1.3 Balanced Plan For Weight Gain

This plan is aimed at individuals who are intending to gain healthy weight in a balanced manner.
Some exercise coupled with this diet plan will allow individuals to gain some muscle steadily.

**Classification:** `Weight Gain`  
**Composition:** `30% Fat` `35% Carbohydrates` `35% Proteins`

#### A1.4 Clean Bulk

The clean bulk is a process which bodybuilders use to gain lean muscle mass.
The clean bulk emphasizes consuming healthy whole foods as compared to eating sugary and 
processed foods to hit the calorie intake.

**Classification:** `Weight Gain`  
**Composition:** `30% Fat` `30% Carbohydrates` `40% Proteins`

#### A1.5 High Carbohydrates Bulk

This plan is intended for athletes who are involved in high intensity sports which require high energy consumption.
As such, this plan prescribes a higher amount of carbohydrates to offset this need whilst ensuring the protein 
intake is enough to promote muscle growth and in turn, healthy weight gain.

**Classification:** `Weight Gain`  
**Composition:** `15% Fat` `55% Carbohydrates` `30% Proteins`

#### A1.6 Balanced Plan

The perfect ying-yang. Eat healthy food and complete the calorie goal.
Eat lots of fruits and vegetables, and base meals on higher fiber starchy carbohydrates.

**Classification:** `Maintain Weight`  
**Composition:** `30% Fat` `40% Carbohydrates` `30% Proteins`
