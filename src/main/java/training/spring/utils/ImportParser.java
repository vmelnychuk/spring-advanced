package training.spring.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public class ImportParser {
    public static <T> T parseJson(MultipartFile file, Class<?> theClass) {
        try {
            byte[] bytes = file.getBytes();
            String jsonString = new String(bytes, "UTF-8");
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(jsonString, mapper.getTypeFactory()
                    .constructCollectionType(List.class, Class.forName(theClass.getName())));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("import problem", e);
        }
    }
}
