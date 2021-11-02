import javax.xml.transform.Result;
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        try
        {
            final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
            final String DB_URL = "jdbc:mysql://localhost:3306/Assignment";
            final String USER = "root";
            final String PASS = "Harsh@123";

            Class.forName(JDBC_DRIVER);
            Connection con=DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("1.Find the total Amount to be paid at the time of checkout for a particular cart. As shown in above table. e.g. Query should return a single integer as total Amount.");
            Statement stmt1=con.createStatement();
            ResultSet r1=stmt1.executeQuery("select sum(Amount) as total from Cart");
            while(r1.next())
                System.out.println("Total = "+r1.getString(1));

            System.out.println("\n\n");
            System.out.println("2. Find the Product name which as sold the most.");
            Statement stmt2=con.createStatement();
            ResultSet r2=stmt2.executeQuery("select name from Product where pid=(select pid from Cart where qty=(select Max(qty) from Cart));");
            while(r2.next())
                System.out.println("Name with max qty = "+r2.getString(1));


            System.out.println("\n\n");
            System.out.println("3.Find all the Products which are not yet sold.");
            Statement stmt3=con.createStatement();
            ResultSet r3=stmt3.executeQuery("select name from Product where pid NOT IN(Select pid from Cart)");
            while(r3.next())
                System.out.println(r3.getString(1));

            System.out.println("\n\n");
            con.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}