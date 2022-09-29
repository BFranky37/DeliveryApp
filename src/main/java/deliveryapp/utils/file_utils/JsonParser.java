package deliveryapp.utils.file_utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonParser implements JsonService{
    private static final Logger LOGGER = Logger.getLogger(JsonParser.class.getName());

    @Override
    public <T> List<T> parseJson(String filename, Class<T> entityClass) {
        ObjectMapper om = new ObjectMapper();
        List<T> objects = null;
        try {
            JavaType jt = om.getTypeFactory().constructCollectionType(List.class, entityClass);
            objects = om.readValue(new File(filename), jt);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return objects;
    }

    @Override
    public <T> void writeToFileJson(String outFilename, List<Class<T>> objects) {
        ObjectMapper om = new ObjectMapper();
        try {
            om.writeValue(new File(outFilename), objects);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
