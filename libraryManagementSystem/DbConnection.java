package libraryManagementSystem;
import java.sql.*;

public class DbConnection {
      static final String url="jdbc:mysql://localhost:3306/library";
      static final String userName="root";
      static final String password="12345Qwe@";

//      public static void getConnection() throws SQLException{
//            String query="SELECT Username FROM library.admin;";
//          Connection con = DriverManager.getConnection(url,userName,password);
//          Statement st=con.createStatement();
//          ResultSet rs= st.executeQuery(query);
//
//          rs.next();

//      }
}
