import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ExtractJSONtoObject {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        CustomerDetailsAppium cda = objectMapper.readValue(new File("D:\\Proyectos\\udemy\\sdet-example\\src\\main\\resources\\customerFinalAppium.json"), CustomerDetailsAppium.class);
        System.out.println(cda.getCourseName());

    }
}
