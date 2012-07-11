Problem Overview
--------------------------------------------------------------------------------
This zip file contains the source code for Billing System Application described below:

On a retail website, the following discounts apply: 
1. If the user is an employee of the store, he gets a 30% discount 
2. If the user is an affiliate of the store, he gets a 10% discount 
3. If the user has been a customer for over 2 years, he gets a 5% discount. 
4. For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount). 
5. The percentage based discounts do not apply on groceries. 
6. A user can get only one of the percentage based discounts on a bill.  

Write a program with test cases such that given a bill, it finds the net payable amount.
 
Contents
--------------------------------------------------------------------------------
The zip file is organized as follows
1) ReadMe.txt
2) Eclipse Project containing Source Code and Unit Tests
3) XML files as data source for bills, items, users and discounts - /billingsystem/data
4) Design Document containing a brief overview, sequence and class diagrams of the design

Instructions to run
--------------------------------------------------------------------------------
The project contains a executable BillingSystem class that contains main method 
that supports reading input. Here is the usage:

java BillingSystem -b <billnumber> -m <mode> -p <path> 
-b <billnumber> [MANDATORY] Bill number for which Net Price should be calculated. 
	 For example: -b B001
-m <mode> [OPTIONAL] Data Access Objects - seeded or xml. DEFAULT: xml 
	 For example: -m seeded OR -m xml 
-p <path> [OPTIONAL] Path to directory containing data XML files DEFAULT: data folder in project 
	 For example: -p c:/data 
	 
For Example: java BillingSystem -b 00B1

NOTE: MAKE SURE BILL NUMBER PASSED AS INPUT IS MAINTAINED IN /billingsystem/data/bills.xml