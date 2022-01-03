package com.surer;
import javax.swing.*;
import java.sql.*;

/**
 * <h2>USERMENU.JAVA</h2>
 */

public class UserMenu extends AdminMenu {

    //CONNECTION
    public static Connection connect() {
        String url = "jdbc:sqlserver://CERVIDAESJ\\SQLEXPRESS;databaseName=library;";
        String userName = "cervi";
        String password = "Cervidaesj21.";

        try {
            Connection connection = DriverManager.getConnection(url , userName , password);
            System.out.println("Connected!.");
        } catch (SQLException e) {
            System.out.println("Ops, there is error(s): ");
            e.printStackTrace();
        }
        return connect();
    }
    //IN USER_MENU FUNCTION
    public static void user_menu(String ID){
        JFrame f = new JFrame("User Functions");
        JButton view_but = new JButton("View Books"); //BUTTON NAME
        view_but.setBounds(20,20,120,25);
        view_but.addActionListener(e -> { //WHEN CLICK ON THE VIEW_BUT
            JFrame f1 = new JFrame("Books Available"); //AVAILABLE BOOKS SHOWN

            //CONNECT TO DB
            Connection connection = connect();
            String sql = "SELECT * FROM books"; //SELECT ALL COLUMN AND ROWS IN BOOKS DB
            try{
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("USE library"); //USING LIBRARY DB
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);


                f1.setSize(800,400);
                f1.setVisible(true); //VISIBLE IS TRUE
                f1.setLocationRelativeTo(null);
            }
            catch (SQLException e1){
                JOptionPane.showMessageDialog(null, e1);

            }
        });

        JButton my_books = new JButton("My books"); //CLICK ON THE MY BOOKS BUTTON STUDENT SEE BOOKS
        my_books.setBounds(150,20,120,25);
        my_books.addActionListener(e -> {
            JFrame f12 = new JFrame("My Books");
            int ID_int = Integer.parseInt(ID);

            Connection connection = connect();
            String sql1 = "select IID from IssueDb where IID =" + ID_int; //IF THE IID AND ID IS EQUAL THEN SHOW ALL OF THEM IN ISSUEDB

            try {
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("USE library");
                stmt = connection.createStatement();

                ResultSet rs = stmt.executeQuery(sql1);
                JTable book_list = new JTable();
                JScrollPane scrollPane = new JScrollPane(book_list);


                f12.add(book_list);
                f12.add(scrollPane);

                f12.setSize(800,400);
                f12.setVisible(true);
                f12.setLocationRelativeTo(null);
            }
            catch (SQLException e1){
                JOptionPane.showMessageDialog(null,e1); //IF THERE IS ERROR(S) OPTION PANE IS OPENING WITH ITS PROBLEM
            }

        });

        f.add(my_books);
        f.add(view_but);
        f.setSize(300,100);
        f.setLayout(null);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
}
