package libraryManagementSystem;
import static libraryManagementSystem.DbConnection.*;

import java.sql.*;
import java.util.*;

public class AdminPage {

    public static void adm() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        syntax();
        int input=scanner.nextInt();
        while (input == 1 || input == 2 || input == 3) {
            switch (input) {
                case 1:
                    AddLibrarian();
                    System.out.println();
                    syntax();
                    input= scanner.nextInt();
                    break;
                case 2:
                    ViewLibrarian();
                    System.out.println();
                    syntax();
                    input= scanner.nextInt();
                    break;
                case 3:
                    DeleteLibrarian();
                    System.out.println();
                    syntax();
                    input= scanner.nextInt();
                    break;
                default:
                    break;
            }
        }
        }
        static void AddLibrarian () throws SQLException {
            Scanner scanner = new Scanner(System.in);
            String query = "insert into librarian values(?,?,?,?,?);";

            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(query);
            System.out.println("Enter the UserName");
            pst.setInt(1, scanner.nextInt());
            System.out.println("Enter the Password");
            pst.setString(2, scanner.next());
            System.out.println("Enter the email");
            pst.setString(3,scanner.next());
            System.out.println("Enter the Contact");
            pst.setString(4,scanner.next());
            System.out.println("Enter the Librarian Name");
            pst.setString(5,scanner.next());
            System.out.println("The librarian added successfully");
            pst.executeUpdate();
            con.close();
        }

        static void ViewLibrarian () throws SQLException {
            Scanner scanner = new Scanner(System.in);
            String query = "select * from librarian";

            Connection con = DriverManager.getConnection(url, userName, password);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                System.out.print(rs.getInt(1) + " | ");
                System.out.print(rs.getString(2)+" | ");
                System.out.print(rs.getString(3) + " | ");
                System.out.println(rs.getString(4));
            }
            con.close();
        }
        static void DeleteLibrarian () throws SQLException {
            Scanner scanner = new Scanner(System.in);
            String query = "delete from librarian where UserName=" + scanner.nextInt();
            Connection con = DriverManager.getConnection(url, userName, password);
            Statement st = con.createStatement();
            st.executeUpdate(query);
        }
        static void syntax(){
            System.out.println("Enter 1 to Add the Librarian");
            System.out.println("Enter 2 to View the Librarian");
            System.out.println("Enter 3 to delete the Librarian");
            System.out.println("Enter AnyKey to Logout");
        }


}
