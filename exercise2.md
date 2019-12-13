# Multiple (and dynamic) datasources
> Use spring boot

Given a database with a configuration table storing information about data sources by region and 
multiple databases (each by region) with a table "stores" create an application that has an endpoint  
to get the list of stores that exist in a specific region

The database for configuration contains a table "configurations" with a list of key-value pairs and 
the foreign key to a region table.  

The specific parameters in table configurations for region data sources will be:
- url
- driverName
- username
- password

The table stores contains:
- id
- name
- address
- phone number
