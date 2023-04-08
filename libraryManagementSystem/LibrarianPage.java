package libraryManagementSystem;
import static libraryManagementSystem.DbConnection.*;
import java.sql.*;
import java.util.*;

public class LibrarianPage {
    public static void lib(int id) throws SQLException{
        Scanner scanner=new Scanner(System.in);
        Connection con= DriverManager.getConnection(url,userName,password);
        Statement st=con.createStatement();


        syntax();
        int input= scanner.nextInt();
        while(input == 1 || input == 2 || input == 3 || input == 4 || input == 5){
            switch(input){
                case 1:AddBooks();
                    System.out.println();
                    syntax();
                    break;
                case 2:ViewBooks();
                    System.out.println();
                    syntax();
                    break;
                case 3:IssueBooks(id);
                    System.out.println();
                    syntax();
                    break;
                case 4:View_Issued_Books();
                    System.out.println();
                    syntax();
                    break;
                default:
                    System.out.println();
                    break;

            }
        }
    }

    public static void AddBooks() throws SQLException{
        Connection con=DriverManager.getConnection(url,userName,password);

        HashSet<Integer> Books_ID=new HashSet<>();
        String query1="select Ref_No from books;";
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query1);
        while(rs.next()){
            Books_ID.add(rs.getInt(1));
        }

        Scanner scanner =new Scanner(System.in);
        String query2="insert into books(?,?,?);";

        PreparedStatement ptr=con.prepareStatement(query2);
        System.out.println("Enter the Ref_No:");
        int input=scanner.nextInt();
        if(!Books_ID.contains(input)){
            ptr.setInt(1,input);
            System.out.println("Enter the Book_name:");
            ptr.setString(2, scanner.next());
            System.out.println("Enter the Authour Name:");
            ptr.setString(3,scanner.next());
            System.out.println("The Book Successfully added");
            ptr.executeUpdate();
            con.close();
        }else{
            System.out.println("You have entered the Wrong Ref_No");
        }

     Books_ID.clear();
    }
    public static void ViewBooks() throws SQLException{
        String query="select * from books;";
        Connection con=DriverManager.getConnection(url,userName,password);
        Statement st=con.createStatement();
        ResultSet rs= st.executeQuery(query);

        while(rs.next()){
            System.out.println("Reference No      ||       Book Name      ||       Authour");
            System.out.println(rs.getInt(1)+"         "+rs.getString(2)+"  "+rs.getString(3));
        }
    }

    public static void IssueBooks(int id) throws SQLException{

        Connection con=DriverManager.getConnection(url,userName,password);

        Scanner scanner=new Scanner(System.in);
        int BookIssueID= scanner.nextInt();
        System.out.println("Please enter the Ref_No:");
        int Ref_No=scanner.nextInt();
        String query1="select * from books where Ref_No="+Ref_No+";";
        Statement st= con.createStatement();
        String query2="update books set Librarian_ID="+id+",Book_Issue_ID="+BookIssueID+" where Ref_No"+Ref_No+";";
        ResultSet rs1=st.executeQuery(query1);
        st.executeUpdate(query2);

        String query3 = "insert into partron values(?,?,?,?);";

        PreparedStatement pst = con.prepareStatement(query3);
        int Name=BookIssueID;
        System.out.println("Enter Partron Name");
        String Partron_Name= scanner.next();
        System.out.println("Enter Partron_Contact");
        String contact= scanner.next();
        System.out.println("Enter Partron Mail_ID");
        String Mail_ID= scanner.next();
//        System.out.println("The librarian added successfully");
        pst.executeUpdate();
        con.close();
    }

    public static void View_Issued_Books() throws SQLException{
        String query="select * from books where Book_Issue_ID is not null";

        Connection con=DriverManager.getConnection(url,userName,password);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);

        while(rs.next()){
            System.out.println("Book Name is"+rs.getString(2));
            System.out.println("Authour is"+rs.getString(3));
        }
    }

//    public static void return_Books() throws SQLException{
//        String query=""
//    }

    private static void syntax(){
        System.out.println("Enter 1 ADD book");
        System.out.println("Enter 2 VIEW books");
        System.out.println("Enter 3 To ISSUE book");
        System.out.println("Enter 4 VIEW ISSUED books");
        System.out.println("Enter 5 Collect the issued books");
        System.out.println("Enter Any Key logout");
    }
}
