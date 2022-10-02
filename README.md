# Credit Card Reward Points System

This rewards calculation system calculates
the total maximum rewards points earned for the month and the maximum reward points applied for each transaction based on a customer's credit card purchases. 

## Prerequisities
- Java 11
- Maven 3

## Usage
- Input: ```JSON Format String```
- Output: ```String```



```java
// input
String transactions =
                "{\"transactions\" : [" +
                "{\"id\": \"T01\",\"date\": \"2021-05-01\", \"merchant_code\" : \"sportcheck\", \"amount_cents\": 21000}," +
                "{\"id\": \"T02\",\"date\": \"2021-05-01\", \"merchant_code\" : \"sportcheck\", \"amount_cents\": 8700}," +
                "{\"id\": \"T03\",\"date\": \"2021-05-03\", \"merchant_code\" : \"tim_hortons\", \"amount_cents\": 323}," +
                "{\"id\": \"T04\",\"date\": \"2021-05-04\", \"merchant_code\" : \"tim_hortons\", \"amount_cents\": 1267}," +
                "{\"id\": \"T05\",\"date\": \"2021-05-05\", \"merchant_code\" : \"tim_hortons\", \"amount_cents\": 2116}," +
                "{\"id\": \"T06\",\"date\": \"2021-05-06\", \"merchant_code\" : \"tim_hortons\", \"amount_cents\": 2211}," +
                "{\"id\": \"T07\",\"date\": \"2021-05-07\", \"merchant_code\" : \"subway\", \"amount_cents\": 1853}," +
                "{\"id\": \"T08\",\"date\": \"2021-05-08\", \"merchant_code\" : \"subway\", \"amount_cents\": 2153}," +
                "{\"id\": \"T09\",\"date\": \"2021-05-09\", \"merchant_code\" : \"sportcheck\", \"amount_cents\": 7326}," +
                "{\"id\": \"T10\",\"date\": \"2021-05-10\", \"merchant_code\" : \"tim_hortons\", \"amount_cents\": 1321}" +
                        "]}";

// output
String output = getReward(transactions);
```
