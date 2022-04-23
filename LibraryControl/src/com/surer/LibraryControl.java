package com.surer;
/**
 * <h1>LIBRARY MANAGEMENT SYSTEM</h1>
 *
 * this final project is being used by a librarian.
 * However, if the student wishes, he/she can perform
 * certain operations in the presence of the librarian.
 * One is to view the book he has given and peruse the
 * list of books. This event is happening in UserMenu.java.
 * In AdminMenu.java, however, data can be processed one by one.
 * He enters the book given by the student and is automatically
 * written to the database we have created. We tried this program
 * on one of our computers. Certain tools were used while writing
 * this program:
 * ```
 * Microsoft SQL Server Management Studio 18
 * mssql-jdbc-9.4.1.jre11.jar
 * javax.swing.*;
 * java.sql.*;
 * ```
 *
 * If you want to run this file, you need to import the above
 * libraries and download an application, a jar file. In addition,
 * the jar file for the connection must be in the path where the
 * project is located. If it gives a Driver error, there is an
 * application that comes with MSSQL as soon as you download it:
 * SQL Server Configuration Manager. Open this application, first
 * click on SQL Serves Services and make sure SQL Server Browser is
 * in Running state. Then click SQL Server Network Configuration
 * and Protocols for SQLEXPRESS. TCP/IP partition needs to be
 * Enabled. Finally, open the Services section of your computer
 * and restart SQL. And then you're done. There is a connection
 * link at the top of each file. You should set this up against
 * MSSQL on your own computer. Then don't forget to add a username
 * and password when logging in.
 * In addition to using the GUI, queries are also created to process the
 * data.
 *


 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

/**
 * @author Jiyan Surer
 * @version 13.2
 *
 */

public class LibraryControl extends UserMenu {

    //CONNECTION LINK
    public static Connection connect() {
        Connection connection = null;

        try {
            String url = "jdbc:sqlserver://CERVIDAESJ\\SQLEXPRESS;databaseName=library;";
            String userName = "cervi";
            String password = "Cervidaesj21.";
            connection = DriverManager.getConnection(url , userName , password);
            System.out.println("Connected!.");
        } catch (SQLException e) {
            System.out.println("Ops, there is error(s): ");
            e.printStackTrace();
        }
        return connect();
    }

    public static void main(String[] args) {

            //CREATING FRAME WITH THEIR LABEL AND SET THEIR BOUNDS
            JFrame f = new JFrame("Login");
            JLabel l1;
            l1 = new JLabel("Student ID");
            l1.setBounds(30,15,100,30);

            JTextField F_user = new JTextField(); //creating text field for id
            F_user.setBounds(110,50,200,30);

            JButton login_but = new JButton("Login");
            login_but.setBounds(130,90,80,25);
            login_but.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //int Student_ID = Integer.parseInt(F_user.getText());

                    String Student_ID = (F_user.getText());
                    if (Student_ID== "")
                    //if(Student_ID == Integer.parseInt("")) //If ID is null
                    {
                        JOptionPane.showMessageDialog(null,"Please enter the ID of student.");
                    }
                    else {
                        Connection conn = connect();
                        try {
                            Statement stmt = conn.createStatement();
                            stmt.executeUpdate("USE library "); //USING THE LIBRARY DATABASE (NAME OF DATABASE IS LIBRARY)
                            String st = ("SELECT * FROM Student WHERE StudentID = '" + Student_ID);
                            ResultSet rs = stmt.executeQuery(st);
                            if (!rs.next()){
                                System.out.println("NOT FOUND THIS USER.");
                                JOptionPane.showMessageDialog(null, "Wrong ID!");
                            }
                            else {
                                f.dispose();
                                rs.beforeFirst();
                                while (rs.next()){
                                    String admin = rs.getString("ADMIN"); //user is admin
                                    String ID = rs.getString("ID"); //get user ID (specific)

                                    if (admin.equals("1")){
                                        admin_menu();
                                    }
                                    else {
                                        user_menu(ID);
                                    }

                                }
                            }
                        }
                        catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                }
            });

        f.add(login_but);
        f.add(F_user);
        f.add(l1);

        f.setSize(40,180);
        f.setLayout(null);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        }


    }



