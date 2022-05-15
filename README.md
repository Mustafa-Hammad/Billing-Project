# Billing System

## Description
The project simulates the real billing system used by the telecom operators in order to rate the postpaid users consumption and provide a detailed invoice for each user with all his owned numbers and the usage for each one.

## Modules

- ### CDR Parser
    Check if there is a new CDR (Call Data Record) from Mediation System. Then process the CDR and insert data into Database.
- ### Rating Engine
     Process every CDR data and rate this consumption based on contract data.
- ### Billing Module
     Calculate users overall usage and fees.
- ### Invoice Module
     Generate a bill for every user containing his consumption for a month and how much he should pay.
- ### Website
    #### Admin
    - Create Service Package
    - Create Rateplan
    - Add New Users
    - Add New Contract (MSISDN) for User
    - Generate Invoice per User
    - Check users Invoice
    
![image](https://user-images.githubusercontent.com/52509314/168492261-dd90f643-16c0-4494-9dba-1ea915682f2b.png)
![image](https://user-images.githubusercontent.com/52509314/168492267-9d11b7b3-0851-480c-b051-c539ece98b19.png)
![image](https://user-images.githubusercontent.com/52509314/168492276-4658bd83-6eff-4bbd-9306-44ab8da71482.png)
![image](https://user-images.githubusercontent.com/52509314/168492282-701f6cb4-6e6b-4708-ae24-345cbd684f98.png)



## Contributors
#### [Ahmed Atef Mohammed](https://github.com/Ahmed-Atef98) 
#### [Ahmed Medhat Mohamed](https://github.com/aMedhatR)
#### [Asmaa Mohamed Ali](https://github.com/AsmaaMohamedAli)
#### [Mustafa Raed Hammad](https://github.com/Mustafa-Hammad)
#### [Nora Alaa-Eldin Salama](https://github.com/nora-alaa)


