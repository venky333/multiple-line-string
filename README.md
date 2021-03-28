# multiple-line-string
processor that takes multiple line string and outputs customized report

# steps to run the application
1) Clone this repository (master branch).
2) If you are using an IDE (IntelliJ), you can run main method and see output logged in console
(or)
3) If you are using terminal, navigate into the workspace and then run `mvn spring-boot:run`
(or)
4) If you are using macbook, then navigate to this workspace via terminal and run `make run`

#output
* Output will be printed in console.
Example: If you clone and run the application as it is without changing input then the output will be the below one.

##################### The number of unique customerId for each contractId ##########################
 
 Number of unique customer ids for contract id 2345 are 3
 Number of unique customer ids for contract id 2346 are 2
 
 ##################### The number of unique customerId for each geozone ##########################
 
 Number of unique customer ids for geo zone eu_west are 2
 Number of unique customer ids for geo zone us_west are 2
 Number of unique customer ids for geo zone us_east are 1
 
 ##################### The average buildduration for each geozone ##########################
 
 Average build duration of geo zone eu_west is 4222.0
 Average build duration of geo zone us_west is 2216.0
 Average build duration of geo zone us_east is 3445.0
 
 ##################### The list of unique customerId for each geozone ##########################
 
 List of unique customer id for geo zone eu_west are:
   * customer id: 3244132
   * customer id: 3244332
 List of unique customer id for geo zone us_west are:
   * customer id: 1223456
   * customer id: 1233456
 List of unique customer id for geo zone us_east are:
   * customer id: 2343225
