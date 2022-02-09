import java.sql.*;

import static java.lang.Class.forName;

public class JsonToJava {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/business", "root", "");
        System.out.println("Conexión exitosa");

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM CustomerInfo WHERE location= 'Argentina' AND purchasedDate=CURDATE() LIMIT 1;");

        while (rs.next()){
            String column1 = rs.getString(1);
            String column2 = rs.getString(2);
            int column3 = rs.getInt(3);
            String column4 = rs.getString(4);

            System.out.println(column1);
            System.out.println(column2);
            System.out.println(column3);
            System.out.println(column4);
        }
        conn.close();
    }

}
