Given a [json file](exercise1-files/input.json), an [xml](exercise1-files/input.xml) file, 
a [csv file](exercise1-files/input.csv) and a [yml file](exercise1-files/input.yml) all of them with fields:
- account
- amount
- reference
- card name
- card type

It is required to know which transactions exists and which not in [other file](exercise1-files/target.out) 
with custom format (same fields with a pipe as separator). The response must be written to an HTML report

SOLUTION:

1. Load all input files to a single table
2. Load target.out file to a different table
3. Using an OUTER JOIN get the differences
4. Write the output to an html file 

NOTE: (files serves only as description of the problem, they do not have a good set of data for tests)
