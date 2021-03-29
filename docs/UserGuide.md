---
layout: page
title: User Guide
---
## Table of Contents

<!--ts-->
* [Introduction](#introduction)
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
* [Command summary](#command-summary)
<!--te-->

--------------------------------------------------------------------------------------------------------------------
## Introduction

DietLAH! is a **desktop app with a Command-Line Interface (CLI) that allows users to easily track and maintain their meals so that they are able to maintain their ideal body weight.** If you can type fast, you’ll be able to record your meals and track your weight in this app much faster than other traditional GUI-based diet tracking apps!

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `DietLAH.jar`.

1. Copy the file to the folder you will be launching DietLAH! from on a daily basis.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Here are some quick commands to get you started:

   * **`bmi`**`h/150 w/70 i/75`: Initializes the current user BMI with height of 150cm, weight of 70kg and ideal weight of 75kg.

   * **`bmi`**`query`: Queries the current user's BMI with its height and weight information.

   * **`bmi`**`update h/170 w/70 i/80`: Updates the current user BMI to height of 170cm, weight of 70kg and ideal weight of 80kg.

   * **`plan`**`recommended`: Queries the recommended diet plan based on user's BMI.

   * **`plan`**`t/1`: Queries more information of the weight loss diet plan.

   * **`food_intake_add`**`d/31 Mar 2021 n/tomatoes p/10 c/10 f/10`: Adds tomato consumption with protein of 10g, carbohydrates of 10g and fats of 10g to the food intake for 31 Mar 2021.

   * **`food_intake_query`**`d/31 Mar 2021`: Queries the food intake for 31 Mar 2021.

1. Refer to the [Features](#features) section below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features


--------------------------------------------------------------------------------------------------------------------

## 1. Body Mass Index (BMI) Tracker

### 1.1 Input weight, height and ideal weight

On the application's first launch, the user is prompted to enter his/her height, weight and ideal weight.
Other commands will be unavailable until this command is first input.

**Format:** `bmi g/GENDER a/AGE h/HEIGHT(CM) w/WEIGHT(KG) i/IDEAL_WEIGHT(KG)`

**Example:** `bmi g/M a/43 h/170 w/70 i/80`

**Expected output:**

<p align="center">
  <img src="images/user-guide/bmi-result.png">
</p>

### 1.2 Query weight, height and BMI

The user can query their weight and height everyday to see their progress.

**Format:** `bmi_query`

**Expected output:**

<p align="center">
  <img src="images/user-guide/bmi-query-result.png">
</p>

### 1.3 Update weight, height and ideal weight

The user can update their weight and height everyday to track their progress. 
This is recommended, so the user will have a more accurate view of their BMI status.

**Format:** `bmi_update g/GENDER a/AGE h/HEIGHT(CM) w/WEIGHT(KG) i/IDEAL_WEIGHT(KG)`

**Example:** `bmi_update g/M a/43 h/170 w/70 i/80`

**Expected output:**

<p align="center">
  <img src="images/user-guide/bmi-result.png">
</p>

## 2. Diet Plan Selector

### 2.1 Get diet recommendation based on current BMI

The user can get a recommended diet plan based on the their current BMI stored in the system.

**Format:** `plan_recommend`

**Expected output:**

<p align="center">
  <img src="images/user-guide/plan-recommend-result.png">
</p>

### 2.2 View active diet plan

Shows the current active diet plan previously selected by the user.

**Format:** `plan_current`

**Expected output:**

<p align="center">
  <img src="images/user-guide/plan-current-result.png">
</p>

### 2.3 Select active diet plan

Changes the current active diet plan to the newly specified plan.

**Format:** `plan_set p/ID`

**Example:** `plan_set p/1`

**Expected output:**

<p align="center">
  <img src="images/user-guide/plan-set-result.png">
</p>

### 2.4 List all available diet plans

Displays a list of available diet plans.

**Format:** `plan_list`

**Expected output:**

<p align="center">
  <img src="images/user-guide/plan-list-result.png">
</p>

### 2.5 View information about a particular diet plan

Displays information about a particular diet plan.

**Format:** `plan p/ID`

**Example:** `plan p/1`

**Expected output:**

<p align="center">
  <img src="images/user-guide/plan-info-result.png">
</p>

## 3. Macronutrients Tracker
### 3.1 Add food item

Adds a new food item with their nutrients value and stores them in the food list. Food items are used as shortcuts to add food intake items without having to type out the values.

**Format:** `food_add n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS`

**Example:** `food_add n/tomato c/10 f/10 p/10`

**Note:** Food item with similar names to existing food item cannot be added. 

**Expected output:**

<p align="center">
  <img src="images/user-guide/add-food-item-result.png">
</p>

### 3.2 Update food item

Updates existing food items with their new nutrients value(s).

**Format:** `food_update n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS`

**Example:** `food_update n/tomato c/20 f/30 p/40`

**Note:** Particular food item has to exist in the list to update. Not all nutrient fields are compulsory. Only the nutrient field(s) specified will have its/their value(s) updated to the latest value. 

**Expected output:**

<p align="center">
  <img src="images/user-guide/update-food-item-result.png">
</p>

### 3.3 List food item

Lists all food items that are stored in the application.

**Format:** `food_list`

**Example:** `food_list`

**Expected output:**

<p align="center">
  <img src="images/user-guide/food-list-result.png">
</p>

### 3.4 Delete food item

Deletes the particular food item stored in the application.

**Format:** `food_delete n/name`

**Example:** `food_delete n/tomato`

**Note:** Particular food item has to exist in order to be deleted. Deletion of a food item will not affect older food intake item record with similar name. 

**Expected output:**

<p align="center">
  <img src="images/user-guide/delete-food-item.png">
</p>

### 3.5 Input food intake

For tracking the user's diet plan progress, the user is encouraged to track their daily food intake by entering the food name and associated macronutrients (carbohydrates, fats and proteins) in grams. There are a few ways to input a food intake such as using the pre-stored food values, or adding a brand new food intake. For Date input, the user can use the alias `d/today` as a shortcut to input today's date. The actions for Food Intake Item can only be performed based on past and current date. The different scenarios are outlined below.

**Note:** If there are multiple food intakes with the same date and name, the food name will be automatically renamed to include a duplicate count for easy identification. E.g. Chicken rice, Chicken rice 2


### 3.5.1 Input food intake (For new food items that are not created before)

Records a food intake for the given date and stores the food in the food list for easy future reuse.

**Format:** `food_intake_add d/DATE(in d MMM yyyy format) n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS`

**Example:** `food_intake_add d/31 Mar 2021 n/tomato c/10 f/10 p/10`

**Note:** At least one nutrient value is required to create new food item. If a particular nutrient value is not provided, it will be set to 0 by default.

**Expected output:**

<p align="center">
  <img src="images/user-guide/add-food-intake-new-food-item-result.png">
</p>


### 3.5.2 Input food intake (For existing food items)

Records a food intake for the given date using an existing food item. The nutrient values will be retrieved from the corresponding food item stored in the application.

**Format:** `food_intake_add d/DATE(in d MMM yyyy format) n/FOOD_NAME`

**Example:** `food_intake_add d/31 Mar 2021 n/tomato`

**Expected output:**

<p align="center">
  <img src="images/user-guide/add-food-intake-new-food-item-result.png">
</p>

### 3.5.3 Input food intake (For existing food items, using different nutrient value(s))

Records a food intake for the given date and updates the existing food item with the new nutrient value(s).

**Format:** `food_intake_add d/DATE(in d MMM yyyy format) n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS`

**Example:** `food_intake_add d/31 Mar 2021 n/tomato c/20 f/35 p/50`

**Note:** Any nutrient value(s) specified for an existing food item will be overwritten and updated in the food list for future use. Older records associated with the food item will retain their original values and is not affected.

**Expected output:**

<p align="center">
  <img src="images/user-guide/add-food-intake-diff-food-item-result.png">
</p>

### 3.6 Update food intake

Updates the nutrient value(s) of an existing food intake matching the given date and food name.

**Format:** `food_intake_update d/DATE(in d MMM yyyy format) n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS`

**Example:** `food_intake_update d/31 Mar 2021 n/tomato c/20 f/40 p/50`

**Note**: The given food intake item must exist in order for update to work. Not all nutrient fields are required and only the specified nutrient fields will be updated with the new value while the other vales remain unchanged.

**Expected output:**

<p align="center">
  <img src="images/user-guide/update-food-intake-item-result.png">
</p>

### 3.7 Delete food intake

Deletes a food intake item for the specified day.

**Format:** `food_intake_delete d/DATE(in d MMM yyyy format) n/FOOD_NAME`

**Example:** `food_intake_delete d/31 Mar 2021 n/tomato`

**Note:** The food item has to exist before it can be deleted.

**Expected output:**

<p align="center">
  <img src="images/user-guide/delete-food-intake-result.png">
</p>

### 3.8 Query food intake

Queries food intake items on either a day or over a period of days. Refer to sub-category for more information. 

### 3.8.1 Query food intake on a day

Queries all the food intake(s) on a certain day.

**Format:** `food_intake_query d/DATE(in d MMM yyyy format)`

**Example:** `food_intake_query d/31 Mar 2021`

**Expected output:**

<p align="center">
  <img src="images/user-guide/query-food-intake-per-day.png">
</p>

### 3.8.2 Query food intake over a period of days

Queries all the existing food intake(s) over a period of days (both inclusive).

**Format:** `food_intake_query df/DATE(in d MMM yyyy format) dt/DATE(in d MMM yyyy format)`

**Example:** `food_intake_query df/1 Mar 2021 dt/31 Mar 2021`

**Expected output:**

<p align="center">
  <img src="images/user-guide/query-food-intake-period.png">
</p>

## 4. Progress report
### 4.1 Generate progress report

Generates a progress report based on the active diet plan. This command will calculate the user's food intakes against the daily requirements and report how much the user has adhered to the plan's requirements.

**Format:** `progress`

**Note:** An active diet plan must be selected before running this command.

**Expected output:**

<p align="center">
  <img src="images/user-guide/progress-report.png">
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
**Add food item** | `food_add n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS` <br> e.g., `food_add n/tomato c/10 f/10 p/10`
**Update food item** | `food_update n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS` <br> e.g., `food_update n/tomato c/20 f/30 p/40`
**List food item** | `food_list`
**Delete food item** | `food_delete n/FOOD_NAME` <br> e.g., `food_delete n/tomato`
**Input food intake (For new food items that are not created before)** | `food_intake_add d/DATE(in d MMM yyyy format) n/FOOD_NAME c/CARBOHYDRATES f/FATS p/PROTEINS` <br> e.g.,`food_intake_add d/31 Mar 2021 n/tomato c/10 f/10 p/10` 
**Input food intake (For existing food items)** | `food_intake_add d/DATE(in d MMM yyyy format) n/FOOD_NAME` <br> e.g., `food_intake_add d/31 Mar 2021 n/tomato`
**Input food intake (For existing food items, using different nutrient value(s))** | `food_intake_add d/DATE(in d MMM yyyy format) n/FOOD_NAME c/CARBOHYDRATES f/FATS p/PROTEINS` <br> e.g., `food_intake_add d/31 Mar 2021 n/tomato c/20 f/35 p/50`
**Update food intake** | `food_intake_update d/DATE(in d MMM yyyy format) n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS` <br> e.g., `food_intake_update d/31 Mar 2021 n/tomato c/20 f/40 p/50`
**Delete food intake** | `food_intake_delete d/DATE(in d MMM yyyy format) n/FOOD_NAME` <br> e.g., `food_intake_delete d/31 Mar 2021 n/tomato`
**Query food intake on a day** | `food_intake_query d/DATE(in d MMM yyyy format)` <br> e.g., `food_intake_query d/31 Mar 2021`
**Query food intake over a period of days** | `food_intake_query df/DATE(in d MMM yyyy format) dt/DATE(in d MMM yyyy format)` <br> e.g., `food_intake_query df/1 Mar 2021 dt/31 Mar 2021`
**Generate progress report** | `progress`