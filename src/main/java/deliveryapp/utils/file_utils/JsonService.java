package deliveryapp.utils.file_utils;

import java.util.List;

public interface JsonService {
    <T> List<T> parseJson(String filename, Class<T> entityClass);
}
