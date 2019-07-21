package com.pack.saviynt.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * The Class TestDataReader.
 */
public class TestDataReader {

    /** The logger. */
    static Logger logger = Logger.getLogger(TestDataReader.class.getName());

    /** The data. */
    public static HashMap<String, Object> data = null;

    static {
        /** The br. */
        BufferedReader br = null;
        if (data == null) {
            data = new HashMap<>();
            try {
                String csvSplitBy = AppData.getProperty("csvSplitBy");
                String[] files = AppData.getProperty("testData").split(";");

                for (String file : files) {
                    logger.info("Fetching the data from " + file + " ...");
                    String line = "";
                    br = new BufferedReader(
                            new FileReader(System.getProperty("user.dir") + "/src/test/resources/" + file));
                    String[] colHeader = br.readLine().split(csvSplitBy);

                    while ((line = br.readLine()) != null) {
                        String[] rows = line.split(csvSplitBy);
                        for (int i = 1; i < rows.length; i++) {
                            data.put(rows[0].trim().toUpperCase().replaceAll(" ", "_") + "_"
                                    + colHeader[1].trim().toUpperCase().replaceAll(" ", "_"), rows[i]);
                            System.out.println(rows[0].trim().toUpperCase().replaceAll(" ", "_") + "_"
                                    + colHeader[1].trim().toUpperCase().replaceAll(" ", "_") + " : " + rows[i]);
                        }
                    }
                }
            } catch (Exception e) {
                logger.warning(e.getMessage());
            }
        }
    }

    /**
     * Gets the property.
     * @param property the property
     * @return the property
     */
    public static String getProperty(String property) {
        try {
            return data.get(property.toUpperCase().trim().replaceAll(" ", "_").toUpperCase()).toString();
        } catch (Exception e) {
            return "";
        }
    }
}
