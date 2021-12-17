package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class PersistableIO {
	// Needs to make sure folder exists but not enough time to implement
	// Solution would be to check if the file path exists, and if not
	// make folders until that file path does exist. 
    public static void save(Persistable persistable, String filePath) {
        if(filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Filepath cannot be null or empty");
        }

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write(persistable.serialize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Since what is being loaded is unknown, but is a file that will be extending persistable we use
    // T for a generic object.
    public static <T extends Persistable> T load(Class<T> clazz, String filePath) {
        if(filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Filepath cannot be null or empty");
        }

        
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
        	// Returns a no arg constructor of the class that is passed into it
            T persistable = clazz.getDeclaredConstructor().newInstance();

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(System.lineSeparator());
                stringBuilder.append(line);
            }

            persistable.applySerializedData(stringBuilder.toString());
            return persistable;
        } catch (IOException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}