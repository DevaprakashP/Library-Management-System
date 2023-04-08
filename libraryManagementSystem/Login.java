package libraryManagementSystem;
import java.sql.*;
import java.util.*;
import static libraryManagementSystem.DbConnection.*;

public class Login {
    HashMap<Integer,String> map=new HashMap<>();
    public static void AdminLogin() throws SQLException {
        String AdminUser="Deva";
        String AdminPass="password";
        Scanner scanner =new Scanner(System.in);
        System.out.println("Enter the Admin UserName");
       if(scanner.next().equals(AdminUser)){
           System.out.println("Enter your Admin Password");
           if(scanner.next().equals(AdminPass)){
              AdminPage.adm();
           }else{
               System.out.println("Your Password is INCORRECT");
           }
       }else{
           System.out.println("UserName does not exist");
       }
    }
    public void LibrarianLogin() throws SQLException{
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the Lib ID");
        int id=scanner.nextInt();

        String query = "select * from librarian";

        Connection con = DriverManager.getConnection(url,userName,password);
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);

        while(rs.next()){
            map.put(rs.getInt(1),rs.getString(2));
        }

        if(map.containsKey(id)){
            System.out.println("Enter the password");
            String password=scanner.next();
            if(map.get(id).equals(password)){
              LibrarianPage.lib(id);
            }else{
                System.out.println("You entered the wrong password");
            }
        }else{
            System.out.println("Sorry! Librarian does not exist");
        }
    }
}
