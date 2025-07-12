package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonReader {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Reads a JSON file and maps it to the specified class type.
     *
     * @param filePath the relative path to the JSON file
     * @param clazz    the class to map the JSON to
     * @param <T>      the type of the class
     * @return an instance of the class populated with JSON data
     */
    public static <T> T readJson(String filePath, Class<T> clazz) {
        try {
            // Assuming filePath is relative to the project root or resources folder
            File file = new File(filePath);
            return mapper.readValue(file, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }
}
