import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static java.lang.Class.forName;

public class JsonToJava {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/business", "root", "");
        System.out.println("Conexión exitosa");

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM CustomerInfo WHERE location= 'Argentina';");


        ArrayList<CustomerDetails> arrayCustomerDetails = new ArrayList<CustomerDetails>();

        while (rs.next()){
            CustomerDetails cd = new CustomerDetails();

            cd.setCourseName(rs.getString(1));
            cd.setPurchasedDate(rs.getString(2));
            cd.setAmount(rs.getInt(3));
            cd.setLocation(rs.getString(4));

            arrayCustomerDetails.add(cd);

            System.out.println("Course name: "+ cd.getCourseName());
            System.out.println("Purchased date: "+ cd.getPurchasedDate());
            System.out.println("Amount: "+ cd.getAmount());
            System.out.println("Location: "+ cd.getLocation());

        }
        for (int i = 0; i< arrayCustomerDetails.size(); i++){
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("D:\\Proyectos\\udemy\\sdet-example\\src\\main\\resources\\customerInfo_"+i+".json"), arrayCustomerDetails.get(i));
        }



        conn.close();
    }


}
