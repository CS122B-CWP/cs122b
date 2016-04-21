Supported Platform:
Windows\Linux\MacOS

Required Environment:
JDK	1.7+
Maven	3.1+

Database Import:
mysql -u your_username -p (-h your_host_ip -P your_host_port) -D your_databse<  ../sql/createtable.sql
mysql -u your_username -p (-h your_host_ip -P your_host_port) -D your_databse<  ../sql/data.sql

Compile and Execution:
cd ../project1
mvn compile
mvn exec:java

Then enjoy it!
