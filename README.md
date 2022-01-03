# Library-Control

 <h1>LIBRARY MANAGEMENT SYSTEM</h1>

   This final project is being used by a librarian.
  However, if the student wishes, he/she can perform certain operations in the presence of the librarian. One is to view the book he has given and peruse the
  list of books. This event is happening in UserMenu.java. In AdminMenu.java, however, data can be processed one by one. S/he enters the book given by the student and 
  is automatic.
  written to the database we have created. We tried this program on one of our computers. Certain tools were used while writing this program:
 
  ```
 * Microsoft SQL Server Management Studio 18
 * mssql-jdbc-9.4.1.jre11.jar
 * javax.swing.*;
 * java.sql.*;
  ```
 
  If you want to run this file, you need to import the above libraries and download an application, a jar file. In addition, the jar file for the connection must be in the
  path where the project is located. If it gives a Driver error, there is an application that comes with MSSQL as soon as you download it: SQL Server Configuration Manager. 
  Open this application, first click on SQL Serves Services and make sure SQL Server Browser is in Running state. Then click SQL Server Network Configuration and Protocols for
  SQLEXPRESS. TCP/IP partition needs to be Enabled. Finally, open the Services section of your computer and restart SQL. And then you're done. There is a connection link at the 
  top of each file. You should set this up against MSSQL on your own computer. Then don't forget to add a username and password when logging in. In addition to using the GUI, 
  queries are also created to process the data. 
