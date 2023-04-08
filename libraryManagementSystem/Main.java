package libraryManagementSystem;
import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Click 1 to Login as a Admin");
        System.out.println("Click 2 to Login as a Librarian");
        Scanner scanner=new Scanner(System.in);
        Login login=new Login();
        switch(scanner.nextInt()){
            case 1:
                login.AdminLogin();
                break;
            case 2:
                login.LibrarianLogin();
                break;
            default:
                System.out.println("Please enter the valid INPUT");
        }

    }
}
