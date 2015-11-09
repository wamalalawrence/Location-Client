package com.goeuro.client.rest.util;


import com.goeuro.client.rest.domain.Location;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/**
 * Created by wamalalawrence on 15/11/04.
 * @apiNote this is a utilities class with some handy static functions
 */
public class Utils {

    public static final String FILE_HEADER = "_id, name, type, latitude, longitude";
    public static final String LINE_SEPARATOR = "\n";
    public static final String COMMA_DELIMITER = ",";
    public static final String CSV_FILE = System.getProperty("user.home") + File.separator + "data.csv";

    public static final String API_CONTEXT = "/api.goeuro.com/api/v2/position/suggest/en/";

    /**
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String readFile(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder out = new StringBuilder();
        String line;
        while( (line = bufferedReader.readLine()) != null ) {
            out.append(line);
        }
        bufferedReader.close();
        return out.toString();
    }

    /**
     *
     * @param inputStream
     * @return Collection<Location>
     * @throws IOException
     */
    public static Collection<Location> getJsonObjects(InputStream inputStream) throws IOException
    {
        String json = readFile(inputStream);
        Type collectionType = new TypeToken<Collection<Location>>(){}.getType();
        Collection<Location> collection = new Gson().fromJson(json, collectionType);
        return collection;
    }

    /**
     *
     * @param jsonStr
     * @return
     * @throws IOException
     */
    public static Collection<Location> getJsonObjects(String jsonStr) throws IOException
    {
        Type collectionType = new TypeToken<Collection<Location>>(){}.getType();
        Collection<Location> collection = new Gson().fromJson(jsonStr, collectionType);
        return collection;
    }

    /**
     *
     * @param jsonData
     * @throws IOException
     */
    public static void generateCsvFile(String jsonData) throws IOException {
        List<Location> locations = (List<Location>) Utils.getJsonObjects(jsonData);
        if(locations.size() > 0)
        {
            FileWriter fileWriter = null;

            try {
                fileWriter = new FileWriter(CSV_FILE);
                fileWriter.append(FILE_HEADER);
                fileWriter.append(LINE_SEPARATOR);

                //Write Location object list to the CSV file
                for (Location location : locations) {
                    fileWriter.append(String.valueOf(location.get_id()));
                    fileWriter.append(Utils.COMMA_DELIMITER);
                    fileWriter.append(location.getName());
                    fileWriter.append(Utils.COMMA_DELIMITER);
                    fileWriter.append(location.getType());
                    fileWriter.append(Utils.COMMA_DELIMITER);
                    fileWriter.append(String.valueOf(location.getGeo_position().getLatitude()));
                    fileWriter.append(Utils.COMMA_DELIMITER);
                    fileWriter.append(String.valueOf(location.getGeo_position().getLongitude()));
                    fileWriter.append(Utils.LINE_SEPARATOR);
                }
                System.out.println("CSV file created successfully to: " + CSV_FILE);
            } catch (IOException ioe)
            {
                throw new IOException("Error while creating cvs file");
            } finally
            {
                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e)
                {
                    System.out.println("Error while flushing/closing fileWriter !!!");
                    e.printStackTrace();
                }
            }
        } else
        {
            System.out.println(">>> No data to write");
        }
    }

}
