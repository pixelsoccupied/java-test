Changes and Updates - 
------------
* IMPORTANT: delete and search needs to have Email and NOT NAME anymore.
* Access modifiers changes to private where applicable
* UserDoa conforms to Singleton architecture  
* UserDoa switched to ConcurrentHashmap from ArrayList - unique email is the now the Key and User Object as the value. Average complexity redueces to O(1) from O(N). It is also thread safe allowing multiple reads
* Added checks for invalid inputs including 'at least one role' requirement
* Added new Tests (Integration and Unit)
* Added support for maven debug (web.xml)
* Added support to view hashmap as a json (web.xml)
* Optimized imports
* And other small updates...
* Also, there's a .py file to quickly fill up the DB with some dummy entires. 


