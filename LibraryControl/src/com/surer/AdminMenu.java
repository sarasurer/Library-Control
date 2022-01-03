package com.surer;

/**
 * <h2>ADMINMENU.JAVA</h2>
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class AdminMenu {

    //CONNECTION
    //connection between mssql and java, username and password are mandatory as I know//
    public static Connection connect() {
        String url = "jdbc:sqlserver://CERVIDAESJ\\SQLEXPRESS;databaseName=library;";
        String userName = "cervi";
        String password = "Cervidaesj21.";

        try {
            Connection connection = DriverManager.getConnection(url , userName , password); //getting the variables if there is no error
            System.out.println("Connected!.");
        } catch (SQLException e) {
            System.out.println("Ops, there is error(s): "); //if there is an error
            e.printStackTrace();
        }
        return connect();
    }

    public static class ex {
        public static int days = 0; //initialized days
    }

    public static void admin_menu(){
        JFrame f = new JFrame("Admin Functions"); //title of frame

        JButton view_but = new JButton("View Books"); //name of button
        view_but.setBounds(20,20,120,25); //bounds
        //when click on this button
        view_but.addActionListener(e -> {
            JFrame f2 = new JFrame("Books Available"); //name of frame

            Connection connection = connect();
            String sql = "select * from books"; //select all columns and rows in books database
            try{
                Statement stmt = connection.createStatement(); //for our query
                stmt.executeUpdate("USE library"); //using this database
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                JTable book_list = new JTable(); //table for the books

                f2.add(book_list); //adding to the frame
                f2.setSize(800,400); //size
                f2.setVisible(true); //visible
                f2.setLocationRelativeTo(null);
                connection.close(); //closing the connection
            }
            catch (SQLException e1){
                JOptionPane.showMessageDialog(null, e1); //if there is errors option pane is open
            }
        });

        JButton users_but = new JButton("View Students"); //name of button
        users_but.setBounds(150,20,120,25); //bounds
        users_but.addActionListener(e -> {
            JFrame f1 = new JFrame("Student List"); //title of frame
            Connection connection = connect();
            String sql = "SELECT * FROM student"; //select all variables in student db
            try{
                Statement stmt = connection.createStatement(); //for connection to db
                stmt.executeUpdate("USE library");
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                JTable students = new JTable(); //table of students
                JScrollPane scrollPane = new JScrollPane(students);

                //same things
                f1.add(scrollPane);
                f1.setSize(800,400);
                f1.setVisible(true);
                f1.setLocationRelativeTo(null);
            }
            catch (SQLException e1){
                JOptionPane.showMessageDialog(null, e1);
            }
        });

        //name of button to issue book
        JButton issue_book = new JButton("Issue Book");
        issue_book.setBounds(450,20,120,25); //bounds

        issue_book.addActionListener(e -> {
            JFrame g = new JFrame("Enter Details"); //title of frame

            //labels name and their text field
            JLabel lb1, lb2,lb3;
            lb1 = new JLabel("BOOK ID(BID)");
            lb1.setBounds(30,15,100,30);

            lb2 = new JLabel("Student ID(SID)");
            lb2.setBounds(60,53,100,30);

            lb3 = new JLabel("Issued Date(DD-MM-YYYY)");
            lb3.setBounds(30,127,150,30);

            JTextField F_bid = new JTextField();
            F_bid.setBounds(110, 15, 200, 30);

            JTextField F_sid=new JTextField();
            F_sid.setBounds(110, 53, 200, 30);

            JTextField F_issue=new JTextField();
            F_issue.setBounds(180, 130, 130, 30);
            // until there


            JButton create_but = new JButton("Submit"); //name of button
            create_but.setBounds(130,170,80,85);
            create_but.addActionListener(e12 -> {
                String sid = F_sid.getText();
                String bid = F_bid.getText();
                String issued_date = F_issue.getText();

                Connection connection = connect(); //connection opening

                //our query
                //when librarian enter the variables to our textfield, it is written to our database at the same time
                try {
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate("USE library");
                    stmt.executeUpdate("INSTERT INTO IssueDb (SID, BID, ReturnDate) VAlUES ('\"+sid+\"','\"+bid+\"','\"+issued_date+\") ");
                    JOptionPane.showMessageDialog(null, "Book Issued!"); //when it is finish book issued warning is opening
                    g.dispose();
                    connection.close();
                }
                catch(SQLException e1){
                    JOptionPane.showMessageDialog(null, e1);

                }
            });

            //adding whole variables that we create to the frame and fitted the size, visible is true

            g.add(lb3);
            g.add(create_but);
            g.add(lb1);
            g.add(lb2);
            g.add(F_sid);
            g.add(F_bid);
            g.add(F_issue);
            g.setSize(350,250);
            g.setLayout(null); //using no layout managers
            g.setVisible(true);
            g.setLocationRelativeTo(null);
        });

        //name of button to return book
        JButton return_book = new JButton("Return Book");
        return_book.setBounds(280,60,160,25);
        return_book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame gf = new JFrame("Enter Details"); //title of frame

                //Wer used just Issue ID and Return Date
                JLabel v1, v3;
                v1 = new JLabel("Issue ID (IID)");
                v1.setBounds(30, 50, 150, 30);

                v3 = new JLabel("Return Date (DD-MM-YYYY)");
                v3.setBounds(30,50,150,30);

                JTextField F_iid = new JTextField();
                F_iid.setBounds(110,15,200,30);

                JTextField F_return = new JTextField();
                F_return.setBounds(180,50,130,30);

                //name of button which is return
                JButton create_but = new JButton("Return");
                create_but.setBounds(130,170,80,25);
                create_but.addActionListener(e13 -> {
                    String iid = F_iid.getText();
                    String return_date = F_return.getText();

                    Connection connection = connect(); //connection opening

                    try{
                        Statement stmt = connection.createStatement();
                        stmt.executeUpdate("USE library");

                        String date1 = null;
                        String dare2 = return_date; //date 2 is equal to return date

                        //query says that in IssueDb we choose ReturnDate and Issue ID (IID) is equal what we enter
                        ResultSet rs = stmt.executeQuery("SELECT ReturnDate FROM IssueDb WHERE IID = " + iid);
                        while (rs.next()) {
                            dare2 = rs.getString(4); //at fourth column
                        }
                        try{
                            Date date_1 = (Date) new SimpleDateFormat("dd-MM-yyyy").parse(date1); //format of dates
                            Date date_2 = (Date) new SimpleDateFormat("dd-MM-yyyy").parse(dare2);
                            long diff = date_2.getTime() - date_1.getTime();
                            ex.days  = (int)(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));

                        }
                        catch (ParseException e1){
                            e1.printStackTrace();
                        }

                        //updating the return date
                        stmt.executeUpdate("UPDATE IssueDb SET ReturnDate='\"+return_date+\"' WHERE IID=\"+iid");
                        gf.dispose();
                        connection.close(); //closing the connection
                    }
                    catch (SQLException e1){
                        JOptionPane.showMessageDialog(null, e1);
                    }
                });

                //adding tools to frame that we created
                gf.add(v3);
                gf.add(create_but);
                gf.add(v1);
                gf.add(F_iid);
                gf.add(F_return);

                //size, layout, visibility,
                gf.setSize(350,250);
                gf.setLayout(null);//using no layout managers
                gf.setVisible(true);
                gf.setLocationRelativeTo(null);

            }
        });

        //adding tools to main frame that we created
        f.add(return_book);
        f.add(issue_book);
        f.add(users_but);
        f.add(view_but);

        f.setSize(600,200);
        f.setLayout(null);
        f.setVisible(true);
        f.setLocationRelativeTo(null);

    }
}
