# multiple-line-string
processor that takes multiple line string and outputs customized report

# steps to run the application
1) Clone this repository (master branch).
2) If you are using an IDE (IntelliJ), you can run main method and see output logged in console
(or)
3) If you are using terminal, navigate into the workspace and then run `mvn spring-boot:run`
(or)
4) If you are using macbook, then navigate to this workspace via terminal and run `make run`

# output
* Output will be printed in console.
Example: If you clone and run the application as it is without changing input then the output will be the below one.

|Contract id|# unique customers|
|---|---|
|2345|		3|
|2346|		2|

|Geo Zone|	# unique customers|
|---|---|
|eu_west|	2|
|us_west|	2|
|us_east|	1|

|Geo Zone|	Average build duration|
|---|---|
|eu_west|	4222.0|
|us_west|	2216.0|
|us_east|	3445.0|

|Geo Zone|	Unique customer ids|
|---|---|
|eu_west|	[3244132, 3244332]|
|us_west|	[1223456, 1233456]|
|us_east|	[2343225]|
