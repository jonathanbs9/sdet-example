import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class OneSingleJson {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/business", "root", "");
        System.out.println("Conexión exitosa");

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM CustomerInfo WHERE location= 'Argentina';");

        JSONArray jsonArray = new JSONArray();

        ArrayList<CustomerDetails> arrayCustomerDetails = new ArrayList<CustomerDetails>();

        while (rs.next()){
            CustomerDetails cd = new CustomerDetails();

            cd.setCourseName(rs.getString(1));
            cd.setPurchasedDate(rs.getString(2));
            cd.setAmount(rs.getInt(3));
            cd.setLocation(rs.getString(4));

            arrayCustomerDetails.add(cd);

            //System.out.println("Course name: "+ cd.getCourseName());
            //System.out.println("Purchased date: "+ cd.getPurchasedDate());
            //System.out.println("Amount: "+ cd.getAmount());
            //System.out.println("Location: "+ cd.getLocation());

        }
        // Java Object -> json object -> json string
        for (int i = 0; i< arrayCustomerDetails.size(); i++) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("D:\\Proyectos\\udemy\\sdet-example\\src\\main\\resources\\customerInfo_" + i + ".json"), arrayCustomerDetails.get(i));

            // CREATE JSON String from JsonObject
            // Gson = Create json string from Java object
            Gson gson = new Gson();
            String jsonString = gson.toJson(arrayCustomerDetails.get(i));
            jsonArray.add(jsonString);

        }

        // JSON SIMPLE
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", jsonArray);
        //System.out.println("jsonObject: \n "+jsonObject);
        //System.out.println("jsonObject.ToJSONString :\n "+jsonObject.toJSONString());
        String unescapeString = StringEscapeUtils.unescapeJava(jsonObject.toJSONString());
        System.out.println("jsonObject.ToJSONString (unescape):\n "+unescapeString);
        String newString = unescapeString.replace("\"{", "{");
        String finalString = newString.replace("}\"", "}");
        System.out.println("newString: \n" + finalString);

        try (FileWriter file = new FileWriter("D:\\Proyectos\\udemy\\sdet-example\\src\\main\\resources\\customerFinal_.json")) {
            file.write(finalString);
        }
        conn.close();
    }


}
