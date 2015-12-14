package de.codecentric.elastic;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;

/**
 * Hello world!
 */
public class ImportDataGenerator {
    public static void main(String[] args) throws IOException, ParseException {

        String filenameIndizes = "/indizes.json";
        String filenameData = "/data.json";
        String filenameOutput = "importData.json";

        if (args != null && args.length == 2) {
            filenameIndizes = args[0];
            filenameData = args[1];
        }

        ImportDataGenerator app = new ImportDataGenerator();
        app.createElasticsearchDataFile(filenameIndizes, filenameData, filenameOutput);

    }

    private void createElasticsearchDataFile(String filenameIndizes, String filenameData, String filenameOutput) throws IOException, ParseException {

        try (
                BufferedReader brIndizes = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filenameIndizes)));
                BufferedReader brData = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filenameData)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filenameOutput).getAbsoluteFile()));
        ) {

            JSONParser parser = new JSONParser();
            JSONObject parseIndizes = (JSONObject) parser.parse(brIndizes);
            JSONObject parseData = (JSONObject) parser.parse(brData);

            JSONArray indizes = (JSONArray) parseIndizes.get("indizes");
            JSONArray datas = (JSONArray) parseData.get("data");

            Iterator indizesIterator = indizes.iterator();
            Iterator dataIterator = datas.iterator();

            int i=0;

            while (indizesIterator.hasNext() && dataIterator.hasNext()) {
                JSONObject index = (JSONObject) indizesIterator.next();
                JSONObject data = (JSONObject) dataIterator.next();

                writer.write(index.toString());
                writer.newLine();
                writer.write(data.toString());
                writer.newLine();

            }
        }

    }
}