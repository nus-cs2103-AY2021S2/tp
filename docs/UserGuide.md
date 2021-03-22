---
layout: page
title: User Guide
---

## Introduction

DietLAH! is a **desktop app with a Command-Line Interface (CLI) that allows users to easily track and maintain their meals so that they are able to maintain their ideal body weight.** If you can type fast, you’ll be able to record your meals in this app much faster than other traditional GUI-based diet tracking apps.

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
  * [3.6. Delete food intake](#36-delete-food-intake)
  * [3.7. Query food intake](#37-query-food-intake)
     * [3.7.1. Query food intake on a day](#371-query-food-intake-on-a-day)
     * [3.7.2. Query food intake over a period of days](#372-query-food-intake-over-a-period-of-days)
* [Command summary](#command-summary)
<!--te-->

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `dietrack.jar`.

1. Copy the file to the folder you will be launching DieTrack from on a daily basis.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`bmi`**`h/150 w/70 i/75`: Initializes the current user BMI with height of 150cm, weight of 70kg and ideal weight of 75kg.

   * **`bmi`**`query`: Queries the current user's BMI with its height and weight information.

   * **`bmi`**`update h/170 w/70 i/80`: Updates the current user BMI to height of 170cm, weight of 70kg and ideal weight of 80kg.

   * **`plan`**`recommended`: Queries the recommended diet plan based on user's BMI.

   * **`plan`**`t/1`: Queries more information of the weight loss diet plan.

   * **`food`**`d/today n/tomatoes p/10 c/10 f/10`: Adds tomato consumption with protein of 10g, carbohydrates of 10g and fats of 10g to the food intake for today.

   * **`food`**`query d/today`: Queries the food intake for today.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features


--------------------------------------------------------------------------------------------------------------------

## 1. Body Mass Index (BMI) Tracker

### 1.1 Input weight, height and ideal weight

On application's first launch, the user is prompted to enter his/her height, weight and ideal weight.
Other commands will be unavailable until this command is first input.

**Format:** `bmi g/GENDER a/AGE h/HEIGHT(CM) w/WEIGHT(KG) i/IDEAL_WEIGHT(KG)`

**Example:** `bmi g/M a/43 h/170 w/70 i/80`

**Expected output:**

Success in updating user information

### 1.2 Query weight, height and BMI

The user can query their weight and height everyday to see their progress.

**Format:** `bmi_query`

**Expected output:**

Below is your current height and weight:\
Last Updated: 2021-02-28\
Weight: 100 kg\
Height: 170 cm\
BMI: 34.6 (High Risk of Obesity-related diseases)

### 1.3 Update weight, height and ideal weight

The user can update their weight and height everyday to track their progress. 
This is recommended, so the user will have a more accurate view of their BMI status.

**Format:** `bmi_update g/GENDER a/AGE h/HEIGHT(CM) w/WEIGHT(KG) i/IDEAL_WEIGHT(KG)`

**Example:** `bmi_update g/M a/43 h/170 w/70 i/80`

**Expected output:**

Success in updating user information

## 2. Diet Plan Selector

### 2.1 Get diet recommendation based on current BMI

Gets a recommended diet plan based on the user's current BMI stored in the system.

**Format:** `plan_recommend`

**Expected output:**

Here are the recommended weight loss diet plans based on your BMI of 24.22:
1. Weight Loss Diet (Less-carbs)

### 2.2 View active diet plan

Shows the current active diet plan previously selected by the user.

**Format:** `plan_current`

**Expected output:**

You are on the Weight Loss Diet (Less-carbs)!\
Here's the information tailored to your BMI:\
Daily calories intake: 1,648 kcal\
Daily Protein intake: 145 g\
Daily Carbohydrates intake: 143 g\
Daily Fat intake: 55 g

### 2.3 Select active diet plan

Changes the current active diet plan to the newly specified plan.

**Format:** `plan_set p/ID`

**Example:** `plan_set p/1`

**Expected output:**

You are now on the Weight Loss Diet (Less-carbs)!\
Here's the information tailored to your BMI:\
Daily calories intake: 1,648 kcal\
Daily Protein intake: 145 g\
Daily Carbohydrates intake: 143 g\
Daily Fat intake: 55 g

### 2.4 List all available diet plans

Displays a list of available diet plans.

**Format:** `plan_list`

**Expected output:**

Here are the available diet plans:

1. Weight Loss Diet
2. Weight Gain Diet
3. Muscle Gain Diet

### 2.5 View information about a particular diet plan

Displays information about a particular diet plan.

**Format:** `plan p/ID`

**Example:** `plan p/1`

**Expected output:**

Here's more information about the Weight Loss plan:\
Daily calories intake: 1,648 kcal\
Daily Protein intake: 145 g\
Daily Carbohydrates intake: 143 g\
Daily Fat intake: 55 g

## 3. Macronutrients Tracker
### 3.1 Add food item

Creates new food items with their nutrients value and stores them in a list.

**Format:** `food_add n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS`

**Example:** `food_add n/tomato c/10 f/10 p/10`

**Note:** Food items with similar names to existing food items cannot be added. 

**Expected output:**

Success adding food item (tomato (Carbos: 10.0g, Fats: 10.0g, Proteins: 10.0g)) into food list.

### 3.2 Update food item

Updates existing food items with their new nutrients value.

**Format:** `food_update n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS`

**Example:** `food_update n/tomato c/20 f/30 p/40`

**Note:** Food item has to exist in the list to update.

**Expected output:**

Successfully updated food item

### 3.3 List food item

Lists all food items that are in the list.

**Format:** `food_list`

**Example:** `food_list`

**Expected output:**

Here are all the food items: 
1. tomato (Carbos: 20.0g, Fats: 30.0g, Proteins: 40.0g)

### 3.4 Delete food item

Lists all food items that are in the list.

**Format:** `food_delete n/name`

**Example:** `food_delete n/tomato`

**Note:** Particular food item has to exist in order to be deleted.

**Expected output:**

Successfully deleted food item:  tomato

### 3.5 Input food intake

For daily meals, the user is required to enter his/her current intake of macronutrients, in terms of carbohydrates, fats, and proteins. The metric unit of measurement specified is in grams, delimited by commas. Refer to sub-category for various scnearios. 

### 3.5.1 Input food intake (For new food items that are not created before)

Inputs food intake for the day and creates new food item.

**Format:** `food_intake_add d/DATE(in d MMM yyyy format) n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS`

**Example:** `food_intake_add d/31 Mar 2021 n/tomato c/10 f/10 p/10`

**Note:** At least one value of nutrients is required to create new food item. If a nutrient value is not provided, it will be set to 0 by default.

**Expected output:**

Success adding food item (tomato (Carbos: 10.0g, Fats: 10.0g, Proteins: 10.0g)) into food intake list.


### 3.5.2 Input food intake (For existing food items)

Inputs food intake for the day using existing food item.

**Format:** `food_intake_add d/DATE(in d MMM yyyy format) n/FOOD_NAME`

**Example:** `food_intake_add d/31 Mar 2021 n/tomato`

**Expected output:**

Success adding food item (tomato (Carbos: 10.0g, Fats: 10.0g, Proteins: 10.0g)) into food intake list.

### 3.5.3 Input food intake (For existing food items, using different nutrient value(s))

Inputs food intake for the day using existing food item with different value(s).

**Format:** `food_intake_add d/DATE(in d MMM yyyy format) n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS`

**Example:** `food_intake_add d/31 Mar 2021 n/tomato c/20 f/35 p/50`

**Note:** Nutrient values are optional to use if the food item exists. Any nutrient value(s) specified for existing food items will be overwritten for future use. Any older records that makes use of this food item will not be affected by the update. 

**Expected output:**

Successfully edited food value to: tomato (Carbos: 20.0g, Fats: 35.0g, Proteins: 50.0g).
Success adding food item (tomato (Carbos: 20.0g, Fats: 35.0g, Proteins: 50.0g)) into food intake list.

### 3.6 Delete food intake

Deletes a food intake item on a specified day.

**Format:** `food_intake_delete d/DATE(in d MMM yyyy format) n/FOOD_NAME`

**Example:** `food_intake_delete d/31 Mar 2021 n/tomato`

**Note:** The food item has to exist before it can be deleted.

**Expected output:**

Successfully deleted food intake:  tomato

### 3.7 Query food intake

Queries food intake items on either a day or over a period of days. Refer to sub-category for more information. 

### 3.7.1 Query food intake on a day

Queries all the food intake(s) on a certain day.

**Format:** `food_intake_query d/DATE(in d MMM yyyy format)`

**Example:** `food_intake_query d/31 Mar 2021`

**Expected output:**

Summary Food Intake for the Day (31 Mar 2021):
1. tomato (Carbos: 10.0g, Fats: 10.0g, Proteins: 10.0g): 170.0 calories
2. tomato (Carbos: 20.0g, Fats: 35.0g, Proteins: 50.0g): 595.0 calories

Total Daily Calories Intake: 765.0 calories.

### 3.7.2 Query food intake over a period of days

Queries all the existing food intake(s) over a period of days (both inclusive).

**Format:** `food_intake_query df/DATE(in d MMM yyyy format) dt/DATE(in d MMM yyyy format)`

**Example:** `food_intake_query df/1 Mar 2021 dt/31 Mar 2021`

**Expected output:**

Summary Food Intake from (1 Mar 2021) to (31 Mar 2021):

Summary Food Intake for the Day (22 Mar 2021):
1. chilli (Carbos: 45.0g, Fats: 70.0g, Proteins: 50.0g): 1010.0 calories
2. tomato (Carbos: 10.0g, Fats: 10.0g, Proteins: 10.0g): 170.0 calories
3. tomato (Carbos: 20.0g, Fats: 35.0g, Proteins: 50.0g): 595.0 calories

Total Daily Calories Intake: 1775.0 calories.

Summary Food Intake for the Day (31 Mar 2021):
1. tomato (Carbos: 10.0g, Fats: 10.0g, Proteins: 10.0g): 170.0 calories
2. tomato (Carbos: 20.0g, Fats: 35.0g, Proteins: 50.0g): 595.0 calories

Total Daily Calories Intake: 765.0 calories.

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
**Delete food intake** | `food_intake_delete d/DATE(in d MMM yyyy format) n/FOOD_NAME` <br> e.g., `food_intake_delete d/31 Mar 2021 n/tomato`
**Query food intake on a day** | `food_intake_query d/DATE(in d MMM yyyy format)` <br> e.g., `food_intake_query d/31 Mar 2021`
**Query food intake over a period of days** | `food_intake_query df/DATE(in d MMM yyyy format) dt/DATE(in d MMM yyyy format)` <br> e.g., `food_intake_query df/1 Mar 2021 dt/31 Mar 2021`
